package asquero.com.myapplication;

/**
 * Created by Anmol on 10-Apr-18.
 */

class LiveList {
    private String contestCode;
    private String contestName;
    private String startDate;
    private String endDate;
    //private int image;
    private String AIC;

    public LiveList(String contestCode, String contestName, String startDate, String endDate, String AIC) {
        this.contestCode = contestCode;
        this.contestName = contestName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.AIC = AIC;
    }

    public String getContestCode() {
        return contestCode;
    }

    public String getContestName() {
        return contestName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getAIC() {
        return AIC;
    }
}
