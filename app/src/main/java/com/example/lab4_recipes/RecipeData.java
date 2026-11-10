package com.example.lab4_recipes;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecipeData {

    private static List<Recipe> recipes;

    public static List<Recipe> getRecipes(Context context) {
        if (recipes == null) {
            recipes = new ArrayList<>();
            loadRecipes(context);
        }
        return recipes;
    }

    private static void loadRecipes(Context context) {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.recipes);
            Scanner scanner = new Scanner(is);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            scanner.close();

            JSONArray array = new JSONArray(builder.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                int id = obj.getInt("id");
                String name = obj.getString("name");
                String method = obj.getString("method");

                JSONArray ingArray = obj.getJSONArray("ingredients");
                List<String> ingredients = new ArrayList<>();
                for (int j = 0; j < ingArray.length(); j++) {
                    ingredients.add(ingArray.getString(j));
                }

                recipes.add(new Recipe(id, name, ingredients, method));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}