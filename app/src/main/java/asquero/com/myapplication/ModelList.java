package asquero.com.myapplication;

/**
 * Created by Anmol on 23-Mar-18.
 */

public class ModelList {
    private String Topic;
    private int startDate;
    private int endDate;
    private int startTime;
    private int endTime;

    public ModelList(String topic, int startDate, int endDate, int startTime, int endTime) {
        Topic = topic;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getTopic() {

        return Topic;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }
}
