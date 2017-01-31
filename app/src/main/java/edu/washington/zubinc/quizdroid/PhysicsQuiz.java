package edu.washington.zubinc.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PhysicsQuiz extends Activity {

    public final static String KEY = "edu.washington.zubinc.quizdroid";
    public RadioGroup options;
    public RadioButton o1;
    public RadioButton o2;
    public RadioButton o3;
    public RadioButton o4;
    public Button submit;
    public Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics_quiz);

        this.options = (RadioGroup)findViewById(R.id.options);
        this.submit = (Button)findViewById(R.id.submit);
        this.o1 = (RadioButton)findViewById(R.id.o1);
        this.o2 = (RadioButton)findViewById(R.id.o2);
        this.o3 = (RadioButton)findViewById(R.id.o3);
        this.o4 = (RadioButton)findViewById(R.id.o4);

        this.o1.setOnClickListener(new MyRadioListener(this.submit));
        this.o2.setOnClickListener(new MyRadioListener(this.submit));
        this.o3.setOnClickListener(new MyRadioListener(this.submit));
        this.o4.setOnClickListener(new MyRadioListener(this.submit));

        this.submit.setOnClickListener(new MyListener(this, options, this.o1));
        this.back.setOnClickListener(new MyBackButton(this));

    }

    public class MyRadioListener implements View.OnClickListener{

        public Button submit;

        public MyRadioListener(Button submit){
            this.submit = submit;
        }

        @Override
        public void onClick(View v) {
            this.submit.setVisibility(View.VISIBLE);
        }
    }

    public class MyBackButton implements View.OnClickListener{

        public Activity activity;
        public Intent intent;

        public MyBackButton(Activity activity){
            this.activity = activity;
            this.intent = new Intent();
        }

        @Override
        public void onClick(View v) {
            intent = new Intent(this.activity, MainActivity.class);
            startActivity(intent);
        }
    }

    public class MyListener implements View.OnClickListener{

        public Activity activity;
        public RadioGroup options;
        public RadioButton correct;

        public MyListener(Activity activity, RadioGroup options, RadioButton correct){
            this.activity = activity;
            this.options = options;
            this.correct = correct;
        }

        @Override
        public void onClick(View v) {
            Class c = PhysicsQuiz2.class;
            int n = 0;
            int id = this.options.getCheckedRadioButtonId();
            if(id == this.correct.getId())
                n = 1;
            RadioButton button = (RadioButton)findViewById(id);
            String selected = button.getText().toString();
            Intent intent = new Intent(this.activity, CheckAnswer.class);
            String[] packet = new String[]{selected, "E = mc^2", "Next", "" + n};
            intent.putExtra(KEY, packet);
            intent.putExtra("CLASS", c);
            startActivity(intent);
        }
    }


}
