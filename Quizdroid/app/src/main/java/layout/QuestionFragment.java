package layout;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.washington.zubinc.quizdroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {


    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);

        int n = getArguments().getInt("CHECK");
        int count = getArguments().getInt("COUNT");

        Button submit = (Button)v.findViewById(R.id.submit);
        RadioGroup radioGroup = (RadioGroup)v.findViewById(R.id.radioGroup);
        RadioButton correct = (RadioButton)v.findViewById(R.id.correct);

        radioGroup.setOnCheckedChangeListener(new MyRadioListener(submit));
        submit.setOnClickListener(new MyListener(correct, n, count + 1));
                // Inflate the layout for this fragment
        return v;
    }

    public class MyRadioListener implements RadioGroup.OnCheckedChangeListener{

        Button submit;

        public MyRadioListener(Button submit){
            this.submit = submit;
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            this.submit.setVisibility(View.VISIBLE);
        }
    }

    public class MyListener implements View.OnClickListener{

        RadioButton correct;
        int n;
        int count;

        public MyListener(RadioButton correct, int n, int count){
            this.correct = correct;
            this.n = n;
            this.count = count;
        }

        @Override
        public void onClick(View v) {
            if(this.correct.isChecked())
                this.n++;
            AnswerFragment answerFragment = new AnswerFragment();
            Bundle b = new Bundle();
            b.putInt("CORRECT", n);
            b.putInt("COUNT", count);
            answerFragment.setArguments(b);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame, answerFragment);
            ft.commit();

        }
    }

}
