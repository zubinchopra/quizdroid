package edu.washington.zubinc.quizdroid;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.telephony.CellSignalStrength;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Macbook on 2/19/17.
 */


public class TopicRepository {

    String url;
    List<Topic> topicList = new ArrayList<Topic>();
    Context context;
    String interval;

    public TopicRepository(String url, String interval, Context context){
        Log.d("TAG", "At topic repository");
        this.url = url;
        this.context = context;
        this.interval = interval;
        Log.d("TAG", this.interval);
        try {
            this.topicList = new MyAsync(this.url, this.topicList).execute().get();
            Log.d("TAG", "SUCCESS");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("LIST Hello", "" + topicList.size());
    }

    public List getTopicRepository(){
        return this.topicList;
    }

    private class MyAsync extends AsyncTask<String, String, List<Topic>> {

        String url;
        JSONArray jsonArray;
        List<Topic> topicList;
        File file;
        TelephonyManager telephonyManager;

        public MyAsync(String url, List<Topic> topicList){
            this.url = url;
            this.topicList = topicList;
            telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        }

        @Override
        protected void onPreExecute() {
            Log.d("PRE_EXEXCUTE", "in here");
            Toast.makeText(context, "Downloading file from URL " + url + " in " + interval + " minutes", Toast.LENGTH_LONG).show();
        }

        @Override
        protected List<Topic> doInBackground(String... params) {
            Log.d("TAG", "inside doInBackground");
            try {
                URL url = new URL(this.url);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                file = new File(context.getFilesDir(), "questions.json");
                FileOutputStream fos = context.openFileOutput("questions.json", context.MODE_PRIVATE);

                if(urlConnection.getResponseCode() == 200 || urlConnection.getResponseCode() == 201){
                    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    Log.d("SB", sb.toString());
                    fos.write(sb.toString().getBytes());
                    fos.close();
                    this.jsonArray = new JSONArray(sb.toString());
                } else if(isAirplaneModeOn(context)){
                    Log.d("TAG", "Airplane Mode On!");
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                addTopics();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this.topicList;
        }

        public void addTopics() throws JSONException {
            Log.d("TAG", "in add topics");
            for(int i = 0; i < jsonArray.length(); i++){
                Log.d("TAG", "topic");
                Topic topic = new Topic();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                topic.setTitle(jsonObject1.getString("title"));
                topic.setShortDes(jsonObject1.getString("desc"));
                JSONArray questions = jsonObject1.getJSONArray("questions");
                for(int j = 0; j < questions.length(); j++) {
                    Log.d("TAG", "Question");
                    Question question = new Question();
                    JSONObject jsonObject2 = questions.getJSONObject(j);
                    question.setQuestion(jsonObject2.getString("text"));
                    question.setCorrect(jsonObject2.getInt("answer"));
                    JSONArray options = jsonObject2.getJSONArray("answers");
                    for(int k = 0; k < options.length(); k++){
                        Log.d("TAG", "OPTION");
                        question.setOptions(options.getString(k));
                    }
                    Log.d("TAG", "Question added");
                    topic.setQuestions(question);

                }
                Log.d("TAG", "Topic added");
                topicList.add(topic);
            }
            Log.d("LIST", "" + topicList.size());
        }

        @Override
        protected void onPostExecute(List<Topic> topics) {
            Log.d("POST_EXECUTE", "in here");
        }

        private boolean isAirplaneModeOn(Context context) {

            return Settings.System.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

        }
    }

}
