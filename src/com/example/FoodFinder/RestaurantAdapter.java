package com.example.FoodFinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class RestaurantAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<Restaurant> restaurants;

    public RestaurantAdapter(Context context) {
        super(context, R.layout.restaurant_list_item);
        this.context = context;

        RestaurantDataSource dataSource = new RestaurantDataSource(context);
        this.restaurants = dataSource.getRestaurants();

        String[] restaurantNames = new String[this.restaurants.size()];
        for (int i=0; i<restaurants.size(); i++) {
            restaurantNames[i] = restaurants.get(i).getName();
        }
        addAll(restaurantNames);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.restaurant_list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.restaurantListItemName);
        textView.setText(restaurants.get(position).getName());

        return rowView;

    }

    public Restaurant getRestaurant(int position) {
        return restaurants.get(position);
    }
}
