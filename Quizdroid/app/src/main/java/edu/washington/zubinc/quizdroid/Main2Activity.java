package edu.washington.zubinc.quizdroid;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import layout.QuestionFragment;

public class Main2Activity extends AppCompatActivity {

    Button begin;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.layout = (LinearLayout)findViewById(R.id.layout);
        this.begin = (Button)findViewById(R.id.begin);
        this.begin.setOnClickListener(new MyListener(this.layout));
    }

    public class MyListener implements View.OnClickListener{

        LinearLayout layout;

        public MyListener(LinearLayout layout){
            this.layout = layout;
        }

        @Override
        public void onClick(View v) {
            this.layout.setVisibility(View.GONE);
            QuestionFragment fragment = new QuestionFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.activity_main2, fragment);
            fragmentTransaction.commit();
        }
    }
}
