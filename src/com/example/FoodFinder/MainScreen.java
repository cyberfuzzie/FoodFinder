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

        adapter = new RestaurantAdapter(this);
        adapter.refresh(budgetValue,appetiteValue,plusOne);

        ListView restaurantList = (ListView) findViewById(R.id.restaurantList);
        restaurantList.setAdapter(adapter);
        restaurantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentDisplayRestaurant = new Intent(MainScreen.this, RestaurantActivity.class);
                Restaurant r = adapter.getRestaurant(position);
                intentDisplayRestaurant.putExtra(RESTAURANT_MESSAGE, r.getId());
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
        adapter.refresh(budgetValue,appetiteValue,plusOne);
    }
}
