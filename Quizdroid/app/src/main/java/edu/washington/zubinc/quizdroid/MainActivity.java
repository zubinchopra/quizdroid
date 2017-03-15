package edu.washington.zubinc.quizdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isAirplaneModeOn(this)){
            Toast.makeText(this, "AIRPLANE MODE ON! Redirecting to settings", Toast.LENGTH_LONG).show();
            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
        }

        else {

            setContentView(R.layout.activity_main);

            this.list = (ListView) findViewById(R.id.list);
            QuizApp app = QuizApp.getInstance();
            Log.d("TAG", "We're here!");

            TopicRepository topicRepository = app.getRepository();
            Log.d("BLAH", "BLAH");
            List<Topic> listOfTopics = new ArrayList<Topic>(topicRepository.getTopicRepository());
            Log.d("TAG", "" + listOfTopics.size());

            String[] title = new String[listOfTopics.size()];
            String[] shortDes = new String[listOfTopics.size()];
            for (int i = 0; i < listOfTopics.size(); i++) {
                title[i] = listOfTopics.get(i).getTitle();
                shortDes[i] = listOfTopics.get(i).getShortDes();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, title);
            this.list.setAdapter(adapter);
            this.list.setOnItemClickListener(new MyListener(listOfTopics));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isAirplaneModeOn(this)){
            Toast.makeText(this, "AIRPLANE MODE ON! Redirecting to settings", Toast.LENGTH_LONG).show();
            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
        }

        else {

            setContentView(R.layout.activity_main);

            this.list = (ListView) findViewById(R.id.list);
            QuizApp app = QuizApp.getInstance();
            Log.d("TAG", "We're here!");

            TopicRepository topicRepository = app.getRepository();
            Log.d("BLAH", "BLAH");
            List<Topic> listOfTopics = new ArrayList<Topic>(topicRepository.getTopicRepository());
            Log.d("TAG", "" + listOfTopics.size());

            String[] title = new String[listOfTopics.size()];
            String[] shortDes = new String[listOfTopics.size()];
            for (int i = 0; i < listOfTopics.size(); i++) {
                title[i] = listOfTopics.get(i).getTitle();
                shortDes[i] = listOfTopics.get(i).getShortDes();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, title);
            this.list.setAdapter(adapter);
            this.list.setOnItemClickListener(new MyListener(listOfTopics));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.myprefs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        startActivity(new Intent(this, MyPrefs.class));

        return super.onOptionsItemSelected(item);
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
                if(t.getTitle().equalsIgnoreCase(selected)) {
                    selectedTopic = t;
                }
            }
            Bundle b = new Bundle();
            b.putSerializable("TOPIC", selectedTopic);
            intent.putExtras(b);
            startActivity(intent);
        }
    }

    private boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

    }

    private class MyAsync extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... params) {
            return null;
        }
    }
}
