package com.example.lab4_recipes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeSelectedListener {

    private boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new RecipeListFragment())
                    .commit();
        }
    }

    @Override
    public void onRecipeSelected(int index) {
        if (isLandscape) {
            // LANDSCAPE: Show detail in the second container (no new activity)
            RecipeDetailFragment detailFragment = RecipeDetailFragment.newInstance(index);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_detail, detailFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("recipeIndex", index);
            startActivity(intent);
        }
    }
}