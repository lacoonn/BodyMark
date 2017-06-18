package com.example.user.bodymanager;

import java.util.ArrayList;

/**
 * Created by Seok on 2017-06-17.
 */

public class MuscleExercise {
    public MuscleExercise() {
    }
    public MuscleExercise(String n)
    {
        this.name = n;
    }
    public MuscleExercise(String n, ArrayList<Exercise> e, int m)
    {
        this.name = n;
        this.exer = e;
        this.Maxexer = m;
    }
    private ArrayList<Exercise> exer; //
    private String name; //
    private int Maxexer; //

    public ArrayList<Exercise> getExer() {
        return exer;
    }
    public String getName() {
        return name;
    }
    public int getMaxexer() {
        return Maxexer;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setExer(ArrayList<Exercise> exer) {
        this.exer = exer;
    }
    public void setMaxexer(int maxexer) {
        Maxexer = maxexer;
    }

}
