package com.example.FoodFinder;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class Rating {

    private String username;
    private String text;
    private int stars;

    public Rating(String username, String text, int stars) {
        this.username = username;
        this.text = text;
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Benutzer: " + username + "\n Sterne " + stars + " von 5\n" + text;
    }
}
