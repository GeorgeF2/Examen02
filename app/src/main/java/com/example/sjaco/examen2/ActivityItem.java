package com.example.sjaco.examen2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sjaco.examen2.beans.Category;
import com.example.sjaco.examen2.beans.ItemProduct;
import com.example.sjaco.examen2.beans.Store;
import com.example.sjaco.examen2.database.CategoryControl;
import com.example.sjaco.examen2.database.DataBaseHandler;
import com.example.sjaco.examen2.database.ItemProductControl;
import com.example.sjaco.examen2.database.StoreControl;

import java.util.ArrayList;

public class ActivityItem extends AppCompatActivity {

    EditText title;
    Spinner categories, stores, images;
    Button save;
    DataBaseHandler dataBaseHandler;
    ItemProduct itemProduct;
    StoreControl storeControl;
    CategoryControl categoryControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        title = findViewById(R.id.activity_item_title);
        categories = findViewById(R.id.activity_item_category);
        stores = findViewById(R.id.activity_item_store);
        images = findViewById(R.id.activity_item_image);
        save = findViewById(R.id.activity_item_save);

        dataBaseHandler = DataBaseHandler.getInstance(this);
        itemProduct = new ItemProduct();

        categoryControl = new CategoryControl();
        ArrayList<Category> arrCategories = categoryControl.getCategories(dataBaseHandler);
        final ArrayAdapter<Category> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapter);

        storeControl = new StoreControl();
        ArrayList<Store> arrStores = storeControl.getStores(dataBaseHandler);
        final ArrayAdapter<Store> adapterStores =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrStores);
        adapterStores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stores.setAdapter(adapterStores);

        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemProduct.setCategory(adapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        stores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemProduct.setStore(adapterStores.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        images.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemProduct.setImage(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemProduct.setTitle(title.getText().toString());

                ItemProductControl itemProductControl = new ItemProductControl();
                itemProductControl.addItemProduct(itemProduct, dataBaseHandler);
                finish();
                //Toast.makeText(ActivityItem.this, itemProduct.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
