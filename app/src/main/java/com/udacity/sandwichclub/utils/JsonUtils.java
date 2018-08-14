package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static String TAG= JsonUtils.class.getSimpleName();
    private static final String NAME_KEY = "name";
    private static String MAIN_NAME_KEY = "mainName";
    private static String DESCRIPTION_KEY = "description";
    private static String PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    private static String IMAGE_KEY = "image";
    private static String INGREDIENTS_KEY = "ingredients";
    private static String ALSO_KNOW_AS_KEY = "alsoKnownAs";


    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            Log.e(TAG,e.getMessage());
        }
        try {
            if (jsonObject != null) {
                JSONObject sandwichName = jsonObject.getJSONObject(NAME_KEY);
                sandwich.setMainName(sandwichName.getString(MAIN_NAME_KEY));
                sandwich.setDescription(jsonObject.getString(DESCRIPTION_KEY));
                sandwich.setPlaceOfOrigin(jsonObject.getString(PLACE_OF_ORIGIN_KEY));
                sandwich.setImage(jsonObject.getString(IMAGE_KEY));

                List<String> names = new ArrayList<>();
                JSONArray mAlsoKnowAs = sandwichName.getJSONArray(ALSO_KNOW_AS_KEY);
                if(mAlsoKnowAs != null){
                    for(int i=0; i < mAlsoKnowAs.length(); i++){
                        names.add(mAlsoKnowAs.get(i).toString());
                    }
                }
                sandwich.setAlsoKnownAs(names);

                List<String> ingredients = new ArrayList<>();
                JSONArray mIngredients = jsonObject.getJSONArray(INGREDIENTS_KEY);
                if(mIngredients != null){
                    for(int i=0; i < mIngredients.length(); i++){
                        ingredients.add(mIngredients.get(i).toString());
                    }
                }
                sandwich.setIngredients(ingredients);
            }
        } catch (JSONException e) {
            Log.e(TAG,e.getMessage());
        }
        return sandwich;
    }
}
