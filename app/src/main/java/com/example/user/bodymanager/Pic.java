package com.example.user.bodymanager;

/**
 * Created by user on 2017-06-19.
 */

public class Pic {
    private int[] pic = new int[4];
    private int Maxpic = 0;
    private String name;

    public Pic(){};
    public Pic(int[] pic, int Maxpic, String name)
    {
        this.pic = pic;
        this.Maxpic = Maxpic;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxpic(int maxpic) {
        Maxpic = maxpic;
    }

    public String getName() {
        return name;

    }

    public void addPic(int p)
    {
        pic[Maxpic++] = p;
    }
    public void setPic(int[] pic) {
        this.pic = pic;
    }

    public int getMaxpic() {
        return Maxpic;
    }

    public int getPic(int i) {
        return pic[i];
    }
}
