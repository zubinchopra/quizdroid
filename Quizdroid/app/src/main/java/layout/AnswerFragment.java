package layout;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.washington.zubinc.quizdroid.Main2Activity;
import edu.washington.zubinc.quizdroid.MainActivity;
import edu.washington.zubinc.quizdroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnswerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AnswerFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_answer, container, false);
        Button next = (Button)v.findViewById(R.id.next);
        Activity activity = this.getActivity();

        TextView noOfCorrect = (TextView)v.findViewById(R.id.noOfCorrect);
        int n = getArguments().getInt("CORRECT") + Integer.parseInt(noOfCorrect.getText().toString());
        noOfCorrect.setText("" + n);
        int count = getArguments().getInt("COUNT");
        Log.d("TAG", ""+count);

        if(count == 2)
            next.setText("Finish");
        next.setOnClickListener(new MyListener(n, count, activity));

        return v;
    }

    public class MyListener implements View.OnClickListener{

        int n;
        int count;
        Activity activity;

        public MyListener(int n, int count, Activity activity){
            this.n = n;
            this.count = count;
            this.activity = activity;
        }

        @Override
        public void onClick(View v) {
            Bundle b = new Bundle();
            b.putInt("CHECK", this.n);
            b.putInt("COUNT", this.count);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if(this.count < 2){
                QuestionFragment questionFragment = new QuestionFragment();
                questionFragment.setArguments(b);
                ft.replace(R.id.frame, questionFragment);
            }
            else{
                Intent intent = new Intent(this.activity, MainActivity.class);
                startActivity(intent);
            }
            ft.commit();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
