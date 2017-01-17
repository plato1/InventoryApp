package com.abnanodegree.jk.inventoryapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by jk on 1/14/2017.
 */

public class ListItems extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listitems);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        // This method makes the icon and title in the action bar clickable so that
        // “up” (ancestral) navigation can be provided. This method will just make
        // the icon and title pressable, but not actually add the functionality of
        // navigating upwards. That has to be done by specifying the android:parentActivityName
        // (takes the parent activity class name) on the activity in the manifest file.
        // It also adds a left-facing caret alongside the app icon.
        // Calls onOptionsItemSelected() on the Activity.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // This method just controls whether to show the Activity icon/logo or not.
        // Generally shows just the title with caret if passed false and used in
        // conjunction with setDisplayHomeAsUpEnabled().
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * When user presses back button on device emulation options, will go back to previous activity
     */
//    @Override
//    public void onBackPressed() {
//        // If the pet hasn't changed, continue with handling back button press
//        super.onBackPressed();
 //       return;
 //   }


}
