package edu.washington.zubinc.quizdroid;

import android.content.Context;
import android.os.AsyncTask;
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
    List<Topic> topicList = new ArrayList<Topic>();
    String url;
    MyAsync myAsync;
    JSONArray jsonArray;

    public TopicRepository(String url){
        Log.d("TAG", "At topic repository");
        this.url = url;
        myAsync = (MyAsync) new MyAsync(this.url).execute();
        Log.d("LIST", "" + topicList.size());
    }

    public List getTopicRepository(){
        return this.topicList;
    }

    private class MyAsync extends AsyncTask<String, String, JSONArray> {

        String url;
        JSONArray jsonArray;

        public MyAsync(String url){
            this.url = url;
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            Log.d("TAG", "inside doinbackground");
            HttpURLConnection urlConnection = null;
            URL url = null;
            try {
                url = new URL(this.url);
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                if(urlConnection.getResponseCode() == 200 || urlConnection.getResponseCode() == 201){
                    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    Log.d("TAG", sb.toString());
                    this.jsonArray = new JSONArray(sb.toString());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            try {
                addTopics();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getTopicRepository();
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

    }

}
