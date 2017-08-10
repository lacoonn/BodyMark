package com.example.user.bodymanager;


import java.io.Serializable;
import java.util.ArrayList;

import static java.util.Collections.addAll;

/**
 * Created by Seok on 2017-06-17.
 */

public class Exercise implements Serializable {
    public Exercise(){};
    public Exercise(String n, int np, ArrayList<String> p, String s, int sp,ArrayList<String> se, String t, String k, String ti)
    {
        name = n;
        partNum = np;
        part = (ArrayList<String>)p.clone(); // deep copy
        simple = s;
        seqNum = sp;
        seq = (ArrayList<String>)se.clone();; // deep copy
        tip = t;
        kcal = k;
        tired = ti;
    }
    private String name;
    private int partNum;
    private ArrayList<String> part;
    private String simple;
    private int seqNum;
    private ArrayList<String> seq;
    private String tip;
    private String kcal;
    private String tired;

    public void setName(String n) {
        name = n;
    }
    public void setPartNum(int partNum) {
        this.partNum = partNum;
    }
    public void setPart(ArrayList<String> n) {
        part = n;
    }
    public void setSimple(String n) {
        simple = n;
    }
    public void setSeq(ArrayList<String> n) {
        seq = n;
    }
    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }
    public void setTip(String n) {
        tip = n;
    }
    public void setKcal(String n) {
        kcal = n;
    }
    public void setTired(String n) {
        tired = n;
    }
    public String getName(){
        return name;
    }
    public int getPartNum() {
        return partNum;
    }
    public ArrayList<String> getPart(){
        return part;
    }
    public String getSimple(){
        return simple;
    }
    public int getSeqNum() {
        return seqNum;
    }
    public ArrayList<String> getSeq(){
        return seq;
    }
    public String getTip(){
        return tip;
    }
    public String getKcal(){
        return kcal;
    }
    public String getTired(){
        return tired;
    }

}
