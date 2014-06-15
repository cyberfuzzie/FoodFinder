package com.example.FoodFinder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class RestaurantOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "foodfinder";

    RestaurantOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE restaurants (" +
                   "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                   "    name TEXT," +
                   "    description TEXT," +
                   "    budget INTEGER," +
                   "    appetite INTEGER" +
                   ");");
        db.execSQL("CREATE TABLE ratings (" +
                   "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                   "    restaurant_id INTEGER," +
                   "    username TEXT," +
                   "    description TEXT," +
                   "    stars INTEGER" +
                   ");");
        createRestaurants(db);
    }

    private void createRestaurants(SQLiteDatabase db) {
        Random random = new Random(42);
        String[] possibleDescription = {"Kleines stilvolles Restaurant","Schöne Lage und nettes Personal","Pizzaria"};
        for (int i=1; i<=20; i++) {
            String restaurantName = "Restaurant " + i;
            String restaurantDescription = possibleDescription[random.nextInt(possibleDescription.length)];
            int restaurantAppetite = random.nextInt(101);
            int restaurantBudget = random.nextInt(101);
            String sql = "INSERT INTO restaurants (id, name, description, budget, appetite) " +
                         "VALUES (?, ?, ?, ?, ?);";
            Object[] params = {i, restaurantName, restaurantDescription, restaurantBudget, restaurantAppetite};
            db.execSQL(sql, params);
            createRatings(i, random, db);
        }
    }

    private void createRatings(int restaurantId, Random random, SQLiteDatabase db) {
        String[] possibleUsernames = {"Max","Hans","Sepp","Emma","Birgit","Hilde"};
        String[][] possibleTexts = {
                {"Nie wieder!","In meiner Suppe war eine Fliege","Das Essen ist ungenießbar","haben 2 Stunden auf ein Gericht gewartet"},
                {"Schlechter Service","Essen war bereits kalt als es auf den Tisch kam","Werde hier nicht mehr her kommen"},
                {"Essen war in Ordnung","Haben relativ lange auf Getränke warten müssen"},
                {"lecker","gerne wieder"},
                {"sehr gut","ausgezeichnet","top"}
        };
        int ratingNumber = random.nextInt(5) + 1;
        for(int i=0;i<ratingNumber;i++) {
            String username = possibleUsernames[random.nextInt(possibleUsernames.length)];
            int stars = random.nextInt(5);
            String text = possibleTexts[stars][random.nextInt(possibleTexts[stars].length)];
            String sql = "INSERT INTO ratings (restaurant_id, username, description, stars) " +
                         "VALUES (?, ?, ?, ?);";
            Object[] params = {restaurantId, username, text, stars};
            db.execSQL(sql, params);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
