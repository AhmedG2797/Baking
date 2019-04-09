package ahmedg2797.baking.Activities;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import java.util.List;

import ahmedg2797.baking.Adapters.StepsAdapter;
import ahmedg2797.baking.Fragments.Details;
import ahmedg2797.baking.Fragments.Steps;
import ahmedg2797.baking.Models.Recipe;
import ahmedg2797.baking.Models.Step;
import ahmedg2797.baking.R;

public class StepsActivity extends AppCompatActivity implements Steps.OnFragmentInteractionListener {

    public static boolean twoPaneMode = false;
    private Recipe recipe = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedRecipe")) {

            recipe = (Recipe) intent.getSerializableExtra("selectedRecipe");
        } else if (savedInstanceState != null){

            recipe = (Recipe) savedInstanceState.getSerializable("recipe");
        }


        if (recipe == null) {
            finish();
        }

        Steps steps = Steps.newInstance(recipe);
        getSupportFragmentManager().beginTransaction().replace(R.id.steps_fragment, steps).commit();

        if (findViewById(R.id.details_fragment) != null){
            twoPaneMode = true;
        }

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
    public void fragmentOnItemClick(int position) {

        if (twoPaneMode) {
            Details fragment = Details.newInstance(position, recipe);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_fragment, fragment).commit();
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("recipe", recipe);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }
}
