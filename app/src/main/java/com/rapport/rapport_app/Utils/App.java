package com.rapport.rapport_app.Utils;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by Hyunjung on 2017-06-16.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addCustom1(Typekit.createFromAsset(this, "NotoSansKR-Regular-Hestia.otf"))
                .addCustom2(Typekit.createFromAsset(this,"NotoSansKR-Medium-Hestia.otf"));
    }

}
