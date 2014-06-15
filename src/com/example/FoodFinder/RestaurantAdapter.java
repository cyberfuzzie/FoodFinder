package com.example.FoodFinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class RestaurantAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] restaurants;

    public RestaurantAdapter(Context context, String[] restaurants) {
        super(context, R.layout.restaurant_list_item, restaurants);
        this.context = context;
        this.restaurants = restaurants;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.restaurant_list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.restaurantListItemName);
        textView.setText(restaurants[position]);

        return rowView;

    }
}
