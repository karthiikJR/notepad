package com.example.notepad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Intent intent,intentView;
    ListView items;
    SharedPreferences sharedPreferences;


    public static ArrayList<String> addItems,actualData;
    public static ArrayAdapter arrayAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.newPage:
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "clicked nothing", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = findViewById(R.id.lvItems);
        addItems = new ArrayList<>();
        actualData = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,addItems);
        items.setAdapter(arrayAdapter);
        intentView = new Intent(this,ViewData.class);
        intent = new Intent(this, addPageText.class);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Set<String> itemsAdded = sharedPreferences.getStringSet("addItems", null);
        Set<String> fullItem = sharedPreferences.getStringSet("actualData", null);
        if (itemsAdded != null && fullItem!=null) {
            ArrayList<String> retrievedArrayList = new ArrayList<>(itemsAdded);
            addItems.addAll(retrievedArrayList);
            Collections.sort(addItems);
            ArrayList<String> retrievedArrayListActual = new ArrayList<>(fullItem);
            actualData.addAll(retrievedArrayListActual);
            Collections.sort(actualData);
            arrayAdapter.notifyDataSetChanged();
        }

        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intentView.putExtra("itemContent",position);
                startActivity(intentView);

            }
        });
    }
}