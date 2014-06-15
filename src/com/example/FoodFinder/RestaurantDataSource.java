package com.example.FoodFinder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class RestaurantDataSource {
    private RestaurantOpenHelper dbHelper;

    public RestaurantDataSource(Context context) {
        dbHelper = new RestaurantOpenHelper(context);
    }

    public List<Restaurant> getRestaurants() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {"id", "name", "description", "budget", "appetite"};
        Cursor cursor = db.query("restaurants", columns, null, null, null, null, "id");
        List<Restaurant> restaurants = new ArrayList<>();
        while (cursor.moveToNext()) {
            int restaurantId = cursor.getInt(0);
            String restaurantName = cursor.getString(1);
            String restaurantDescription = cursor.getString(2);
            int restaurantAppetite = cursor.getInt(3);
            int restaurantBudget = cursor.getInt(4);
            restaurants.add(new Restaurant(restaurantId, restaurantName, restaurantDescription, restaurantBudget, restaurantAppetite));
        }
        return restaurants;
    }

    public Restaurant getRestaurant(int restaurantId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {"name", "description", "budget", "appetite"};
        String[] selectionArgs = {"" + restaurantId};
        Cursor cursor = db.query("restaurants", columns, "id = ?", selectionArgs, null, null, "id");
        if (cursor.moveToNext()) {
            String restaurantName = cursor.getString(0);
            String restaurantDescription = cursor.getString(1);
            int restaurantAppetite = cursor.getInt(2);
            int restaurantBudget = cursor.getInt(3);
            return new Restaurant(restaurantId, restaurantName, restaurantDescription, restaurantBudget, restaurantAppetite);
        } else {
            return null;
        }
    }

    public List<Rating> getRatings(int restaurantId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {"username", "description", "stars"};
        String[] selectionArgs = {"" + restaurantId};
        Cursor cursor = db.query("ratings", columns, "restaurant_id = ?", selectionArgs, null, null, "id");
        List<Rating> ratings = new ArrayList<>();
        while (cursor.moveToNext()) {
            String ratingUsername = cursor.getString(0);
            String ratingText = cursor.getString(1);
            int ratingStars = cursor.getInt(2);
            ratings.add(new Rating(ratingUsername, ratingText, ratingStars));
        }
        return ratings;
    }
}
