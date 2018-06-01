package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);

            // get name
            JSONObject nameObject = jsonObject.getJSONObject("name");
            String nameMain = nameObject.getString("mainName");
            // get alsoKnownAs
            JSONArray alsoKnownAsJSONArray = nameObject.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJSONArray.length(); i++){
                alsoKnownAsList.add(alsoKnownAsJSONArray.getString(i));
            }
            // get placeOfOrigin, description and image
            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            String image = jsonObject.getString("image");
            // get ingredients
            JSONArray ingredientsJSONArray = jsonObject.getJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsJSONArray.length(); i++){
                ingredientsList.add(ingredientsJSONArray.getString(i));
            }

           return new Sandwich(nameMain, alsoKnownAsList, placeOfOrigin, description,
                    image, ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

