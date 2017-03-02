package edu.washington.zubinc.quizdroid;

import android.app.Application;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Macbook on 2/19/17.
 */

public class QuizApp extends Application {

    private static QuizApp instance = new QuizApp();
    private TopicRepository topicRepository = new TopicRepository();
    private FileInputStream fileInputStream;

    public void onCreate(){
        Log.d("TAG", "onCreate Fired");
        try {
            fileInputStream = openFileInput("questions.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("HERE", "now here");
    }

    public static QuizApp getInstance(){
        return instance;
    }

    public TopicRepository getRepository(){
        return this.topicRepository;
    }

}
