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
import android.widget.TextView;

public class MainScreen extends Activity {
    public static final String RESTAURANT_MESSAGE = "com.example.FoodFinder.RESTAURANT";
    public static final int PREFERENCES_REQUEST = 1;

    private RestaurantAdapter adapter;
    private int appetiteValue = 70;
    private int budgetValue = 30;
    private boolean plusOne = false;

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
        intentDisplayRestaurantPreferences.putExtra(PreferencesActivity.PREFERENCES_APPETITE,appetiteValue);
        intentDisplayRestaurantPreferences.putExtra(PreferencesActivity.PREFERENCES_BUDGET,budgetValue);
        intentDisplayRestaurantPreferences.putExtra(PreferencesActivity.PREFERENCES_PLUSONE,plusOne);
        startActivityForResult(intentDisplayRestaurantPreferences,PREFERENCES_REQUEST);
     }

    public void openSettings(MenuItem item) {
        Intent intentDisplaySettings = new Intent(this, SettingsActivity.class);
        startActivity(intentDisplaySettings);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case PREFERENCES_REQUEST: handlePreferencesRequest(resultCode,data);
        }
    }

    private void handlePreferencesRequest(int resultCode, Intent data) {
        appetiteValue = data.getIntExtra(PreferencesActivity.PREFERENCES_APPETITE,-1);
        budgetValue = data.getIntExtra(PreferencesActivity.PREFERENCES_BUDGET,-1);
        plusOne = data.getBooleanExtra(PreferencesActivity.PREFERENCES_PLUSONE,false);
    }
}
