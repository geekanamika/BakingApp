package com.example.android.bakingapp.data;

import android.arch.lifecycle.LiveData;

import com.example.android.bakingapp.data.db.LocalDbHelper;
import com.example.android.bakingapp.data.db.entities.RecipeResponse;
import com.example.android.bakingapp.data.prefs.PrefHelper;

import java.util.List;

/**
 * Created by Anamika Tripathi on 27/10/18.
 */
public interface RepositoryHelper extends PrefHelper, LocalDbHelper {

    LiveData<List<RecipeResponse>> getRecipeList();
}
