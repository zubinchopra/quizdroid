package edu.washington.zubinc.quizdroid;

/**
 * Created by Macbook on 2/19/17.
 */

public class Question {

    private String question;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private int correct;

    public String getQuestion(){
        return this.question;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public String getA1(){
        return this.a1;
    }

    public void setA1(String a1){
        this.a1 = a1;
    }

    public String getA2(){
        return this.a2;
    }

    public void setA2(String a2){
        this.a2 = a2;
    }

    public String getA3(){
        return this.a3;
    }

    public void setA3(String a3){
        this.a3 = a3;
    }

    public String getA4(){
        return this.a4;
    }

    public void setA4(String a4){
        this.a4 = a4;
    }

    public int getCorrect(){
        return this.correct;
    }

    public void setCorrect(int correct){
        this.correct = correct;
    }

}
