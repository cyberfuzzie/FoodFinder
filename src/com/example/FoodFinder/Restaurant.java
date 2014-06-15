package com.example.FoodFinder;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Thomas on 15.06.2014.
 */
public class Restaurant {



    private String name;
    private String description;
    private int budget;
    private int appetite;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getBudget() {
        return budget;
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean isPlusOne() {
        return plusOne;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    private boolean plusOne;

    List<Rating> ratings;


    static class RestaurantGenerator {

        static int number = 1;
        static String[] possibleDescprition = {"Kleines stilvolles Restaurant","Schöne Lage und nettes Personal","Pizzaria"};

        static Restaurant createNewRestaurant(){
            Random random = new Random();
            Restaurant restaurant = new Restaurant();
            restaurant.name = "Restaurant " + number++;
            restaurant.description = possibleDescprition[random.nextInt(possibleDescprition.length)];
            restaurant.appetite = random.nextInt(101);
            restaurant.budget = random.nextInt(101);
            restaurant.plusOne = random.nextBoolean();
            int ratingNumber = random.nextInt(5) + 1;
            restaurant.ratings = new ArrayList<Rating>();
            for(int i=0;i<ratingNumber;i++)
                restaurant.ratings.add(RatingGenerator.createNewRating());
            return restaurant;
        }
    }

    static class Rating {

        private String username;
        private String text;
        private int stars;

        @Override
        public String toString() {
            return "Benutzer: " + username + "\n Sterne " + stars + " von 5\n" + text;
        }
    }

    static class RatingGenerator{
        static String[] possibleUsernames = {"Max","Hans","Sepp","Emma","Birgit","Hilde"};
        static String[][] possibleTexts = {
                {"Nie wieder!","In meiner Suppe war eine Fliege","Das Essen ist ungenießbar","haben 2 Stunden auf ein Gericht gewartet"},
                {"Schlechter Service","Essen war bereits kalt als es auf den Tisch kam","Werde hier nicht mehr her kommen"},
                {"Essen war in Ordnung","Haben relativ lange auf Getränke warten müssen"},
                {"lecker","gerne wieder"},
                {"sehr gut","ausgezeichnet","top"}
        };

        static Rating createNewRating() {
            Random random = new Random();
            int username = random.nextInt(possibleUsernames.length);
            int stars = random.nextInt(5);
            int text = random.nextInt(possibleTexts[stars].length);
            Rating rating = new Rating();
            rating.username = possibleUsernames[username];
            rating.stars = stars;
            rating.text = possibleTexts[stars][text];
            return rating;
        }
    }
}
