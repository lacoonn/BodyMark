package com.example.user.bodymanager;


import android.graphics.drawable.Drawable;

/**
 * Created by user on 2017-06-19.
 */

public class Explain {
    private Drawable image;
    private String text;
    private boolean vis;

    Explain(){};
    Explain(Drawable img, String t, boolean v)
    {
        image = img;
        text = t;
        vis = v;
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
    public Drawable getImage() {
        return image;
    }
    public String getText() {
        return text;
    }
}
