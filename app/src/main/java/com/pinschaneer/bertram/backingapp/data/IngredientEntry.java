package com.pinschaneer.bertram.backingapp.data;

import org.json.JSONException;
import org.json.JSONObject;

public class IngredientEntry
{
    private static final String BJS_QUANTITY = "quantity";
    private static final String BJS_MEASURE = "measure";
    private static final String BJS_INGREDITENT = "ingredient";

    private double quantity;
    private String measure;
    private String ingredient;

    public double getQuantity() {
        return quantity;
    }

    private void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    private void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    private void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    static IngredientEntry getIngredientEntry(JSONObject jsonData) {
        IngredientEntry ingredientEntry = new IngredientEntry();
        try {
            if (jsonData.has(BJS_QUANTITY)) {
                ingredientEntry.setQuantity(jsonData.getDouble(BJS_QUANTITY));
            }
            if (jsonData.has(BJS_INGREDITENT)) {
                ingredientEntry.setIngredient(jsonData.getString(BJS_INGREDITENT));
            }
            if (jsonData.has(BJS_MEASURE)) {
                ingredientEntry.setMeasure(jsonData.getString(BJS_MEASURE));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return ingredientEntry;
    }

}
