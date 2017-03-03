package edu.washington.zubinc.quizdroid;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Macbook on 2/19/17.
 */
public class TopicRepository {
    private List<Topic> topicList = new ArrayList<Topic>();
    StringBuilder text;
    String jsonString;

    public TopicRepository(){
        Log.d("TAG", "At topic repository");
        this.jsonString = jsonText();
        Log.d("JSON", "SUCCESSFUL");
        try {
            JSONArray jsonArray = new JSONArray(jsonText());
            Log.d("TAG", "Array Made!");
            addTopics(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String jsonText(){

        text = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File("data/questions.json")));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            // do exception handling
        } finally {
            try { br.close(); } catch (Exception e) { }
        }
        return text.toString();
    }

    public void addTopics(JSONArray jsonArray) throws JSONException {
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
            this.topicList.add(topic);
        }
    }

    public List getTopicRepository(){
        return this.topicList;
    }
}
