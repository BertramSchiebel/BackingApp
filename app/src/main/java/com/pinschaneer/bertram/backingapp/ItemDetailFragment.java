package com.pinschaneer.bertram.backingapp;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pinschaneer.bertram.backingapp.activities.ItemListActivity;
import com.pinschaneer.bertram.backingapp.activities.ItemListActivityViewModel;
import com.pinschaneer.bertram.backingapp.data.IngredientEntry;
import com.pinschaneer.bertram.backingapp.data.RecipeEntry;
import com.pinschaneer.bertram.backingapp.data.StepEntry;

import java.util.List;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment
{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    ItemListActivityViewModel viewModel;

    /**
     * The dummy content this fragment is presenting.
     */
    private int id = -1;
    private List<RecipeEntry> recipeEntries;
    private RecipeEntry currentRecipe;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ItemListActivityViewModel.class);
        viewModel.getRecipeEntries().observe(this, new Observer<List<RecipeEntry>>()
        {

            @Override
            public void onChanged(@Nullable List<RecipeEntry> recipeEntries) {
                setupRecipeDetail(recipeEntries);

            }
        });

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            id = getArguments().getInt(ARG_ITEM_ID);
        }
    }

    private void setupRecipeDetail(List<RecipeEntry> recipeEntries) {
        this.recipeEntries = recipeEntries;
        currentRecipe = null;
        for (RecipeEntry recipe : recipeEntries) {
            if (recipe.getId() == id) {
                currentRecipe = recipe;
                break;
            }
        }
        showDetails();

    }

    private void showDetails() {

        if (currentRecipe == null) {
            return;
        }
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(currentRecipe.getName());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Ingredients;" + "\r\n");
        for (IngredientEntry ingredient : currentRecipe.getIngredients()) {
            sb.append(ingredient.getIngredient() + " " + ingredient.getQuantity() + ingredient.getMeasure());
            sb.append("\r\n");
        }

        sb.append("Steps;" + "\r\n");
        for (StepEntry step : currentRecipe.getSteps()) {
            sb.append(step.getShortDescription() + ": \r\n");
            sb.append(step.getDescription());
            sb.append("\r\n");
        }
        TextView itemDetail = activity.findViewById(R.id.item_detail);
        itemDetail.setText(sb.toString());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        if (currentRecipe == null) {
            return rootView;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Ingredients;" + "\r\n");
        for (IngredientEntry ingredient : currentRecipe.getIngredients()) {
            sb.append(ingredient.getIngredient() + " " + ingredient.getQuantity() + ingredient.getMeasure());
            sb.append("\r\n");
        }

        sb.append("Steps;" + "\r\n");
        for (StepEntry step : currentRecipe.getSteps()) {
            sb.append(step.getShortDescription() + ": \r\n");
            sb.append(step.getDescription());
            sb.append("\r\n");
        }


        // Show the dummy content as text in a TextView.
        ((TextView) rootView.findViewById(R.id.item_detail)).setText(sb.toString());

        return rootView;
    }
}
