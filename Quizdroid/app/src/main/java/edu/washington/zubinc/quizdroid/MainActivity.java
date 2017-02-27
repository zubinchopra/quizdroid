package edu.washington.zubinc.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class MainActivity extends Activity {

    ListView list;
    FileInputStream fileInputStream = null;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.list = (ListView)findViewById(R.id.list);
        QuizApp app = QuizApp.getInstance();
        List<Topic> listOfTopics = app.getRepository().getTopic();
        String[] title = new String[listOfTopics.size()];
        String[] shortDes = new String[listOfTopics.size()];
        for(int i = 0; i < listOfTopics.size(); i++) {
            title[i] = listOfTopics.get(i).getTitle();
            shortDes[i] = listOfTopics.get(i).getShortDes();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, title);
        this.list.setAdapter(adapter);
        this.list.setOnItemClickListener(new MyListener(listOfTopics));
    }

    public class MyListener implements ListView.OnItemClickListener{

        List<Topic> listOfTopics;

        public MyListener(List<Topic> listOfTopics){
            this.listOfTopics = listOfTopics;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Topic selectedTopic = null;
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            String selected = (String)(list.getItemAtPosition(position));
            for(Topic t : listOfTopics){
                if(t.getTitle().equalsIgnoreCase(selected))
                    selectedTopic = t;
            }
            Bundle b = new Bundle();
            b.putSerializable("selectedTopic", selectedTopic);
            startActivity(intent);
        }
    }
}
