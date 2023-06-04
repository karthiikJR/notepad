package com.example.notepad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class addPageText extends AppCompatActivity {

    EditText editText;
    Button btn;
    SharedPreferences sharedPreferences;

    int pos;

    public void btnPressed(View view) {
        if(pos==-1) {
            if(!editText.getText().toString().isEmpty()) {
                String text = editText.getText().toString();
                MainActivity.actualData.add(text);
                String[] insertTextArr = text.split(" ");
                MainActivity.addItems.add(insertTextArr[0]);
                MainActivity.arrayAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                try{
                    Thread.sleep(1000);
                }catch(Exception e) {}
            }
        }
        else {
            MainActivity.actualData.set(pos,editText.getText().toString());
            Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show();
            MainActivity.arrayAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            try{
                Thread.sleep(1000);
            }catch(Exception e) {}
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("addItems", new HashSet<>(MainActivity.addItems));
        editor.putStringSet("actualData", new HashSet<>(MainActivity.actualData));
        editor.apply();

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page_text);
        editText = findViewById(R.id.etText);
        btn = findViewById(R.id.btnAddOrUpdate);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        pos = getIntent().getIntExtra("data",-1);
        if(pos!=-1) {
            btn.setText("UPDATE");
            editText.setText(MainActivity.actualData.get(pos));
        }
    }
}