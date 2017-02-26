package edu.washington.zubinc.quizdroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import layout.QuestionFragment;

public class Main2Activity extends Activity {

    TextView title;
    TextView longDes;
    Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Topic topic = (Topic) getIntent().getSerializableExtra("selectedTopic");
        this.title = (TextView)findViewById(R.id.title);
        this.title.setText(topic.getTitle());
        this.longDes = (TextView)findViewById(R.id.des);
        this.longDes.setText(topic.getLongDes());
        this.proceed = (Button)findViewById(R.id.proceed);

        this.proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putSerializable("TOPIC", topic);
                b.putInt("COUNTER", 1);
                b.putInt("CORRECT_COUNTER", 0);
                callFragment(b);
            }
        });
    }

    public void callFragment(Bundle b){

        Fragment fragment = new QuestionFragment();
        fragment.setArguments(b);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, fragment);
        ft.commit();
    }
}
