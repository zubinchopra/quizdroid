package edu.washington.zubinc.quizdroid;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Macbook on 2/19/17.
 */

public class Question implements Serializable{

    private String question;
    private String[] options;
    private int correct;

    public String getQuestion(){
        return this.question;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public String[] getOptions(){
        return this.options;
    }

    public void setOptions(String option){
        options[options.length] = option;
    }

    public int getCorrect(){
        return this.correct;
    }

    public void setCorrect(int correct){
        this.correct = correct;
    }

}
