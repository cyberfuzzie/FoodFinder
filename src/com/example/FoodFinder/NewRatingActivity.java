package com.example.FoodFinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class NewRatingActivity extends Activity {
    public final static String MESSAGE_RESTAURANTID = "com.example.FoodFinder.RestaurantId";

    private int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_new_rating);

        Intent intent = getIntent();
        restaurantId = intent.getIntExtra(MESSAGE_RESTAURANTID, 0);
    }

    public void saveNewRating(View view) {
        EditText inputUsername = (EditText) findViewById(R.id.new_rating_username);
        String username = inputUsername.getText().toString();
        RatingBar inputStars = (RatingBar) findViewById(R.id.new_rating_stars);
        int rating = (int)(inputStars.getRating());
        EditText inputText = (EditText) findViewById(R.id.new_rating_text);
        String text = inputText.getText().toString();

        RestaurantDataSource dataSource = new RestaurantDataSource(this);
        dataSource.saveRating(restaurantId, username, text, rating);
        this.finish();
    }
}
