package ahmedg2797.baking.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ahmedg2797.baking.Fragments.Details;
import ahmedg2797.baking.Models.Recipe;
import ahmedg2797.baking.R;

public class DetailsActivity extends AppCompatActivity {

    private Recipe recipe = null;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("recipe") && intent.hasExtra("position")) {

            recipe = (Recipe) intent.getSerializableExtra("recipe");
            position = intent.getIntExtra("position", -1);
        } else if (savedInstanceState != null){

            recipe = (Recipe) savedInstanceState.getSerializable("recipe");
            position = savedInstanceState.getInt("position", -1);
        }

        if (recipe != null && position != -1){

            Details fragment = Details.newInstance(position, recipe);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_fragment, fragment).commit();
        } else {
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("recipe", recipe);
        outState.putInt("position", position);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        recipe = (Recipe) savedInstanceState.getSerializable("recipe");
        position = savedInstanceState.getInt("position", -1);
    }
}
