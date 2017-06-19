package com.example.user.bodymanager;

import android.app.Application;

import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by jum on 2017-06-16.
 */

// Variables v = (Variables) getApplication()  으로 선언한 후, v에 저장된 변수를 사용 가능
public class Variables extends Application {
    static private Calendar calendar = Calendar.getInstance();

    static public final int  year = calendar.get(Calendar.YEAR);
    static public final int month = (calendar.get(Calendar.MONTH) + 1);
    static public final int day = calendar.get(Calendar.DAY_OF_MONTH);
    static public String path = "data/user/0/com.example.user.bodymanager/files/";
    private Muscle[] muscles;
    public ExerciseList todayExerciseList = new ExerciseList(year, month, day);
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