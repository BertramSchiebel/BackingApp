package com.pinschaneer.bertram.backingapp.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeEntry
{
    private static final String BJS_ID = "id";
    private static final String BJS_NAME = "name";
    private static final String BJS_INGREDIENTS = "ingredients";
    private static final String BJS_STEPS = "steps";
    private int id;
    private String name;
    private ArrayList<IngredientEntry> ingredients;
    private ArrayList<StepEntry> steps;

    private RecipeEntry() {
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public ArrayList<IngredientEntry> getIngredients() {
        return ingredients;
    }

    public ArrayList<StepEntry> getSteps() {
        return steps;
    }

    public static RecipeEntry getRecipeEntry(JSONObject jsonBackingData) {
        RecipeEntry entry = new RecipeEntry();
        try {
            if (jsonBackingData.has(BJS_ID)) {
                entry.setId(jsonBackingData.getInt(BJS_ID));
            }
            if (jsonBackingData.has(BJS_NAME)) {
                entry.setName(jsonBackingData.getString(BJS_NAME));
            }
            if (jsonBackingData.has(BJS_INGREDIENTS)) {
                JSONArray ingredientsList = jsonBackingData.getJSONArray(BJS_INGREDIENTS);
                entry.ingredients = parseIngredients(ingredientsList);
            }
            if (jsonBackingData.has(BJS_STEPS)) {
                JSONArray stepList = jsonBackingData.getJSONArray(BJS_STEPS);
                entry.steps = parseSteps(stepList);
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return entry;
    }

    private static ArrayList<StepEntry> parseSteps(JSONArray stepList) {
        ArrayList<StepEntry> steps = new ArrayList<>();
        for (int i = 0; i < stepList.length(); i++) {
            try {
                JSONObject jsonStepEntry = stepList.getJSONObject(i);
                StepEntry step = StepEntry.getStepEntry(jsonStepEntry);
                if (step != null){
                    steps.add(step);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return steps;
    }

    private static ArrayList<IngredientEntry> parseIngredients(JSONArray ingredientsList) {
        ArrayList<IngredientEntry> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientsList.length(); i++) {
            try {
                JSONObject jsonIngredientEntry = ingredientsList.getJSONObject(i);
                IngredientEntry ingredient = IngredientEntry.getIngredientEntry(jsonIngredientEntry);
                if (ingredient != null){
                    ingredients.add(ingredient);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ingredients;
    }

}
