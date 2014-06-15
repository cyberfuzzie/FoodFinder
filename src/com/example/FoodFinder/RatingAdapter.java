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
public class RatingAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<Rating> ratings;

    public RatingAdapter(Context context, int restaurantId) {
        super(context, R.layout.restaurant_list_item);
        this.context = context;

        RestaurantDataSource dataSource = new RestaurantDataSource(context);
        this.ratings = dataSource.getRatings(restaurantId);

        String[] ratingTexts = new String[this.ratings.size()];
        for (int i=0; i<ratings.size(); i++) {
            ratingTexts[i] = ratings.get(i).toString();
        }
        addAll(ratingTexts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.restaurant_list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.restaurantListItemName);
        textView.setText(ratings.get(position).toString());

        return rowView;

    }
}
