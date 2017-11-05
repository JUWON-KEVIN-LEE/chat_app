package com.immymemine.kevin.chatapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by quf93 on 2017-11-03.
 */

public class PreferenceUtil {
    private static final String FILENAME = "ChatApp";
    private static PreferenceUtil preferenceUtil = null;
    private PreferenceUtil(Context context) {
        initiate(context);
    }
    public static PreferenceUtil getInstance(Context context) {
        if(preferenceUtil == null) {
            preferenceUtil = new PreferenceUtil(context);
        }
        return preferenceUtil;
    }
    private static SharedPreferences sPref = null;
    private static SharedPreferences.Editor editor = null;
    private void initiate(Context context) {
        if(sPref == null || editor == null) {
            sPref = context.getSharedPreferences("ChatApp", Context.MODE_PRIVATE);
            editor = sPref.edit();
        }
    }

    public void setValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void setValue(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public String getValue(String key) {
        return sPref.getString(key,"");
    }

    public Long getLongValue(String key) {
        return sPref.getLong(key, -1);
    }
}
