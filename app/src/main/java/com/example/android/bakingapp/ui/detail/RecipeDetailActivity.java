package com.example.android.bakingapp.ui.detail;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.db.entities.RecipeResponse;
import com.example.android.bakingapp.ui.detail.list.DetailListFragment;
import com.example.android.bakingapp.utils.Constant;

import timber.log.Timber;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        // getting value from recipe list
        extractDataFromBundle();


    }

    private void setStepListFragment(RecipeResponse recipeResponse) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.detail_list_contaner,
                DetailListFragment.newInstance(recipeResponse), "list fragment")
                .commit();

    }


    private void extractDataFromBundle() {
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        if (intent != null) {
            Bundle data = intent.getExtras();
            if (data != null) {
                // set values on text and images
                RecipeResponse recipeResponse = data.getParcelable(Constant.EXTRA_KEY);
                if (recipeResponse != null) {
                    Timber.d(recipeResponse.getName());
                    setStepListFragment(recipeResponse);
                }

            }
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }


}
