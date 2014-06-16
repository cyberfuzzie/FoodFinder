package com.example.FoodFinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

/**
 * Created by cyberfuzzie on 6/15/14.
 */
public class SettingsStringListActivity extends FoodFinderBaseActivity {
    public final static String TITLE_MESSAGE = "com.example.FoodFinder.Settings.StringList.Title";
    public final static String SETTINGSITEM_MESSAGE = "com.example.FoodFinder.Settings.StringList.SettingsItem";
    public final static String ADDITEMLABEL_MESSAGE = "com.example.FoodFinder.Settings.StringList.AddItemLabel";
    public final static String REMOVEITEMLABEL_MESSAGE = "com.example.FoodFinder.Settings.StringList.RemoveItemLabel";

    private SettingsStringListAdapter adapter;

    private String addItemLabel;
    private String removeItemLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_stringlist);

        Intent intent = getIntent();
        String title = intent.getStringExtra(TITLE_MESSAGE);
        setTitle(title);
        String settingsName = intent.getStringExtra(SETTINGSITEM_MESSAGE);
        addItemLabel = intent.getStringExtra(ADDITEMLABEL_MESSAGE);
        removeItemLabel = intent.getStringExtra(REMOVEITEMLABEL_MESSAGE);

        adapter = new SettingsStringListAdapter(this,
                settingsName,
                addItemLabel);
        ListView preferredListView = (ListView) findViewById(R.id.settings_stringlist_list);
        preferredListView.setAdapter(adapter);
        preferredListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (adapter.isNewItemPosition(position)) {
                    displayNewPreferencePopup();
                }
            }
        });
        preferredListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            private static final int MENU_ITEM_DELETE = 1;
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (adapter.isNewItemPosition(position)) {
                    return false;
                }
                final PopupMenu popupMenu = new PopupMenu(SettingsStringListActivity.this, view);
                popupMenu.getMenu().add(Menu.NONE, MENU_ITEM_DELETE, Menu.NONE, removeItemLabel);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == MENU_ITEM_DELETE) {
                            adapter.deleteItem(position);
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
    }

    private void displayNewPreferencePopup() {
        LinearLayout viewGroup = (LinearLayout) findViewById(R.id.settings_stringlist_window_new);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = layoutInflater.inflate(R.layout.settings_stringlist_window_new, viewGroup);

        TextView title = (TextView) layout.findViewById(R.id.settings_stringlist_new_title);
        title.setText(addItemLabel);
        ListView preferredListView = (ListView) findViewById(R.id.settings_stringlist_list);
        final PopupWindow popup = new PopupWindow(this);

        popup.setContentView(layout);
        popup.setWidth((int) (preferredListView.getWidth() * 0.8));
        popup.setHeight((int) (preferredListView.getHeight() * 0.3));
        popup.setFocusable(true);
        popup.showAtLocation(layout, Gravity.NO_GRAVITY,
                (int) (preferredListView.getWidth() * 0.1),
                (int) (preferredListView.getHeight() * 0.35));

        Button popupButton = (Button) layout.findViewById(R.id.settings_stringlist_new_button_ok);
        popupButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) layout.findViewById(R.id.settings_stringlist_new_input_name);
                if (edit.getText().length() > 0)
                    adapter.addNewItem(edit.getText().toString());
                popup.dismiss();
            }
        });
    }
}
