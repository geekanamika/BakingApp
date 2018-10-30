package com.example.android.bakingapp.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.FoodRepository;
import com.example.android.bakingapp.data.db.entities.RecipeResponse;
import com.example.android.bakingapp.utils.Constant;
import com.example.android.bakingapp.utils.InjectorUtil;

import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private RecipeListAdapter adapter;
    private boolean tabletSize;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabletSize = getResources().getBoolean(R.bool.isTablet);

        initRecyclerView();

        viewModelSetUp();
    }

    private void initRecyclerView() {
        RecyclerView recipeListView = findViewById(R.id.list_recipe);
        adapter = new RecipeListAdapter(this, new RecipeListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(RecipeResponse recipeResponse) {
                Timber.d("item clicked " + recipeResponse.getName());
            }
        });
        recipeListView.setAdapter(adapter);
        int recyclerViewSpanCount = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT ? 3 : 5;

        if (tabletSize) {
            GridLayoutManager manager = new GridLayoutManager(this, recyclerViewSpanCount,
                    GridLayoutManager.VERTICAL, false);
            recipeListView.setLayoutManager(manager);
            recipeListView.addItemDecoration(new SpacesItemDecoration(4));
        } else {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recipeListView.setLayoutManager(manager);
        }
    }

    private void viewModelSetUp() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        viewModel.getRecipeLiveData().observe(this, new Observer<List<RecipeResponse>>() {
            @Override
            public void onChanged(@Nullable List<RecipeResponse> recipeResponses) {
                if (recipeResponses != null) {
                    adapter.setList(recipeResponses);
                    Timber.d(recipeResponses.toString());
                }
            }
        });
    }

}