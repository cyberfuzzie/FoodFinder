package com.example.FoodFinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class RestaurantActivity extends Activity {

    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_view);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainScreen.RESTAURANT_MESSAGE);

        restaurant = Restaurant.RestaurantGenerator.createNewRestaurant();

        setTitle(restaurant.getName());

        String description = "Preis: ";
        if(restaurant.getBudget() < 30)
            description += "günstig\n";
        else if(restaurant.getBudget() < 65)
            description += "mittel\n";
        else
            description += "teuer\n";
        description += "Portion: ";
        if(restaurant.getAppetite() < 30)
            description += "klein";
        else if(restaurant.getAppetite() < 65)
            description += "mittel";
        else
            description += "groß";
        description += "\nBeschreibung; " + restaurant.getDescription();

        TextView textDescription = (TextView) findViewById(R.id.restaurant_description);
        textDescription.setText(description);

        ListView ratings = (ListView) findViewById(R.id.restaurant_ratings_list);
        String[] array = new String[restaurant.getRatings().size()];
        for(int i=0;i<array.length;i++){
            array[i] = restaurant.getRatings().get(i).toString();
        }
        RestaurantAdapter adapter = new RestaurantAdapter(this,array);
        ratings.setAdapter(adapter);
    }

    public void createNewRating(View view) {
        Intent intentDisplayNewRating = new Intent(this, NewRatingActivity.class);
        startActivity(intentDisplayNewRating);
    }
}
