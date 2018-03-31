package asquero.com.myapplication;

/**
 * Created by Anmol on 23-Mar-18.
 */

public class ModelList {
    private int contestCode;
    private String contestName;
    private int startDate;
    private int endDate;

    public ModelList(int contestCode, String contestName, int startDate, int endDate) {
        this.contestCode = contestCode;
        this.contestName = contestName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getContestCode() {
        return contestCode;
    }

    public String getContestName() {
        return contestName;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }
}
