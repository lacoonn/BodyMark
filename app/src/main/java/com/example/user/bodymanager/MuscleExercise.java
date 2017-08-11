package com.example.user.bodymanager;

import java.util.ArrayList;

/**
 * Created by Seok on 2017-06-17.
 */

public class MuscleExercise {
    public MuscleExercise() {
    }
    public MuscleExercise(String m)
    {
        this.muscle = m;
    }
    public MuscleExercise(String m, String p, ArrayList<Exercise> e, int max)
    {
        this.muscle = m;
        this.part = p;
        this.exer = e;
        this.Maxexer = max;
    }
    private ArrayList<Exercise> exer; //
    private String muscle; //
    private String part;
    private int Maxexer; //



    //getter setter

    public ArrayList<Exercise> getExer() {
        return exer;
    }
    public String getMuscle() {
        return muscle;
    }
    public int getMaxexer() {
        return Maxexer;
    }
    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }
    public void setExer(ArrayList<Exercise> exer) {
        this.exer = exer;
    }
    public void setMaxexer(int maxexer) {
        Maxexer = maxexer;
    }
    public String getPart() {
        return part;
    }
    public void setPart(String part) {
        this.part = part;
    }
}
