package com.rapport.rapport_app.Utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hyunjung on 2017-11-17.
 */

public class DateCount {


    private int count = 0;
    private Date startDay;

    public void setDateCount(Date today){
        //기준일
        startDay=new Date();
        Calendar cal = Calendar.getInstance ( );
        cal.setTime(startDay);

        //오늘
        Calendar cal2 = Calendar.getInstance ( );
        cal2.setTime(today);


        while ( !cal2.before ( cal ) ) {
            //cal2가 cal보다 이전이 아니면 count++
            count++;
            //다음날로 바뀜
            cal.add ( Calendar.DATE, 1 );
        }
        setCount(count);

    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



}
