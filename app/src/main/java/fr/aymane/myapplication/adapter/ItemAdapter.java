package fr.aymane.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.aymane.myapplication.R;
import fr.aymane.myapplication.models.Item;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private List<Item> itemList;
    private LayoutInflater inflater;

    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Item getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_item, null);

        // get data abdout item
        Item currentItem = getItem(position);
        String itemName = currentItem.getName();
        String itemNickename = currentItem.getNikcname();
        String itemPhone = currentItem.getPhone();
        String itemMail = currentItem.getEmail();
        String gender = currentItem.getGenre();
        Uri itemImg = currentItem.getImg();

        // edit name TextView
        TextView itemNameView = convertView.findViewById(R.id.item_name);
        TextView itemEmailView = convertView.findViewById(R.id.item_email);
        TextView itemPhoneView = convertView.findViewById(R.id.item_phone);


        itemNameView.setText(itemName + " | " + itemNickename);
        itemEmailView.setText(itemMail);
        itemPhoneView.setText(itemPhone);

        // edit image TextView
        ImageView itemImageView = convertView.findViewById(R.id.item_icon);

        if(gender.equals("homme")){
            Log.d("hhh", "homme");
            int resId = context.getResources().getIdentifier("avatar_men","drawable", context.getPackageName());
            itemImageView.setImageResource(resId);
        }else if(gender.equals("femme")){
            Log.d("hhh", "femme");
            int resId = context.getResources().getIdentifier("avatar_women","drawable", context.getPackageName());
            itemImageView.setImageResource(resId);
        }else if(itemImg == null){
            //Log.d("hhh", gender);
            int resId = context.getResources().getIdentifier("ic_person","drawable", context.getPackageName());
            itemImageView.setImageResource(resId);
        }else{
            itemImageView.setImageURI(itemImg);
        }


        return convertView;
    }
}
