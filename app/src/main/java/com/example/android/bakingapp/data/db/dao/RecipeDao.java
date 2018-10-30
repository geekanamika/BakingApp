package com.example.android.bakingapp.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.example.android.bakingapp.data.db.entities.RecipeWithIngredientStep;

import java.util.List;

/**
 * Created by Anamika Tripathi on 26/10/18.
 */
@Dao
public interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM recipe")
    LiveData<List<RecipeWithIngredientStep>> getRecipeList();

//    @Query("SELECT COUNT(id) FROM recipe WHERE id = :recipeId")
//    LiveData<Integer> isFavourite(int recipeId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipeList(List<RecipeWithIngredientStep> responses);

//    @Query("DELETE FROM recipe WHERE id = :deleteId")
//    void deleteRecipe(int deleteId);

//    @Query("SELECT * FROM recipe WHERE id= :rowId")
//    LiveData<RecipeResponse> getRecipeDetails(int rowId);

}
