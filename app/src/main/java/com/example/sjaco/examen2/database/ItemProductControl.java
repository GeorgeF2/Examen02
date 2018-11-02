package com.example.sjaco.examen2.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sjaco.examen2.beans.Category;
import com.example.sjaco.examen2.beans.ItemProduct;
import com.example.sjaco.examen2.tools.Constant;

import java.util.ArrayList;

public class ItemProductControl {

    public void addItemProduct(ItemProduct itemProduct, DataBaseHandler dh) {
        long inserted;
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_PRODUCT_TITLE, itemProduct.getTitle());
        values.put(DataBaseHandler.KEY_PRODUCT_IMAGE, itemProduct.getImage());
        values.put(DataBaseHandler.KEY_CATEGORY_ID, itemProduct.getCategory().getId());

        // Inserting Product
        inserted = db.insert(DataBaseHandler.TABLE_PRODUCT, null, values);

        values.clear();

        // Relate a Product into a Store
        values.put(DataBaseHandler.KEY_PRODUCT_ID, inserted);
        values.put(DataBaseHandler.KEY_STORE_ID, itemProduct.getStore().getId());
        db.insert(DataBaseHandler.TABLE_STORE_PRODUCTS, null, values);

        // Closing database connection
        try {
            db.close();
        } catch (Exception e) {
            Log.v(Constant.APP_NAME, e.getMessage());
        }
    }

    public ArrayList<ItemProduct> getItemProductsByCategory (int idCategory, DataBaseHandler dh){
        ArrayList<ItemProduct> products = new ArrayList<>();

        String selectQuery = "SELECT  I." + DataBaseHandler.KEY_PRODUCT_ID + ","
                + "I." + DataBaseHandler.KEY_PRODUCT_TITLE + ","
                + "I." + DataBaseHandler.KEY_PRODUCT_IMAGE + ","
                + "S." + DataBaseHandler.KEY_STORE_ID + ","
                + "I." + DataBaseHandler.KEY_CATEGORY_ID + ","
                + "C." + DataBaseHandler.KEY_CATEGORY_NAME + " FROM "
                + DataBaseHandler.TABLE_PRODUCT + " I, "
                + DataBaseHandler.TABLE_STORE_PRODUCTS + " S, "
                + DataBaseHandler.TABLE_CATEGORY + " C WHERE I."
                + DataBaseHandler.KEY_CATEGORY_ID + " = C." + DataBaseHandler.KEY_CATEGORY_ID
                + " AND S." + DataBaseHandler.KEY_PRODUCT_ID + " = I." + DataBaseHandler.KEY_PRODUCT_ID
                + " AND I." + DataBaseHandler.KEY_CATEGORY_ID + " = " + idCategory;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        StoreControl storeControl = new StoreControl();
        while (cursor.moveToNext()) {
            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setCode(cursor.getInt(0));
            itemProduct.setTitle(cursor.getString(1));
            itemProduct.setImage(cursor.getInt(2));

            Category category = new Category();
            category.setId(cursor.getInt(4));
            category.setName(cursor.getString(5));
            itemProduct.setCategory(category);

            itemProduct.setStore(storeControl.getStoreById(cursor.getInt(3), dh));
            products.add(itemProduct);
        }
        try {
            cursor.close();
            db.close();
        } catch (Exception e) {
            Log.v(Constant.APP_NAME, e.getMessage());
        }

        return products;
    }

    public ArrayList<ItemProduct> getItemProducts (DataBaseHandler dh){
        ArrayList<ItemProduct> products = new ArrayList<>();

        String selectQuery = "SELECT  I." + DataBaseHandler.KEY_PRODUCT_ID + ","
                + "I." + DataBaseHandler.KEY_PRODUCT_TITLE + ","
                + "I." + DataBaseHandler.KEY_PRODUCT_IMAGE + ","
                + "S." + DataBaseHandler.KEY_STORE_ID + ","
                + "I." + DataBaseHandler.KEY_CATEGORY_ID + ","
                + "C." + DataBaseHandler.KEY_CATEGORY_NAME + " FROM "
                + DataBaseHandler.TABLE_PRODUCT + " I, "
                + DataBaseHandler.TABLE_STORE_PRODUCTS + " S, "
                + DataBaseHandler.TABLE_CATEGORY + " C WHERE I."
                + DataBaseHandler.KEY_CATEGORY_ID + " = C." + DataBaseHandler.KEY_CATEGORY_ID
                + " AND S." + DataBaseHandler.KEY_PRODUCT_ID + " = I." + DataBaseHandler.KEY_PRODUCT_ID;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        StoreControl storeControl = new StoreControl();
        while (cursor.moveToNext()) {
            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setCode(cursor.getInt(0));
            itemProduct.setTitle(cursor.getString(1));
            itemProduct.setImage(cursor.getInt(2));

            Category category = new Category();
            category.setId(cursor.getInt(4));
            category.setName(cursor.getString(5));
            itemProduct.setCategory(category);

            itemProduct.setStore(storeControl.getStoreById(cursor.getInt(3), dh));
            products.add(itemProduct);
        }
        try {
            cursor.close();
            db.close();
        } catch (Exception e) {
            Log.v(Constant.APP_NAME, e.getMessage());
        }

        return products;
    }


}
