package com.example.android.bakingapp.data.db.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by Anamika Tripathi on 30/10/18.
 */
public class RecipeWithIngredientStep {
    @Embedded
    public RecipeResponse response;

    @Relation(parentColumn = "id",
            entityColumn = "responseId")
    public List<Ingredient> ingredientList;

    @Relation(parentColumn = "id",
            entityColumn = "responseId")
    public List<Step> stepList;
}
