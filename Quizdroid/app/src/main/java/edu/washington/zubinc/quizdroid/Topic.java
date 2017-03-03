package edu.washington.zubinc.quizdroid;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Macbook on 2/19/17.
 */

public class Topic implements Serializable {

    private String title;
    private String shortDes;
    private String longDes;
    private List<Question> questions;

    public Topic(){
        this.title = "";
        this.shortDes = "";
        this.longDes = "";
        this.questions = new ArrayList<Question>();
    }

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
