package fr.aymane.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        EditText editActivity = findViewById(R.id.editActivityB);
        Button btnDone = findViewById(R.id.btnDoneB);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(ActivityC.this, fr.aymane.myapplication.MainActivity.class);
                myIntent.putExtra("keyC", editActivity.getText().toString());
                startActivity(myIntent);
            }
        });
    }
}