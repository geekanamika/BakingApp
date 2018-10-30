package com.example.android.bakingapp.data.prefs;

/**
 * Created by Anamika Tripathi on 27/10/18.
 */
public interface PrefHelper {
    boolean checkDbExistOrNot();

    void setDbExist(boolean dbExist);
}
