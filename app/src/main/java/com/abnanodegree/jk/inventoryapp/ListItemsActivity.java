package com.abnanodegree.jk.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.abnanodegree.jk.inventoryapp.data.StockContract;

/**
 * Created on 1/14/2017.
 */

public class ListItemsActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>  {

    /** Identifier for the item data loader */
    private static final int STOCK_LOADER = 0;

    /** Adapter for the ListView */
    StockCursorAdapter mCursorAdapter;

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



        // Find the ListView which will be populated with the item data
        ListView stockListView = (ListView) findViewById(R.id.list_item_view);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        stockListView.setEmptyView(emptyView);

        // Setup an Adapter to create a list item for each row of item data in the Cursor.
        // There is no item data yet (until the loader finishes) so pass in null for the Cursor.
        mCursorAdapter = new StockCursorAdapter(this, null);
        stockListView.setAdapter(mCursorAdapter);

        // Setup the item click listener.....will provide detailed view of an item.
        stockListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Create new intent to go to {@link EditorActivity}
                Intent intent = new Intent(ListItemsActivity.this, ItemDetailsActivity.class);

                // Form the content URI that represents the specific item that was clicked on,
                // by appending the "id" (passed as input to this method) onto the
                // {@link StockEntry#CONTENT_URI}.
                // For example, the URI would be "content://com.example.android.stock/stock/2"
                // if the stock with ID 2 was clicked on.
                Uri currentPetUri = ContentUris.withAppendedId(StockContract.StockEntry.CONTENT_URI, id);

                // Set the URI on the data field of the intent
                intent.setData(currentPetUri);

                // Launch the {@link EditorActivity} to display the data for the current item.
                startActivity(intent);
            }
        });

        // Kick off the loader
        getLoaderManager().initLoader(STOCK_LOADER, null, this);
    }

    /**
     * Helper method to insert hardcoded item data into the database. For debugging purposes only.
     */
    private void insertItem() {
        // Create a ContentValues object where column names are the keys,
        // and  attributes are the values.
        ContentValues values = new ContentValues();
        values.put(StockContract.StockEntry.COLUMN_ITEM_NAME, "Prod1");
        values.put(StockContract.StockEntry.COLUMN_ITEM_QUANTITY, "3");
        values.put(StockContract.StockEntry.COLUMN_ITEM_PRICE, "$12");

        // Insert a new row for Toto into the provider using the ContentResolver.
        // Use the {@link StockEntry#CONTENT_URI} to indicate that we want to insert
        // into the pets database table.
        // Receive the new content URI that will allow us to access items data in the future.
        Uri newUri = getContentResolver().insert(StockContract.StockEntry.CONTENT_URI, values);
    }

    /**
     * Helper method to delete all pets in the database.
     */
    private void deleteAllItems() {
        int rowsDeleted = getContentResolver().delete(StockContract.StockEntry.CONTENT_URI, null, null);
        Log.v("ListItemsActivity", rowsDeleted + " rows deleted from stock database");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_listitems, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertItem();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllItems();
                return true;
        }
        return super.onOptionsItemSelected(item);
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




    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                StockContract.StockEntry._ID,
                StockContract.StockEntry.COLUMN_ITEM_NAME,
                StockContract.StockEntry.COLUMN_ITEM_PRICE,
                StockContract.StockEntry.COLUMN_ITEM_QUANTITY };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                StockContract.StockEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update {@link StockCursorAdapter} with this new cursor containing updated pet data
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Callback called when the data needs to be deleted
        mCursorAdapter.swapCursor(null);
    }


}
