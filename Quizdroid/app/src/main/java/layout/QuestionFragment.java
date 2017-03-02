package layout;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import edu.washington.zubinc.quizdroid.AnswerFragment;
import edu.washington.zubinc.quizdroid.Question;
import edu.washington.zubinc.quizdroid.R;
import edu.washington.zubinc.quizdroid.Topic;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class QuestionFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    TextView questionText;
    RadioGroup radioGroup;
    RadioButton o1;
    RadioButton o2;
    RadioButton o3;
    RadioButton o4;
    Button submit;

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_question, container, false);

        Topic topic = (Topic) getArguments().getSerializable("TOPIC");
        int numberOfQuestions = topic.getQuestions().size();
        int counter = getArguments().getInt("COUNTER");
        int correctCounter = getArguments().getInt("CORRECT_COUNTER");
        Question question = (Question) topic.getQuestions().get(counter - 1);
        int check = question.getCorrect();
        String correctAnswer = question.getOptions()[check];

        this.questionText.setText(question.getQuestion());
        this.radioGroup = (RadioGroup)v.findViewById(R.id.radioGroup);
        this.o1 = (RadioButton)v.findViewById(R.id.o1);
        this.o1.setText(question.getOptions()[0]);
        this.o2 = (RadioButton)v.findViewById(R.id.o2);
        this.o2.setText(question.getOptions()[1]);
        this.o3 = (RadioButton)v.findViewById(R.id.o3);
        this.o3.setText(question.getOptions()[2]);
        this.o4 = (RadioButton)v.findViewById(R.id.o4);
        this.o4.setText(question.getOptions()[3]);

        this.submit = (Button)v.findViewById(R.id.submit);
        this.submit.setOnClickListener(new MyListener(topic, v, correctAnswer, correctCounter, counter, numberOfQuestions));

        return v;
    }

    public class MyListener implements View.OnClickListener{

        Topic topic;
        int counter;
        int correctCounter;
        View v;
        String correctAnswer;
        int numberOfQuestions;

        public MyListener(Topic topic, View v, String correctAnswer, int correctCounter, int counter, int numberOfQuestions){
            this.topic = topic;
            this.v = v;
            this.correctAnswer = correctAnswer;
            this.correctCounter = correctCounter;
            this.counter = counter;
            this.numberOfQuestions = numberOfQuestions;
        }

        @Override
        public void onClick(View v) {
            Bundle b = new Bundle();
            if(checkCorrect(v, correctAnswer))
                correctCounter += 1;
            counter += 1;
            b.putSerializable("TOPIC", topic);
            b.putInt("CORRECT_COUNTER", correctCounter);
            b.putInt("COUNTER", counter);
            b.putInt("NUMBER_OF_QUESTIONS", numberOfQuestions);
            AnswerFragment answerFragment = new AnswerFragment();
            answerFragment.setArguments(b);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.placeholder, answerFragment);
            ft.addToBackStack("QuestionFragment");
            ft.commit();
        }
    }

    // Check whether the user answered the question correctly
    public boolean checkCorrect(View v, String correctAnswer){
        int checkId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)v.findViewById(checkId);
        String answer = radioButton.getText().toString();
        return answer.equalsIgnoreCase(correctAnswer);
    }

    // Makes the submit button visible
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