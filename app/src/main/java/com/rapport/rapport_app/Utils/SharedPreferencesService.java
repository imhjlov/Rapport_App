package com.rapport.rapport_app.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesService {
    private static final String SHARED_MAIN_NAME = "SharedMainName";
    public static final String SHARED_ALERT_HOUR = "shared_alert_hour";
    public static final String SHARED_ALERT_MINUTE = "shared_alert_minute";
    public static final String SHARED_VIBRATOR = "shared_vibrator";//진동 울림 (밀리초)
    public static final String SHARED_VIBRATOR_SLEEP = "shared_vibrator_sleep"; // 진동 쉼 (밀리초)
    public static final String SHARED_RINGTONE= "shared_ringthone"; // 알람음 저장
    public static final String SHARED_ALERT_VOLUME= "shared_alert_volume";

    //순서저장
    public static final String SHARED_ORDER_SET_OK="shared_order_set_ok";
    public static final String SHARED_ORDER_BATH="shared_order_bath";
    public static final String SHARED_ORDER_MASSAGE="shared_order_massage";
    public static final String SHARED_ORDER_LULLABY="shared_order_lullaby";
    public static final String SHARED_ORDER_BOOK="shared_order_book";


    private static SharedPreferencesService sharedPreferencesManager;
    private SharedPreferences pref;

    public static SharedPreferencesService getInstance(){
        if (sharedPreferencesManager == null) {
            synchronized (SharedPreferencesService.class) {
                if (sharedPreferencesManager == null)
                    sharedPreferencesManager = new SharedPreferencesService();
            }
        }
        return sharedPreferencesManager;
    }

    private void getPref(Context cont) {
        if (pref == null) {
            pref = cont.getSharedPreferences(SHARED_MAIN_NAME, Context.MODE_PRIVATE);
        }
    }

    public void load(Context context) {
        getPref(context);
    }

    public String getPrefStringData(String key){
        return pref.getString(key, null);
    }

    public int getPrefIntData(String key){
        return pref.getInt(key, 1);
    }

    public boolean getPrefBooleanData(String key){
        return pref.getBoolean(key, false);
    }

    public Long getPrefLongData(String key){
        return pref.getLong(key, 0);
    }

    public void setPrefData(String key, boolean value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setPrefStringData(String key, String value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public void setPrefLongData(String key, long value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void setPrefIntData(String key, int value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }
}
