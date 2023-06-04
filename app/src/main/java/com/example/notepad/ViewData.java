package com.example.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewData extends AppCompatActivity {

    TextView textView;
    Intent intent;


    public void pressedBtn(View view) {
        intent.putExtra("data",getIntent().getIntExtra("itemContent",-1));
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        textView = findViewById(R.id.tvData);
        int dataPos = getIntent().getIntExtra("itemContent",-1);
        textView.setText(MainActivity.actualData.get(dataPos));
        intent = new Intent(this,addPageText.class);
    }
}