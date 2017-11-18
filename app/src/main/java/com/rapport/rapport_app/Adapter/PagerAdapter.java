package com.rapport.rapport_app.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rapport.rapport_app.Fragment.AFragment;
import com.rapport.rapport_app.Fragment.FFragment;
import com.rapport.rapport_app.Fragment.MFragment;
import com.rapport.rapport_app.Fragment.QFragment;

/**
 * Created by Hyunjung on 2017-08-21.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        mNumOfTabs=numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            //수면의식시작
            case 0:
                MFragment tab1=new MFragment();
                return tab1;

            //피드백선택
            case 1:
                FFragment tab2=new FFragment();
                return tab2;

            //음악선택
            case 2:
                AFragment tab3=new AFragment();
                return tab3;
            //faq선택
            case 3:
                QFragment tab4=new QFragment();
                return tab4;

        }

        return null;
    }

    //몇 개의 탭을 만들지
    // @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
