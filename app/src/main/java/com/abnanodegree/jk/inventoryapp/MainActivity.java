package com.abnanodegree.jk.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

/**
 * https://developer.android.com/guide/topics/providers/content-providers.html
 * https://developer.android.com/guide/topics/providers/content-provider-creating.html?utm_source=udacity&utm_medium=course&utm_campaign=android_basics#ContentURI
 * https://developer.android.com/guide/topics/providers/content-provider-creating.html#ContentURI
 * https://developer.android.com/reference/android/content/ContentValues.html?utm_source=udacity&utm_medium=course&utm_campaign=android_basics
 * https://developer.android.com/training/basics/data-storage/databases.html
 * https://developer.android.com/guide/components/intents-filters.html?utm_source=udacity&utm_medium=course&utm_campaign=android_basics#Building
 * https://github.com/codepath/android_guides/wiki/Populating-a-ListView-with-a-CursorAdapter
 * https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/java/android/widget/CursorAdapter.java#274
 * http://www.theappguruz.com/blog/use-android-cursorloader-example
 * https://developer.android.com/guide/components/loaders.html?utm_source=udacity&utm_medium=course&utm_campaign=android_basics
 * https://developer.android.com/guide/topics/ui/layout/listview.html?utm_source=udacity&utm_medium=course&utm_campaign=android_basics
 * https://developer.android.com/training/best-background.html
 * http://responsiveandroid.com/2012/03/19/using-an-android-cursor-loader-with-a-content-provider.html
 * https://git-scm.com/book/en/v2/Getting-Started-Git-Basics
 * http://www.grokkingandroid.com/android-tutorial-writing-your-own-content-provider/
 * http://www.grokkingandroid.com/android-tutorial-using-content-providers/
 * http://stackoverflow.com/questions/4186021/how-to-start-new-activity-on-button-click
 * https://developer.android.com/training/implementing-navigation/temporal.html
 * https://guides.codepath.com/android/Using-the-App-ToolBar
 * http://stackoverflow.com/questions/26651602/display-back-arrow-on-toolbar-android
 * http://stackoverflow.com/questions/10108774/how-to-implement-the-android-actionbar-back-button
 * https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
 * https://www.tutorialspoint.com/android/android_content_providers.htm
 * http://www.androiddesignpatterns.com/2012/06/content-resolvers-and-content-providers.html
 * http://www.blogc.at/2014/03/03/swapcursor-vs-changecursor-whats-the-difference/
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
    }

    /**
     * Single method to process all buttons on View. Xml for each button points back to this method
     * @param v
     */
    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.view_button:
                // your code for button1 here
                Toast.makeText(getBaseContext(), "View", Toast.LENGTH_SHORT).show();
                // start Item List activity
                Intent intent = new Intent(getApplicationContext(), ListItemsActivity.class);
                startActivity(intent);
                break;
            case R.id.add_button:
                // your code for button2 here
                Toast.makeText(getApplicationContext(), "Add", Toast.LENGTH_SHORT).show();
                // start Activity
                Intent myIntent = new Intent(this, AddItemActivity.class);
                this.startActivity(myIntent);
                break;
            case R.id.options_button:
                // your code for button2 here
                Toast.makeText(getApplicationContext(), "Options", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit_button:
                // your code for button2 here
                Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
