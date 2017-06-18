package com.example.user.bodymanager;

/**
 * Created by jum on 2017-06-16.
 */
public class Muscle{

    private String name;

    private int damage; //0~510

    private int resource_num;

    public Muscle(String name)  //근육 object 생성
    {
        this.name = name;   //이름 설정
        //this.image = BitmapFactory.decodeFile("R.drawable." + this.name + ".png");
        //this.image = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/" + name + ".png");

    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getDamage()
    {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getResource_num() {
        return resource_num;
    }
    public void setResource_num(int resource_num) {
        this.resource_num = resource_num;
    }
}
