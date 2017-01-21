package com.abnanodegree.jk.inventoryapp;

import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.abnanodegree.jk.inventoryapp.data.StockContract;

/**
 * Created  on 1/17/2017.
 * Dont use default import for LoadManager --> android.support.v4.app.LoaderManager;
 * Instead, use --> android.app.LoaderManager;
 */

public class ItemDetailsActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    /** Identifier for the stock data loader */
    private static final int EXISTING_STOCK_LOADER = 0;

    /** Content URI for the existing item (null if it's a new item) */
    private Uri mCurrentPetUri;

    // Edit text field to enter name of new item
    private EditText mItemEditText;
    // Edit text field to enter quantity of new item
    private EditText mQuantityEditText;
    // Edit text field to enter supplier of new item
    private EditText mSupplierEditText;
    // Edit text field to enter price of new item
    private EditText mPriceEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdetails);

        // Examine the intent that was used to launch this activity,
        // in order to save the URI to be used by Loader to identify which record to display
        Intent intent = getIntent();
        mCurrentPetUri = intent.getData();

        // Initialize a loader to read the item data from the database
        // and display the current values in the editor
        getLoaderManager().initLoader(EXISTING_STOCK_LOADER, null, this);
    }



    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Since the editor shows all item attributes, define a projection that contains
        // all columns from the item table
        String[] projection = {
                StockContract.StockEntry._ID,
                StockContract.StockEntry.COLUMN_ITEM_NAME,
                StockContract.StockEntry.COLUMN_ITEM_PRICE,
                StockContract.StockEntry.COLUMN_ITEM_QUANTITY,
                StockContract.StockEntry.COLUMN_ITEM_SUPPLIER };


        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentPetUri,         // Query the content URI for the current pet
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {
            // Find the columns of pet attributes that we're interested in
            int nameColumnIndex = cursor.getColumnIndex(StockContract.StockEntry.COLUMN_ITEM_NAME);
            int priceColumnIndex = cursor.getColumnIndex(StockContract.StockEntry.COLUMN_ITEM_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(StockContract.StockEntry.COLUMN_ITEM_QUANTITY);
            int supplierColumnIndex = cursor.getColumnIndex(StockContract.StockEntry.COLUMN_ITEM_SUPPLIER);

            // Extract out the value from the Cursor for the given column index
            String name = cursor.getString(nameColumnIndex);
            String price = cursor.getString(priceColumnIndex);
            int quantity = cursor.getInt(quantityColumnIndex);
            String supplier = cursor.getString(supplierColumnIndex);

            // Update the views on the screen with the values from the database
            mItemEditText.setText(name);
            mQuantityEditText.setText(quantity);
            mSupplierEditText.setText(supplier);
            mPriceEditText.setText(price);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        mItemEditText.setText("");
        mQuantityEditText.setText("");
        mSupplierEditText.setText(0);
        mPriceEditText.setText("");
    }

}
