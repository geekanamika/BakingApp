package com.example.android.bakingapp.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Anamika Tripathi on 27/10/18.
 */
public class AppPrefHelper implements PrefHelper {

    private static final String CHECK_DB_EXIST_OR_NOT = "db-values";

    private final SharedPreferences mPrefs;

    public AppPrefHelper(Context context, String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public boolean checkDbExistOrNot() {
        return mPrefs.getBoolean(CHECK_DB_EXIST_OR_NOT, false);
    }

    @Override
    public void setDbExist(boolean dbExist) {
        mPrefs.edit().putBoolean(CHECK_DB_EXIST_OR_NOT, dbExist).apply();
    }
}
