package com.example.android.bakingapp.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.android.bakingapp.data.db.dao.RecipeDao;
import com.example.android.bakingapp.data.db.entities.Ingredient;
import com.example.android.bakingapp.data.db.entities.RecipeResponse;
import com.example.android.bakingapp.data.db.entities.Step;

/**
 * Created by Anamika Tripathi on 26/10/18.
 */
@Database(entities = {RecipeResponse.class, Ingredient.class, Step.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "RecipeDb";
    private static volatile AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, AppDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }
}