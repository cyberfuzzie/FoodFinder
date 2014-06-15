package com.example.FoodFinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class PreferencesActivity extends Activity {

    public static final String PREFERENCES_BUDGET = "com.example.FoodFinder.preferences_budget";
    public static final String PREFERENCES_APPETITE = "com.example.FoodFinder.preferences_appetite";

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
        intent = getIntent();
        ((SeekBar)findViewById(R.id.budgetSeeker)).setProgress(intent.getIntExtra(PREFERENCES_BUDGET,0));
        ((SeekBar)findViewById(R.id.appetiteSeeker)).setProgress(intent.getIntExtra(PREFERENCES_APPETITE,0));
    }

    public void lowerBudget(View view) {
        SeekBar seeker = (SeekBar) findViewById(R.id.budgetSeeker);
        int tmp = seeker.getProgress();
        tmp -= 10;
        seeker.setProgress(tmp);
    }

    public void increaseBudget(View view){
        SeekBar seeker = (SeekBar) findViewById(R.id.budgetSeeker);
        int tmp = seeker.getProgress();
        tmp += 10;
        seeker.setProgress(tmp);
    }

    public void lowerAppetie(View view){
        SeekBar seeker = (SeekBar) findViewById(R.id.appetiteSeeker);
        int tmp = seeker.getProgress();
        tmp -= 10;
        seeker.setProgress(tmp);
    }

    public void increaseAppetie(View view){
        SeekBar seeker = (SeekBar) findViewById(R.id.appetiteSeeker);
        int tmp = seeker.getProgress();
        tmp += 10;
        seeker.setProgress(tmp);
    }
}
