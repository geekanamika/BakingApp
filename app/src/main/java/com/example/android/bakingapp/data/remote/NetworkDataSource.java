package com.example.android.bakingapp.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.bakingapp.App;
import com.example.android.bakingapp.data.db.entities.RecipeResponse;
import com.example.android.bakingapp.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Anamika Tripathi on 26/10/18.
 */
public class NetworkDataSource {
    private MutableLiveData<List<RecipeResponse>> mutableRecipeData;

    public NetworkDataSource() {
        this.mutableRecipeData = new MutableLiveData<>();
    }

    private void deserializeToJson(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        mutableRecipeData.setValue(Arrays.asList(gson.fromJson(response, RecipeResponse[].class)));
    }

    private StringRequest getStringRequestForRecipe() {
        return new StringRequest(Request.Method.GET, Constant.RECIPE_URL,

        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                deserializeToJson(response);
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Timber.e(error);
            }
        }
);

    }

    public LiveData<List<RecipeResponse>> getRecipesFromNetwork() {
        MySingleton.getInstance(App.getInstance())
                .addToRequestQueue(getStringRequestForRecipe());
        return mutableRecipeData;
    }


}
