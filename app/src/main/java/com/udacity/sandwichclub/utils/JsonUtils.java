package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) {

        Log.i(TAG, "json is... " + json);

        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject nameObject = jsonObject.getJSONObject("name");
            String nameMain = nameObject.getString("mainName");

            Log.i(TAG, "nameMain is... " + nameMain);


            JSONArray alsoKnownAsJSONArray = nameObject.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJSONArray.length(); i++){
                alsoKnownAsList.add(alsoKnownAsJSONArray.getString(i));
            }
            Log.i(TAG, "alsoKnownAsList is... " + alsoKnownAsList);

            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            Log.i(TAG, "placeOfOrigin is... " + placeOfOrigin);

            String description = jsonObject.getString("description");
            Log.i(TAG, "description is... " + description);

            String image = jsonObject.getString("image");
            Log.i(TAG, "image is... " + image);

            JSONArray ingredientsJSONArray = jsonObject.getJSONArray("ingredients");
            Log.i(TAG, "ingredientsJSONArray is... " + ingredientsJSONArray.toString());
            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsJSONArray.length(); i++){
                ingredientsList.add(ingredientsJSONArray.getString(i));
                Log.i(TAG, "ingredientsList.Add is... " + ingredientsJSONArray.getString(i));

            }

           return new Sandwich(nameMain, alsoKnownAsList, placeOfOrigin, description,
                    image, null);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

