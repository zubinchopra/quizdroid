package edu.washington.zubinc.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by Macbook on 2/19/17.
 */

public class QuizApp extends Application {

    private static QuizApp instance = new QuizApp();
    private TopicRepository topicRepository = new TopicRepository();

    public void onCreate(){
        Log.d("TAG", "onCreate Fired");
    }

    public static QuizApp getInstance(){
        return instance;
    }

    public TopicRepository getRepository(){
        return this.topicRepository;
    }

}
