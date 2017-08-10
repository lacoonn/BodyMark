package com.example.user.bodymanager;

import android.app.Application;
import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;


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
    private static ArrayList<String> SelectedExerciseList = new ArrayList<String>(); // 장바구니

    ListView todayListView;
    TodayListViewAdapter todayAdapter;
    ExerciseListViewAdapter exerciseAdapter;

    static public int gender;
    static public int age;
    static public int weight;
    static public int height;
    //-------------------------function declaration---------------------

    public  ExerciseManager getExManager() {
        return exManager;
    }

    public  MuscleExerciseManager getMeManager() {
        return meManager;
    }

    public ArrayList<String> getArrayList() {
        return SelectedExerciseList;
    }

    public void addArrayList(String n){
        SelectedExerciseList.add(n);
        Toast.makeText(this, n, Toast.LENGTH_SHORT).show();
    }
    public void removeArrayList(String n){
        SelectedExerciseList.remove(n);
    }

    public void updateTodayExerciseList() {
        todayExerciseList.clearExerciseList();
        for(String i : SelectedExerciseList) {
            //todayExerciseList.addExercise(exManager.searchName(i));
            todayExerciseList.addExercise(new Exercise(i, 0, null, null, 0, null, null, null, null));
        }
    }


    public Muscle[] getMuscles() {
        return muscles;
    }

    public void setMuscles(Muscle[] muscles) {
        this.muscles = muscles;
    }

    public void saveTodayExerciseListToFile() {
        Context context = this;
        FileOutputStream fos = null;
        try {
            String fileNameString = String.format("%04d%02d%02d.bin", year, month, day);
            fos = context.openFileOutput(fileNameString, 0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(todayExerciseList);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}