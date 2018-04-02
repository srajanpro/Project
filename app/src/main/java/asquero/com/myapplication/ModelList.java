package asquero.com.myapplication;

import android.graphics.Bitmap;

/**
 * Created by Anmol on 23-Mar-18.
 */

public class ModelList {
    private String contestCode;
    private String contestName;
    private String startDate;
    private String endDate;
    //private int image;
    private String AIC;

    public ModelList(String contestCode, String contestName, String startDate, String endDate, int image, String AIC) {
        this.contestCode = contestCode;
        this.contestName = contestName;
        this.startDate = startDate;
        this.endDate = endDate;
        //this.image = image;
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

   /* public int getImage() {
        return image;
    }*/

    public String getAIC() {
        return AIC;
    }
}
