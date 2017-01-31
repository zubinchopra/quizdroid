package edu.washington.zubinc.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MathQuiz2 extends Activity {

    public final static String KEY = "edu.washington.zubinc.quizdroid";
    public RadioGroup options;
    public RadioButton o1;
    public RadioButton o2;
    public RadioButton o3;
    public RadioButton o4;
    public Button submit;
    public Button back;
    public String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_quiz2);

        Intent intent = getIntent();
        this.s = intent.getStringExtra(CheckAnswer.KEY).toString();

        this.options = (RadioGroup)findViewById(R.id.options);
        this.submit = (Button)findViewById(R.id.submit);
        this.back = (Button)findViewById(R.id.back);
        this.o1 = (RadioButton)findViewById(R.id.o1);
        this.o2 = (RadioButton)findViewById(R.id.o2);
        this.o3 = (RadioButton)findViewById(R.id.o3);
        this.o4 = (RadioButton)findViewById(R.id.o4);

        this.o1.setOnClickListener(new MyRadioListener(this.submit));
        this.o2.setOnClickListener(new MyRadioListener(this.submit));
        this.o3.setOnClickListener(new MyRadioListener(this.submit));
        this.o4.setOnClickListener(new MyRadioListener(this.submit));

        this.submit.setOnClickListener(new MyListener(this, options, this.o2, this.s));
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
            intent = new Intent(this.activity, MathQuiz.class);
            startActivity(intent);
        }
    }

    public class MyListener implements View.OnClickListener{

        public Activity activity;
        public RadioGroup options;
        public RadioButton correct;
        public String s;

        public MyListener(Activity activity, RadioGroup options, RadioButton correct, String s){
            this.activity = activity;
            this.options = options;
            this.correct = correct;
            this.s = s;
        }

        @Override
        public void onClick(View v) {
            Class c = MainActivity.class;
            int n = Integer.parseInt(this.s);
            Log.d("TAG", "" + n);
            int id = this.options.getCheckedRadioButtonId();
            if(id == this.correct.getId())
                n++;
            RadioButton button = (RadioButton)findViewById(id);
            String selected = button.getText().toString();
            Intent intent = new Intent(this.activity, CheckAnswer.class);
            String[] packet = new String[]{selected, "cos(x)", "Finish", "" + n};
            intent.putExtra(KEY, packet);
            intent.putExtra("CLASS", c);
            startActivity(intent);
        }
    }

}