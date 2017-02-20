package edu.washington.zubinc.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by Macbook on 2/19/17.
 */

public class QuizApp extends Application {

    private TopicRepository instance = new TopicRepository();

    public void onCreate(){
        Log.d("TAG", "onCreate Fired");
    }

    public TopicRepository getRepository(){
        return this.instance;
    }
}
