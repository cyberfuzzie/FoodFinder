package com.example.FoodFinder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class SettingsBudgetActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_budget);

        FoodFinderSettings settings = new FoodFinderSettings(this);
        SeekBar budgetBar = (SeekBar) findViewById(R.id.settings_budget_value);
        budgetBar.setProgress(settings.getBudget());
    }

    @Override
    protected void onDestroy() {
        FoodFinderSettings settings = new FoodFinderSettings(this);
        SeekBar budgetBar = (SeekBar) findViewById(R.id.settings_budget_value);
        settings.setBudget(budgetBar.getProgress());

        super.onDestroy();
    }
}
