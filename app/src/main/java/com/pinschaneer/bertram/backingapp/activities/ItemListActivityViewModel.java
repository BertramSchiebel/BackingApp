package com.pinschaneer.bertram.backingapp.activities;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.pinschaneer.bertram.backingapp.data.RecipeEntry;
import com.pinschaneer.bertram.backingapp.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemListActivityViewModel extends AndroidViewModel
{
    public MutableLiveData<List<RecipeEntry>> getRecipeEntries() {
        return recipeEntries;
    }

    public MutableLiveData<List<RecipeEntry>> recipeEntries;

    public ItemListActivityViewModel(@NonNull Application application) {
        super(application);
        recipeEntries = new MutableLiveData<>();
        recipeEntries.setValue(new ArrayList<RecipeEntry>());
        loadRecepies();
    }

    private void loadRecepies() {
        new FetchRecipes().execute();
    }

    private class FetchRecipes extends AsyncTask<Void, Void, String>
    {

        @Override
        protected String doInBackground(Void... voids) {
            String response = "";
            try {
                response = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.backingDataUrl());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            ArrayList<RecipeEntry> recepies = new ArrayList<>();
            try {
                JSONArray jsonResponse = new JSONArray(response);
                for (int i = 0; i < jsonResponse.length(); i++) {
                    RecipeEntry recipeEntry = RecipeEntry.getRecipeEntry(jsonResponse.getJSONObject(i));
                    if (recipeEntry != null) {
                        recepies.add(recipeEntry);
                    }
                }
            }

            catch (JSONException e) {
                e.printStackTrace();
                // TODO Fehelerbehandlung
            }
            recipeEntries.postValue(recepies);
        }
    }
}
