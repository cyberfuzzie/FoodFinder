package com.example.FoodFinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class RestaurantAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<Restaurant> restaurants;
    private List<Restaurant> matchingRestaurants;

    public RestaurantAdapter(Context context) {
        super(context, R.layout.restaurant_list_item);
        this.context = context;

        matchingRestaurants = new ArrayList<>();

        RestaurantDataSource dataSource = new RestaurantDataSource(context);
        this.restaurants = dataSource.getRestaurants();
    }

    public void refresh(int budget, int appetite, boolean plusOne){
        matchingRestaurants.clear();
        List<String> names = new ArrayList<>();
        for(Restaurant restaurant:restaurants){
            if(match(budget,appetite,plusOne,restaurant)){
                matchingRestaurants.add(restaurant);
                names.add(restaurant.getName());
            }
        }
        clear();
        addAll(names);
    }

    private boolean match(int budget, int appetite, boolean plusOne, Restaurant restaurant){
        boolean matchPlusOne = plusOne == restaurant.isPlusOne();
        boolean matchBudget = budget - 15 <= restaurant.getBudget() && restaurant.getBudget() <= budget + 15;
        boolean matchAppetite = appetite - 15 <= restaurant.getAppetite() && restaurant.getAppetite() <= appetite + 15;
        return matchPlusOne && matchBudget && matchAppetite;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.restaurant_list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.restaurantListItemName);
        textView.setText(matchingRestaurants.get(position).getName());

        return rowView;

    }

    public Restaurant getRestaurant(int position) {
        return matchingRestaurants.get(position);
    }
}
