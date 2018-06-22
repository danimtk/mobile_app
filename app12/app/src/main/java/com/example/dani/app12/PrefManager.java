package com.example.dani.app12;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dani on 06/06/18.
 */
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "androidhive-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private static final String IS_FIRST_TRAINING = "IsFirstTraining";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setFirstTraining(boolean isFirstTraining) {
        editor.putBoolean(IS_FIRST_TRAINING, isFirstTraining);
        editor.commit();
    }

    public boolean isFirstTrainingLaunch() {
        return pref.getBoolean(IS_FIRST_TRAINING, true);
    }

}
