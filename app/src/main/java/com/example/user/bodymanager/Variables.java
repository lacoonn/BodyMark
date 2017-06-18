package com.example.user.bodymanager;

import android.app.Application;

import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by jum on 2017-06-16.
 */

public class Variables extends Application {
//Variables v = (Variables) getApplication()  으로 선언한 후, v에 저장된 변수를 사용 가능


    static public String path = "data/user/0/com.example.user.bodymanager/files/";
    private Muscle[] muscles;
    public ExerciseList todayExerciseList = new ExerciseList();
    private static ExerciseManager exManager = ExerciseManager.getInstance();
    private static MuscleExerciseManager meManager = MuscleExerciseManager.getInstance();
    private ArrayList<String> arrayList = new ArrayList<String>(); // 장바구니

    public  ExerciseManager getExManager() {
        return exManager;
    }

    public  MuscleExerciseManager getMeManager() {
        return meManager;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void addArrayList(String n){
        arrayList.add(n);
        Toast.makeText(this, n, Toast.LENGTH_SHORT).show();
    }
    public void removeArrayList(String n){
        arrayList.remove(n);
    }

    


    public Muscle[] getMuscles() {
        return muscles;
    }

    public void setMuscles(Muscle[] muscles) {
        this.muscles = muscles;
    }
}