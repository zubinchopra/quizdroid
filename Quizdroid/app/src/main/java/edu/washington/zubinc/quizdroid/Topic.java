package edu.washington.zubinc.quizdroid;

import java.util.List;

/**
 * Created by Macbook on 2/19/17.
 */

public class Topic {

    private String title;
    private String shortDes;
    private String longDes;
    private List<Question> questions;

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getShortDes(){
        return this.shortDes;
    }

    public void setShortDes(String shortDes){
        this.shortDes = shortDes;
    }

    public String getLongDes(){
        return this.longDes;
    }

    public void setLongDes(String longDes){
        this.longDes = longDes;
    }

    public List getQuestions(){
        return this.questions;
    }

    public void setQuestions(Question question){
        this.questions.add(question);
    }

}
