package layout;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        Button submit = (Button)v.findViewById(R.id.submit);
        RadioGroup radioGroup = (RadioGroup)v.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new MyRadioListener(submit));
        submit.setOnClickListener(new MyListener());
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

        @Override
        public void onClick(View v) {

        }
    }

}
