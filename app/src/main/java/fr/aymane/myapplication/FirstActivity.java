package fr.aymane.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class FirstActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //String list = getIntent().getStringExtra("ListToForm");

        ImageView btnAdd = findViewById(R.id.btnAddActivity);
        ImageView btnList = findViewById(R.id.btnListActivity);
        String list = getIntent().getStringExtra("ListToForm");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(FirstActivity.this, MainActivity.class);
                myIntent.putExtra("ListToForm", getIntent().getStringExtra("ListToForm"));
                startActivity(myIntent);
            }
        });


        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(FirstActivity.this, ListActivity.class);
                myIntent.putExtra("ListToForm", getIntent().getStringExtra("ListToForm"));
                startActivity(myIntent);
            }
        });
    }
}