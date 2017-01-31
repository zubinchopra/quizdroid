package edu.washington.zubinc.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BeginMessage extends Activity {

    public TextView title;
    public Button begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        title = (TextView)findViewById(R.id.title);
        begin = (Button)findViewById(R.id.begin);
        Intent intent = getIntent();
        final String subject = intent.getStringExtra(MainActivity.KEY);
        title.setText(subject);
        begin.setOnClickListener(new MyListener(this, subject));
    }

    public class MyListener implements View.OnClickListener{

        public Activity beginMessage;
        public String subject;
        public Intent intent;

        public MyListener(Activity beginMessage, String subject){
            this.beginMessage = beginMessage;
            this.subject = subject;
            this.intent = new Intent();
        }

        @Override
        public void onClick(View v) {
            if(subject.equalsIgnoreCase("math"))
                intent = new Intent(this.beginMessage, MathQuiz.class);
            else if(subject.equalsIgnoreCase("physics"))
                intent = new Intent(this.beginMessage, PhysicsQuiz.class);
            else
                intent = new Intent(this.beginMessage, MarvelQuiz.class);
            startActivity(intent);
        }
    }
}
