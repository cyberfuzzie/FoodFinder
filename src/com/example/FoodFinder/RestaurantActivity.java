package com.example.FoodFinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class RestaurantActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_view);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainScreen.RESTAURANT_MESSAGE);

        setTitle(message);
    }

    public void createNewRating(View view) {
        Intent intentDisplayNewRating = new Intent(this, NewRatingActivity.class);
        startActivity(intentDisplayNewRating);
    }
}
