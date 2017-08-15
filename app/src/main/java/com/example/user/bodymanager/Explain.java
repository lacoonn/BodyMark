package com.example.user.bodymanager;


import android.graphics.drawable.Drawable;

/**
 * Created by user on 2017-06-19.
 */

public class Explain {
    private Drawable image;
    private String text;
    private boolean vis;
    private String color = "black";

    Explain(){};
    Explain(Drawable img, String t, boolean v)
    {
        image = img;
        text = t;
        vis = v;
    }

    Explain(Drawable img, String t, boolean v, String c)
    {
        image = img;
        text = t;
        vis = v;
        color = c;
    }

    public void setVis(boolean vis) {
        this.vis = vis;
    }

    public boolean isVis() {
        return vis;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public Drawable getImage() {
        return image;
    }
    public String getText() {
        return text;
    }
    public String getColor() {
        return this.color;
    }
}
