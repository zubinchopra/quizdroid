package edu.washington.zubinc.quizdroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import layout.QuestionFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnswerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnswerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView noOfCorrect;
    TextView totalNumber;
    Button button;

    public AnswerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnswerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnswerFragment newInstance(String param1, String param2) {
        AnswerFragment fragment = new AnswerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_answer, container, false);

        Topic topic = (Topic)getArguments().getSerializable("TOPIC");
        int correctCounter = getArguments().getInt("CORRECT_COUNTER");
        int counter = getArguments().getInt("COUNTER");
        int numberOfQuestions = getArguments().getInt("NUMBER_OF_QUESTIONS");
        Activity activity = getActivity();

        this.noOfCorrect = (TextView)v.findViewById(R.id.noOfCorrect);
        this.noOfCorrect.setText("" + correctCounter);
        this.totalNumber = (TextView)v.findViewById(R.id.totalNumber);
        this.totalNumber.setText("" + numberOfQuestions);
        this.button = (Button)v.findViewById(R.id.next);

        if(isDone(counter, numberOfQuestions))
            this.button.setText("Finish");
        this.button.setOnClickListener(new MyListener(topic, correctCounter, counter, numberOfQuestions, activity, v));

        return v;
    }

    public class MyListener implements View.OnClickListener{

        Topic topic;
        int correctCounter;
        int counter;
        int numberOfQuestions;
        Activity activity;
        View v;

        public MyListener(Topic topic, int correctCounter, int counter, int numberOfQuestions, Activity activity, View v){
            this.topic = topic;
            this.correctCounter = correctCounter;
            this.counter = counter;
            this.numberOfQuestions = numberOfQuestions;
            this.activity = activity;
            this.v = v;
        }

        @Override
        public void onClick(View v) {
            if(isDone(counter, numberOfQuestions)){
                Intent intent = new Intent(this.activity, MainActivity.class);
                startActivity(intent);
            }
            else{
                Bundle b = new Bundle();
                b.putSerializable("TOPIC", topic);
                b.putInt("CORRECT_COUNTER", correctCounter);
                b.putInt("COUNTER", counter);
                QuestionFragment questionFragment = new QuestionFragment();
                questionFragment.setArguments(b);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.placeholder, questionFragment);
                ft.commit();
            }
        }
    }

    public boolean isDone(int counter, int numberOfQuestions){
        return counter == numberOfQuestions;
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
