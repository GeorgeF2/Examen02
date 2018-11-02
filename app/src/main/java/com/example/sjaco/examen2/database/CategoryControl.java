package com.example.sjaco.examen2.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.sjaco.examen2.beans.Category;
import com.example.sjaco.examen2.tools.Constant;

import java.util.ArrayList;



public class CategoryControl {
    public ArrayList<Category> getCategories(DataBaseHandler dh) {
        ArrayList<Category> categories = new ArrayList<>();
        String selectQuery = "SELECT  " + DataBaseHandler.KEY_CATEGORY_ID + ","
                 + DataBaseHandler.KEY_CATEGORY_NAME + " FROM "
                 + DataBaseHandler.TABLE_CATEGORY;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
        }
        try {
            cursor.close();
            db.close();
        } catch (Exception e) {
            Log.v(Constant.APP_NAME, e.getMessage());
        }
        return categories;
    }
}
