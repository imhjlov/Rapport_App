package com.rapport.rapport_app.Utils;

/**
 * Created by Hyunjung on 2017-06-01.
 */

public class Timerecord {

    int min=0;
    int sec=0;
    int totalSec=0;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getTotalSec() {
        return totalSec;
    }

    public void setTotalSec(int totalSec) {
        this.totalSec = totalSec;
    }

    public void calMinSec(int totalSec) {
        setTotalSec(totalSec);
        if (totalSec >= 60) {
            setMin(totalSec/60);
            setSec(totalSec%60);
        } else
            setSec(totalSec%60);
    }


}
