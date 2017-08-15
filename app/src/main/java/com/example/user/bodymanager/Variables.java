package com.example.user.bodymanager;

import android.app.Application;
import android.content.Context;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.user.bodymanager.BodygraphActivity.bodygraphDrawable;


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

    ListView exerciseListView;
    ExerciseActivity.ExerciseListViewAdapter exerciseAdapter;

    boolean[] checkBoxBody;
    boolean[] checkBoxArms;
    boolean[] checkBoxLegs;

    static public int gender;
    static public int age;
    static public int weight;
    static public int height;

    private int[] savedPreviousDamage = new int[bodygraphDrawable.length];
    //-------------------------function declaration---------------------

    public  ExerciseManager getExManager() {
        return exManager;
    }

    public  MuscleExerciseManager getMeManager() {
        return meManager;
    }

    public ArrayList<String> getSelectedExerciseList() {
        return SelectedExerciseList;
    }

    public void SelectExercise(String n){
        SelectedExerciseList.add(n);
    }
    public void removeArrayList(String n){
        SelectedExerciseList.remove(n);
    }

    public void updateTodayExerciseList() {
        todayExerciseList.clearExerciseList();
        for(String i : SelectedExerciseList) {
            todayExerciseList.addExercise(new Exercise(i));
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

    //MainActivity의 '오늘의 운동' 세팅
    public void updateMainListView() {
        // 메인 ListView에 selectedExerciseList의 데이터를 추가합니다
        ArrayList<String> temp = getSelectedExerciseList();
        todayAdapter.clearItem();
        for(String i : temp) {
            todayAdapter.addItem(i);
        }

        todayAdapter.notifyDataSetChanged();

        // 메인 ListView를 업데이트 할 때 todayExerciseList를 업데이트하고 파일로 저장합니다.
        updateTodayExerciseList();
        saveTodayExerciseListToFile();
    }

    //ExerciseActivity의 운동 선택창 세팅
    public void updateExerciseListView(ArrayList<String> exerciseName, String Part) {
        //checkBox에 해당하는 이름을 인자로 받는다

        // ExerciseListView에  데이터를 추가합니다------------------------------------------
        exerciseAdapter.clearItem();
        for(String i : exerciseName) {
            exerciseAdapter.addItem(i);
        }

        // 체크 여부 상태 로딩
        if(Part.equals("body"))
            exerciseAdapter.checkBoxState = checkBoxBody;
        else if(Part.equals("arm"))
            exerciseAdapter.checkBoxState = checkBoxArms;
        else if(Part.equals("leg"))
            exerciseAdapter.checkBoxState = checkBoxLegs;

        exerciseAdapter.notifyDataSetChanged();
    }   //oncreate의 운동.txt 읽어오는 구간에 ArrayList<String>을 추가하여 모든 운동을 다 읽으면 인자(exerciseName)로 준다.

    //------------------------- getter & setter ---------------------

    public int[] getSavedPreviousDamage() {
        return savedPreviousDamage;
    }

    public void setsavedPreviousDamage(int[] damage, int day_passed) {
        //deep copy
        //
        for(int i = 0; i<damage.length; i++) {
            damage[i] = damage[i] * (3 - day_passed) / 3;
            this.savedPreviousDamage[i] += damage[i];
        }
    }
}