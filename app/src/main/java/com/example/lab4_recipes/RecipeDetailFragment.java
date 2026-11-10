package com.example.lab4_recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RecipeDetailFragment extends Fragment {

    private static final String ARG_INDEX = "recipeIndex";

    public static RecipeDetailFragment newInstance(int index) {
        RecipeDetailFragment f = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        int index = getArguments() != null ? getArguments().getInt(ARG_INDEX) : 0;
        Recipe recipe = RecipeData.getRecipes(requireContext()).get(index);

        TextView tvName = view.findViewById(R.id.tvRecipeName);
        TextView tvIngredients = view.findViewById(R.id.tvIngredients);
        TextView tvMethod = view.findViewById(R.id.tvMethod);

        tvName.setText(recipe.getName());

        // Format ingredients list into a string
        StringBuilder ingText = new StringBuilder("Ingredients:\n");
        for (String item : recipe.getIngredients()) {
            ingText.append("- ").append(item).append("\n");
        }
        tvIngredients.setText(ingText.toString());

        tvMethod.setText("Method:\n" + recipe.getMethod());

        return view;
    }
}