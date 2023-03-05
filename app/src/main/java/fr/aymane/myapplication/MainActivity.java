package fr.aymane.myapplication;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import fr.aymane.myapplication.models.Item;

public class MainActivity extends AppCompatActivity {

    /* --------------------- Statics and final variables --------------------- */
    public static final int PICK_IMAGE = 1;
    final Calendar myCalendar = Calendar.getInstance();
    
    /* --------------------- Globals variables --------------------- */
    boolean darkMode = true;
    EditText inputDate;
    ImageView profilePicture;
    Uri img;
    /* --------------------- END --------------------- */

    /* --------------------- Functions --------------------- */
    //Sends an SMS message to another device
    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber)));
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        inputDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    /* --------------------- END --------------------- */
    
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* --------------------- inputs cases --------------------- */
        EditText inputNickname = findViewById(R.id.editPseudo);
        EditText inputName = findViewById(R.id.editPremon);
        EditText inputPhone = findViewById(R.id.editTelephone);
        EditText inputEmail = findViewById(R.id.editMail);
        RadioButton rbMen = findViewById(R.id.radioButtonHomme);
        RadioButton rbWomen = findViewById(R.id.radioButtonFemme);
        inputDate = findViewById(R.id.editDate);
        /* --------------------- END --------------------- */
        
        /* --------------------- views cases --------------------- */
        profilePicture = findViewById(R.id.imageView5);
        //TextView title = findViewById(R.id.tvPseudo);

        /* --------------------- END --------------------- */
        
        /* --------------------- buttons --------------------- */
        Button btnAddToList = findViewById(R.id.btnAddToList);
        ImageView btnBack = findViewById(R.id.btnBack);
        /* --------------------- END --------------------- */

        /* --------------------- auther elements --------------------- */
        ConstraintLayout mainView = findViewById(R.id.btn_back);
        ToggleButton toggleGender = findViewById(R.id.darkMode);
        /* --------------------- END --------------------- */

        /* --------------------- permissions --------------------- */
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED ) {
            Log.d("PLAYGROUND", "Permission is not granted, requesting");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);

        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
            Log.d("PLAYGROUND", "Permission is not granted, requesting");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 123);
        }
        else {
            Log.d("PLAYGROUND", "Permission is granted");
        }
        /* --------------------- END --------------------- */

        
        /* --------------------- on click --------------------- */

        /*btnSendB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gender choise
                String gender;
                if (rbMen.isChecked()) {
                    gender = "homme";
                } else if (rbWomen.isChecked()) {
                    gender = "femme";
                } else {
                    gender = "Vous n'avez pas entré de genre";
                }

                title.setText(inputNickname.getText().toString());

                sendSMS("0749887173", " Message to 0749887173 ");

                Pattern p = Pattern.compile("[a-zA-Z] [a-z[A-Z]]");
                Pattern p2 = Pattern.compile("");
                Matcher m = p.matcher(inputEmail.getText().toString());

                if(p.matcher(inputName.getText().toString()).matches() &&
                        p.matcher(inputEmail.getText().toString()).matches() &&
                        p.matcher(inputEmail.getText().toString()).matches() ) {
                    title.setText("bonne saisie");
                }

                Intent myIntent = new Intent(MainActivity.this, ActivityB.class);
                myIntent.putExtra("name", inputNickname.getText().toString());
                myIntent.putExtra("nickname", inputName.getText().toString());
                myIntent.putExtra("phone", inputPhone.getText().toString());
                myIntent.putExtra("mail", inputEmail.getText().toString());
                myIntent.putExtra("date", inputDate.getText().toString());
                myIntent.putExtra("gender", gender);
                startActivity(myIntent);
            }
        });*/
        /*btnSendC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setText(inputNickname.getText().toString());
                Intent myIntent = new Intent(MainActivity.this, ActivityC.class);
                startActivity(myIntent);
            }
        });*/

        btnAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String str = inputNickname.getText() + "-" + inputName.getText() + "-" +
                        inputPhone.getText() + "-" + inputEmail.getText() + "-" + inputDate.getText();

                if(rbMen.isChecked()){
                    str += "-homme";
                }

                else if(rbWomen.isChecked()){
                    str += "-femme";
                }

                else{
                    str += "- ";
                }
                /*
                FileOutputStream fos;
                ObjectOutputStream out;
                try {
                    fos = openFileOutput("saveFile", Context.MODE_PRIVATE);
                    out = new ObjectOutputStream(fos);
                    //Item item = new Item(inputNickname.getText().toString(), inputName.getText().toString(), inputPhone.getText().toString(), inputEmail.getText().toString(), inputDate.getText().toString());
                    out.writeObject(str);
                    out.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                inputNickname.setText("");
                inputName.setText("");
                inputPhone.setText("");
                inputEmail.setText("");
                inputDate.setText("");

                if(inputNickname.getText() != null || inputName.getText() != null || inputDate.getText() != null || inputPhone.getText() != null ||  inputEmail.getText() != null){

                    Toast.makeText(MainActivity.this, "Contact ajouté", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(MainActivity.this, ListActivity.class);
                    if(getIntent().getStringExtra("ListToForm") != null){
                        myIntent.putExtra("ListToForm", str + " ! " + getIntent().getStringExtra("ListToForm"));
                        Log.d("messageList", str + " ! "+ getIntent().getStringExtra("ListToForm"));
                        if(img != null){
                            myIntent.putExtra("image", img.toString());
                        }
                    }else{
                        myIntent.putExtra("ListToForm", str);
                        if(img != null){
                            myIntent.putExtra("image", img.toString());
                        }
                        Log.d("messageList", str);
                    }
                    startActivity(myIntent);
                    //setResult(RESULT_OK, myIntent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, " champs non remplis ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = inputNickname.getText() + "-" + inputName.getText() + "-" +
                        inputPhone.getText() + "-" + inputEmail.getText() + "-" + inputDate.getText();
                Intent myIntent = new Intent(MainActivity.this, FirstActivity.class);
                myIntent.putExtra("ListToForm", str);
                if(getIntent().getStringExtra("ListToForm") != null){
                    myIntent.putExtra("ListToForm", str + " ! " + getIntent().getStringExtra("ListToForm"));
                    Log.d("messageListkkk", str + " ! "+ getIntent().getStringExtra("ListToForm"));
                }else{
                    myIntent.putExtra("ListToForm", str);
                    Log.d("messageListkkk", str);
                }
                setResult(RESULT_OK, myIntent);
                finish();
            }
        });

        toggleGender.setText("DARK");
        toggleGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (darkMode) {
                    mainView.setBackgroundColor(Color.BLACK);
                    toggleGender.setBackgroundColor(Color.WHITE);
                    toggleGender.setTextColor(Color.BLACK);
                    toggleGender.setText("LIGTH");
                    inputNickname.setTextColor(Color.WHITE);
                    //title.setTextColor(Color.WHITE);
                    inputNickname.setBackgroundColor(Color.WHITE);

                } else {
                    mainView.setBackgroundColor(Color.WHITE);
                    toggleGender.setBackgroundColor(Color.BLACK);
                    toggleGender.setTextColor(Color.WHITE);
                    toggleGender.setText("DARK");
                }
                darkMode = !darkMode;
            }
        });
        // edit profile Picture
        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickIntent = new Intent(Intent.ACTION_PICK);
                pickIntent.setType("image/*");

                startActivityForResult(pickIntent, PICK_IMAGE);

            }
        });
        // Date
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        /* --------------------- END on click --------------------- */

        String messageB = getIntent().getStringExtra("keyB");
        String messageC = getIntent().getStringExtra("keyC");

        /*
        tvAnswerB.setText(messageB);
        tvAnswerC.setText(messageC);
        */
    }


    /* --------------------- Override --------------------- */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            profilePicture.setImageURI(data.getData());
            img = data.getData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("PLAYGROUND", "Permission has been granted");

            } else {
                Log.d("PLAYGROUND", "Permission has been denied or request cancelled");

            }
        }
    }
    /* --------------------- END Override --------------------- */
}

