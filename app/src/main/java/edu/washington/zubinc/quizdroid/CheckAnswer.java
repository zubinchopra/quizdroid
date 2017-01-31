package edu.washington.zubinc.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheckAnswer extends Activity {

    public final static String KEY = "edu.washington.zubinc.quizdroid";
    TextView correctAnswer;
    TextView userAnswer;
    TextView noOfCorrect;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_answer);

        correctAnswer = (TextView)findViewById(R.id.correctAnswer);
        userAnswer = (TextView)findViewById(R.id.userAnswer);
        noOfCorrect = (TextView)findViewById(R.id.numberOfCorrect);
        next = (Button)findViewById(R.id.next);
        Intent intent = getIntent();
        String[] packet = intent.getStringArrayExtra(MathQuiz.KEY);
        userAnswer.setText(packet[0]);
        correctAnswer.setText(packet[1]);
        next.setText(packet[2]);
        String s = (packet[3]);
        Log.d("TAG", s);
        noOfCorrect.setText(s);
        Bundle b = intent.getExtras();
        Class c = (Class<Activity>)b.getSerializable("CLASS");
        this.next.setOnClickListener(new MyListener(this, s, c));

    }

    public class MyListener implements View.OnClickListener{

        Activity activity;
        Intent intent;
        String s;
        Class c;

        public MyListener(Activity activity, String s, Class c){
            this.activity = activity;
            this.intent = new Intent();
            this.s = s;
            this.c = c;
        }

        @Override
        public void onClick(View v) {
            intent = new Intent(this.activity, this.c);
            intent.putExtra(KEY, this.s);
            startActivity(intent);
        }
    }
}
