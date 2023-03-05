package fr.aymane.myapplication.models;

import android.net.Uri;

public class Item {
    private String name;
    private String nikcname;
    private String phone;
    private String email;
    private Uri img;
    private String genre;

    public Item(String name, String nikcname, String phone, String email, Uri img, String gnr) {
        this.name = name;
        this.nikcname = nikcname;
        this.phone = phone;
        this.email = email;
        this.img = img;
        this.genre = gnr;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public String getNikcname() {
        return nikcname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Uri getImg() {
        return img;
    }
}
