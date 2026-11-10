package com.example.lab4_recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class RecipeListFragment extends Fragment {

    public interface OnRecipeSelectedListener {
        void onRecipeSelected(int index);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ListView listView = view.findViewById(R.id.listRecipes);

        List<Recipe> recipes = RecipeData.getRecipes(requireContext());
        List<String> names = new ArrayList<>();
        for (Recipe r : recipes) { names.add(r.getName()); }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, v, position, id) -> {
            if (getActivity() instanceof OnRecipeSelectedListener) {
                ((OnRecipeSelectedListener) getActivity()).onRecipeSelected(position);
            }
        });

        return view;
    }
}