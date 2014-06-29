package com.example.FoodFinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class PreferencesActivity extends FoodFinderBaseActivity {

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

    public void openSettingsPreferred(View view) {
        Intent intentDisplaySettingsPreferred = new Intent(this, SettingsStringListActivity.class);
        intentDisplaySettingsPreferred.putExtra(SettingsStringListActivity.TITLE_MESSAGE, getString(R.string.settings_preferred));
        intentDisplaySettingsPreferred.putExtra(SettingsStringListActivity.SETTINGSITEM_MESSAGE, FoodFinderSettings.PREF_NAME_PREFERRED);
        intentDisplaySettingsPreferred.putExtra(SettingsStringListActivity.ADDITEMLABEL_MESSAGE, getString(R.string.settings_preferred_add_new));
        intentDisplaySettingsPreferred.putExtra(SettingsStringListActivity.REMOVEITEMLABEL_MESSAGE, getString(R.string.settings_preferred_remove));
        startActivity(intentDisplaySettingsPreferred);
    }

    public void openSettingsAllergies(View view) {
        Intent intentDisplaySettingsAllergies = new Intent(this, SettingsStringListActivity.class);
        intentDisplaySettingsAllergies.putExtra(SettingsStringListActivity.TITLE_MESSAGE, getString(R.string.settings_allergies));
        intentDisplaySettingsAllergies.putExtra(SettingsStringListActivity.SETTINGSITEM_MESSAGE, FoodFinderSettings.PREF_NAME_ALLERGIES);
        intentDisplaySettingsAllergies.putExtra(SettingsStringListActivity.ADDITEMLABEL_MESSAGE, getString(R.string.settings_allergies_add_new));
        intentDisplaySettingsAllergies.putExtra(SettingsStringListActivity.REMOVEITEMLABEL_MESSAGE, getString(R.string.settings_allergies_remove));
        startActivity(intentDisplaySettingsAllergies);
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
