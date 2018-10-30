package com.example.android.bakingapp.utils;

import android.content.Context;

import com.example.android.bakingapp.data.FoodRepository;
import com.example.android.bakingapp.data.db.AppDatabase;
import com.example.android.bakingapp.data.db.LocalDataSource;
import com.example.android.bakingapp.data.db.LocalDbHelper;
import com.example.android.bakingapp.data.prefs.AppPrefHelper;
import com.example.android.bakingapp.data.remote.NetworkDataSource;

import timber.log.Timber;

/**
 * Created by Anamika Tripathi on 4/10/18.
 */
public class InjectorUtil {
    public static FoodRepository provideRepository(Context context) {
        // local db
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        LocalDbHelper dbHelper = LocalDataSource.getInstance(database.recipeDao(), executors);
        // remote
        NetworkDataSource networkDataSource =
                new NetworkDataSource();
        // pref
        AppPrefHelper preferenceHelper = new AppPrefHelper(context, "movie-pref");
        Timber.d("providing repository");

        return FoodRepository.getInstance(networkDataSource, preferenceHelper, dbHelper);
    }

}
