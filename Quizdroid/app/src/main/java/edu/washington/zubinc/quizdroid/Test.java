package edu.washington.zubinc.quizdroid;

/**
 * Created by Macbook on 2/18/17.
 */
public class Test {
    private static Test ourInstance = new Test();

    public static Test getInstance() {
        return ourInstance;
    }

    private Test() {
    }
}
