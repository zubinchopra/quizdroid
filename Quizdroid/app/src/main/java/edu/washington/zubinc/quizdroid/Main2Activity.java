package edu.washington.zubinc.quizdroid;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import layout.QuestionFragment;
import layout.TopicOverview;

public class Main2Activity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TopicOverview topicOverview = new TopicOverview();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame, topicOverview);
        ft.commit();

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
