package com.example.android.bakingapp.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.db.entities.RecipeResponse;
import com.example.android.bakingapp.data.db.entities.Step;
import com.example.android.bakingapp.ui.detail.list.DetailListFragment;
import com.example.android.bakingapp.ui.detail.step.DetailStepFragment;
import com.example.android.bakingapp.ui.detail.step.RecipeStepActivity;
import com.example.android.bakingapp.utils.Constant;

import timber.log.Timber;

public class RecipeDetailActivity extends AppCompatActivity implements DetailListFragment.OnDetailListListener {

    private RecipeResponse recipeResponse;
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        isTwoPane = getResources().getBoolean(R.bool.isTablet);

        // getting value from recipe list
        extractDataFromBundle();
    }

    private void setUpUIForDifferentScreenSize() {
        if (isTwoPane) {
            Timber.d("tablet screen");
            setStepListFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().add(R.id.recipe_step_container,
                    DetailStepFragment.newInstance(recipeResponse.getSteps().get(0))).commit();

        } else {
            setStepListFragment();
        }


    }

    // start stepListFragment in activity for phone screen
    private void setStepListFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.detail_list_container,
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
                recipeResponse = data.getParcelable(Constant.EXTRA_KEY);
                if (recipeResponse != null) {
                    setUpUIForDifferentScreenSize();
                }

            }
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onFragmentInteraction(Step step) {
        if (isTwoPane) {
            Timber.d(step.getShortDescription());
            // instantly update step fragment with step, create new fragment and set correct values
            // replace existing fragment
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.recipe_step_container,
                    DetailStepFragment.newInstance(step)).commit();
        } else {
            Intent intent = new Intent(RecipeDetailActivity.this, RecipeStepActivity.class);
            intent.putExtra(Constant.EXTRA_KEY, step);
            startActivity(intent);
        }
    }
}
