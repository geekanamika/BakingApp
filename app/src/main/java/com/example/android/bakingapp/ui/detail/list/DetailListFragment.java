package com.example.android.bakingapp.ui.detail.list;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.db.entities.Ingredient;
import com.example.android.bakingapp.data.db.entities.RecipeResponse;
import com.example.android.bakingapp.data.db.entities.Step;
import com.example.android.bakingapp.utils.Constant;
import com.example.android.bakingapp.utils.StringUtils;
import com.example.android.bakingapp.utils.TextViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class DetailListFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecipeResponse recipeResponse;
    @BindView(R.id.rv_recipe_steps)
    RecyclerView stepListView;
    @BindView(R.id.recipe_details_ingredients)
    TextView ingredientsView;
    private Context context;
    private RecipeStepAdapter adapter;

    //private OnFragmentInteractionListener mListener;

    public DetailListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1
     * @return A new instance of fragment DetailListFragment.
     */
    public static DetailListFragment newInstance(RecipeResponse param1) {
        DetailListFragment fragment = new DetailListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.EXTRA_KEY, param1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recipeResponse = getArguments().getParcelable(Constant.EXTRA_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        context = getContext();
        // set ingredients
        createAndSetIngredientList(recipeResponse.getIngredients());
        // set recycler view for steps
        initRecyclerView();

    }

    private void initRecyclerView() {
        adapter = new RecipeStepAdapter(context, new RecipeStepAdapter.StepClickListener() {
            @Override
            public void onItemClick(Step step) {
                Timber.d(step.getShortDescription());
            }
        });
        stepListView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(context);
        stepListView.setLayoutManager(manager);
        stepListView.addItemDecoration(new DividerItemDecoration(context, manager.getOrientation()));
        adapter.setList(recipeResponse.getSteps());

    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    private void createAndSetIngredientList(List<Ingredient> ingredients) {

        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.label_ingredient_list));

        for (Ingredient ing: ingredients
             ) {
            sb.append("\n");
            sb.append(StringUtils.formatIngdedient(context, ing.getIngredient(), ing.getQuantity(), ing.getMeasure()));

        }
        ingredientsView.setText(sb.toString());
    }


}

