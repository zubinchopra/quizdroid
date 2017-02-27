package edu.washington.zubinc.quizdroid;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Macbook on 2/19/17.
 */
public class TopicRepository {
    private List<Topic> topicList = new ArrayList<Topic>();

    public void addTopic(Topic topic){
        topicList.add(topic);
    }

    public List getTopic(){
        return this.topicList;
    }
}
