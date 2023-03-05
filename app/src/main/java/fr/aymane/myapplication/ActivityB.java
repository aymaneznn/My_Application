package fr.aymane.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        String name = getIntent().getStringExtra("name");
        String nickname = getIntent().getStringExtra("nickname");
        String phone = getIntent().getStringExtra("phone");
        String mail = getIntent().getStringExtra("mail");
        String date = getIntent().getStringExtra("date");
        String gender = getIntent().getStringExtra("gender");

        TextView txtPseudo = findViewById(R.id.tvPage2);
        txtPseudo.setText(
                "Nom : " + name + "\n" +
                        "Prenom : " + nickname + "\n" +
                        "Telephone : " + phone + "\n" +
                        "Mail : " + mail + "\n" +
                        "Date : " + date + "\n" +
                        "Genre : " + gender + "\n"
        );

        EditText editActivity = findViewById(R.id.editActivityB);
        Button btnDone = findViewById(R.id.btnDoneB);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(ActivityB.this, fr.aymane.myapplication.MainActivity.class);
                myIntent.putExtra("keyB", editActivity.getText().toString());
                startActivity(myIntent);
            }
        });

    }
}