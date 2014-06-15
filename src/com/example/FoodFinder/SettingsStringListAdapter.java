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
public class SettingsStringListAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> stringList;
    private String newItemLabel;
    private String settingsKey;

    public SettingsStringListAdapter(Context context, String settingsKey, String newItemLabel) {
        super(context, R.layout.settings_stringlist_item);

        this.newItemLabel = newItemLabel;
        this.settingsKey = settingsKey;

        FoodFinderSettings settings = new FoodFinderSettings(context);
        stringList = settings.getStringList(settingsKey);

        addAll(stringList);
        add(newItemLabel);

        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.settings_stringlist_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.settings_stringlist_item_name);
        if (position < stringList.size())
            textView.setText(stringList.get(position));
        else
            textView.setText(this.newItemLabel);

        return rowView;

    }

    public boolean isNewItemPosition(int position) {
        return position >= stringList.size();
    }

    public void addNewItem(String name) {
        insert(name, stringList.size());
        stringList.add(name);
        FoodFinderSettings settings = new FoodFinderSettings(context);
        settings.setStringList(settingsKey, stringList);
    }

    public void deleteItem(int position) {
        remove(getItem(position));
        stringList.remove(position);
        FoodFinderSettings settings = new FoodFinderSettings(context);
        settings.setStringList(settingsKey, stringList);
    }
}
