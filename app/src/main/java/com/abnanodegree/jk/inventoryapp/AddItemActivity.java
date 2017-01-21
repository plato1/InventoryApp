package com.abnanodegree.jk.inventoryapp;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.abnanodegree.jk.inventoryapp.data.StockContract;

/**
 * Created on 1/19/2017.
 */

public class AddItemActivity extends AppCompatActivity {

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
        setContentView(R.layout.add_item);

        mItemEditText = (EditText) findViewById(R.id.add_item_name);
        mQuantityEditText = (EditText) findViewById(R.id.add_quantity);
        mSupplierEditText = (EditText) findViewById(R.id.add_supplier);
        mPriceEditText = (EditText) findViewById(R.id.add_price);

    }

    public void onClick_saveItem(View view) {

        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String itemString = mItemEditText.getText().toString().trim();
        String quantityString = mQuantityEditText.getText().toString().trim();
        String supplierString = mSupplierEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(StockContract.StockEntry.COLUMN_ITEM_NAME, itemString);
        values.put(StockContract.StockEntry.COLUMN_ITEM_QUANTITY, quantityString);
        values.put(StockContract.StockEntry.COLUMN_ITEM_SUPPLIER, supplierString);
        values.put(StockContract.StockEntry.COLUMN_ITEM_PRICE, priceString);

        // Validate user entry
        if ((TextUtils.isEmpty(itemString)) || (TextUtils.isEmpty(quantityString)) ||
            (TextUtils.isEmpty(priceString)) || (TextUtils.isEmpty(supplierString))) {
            Toast.makeText(this, getString(R.string.add_item_empty_field), Toast.LENGTH_SHORT).show();
            return;
        }

            // This is a NEW pet, so insert a new pet into the provider,
            // returning the content URI for the new pet.
            Uri newUri = getContentResolver().insert(StockContract.StockEntry.CONTENT_URI, values);

            // Show a toast message depending on whether or not the insertion was successful.
            if (newUri == null) {
                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, getString(R.string.add_item_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.add_item_successful),
                        Toast.LENGTH_SHORT).show();
            }

        finish();
    }

    public void onClick_cancel(View view) {
        finish();
    }


}
