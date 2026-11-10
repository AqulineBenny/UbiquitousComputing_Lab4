package com.example.lab4_recipes;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int index = getIntent().getIntExtra("recipeIndex", 0);

        if (savedInstanceState == null) {
            RecipeDetailFragment detailFragment = RecipeDetailFragment.newInstance(index);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}