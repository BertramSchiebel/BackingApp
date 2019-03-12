package com.pinschaneer.bertram.backingapp.data;

import org.json.JSONException;
import org.json.JSONObject;

public class IngredientEntry
{
    private final String BJS_QUANTITY = "quantity";
    private final String BJS_MEASURE = "measure";
    private final String BJS_INGREDITENT = "ingredient";

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

    public IngredientEntry getIngredientEntry(String jsonData) {
        IngredientEntry ingredientEntry = new IngredientEntry();
        try {
            JSONObject backingDataJSON = new JSONObject(jsonData);
            if (backingDataJSON.has(BJS_QUANTITY)) {
                ingredientEntry.setQuantity(backingDataJSON.getDouble(BJS_QUANTITY));
            }
            if (backingDataJSON.has(BJS_INGREDITENT)) {
                ingredientEntry.setIngredient(backingDataJSON.getString(BJS_INGREDITENT));
            }
            if (backingDataJSON.has(BJS_MEASURE)) {
                ingredientEntry.setMeasure(backingDataJSON.getString(BJS_MEASURE));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return ingredientEntry;
    }

}
