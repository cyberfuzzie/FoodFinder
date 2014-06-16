package com.example.FoodFinder;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class RestaurantActivity extends FoodFinderBaseActivity {

    private Restaurant restaurant;
    private static String[] DESTINATIONS = {
            "Studentenwerk Augsburg - Mensa an der Universität Augsburg",
            "Gaststätte Unikum",
            "Mr. Onions",
            "Presto-Pizza",
            "Il Porcino Ristorante"
    };
    private int destId;

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

        destId = new Random().nextInt(DESTINATIONS.length);
    }

    public void createNewRating(View view) {
        Intent intentDisplayNewRating = new Intent(this, NewRatingActivity.class);
        intentDisplayNewRating.putExtra(NewRatingActivity.MESSAGE_RESTAURANTID, restaurant.getId());
        startActivity(intentDisplayNewRating);
    }

    public void navigateToRestaurant(View view) {
        //String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        String uri = "geo:0,0?q=" + DESTINATIONS[destId];
        Intent intentNavigateTo = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        try {
            startActivity(intentNavigateTo);
        } catch (ActivityNotFoundException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.no_maps_app));
            builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
