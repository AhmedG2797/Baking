package ahmedg2797.baking.Widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import ahmedg2797.baking.Activities.Ingredients;
import ahmedg2797.baking.Activities.MainActivity;
import ahmedg2797.baking.Database.DataBaseBaking;
import ahmedg2797.baking.R;

import static ahmedg2797.baking.Widgets.BakingAppWidgetConfig.KEY;
import static android.content.Context.MODE_PRIVATE;

public class BakingAppWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);

        SharedPreferences sharedPreferences = context.getSharedPreferences("pref", MODE_PRIVATE);
        int id = sharedPreferences.getInt(KEY, 1);

        DataBaseBaking dataBaseBaking = new DataBaseBaking(context);
        String nameRecipe = dataBaseBaking.getRecipes().get(id - 1).getName();

        views.setTextViewText(R.id.recipe_widget, nameRecipe);

        Intent intent = new Intent(context, BakingAppWidgetService.class);
        views.setRemoteAdapter(R.id.list_widget, intent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}