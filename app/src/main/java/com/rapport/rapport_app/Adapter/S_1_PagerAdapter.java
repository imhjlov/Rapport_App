package com.rapport.rapport_app.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rapport.rapport_app.Fragment.S_1_1Fragment;
import com.rapport.rapport_app.Fragment.S_1_2Fragment;

/**
 * Created by Hyunjung on 2017-08-21.
 */

public class S_1_PagerAdapter extends FragmentPagerAdapter {

    private int mNumOfTabs;

    public S_1_PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        mNumOfTabs=numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                S_1_1Fragment tab1=new S_1_1Fragment();
                return tab1;

            case 1:
                S_1_2Fragment tab2=new S_1_2Fragment();
                return tab2;


        }

        return null;
    }

    //몇 개의 탭을 만들지
    // @Override
    public int getCount() {
        return mNumOfTabs;
    }
}