package com.example.FoodFinder;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class FoodFinderSettings {
    private static final String PREFS_NAME = "FoodFinderSettings";
    public static final String PREF_NAME_BUDGET = "budget";
    public static final String PREF_NAME_PREFERRED = "preferred";
    public static final String PREF_NAME_ALLERGIES = "allergies";

    private final Context context;

    public FoodFinderSettings(Context context) {
        this.context = context;
    }

    public int getBudget() {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt(PREF_NAME_BUDGET, 0);
    }
    public void setBudget(int budget) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(PREF_NAME_BUDGET, budget);
        editor.apply();
    }

    public List<String> getStringList(String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        String settingsString = settings.getString(key, "");
        if (settingsString.length() == 0) {
            return new ArrayList<String>();
        } else {
            String[] splitted = settingsString.split("\\|");
            List<String> list = new ArrayList<String>();
            Collections.addAll(list, splitted);
            return list;
        }
    }
    public void setStringList(String key, List<String> stringList) {
        StringBuilder builder = new StringBuilder();
        if (stringList.size() > 0)
            builder.append(stringList.get(0));
        for (int i=1; i<stringList.size(); i++)
            builder.append("|").append(stringList.get(i));

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, builder.toString());
        editor.apply();
    }

    public List<String> getPreferred() {
        return getStringList(PREF_NAME_PREFERRED);
    }
    public void setPreferred(List<String> preferred) {
        setStringList(PREF_NAME_PREFERRED, preferred);
    }

    public List<String> getAllergies() {
        return getStringList(PREF_NAME_ALLERGIES);
    }
    public void setAllergies(List<String> allergies) {
        setStringList(PREF_NAME_ALLERGIES, allergies);
    }
}
