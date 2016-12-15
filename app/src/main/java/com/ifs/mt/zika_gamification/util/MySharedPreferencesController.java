package com.ifs.mt.zika_gamification.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Betto Silva on 13/12/2016.
 */
public class MySharedPreferencesController {

    public static final String M1 = "M1";
    public static final String M1_E1 = "M1E1";
    public static final String M1_E2 = "M1E2";
    public static final String M2 = "M2";

    private static MySharedPreferencesController myPreference;
    private SharedPreferences sharedPreferences;

    public static MySharedPreferencesController getInstance(Context context) {
        if (myPreference == null) {
            myPreference = new MySharedPreferencesController(context);
        }
        return myPreference;
    }


    private MySharedPreferencesController(Context context) {
        sharedPreferences = context.getSharedPreferences("EstadoModuloEtapa", Context.MODE_PRIVATE);
    }

    public void saveData(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public boolean getData(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(key, false);
        }
        return false;
    }

    public void clearData(){
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.clear();
        prefsEditor.commit();

    }

}

