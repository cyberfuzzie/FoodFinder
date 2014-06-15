package com.example.FoodFinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

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
        int restaurantId = intent.getIntExtra(MainScreen.RESTAURANT_MESSAGE, 0);

        RestaurantDataSource dataSource = new RestaurantDataSource(this);
        restaurant = dataSource.getRestaurant(restaurantId);

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

        ListView ratingListView = (ListView) findViewById(R.id.restaurant_ratings_list);
        List<Rating> ratings = dataSource.getRatings(restaurantId);
        String[] array = new String[ratings.size()];
        for(int i=0;i<array.length;i++){
            array[i] = ratings.get(i).toString();
        }
        RatingAdapter adapter = new RatingAdapter(this, restaurantId);
        ratingListView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:finish();return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void createNewRating(View view) {
        Intent intentDisplayNewRating = new Intent(this, NewRatingActivity.class);
        startActivity(intentDisplayNewRating);
    }
}
