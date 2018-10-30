package com.example.android.bakingapp.data.db;

import android.arch.lifecycle.LiveData;

import com.example.android.bakingapp.data.db.entities.RecipeResponse;
import com.example.android.bakingapp.data.db.entities.RecipeWithIngredientStep;

import java.util.List;

/**
 * Created by Anamika Tripathi on 26/10/18.
 */
public interface LocalDbHelper {

    void insertRecipeList(List<RecipeWithIngredientStep> recipeResponses);
    LiveData<List<RecipeWithIngredientStep>> getRecipeList();
    //LiveData<RecipeResponse> getRecipeById(int id);
}
