package com.example.user.bodymanager;


import android.graphics.drawable.Drawable;

/**
 * Created by user on 2017-06-19.
 */

public class Explain {
    private Drawable image;
    private String text;

    Explain(){};
    Explain(Drawable img, String t)
    {
        image = img;
        text = t;
    }


    public void setImage(Drawable image) {
        this.image = image;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Drawable getImage() {
        return image;
    }
    public String getText() {
        return text;
    }
}
