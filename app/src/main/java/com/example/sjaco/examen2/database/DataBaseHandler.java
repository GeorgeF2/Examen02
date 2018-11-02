package com.example.sjaco.examen2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sjaco.examen2.tools.Constant;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyProducts.db";
    private static final int DATABASE_VERSION = 1;
    private static DataBaseHandler dataBaseHandler;

    // Table names
    static final String TABLE_STORE = "store";
    static final String TABLE_PRODUCT = "product";
    static final String TABLE_CATEGORY = "category";
    static final String TABLE_CITY = "city";
    static final String TABLE_STORE_PRODUCTS = "storeProduct";

    // Columns Store
    static final String KEY_STORE_ID = "idStore";
    static final String KEY_STORE_NAME = "name";
    static final String KEY_STORE_PHONE = "phone";
    static final String KEY_STORE_CITY = "idCity";
    static final String KEY_STORE_THUMBNAIL = "thumbnail";
    static final String KEY_STORE_LAT = "latitude";
    static final String KEY_STORE_LNG = "longitude";

    // Columns Cities
    static final String KEY_CITY_ID = "idCity";
    static final String KEY_CITY_NAME = "name";

    // Columns Category
    static final String KEY_CATEGORY_ID = "idCategory";
    static final String KEY_CATEGORY_NAME = "name";

    // Columns Products
    static final String KEY_PRODUCT_ID = "idProduct";
    static final String KEY_PRODUCT_TITLE = "name";
    static final String KEY_PRODUCT_IMAGE = "image";

    //Columns Store Products
    private static final String KEY_STORE_PRODUCT_ID = "id";

    private DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context) {
        if (dataBaseHandler == null) {
            dataBaseHandler = new DataBaseHandler(context);
        }
        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CITY_TABLE = "CREATE TABLE " + TABLE_CITY + "("
                + KEY_CITY_ID + " INTEGER PRIMARY KEY,"
                + KEY_CITY_NAME + " TEXT)";
        db.execSQL(CREATE_CITY_TABLE);

        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
                + KEY_CATEGORY_ID + " INTEGER PRIMARY KEY,"
                + KEY_CATEGORY_NAME + " TEXT)";
        db.execSQL(CREATE_CATEGORY_TABLE);

        String CREATE_STORE_TABLE = "CREATE TABLE " + TABLE_STORE + "("
                + KEY_STORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_STORE_NAME + " TEXT,"
                + KEY_STORE_PHONE + " TEXT,"
                + KEY_STORE_CITY + " INTEGER,"
                + KEY_STORE_THUMBNAIL + " INTEGER,"
                + KEY_STORE_LAT + " DOUBLE,"
                + KEY_STORE_LNG + " DOUBLE)";
        db.execSQL(CREATE_STORE_TABLE);

        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
                + KEY_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PRODUCT_TITLE + " TEXT,"
                + KEY_PRODUCT_IMAGE + " INTEGER,"
                + KEY_CATEGORY_ID + " INTEGER)";
        db.execSQL(CREATE_PRODUCT_TABLE);

        String CREATE_STORE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_STORE_PRODUCTS + "("
                + KEY_STORE_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_STORE_ID + " INTEGER,"
                + KEY_PRODUCT_ID + " INTEGER)";
        db.execSQL(CREATE_STORE_PRODUCTS_TABLE);

        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (1, 'El Salto')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (2, 'Guadalajara')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (3, 'Ixtlahuacán de los Membrillos')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (4, 'Juanacatlán')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (5, 'San Pedro Tlaquepaque')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (6, 'Tlajomulco')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (7, 'Tonalá')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (8, 'Zapopan')");

        db.execSQL("INSERT INTO " + TABLE_CATEGORY + " (" + KEY_CATEGORY_ID + "," + KEY_CATEGORY_NAME + ") VALUES (" + Constant.FRAGMENT_TECHNOLOGY + ",'TECHNOLOGY')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + " (" + KEY_CATEGORY_ID + "," + KEY_CATEGORY_NAME + ") VALUES (" + Constant.FRAGMENT_HOME + ",'HOME')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + " (" + KEY_CATEGORY_ID + "," + KEY_CATEGORY_NAME + ") VALUES (" + Constant.FRAGMENT_ELECTRONICS + ",'ELECTRONICS')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
