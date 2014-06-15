package com.example.FoodFinder;

/**
 * Created by Thomas on 15.06.2014.
 */
public class Restaurant {


    private int id;
    private String name;
    private String description;
    private int budget;
    private int appetite;
    private boolean plusOne = false;

    public Restaurant(int id, String name, String description, int budget, int appetite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.budget = budget;
        this.appetite = appetite;
    }

    public int getId() {
        return id;
    }

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

}
