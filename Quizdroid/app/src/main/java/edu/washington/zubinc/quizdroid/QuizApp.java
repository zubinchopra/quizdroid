package edu.washington.zubinc.quizdroid;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Macbook on 2/19/17.
 */

public class QuizApp extends Application {

    private static QuizApp instance = new QuizApp();
    private TopicRepository topicRepository;
    private String url;
    private String interval;
    private Context context;

    public void onCreate(){
        super.onCreate();
        Log.d("TAG", "onCreate Fired");

        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        instance.url = getPrefs.getString("url_text", "http://tednewardsandbox.site44.com/questions.json");
        instance.interval = getPrefs.getString("interval_text", "");
        instance.context = getApplicationContext();
    }

    public static QuizApp getInstance(){
        if(instance == null)
            instance = new QuizApp();
        instance.topicRepository = new TopicRepository(instance.url, instance.interval, instance.context);
        return instance;
    }

    public TopicRepository getRepository(){
        return instance.topicRepository;
    }

}
