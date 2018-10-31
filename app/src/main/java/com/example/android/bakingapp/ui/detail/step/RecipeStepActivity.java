package com.example.android.bakingapp.ui.detail.step;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.db.entities.RecipeResponse;
import com.example.android.bakingapp.data.db.entities.Step;
import com.example.android.bakingapp.utils.Constant;

import timber.log.Timber;

public class RecipeStepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        extractDataFromBundle();
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
                Step step = data.getParcelable(Constant.EXTRA_KEY);
                if (step != null) {
                    Timber.d(step.getShortDescription());
                    initFragment(step);
                }

            }
        }
    }

    private void initFragment(Step step) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.recipe_step_container,
                DetailStepFragment.newInstance(step)).commit();
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
}
