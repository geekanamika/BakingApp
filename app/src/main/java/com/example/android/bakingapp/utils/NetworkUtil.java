package com.example.android.bakingapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.android.bakingapp.App;

import java.util.Objects;

/**
 * Created by Anamika Tripathi on 12/10/18.
 */
public class NetworkUtil {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
