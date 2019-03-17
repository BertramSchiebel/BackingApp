package com.pinschaneer.bertram.backingapp.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeEntry
{
    private final String BJS_ID = "id";
    private final String BJS_NAME = "name";
    private final String BJS_INGEDIENTS = "ingredients";
    private final String BJS_STEPS = "steps";
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

    public RecipeEntry getRecipeEntry(String jsonData) {
        RecipeEntry entry = new RecipeEntry();
        try {
            JSONObject backingDataJSON = new JSONObject(jsonData);
            if (backingDataJSON.has(BJS_ID)) {
                entry.setId(backingDataJSON.getInt(BJS_ID));
            }
            if (backingDataJSON.has(BJS_NAME)) {
                entry.setName(backingDataJSON.getString(BJS_NAME));
            }
            if (backingDataJSON.has(BJS_INGEDIENTS)){
                JSONArray  ingredientsList = backingDataJSON.getJSONArray(BJS_INGEDIENTS);
                parseIngredients(ingredientsList);
            }
            if (backingDataJSON.has(BJS_STEPS)){
                JSONArray  stepList = backingDataJSON.getJSONArray(BJS_STEPS);
                parseSteps(stepList);
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return entry;
    }

    private void parseSteps(JSONArray stepList) {
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

    }

    private void parseIngredients(JSONArray ingredientsList) {
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
    }

}
