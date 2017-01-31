package edu.washington.zubinc.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    public final static String KEY = "edu.washington.zubinc.quizdroid";

    public Button math;
    public Button physics;
    public Button marvel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        math = (Button)findViewById(R.id.math);
        physics = (Button)findViewById(R.id.physics);
        marvel = (Button)findViewById(R.id.marvel);

        math.setOnClickListener(new MyListener(this, "Math"));
        physics.setOnClickListener(new MyListener(this, "Physics"));
        marvel.setOnClickListener(new MyListener(this, "Marvel Super Heroes"));

    }

    public class MyListener implements View.OnClickListener{

        public Activity myActivity;
        public String subject;

        public MyListener(Activity myActivity, String subject){
            this.myActivity = myActivity;
            this.subject = subject;
        }

        @Override
        public void onClick(View v) {
            Intent beginMessage = new Intent(this.myActivity, BeginMessage.class);
            beginMessage.putExtra(KEY, this.subject);
            startActivity(beginMessage);
        }
    }
}
