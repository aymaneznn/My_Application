package fr.aymane.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import fr.aymane.myapplication.adapter.ItemAdapter;
import fr.aymane.myapplication.models.Item;

public class ListActivity extends AppCompatActivity {
    /* --------------------- Statics and final variables --------------------- */


    /* --------------------- Globals variables --------------------- */


    /* --------------------- END --------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        /* --------------------- buttons --------------------- */
        ImageView btnAdd = findViewById(R.id.btn_add);
        ImageView btnBack = findViewById(R.id.btnBack2);

        /* --------------------- END --------------------- */
        List<Item> itemsList = new ArrayList<>();
        ListView items_list_view = findViewById(R.id.items_list_view);




        String list = getIntent().getStringExtra("ListToForm");

        String image =  getIntent().getStringExtra("image");
        Uri img = null;
        if(image != null){
            img = Uri.parse(image);
        }

        String[] words;
        String[] words2;


        if(list != null) {

            words = list.split("!");

            for (int i = 0; i < words.length; i++) {
                words2 = words[i].split("-");
                itemsList.add(new Item(words2[0], words2[1], words2[2], words2[3], img, words2[5]));
            }
        }



        /* --------------------- onClick--------------------- */
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(ListActivity.this, MainActivity.class);
                myIntent.putExtra("ListToForm", list);
                //FOR RESSULT
                startActivity(myIntent);
                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ListActivity.this, FirstActivity.class);
                myIntent.putExtra("ListToForm", getIntent().getStringExtra("ListToForm"));
                //setResult(RESULT_OK, myIntent);
                startActivity(myIntent);
                finish();
            }
        });

        /* --------------------- END --------------------- */

        /*
        itemsList.add(new Item("ASM", "asm", nickname));
        itemsList.add(new Item("ASSE", "asse", nickname));
        itemsList.add(new Item("GIRONDINS", "girondins", nickname));
        itemsList.add(new Item("LOSC", "losc", nickname));
        itemsList.add(new Item("OL", "ol", nickname));
        itemsList.add(new Item("OM", "om", nickname));*/


        File directory = this.getFilesDir();
        File file = new File(directory, "saveFile");

        if(file.exists()){
            FileInputStream fis = null;
            ObjectInputStream in = null;
            try {
                fis = openFileInput("saveFile");
                in = new ObjectInputStream(fis);

            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        ListView itemListView = findViewById(R.id.items_list_view);
        itemListView.setAdapter(new ItemAdapter(this, itemsList));

        /*  ce bout de code permet la supprission mais il y a un probleme je ne sais pas comment le resoudre ça me met ça -> onKeyLongPress in class View cannot be applied to given types;*/

        /*
        items_list_view.onKeyLongPress(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(ListActivity.this).setTitle("Voulez vous supprimer ce contacte ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                items_list_view.removeViewAt(position);
                                items_list_view.notify();
                            }
                        }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                return false;
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String uri = data.getStringExtra("image");
        Log.e("image", uri);

    }
}

