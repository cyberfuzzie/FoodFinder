package com.example.FoodFinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    public void openSettingsBudget(View view) {
        Intent intentDisplaySettingsBudget = new Intent(this, SettingsBudgetActivity.class);
        startActivity(intentDisplaySettingsBudget);
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
}
