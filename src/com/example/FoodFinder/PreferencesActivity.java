package com.example.FoodFinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class PreferencesActivity extends Activity {

    public static final String PREFERENCES_BUDGET = "com.example.FoodFinder.preferences_budget";
    public static final String PREFERENCES_APPETITE = "com.example.FoodFinder.preferences_appetite";
    public static final String PREFERENCES_PLUSONE = "com.example.FoodFinder.preferences_plusone";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
        ((SeekBar)findViewById(R.id.budgetSeeker)).setProgress(getIntent().getIntExtra(PREFERENCES_BUDGET, 0));
        ((SeekBar)findViewById(R.id.appetiteSeeker)).setProgress(getIntent().getIntExtra(PREFERENCES_APPETITE, 0));
        ((Switch)findViewById(R.id.switchPlusOne)).setChecked(getIntent().getBooleanExtra(PREFERENCES_PLUSONE,false));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:finish();return true;
            default: return super.onOptionsItemSelected(item);
        }
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

    public void saveButtonClicked(View view){
        finish();
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra(PREFERENCES_APPETITE,((SeekBar)findViewById(R.id.appetiteSeeker)).getProgress());
        data.putExtra(PREFERENCES_BUDGET,((SeekBar)findViewById(R.id.budgetSeeker)).getProgress());
        data.putExtra(PREFERENCES_PLUSONE,((Switch)findViewById(R.id.switchPlusOne)).isChecked());
        setResult(RESULT_OK, data);
        super.finish();
    }
}
