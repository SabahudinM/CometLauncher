package com.example.cometlauncher;

import android.graphics.drawable.Drawable;

public class AppObject {
    private String name, packageName;
    private final Drawable image;
    public AppObject(String packageName, String name, Drawable image){
        this.packageName=packageName;
        this.name=name;
        this.image=image;

    }

    public String getName(){return name;}
    public String getPackageName(){return packageName;}
    public Drawable getImage(){return image;}



}
