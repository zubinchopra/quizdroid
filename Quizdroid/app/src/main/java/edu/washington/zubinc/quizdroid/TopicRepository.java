package edu.washington.zubinc.quizdroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Macbook on 2/19/17.
 */
public class TopicRepository {
    private static TopicRepository ourInstance = new TopicRepository();
    private List<Topic> topicList = new ArrayList<Topic>();

    public static TopicRepository getInstance() {
        return ourInstance;
    }
}
