package com.example.android.bakingapp.data.remote;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.bakingapp.App;
import com.example.android.bakingapp.data.entities.RecipeResponse;
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
    private StringRequest request;
    private Gson gson;
    private GsonBuilder gsonBuilder;

    private void deserializeToJson(String response){
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        List<RecipeResponse> responses = Arrays.asList(gson.fromJson(response, RecipeResponse[].class));
        for (RecipeResponse r: responses
             ) {
            Timber.d(r.getName()+ "\n");
        }
    }

    public void getStringRequestForRecipe(){
        request = new StringRequest(Request.Method.GET, Constant.RECIPE_URL,

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

    public void addToRequestQueue(){
        MySingleton.getInstance(App.getInstance())
                .addToRequestQueue(request);
    }


}
