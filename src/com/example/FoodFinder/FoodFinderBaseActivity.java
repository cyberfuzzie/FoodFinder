package com.example.FoodFinder;

import android.app.Activity;
import android.view.MenuItem;

/**
 * Created by cyberfuzzie on 6/16/14.
 */
public class FoodFinderBaseActivity extends Activity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:finish();return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
