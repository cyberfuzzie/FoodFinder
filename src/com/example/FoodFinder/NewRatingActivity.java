package com.example.FoodFinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class NewRatingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_new_rating);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:finish();return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
