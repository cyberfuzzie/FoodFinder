package com.example.FoodFinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainScreen extends Activity {
    public final static String RESTAURANT_MESSAGE = "com.example.FoodFinder.RESTAURANT";

    private RestaurantAdapter adapter;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String[] restaurantNames = {"Restaurant 1", "Restaurant 2", "Restaurant 3", "Restaurant 4", "Restaurant 5",
                "Restaurant 6", "Restaurant 7", "Restaurant 8", "Restaurant 9", "Restaurant 10",
                "Restaurant 11", "Restaurant 12", "Restaurant 13", "Restaurant 14", "Restaurant 15",
                "Restaurant 16", "Restaurant 17", "Restaurant 18", "Restaurant 19", "Restaurant 20",};
        adapter = new RestaurantAdapter(this, restaurantNames);

        ListView restaurantList = (ListView) findViewById(R.id.restaurantList);
        restaurantList.setAdapter(adapter);
        restaurantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentDisplayRestaurant = new Intent(MainScreen.this, RestaurantActivity.class);
                intentDisplayRestaurant.putExtra(RESTAURANT_MESSAGE, adapter.getItem(position));
                startActivity(intentDisplayRestaurant);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void openRestaurantPreferences(MenuItem item) {
        Intent intentDisplayRestaurantPreferences = new Intent(this, PreferencesActivity.class);
        intentDisplayRestaurantPreferences.putExtra(PreferencesActivity.PREFERENCES_APPETITE,30);
        intentDisplayRestaurantPreferences.putExtra(PreferencesActivity.PREFERENCES_BUDGET,70);
        startActivity(intentDisplayRestaurantPreferences);
    }

    public void openSettings(MenuItem item) {
        Intent intentDisplaySettings = new Intent(this, SettingsActivity.class);
        startActivity(intentDisplaySettings);
    }
}
