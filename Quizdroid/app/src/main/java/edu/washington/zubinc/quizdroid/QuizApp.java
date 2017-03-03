package edu.washington.zubinc.quizdroid;

import android.app.Application;
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

    public void onCreate(){
        Log.d("TAG", "onCreate Fired");
    }

    public static QuizApp getInstance(){
        if(instance == null)
            instance = new QuizApp();
        instance.topicRepository = new TopicRepository();
        return instance;
    }

    public TopicRepository getRepository(){
        return instance.topicRepository;
    }

}
