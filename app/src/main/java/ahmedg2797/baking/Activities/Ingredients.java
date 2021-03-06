package ahmedg2797.baking.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import ahmedg2797.baking.Adapters.IngredientsAdapter;
import ahmedg2797.baking.Models.Ingredient;
import ahmedg2797.baking.Models.Recipe;
import ahmedg2797.baking.Models.Step;
import ahmedg2797.baking.R;

public class Ingredients extends AppCompatActivity {

    private Recipe recipe = null;
    private List<Ingredient> ingredientList = null;

    IngredientsAdapter ingredientsAdapter = null;
    RecyclerView ingredientRecyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("recipe")) {

            recipe = (Recipe) intent.getSerializableExtra("recipe");
        } else if (savedInstanceState != null){

            recipe = (Recipe) savedInstanceState.getSerializable("recipe");
        }

        initializeViews();
    }

    private void initializeViews() {

        ingredientList = recipe.getIngredients();
        ingredientsAdapter = new IngredientsAdapter(ingredientList, this);

        ingredientRecyclerView = findViewById(R.id.list_ingredient);
        ingredientRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL));
        ingredientRecyclerView.setAdapter(ingredientsAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("recipe", recipe);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        recipe = (Recipe) savedInstanceState.getSerializable("recipe");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
