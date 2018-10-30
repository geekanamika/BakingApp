package com.example.android.bakingapp.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.bakingapp.data.FoodRepository;
import com.example.android.bakingapp.data.db.entities.RecipeResponse;
import com.example.android.bakingapp.utils.InjectorUtil;

import java.util.List;

/**
 * Created by Anamika Tripathi on 30/10/18.
 */
public class MainActivityViewModel extends AndroidViewModel {

    private FoodRepository repository;
    private LiveData<List<RecipeResponse>> recipeLiveData;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = InjectorUtil.provideRepository(application.getApplicationContext());
        recipeLiveData = repository.getRecipeList();
    }

    public LiveData<List<RecipeResponse>> getRecipeLiveData() {
        return recipeLiveData;
    }
}
