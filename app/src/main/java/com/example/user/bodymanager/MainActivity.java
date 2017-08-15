package com.example.user.bodymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;

public class MainActivity extends BodygraphActivity {

    Variables v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, SplashActivity.class));

//variables initializing ==================|
        v = (Variables) getApplication();

        v.todayListView = (ListView) findViewById(R.id.main_todaylistview);
        v.todayAdapter = new TodayListViewAdapter();
        v.todayListView.setAdapter(v.todayAdapter);

    //read user's info
        readInfo();
//=========================================|

        loadTodayExercise();
        v.updateMainListView();
        setMuscleExercise();
        readPreviousDamage();
        applyPNG();

        v.checkBoxBody = new boolean[v.getMeManager().searchPart("body").size()];
        v.checkBoxArms = new boolean[v.getMeManager().searchPart("arm").size()];
        v.checkBoxLegs = new boolean[v.getMeManager().searchPart("leg").size()];
    }

    public void onClick(View view) {
        Intent intent1 = new Intent(MainActivity.this, ExerciseActivity.class);
        Intent intent2 = new Intent(MainActivity.this, CalenderActivity.class);
        Intent intent3 = new Intent(MainActivity.this, CustomActivity.class);
        Intent intent4 = new Intent(MainActivity.this, SettingsActivity.class);

        switch (view.getId()) {
            case R.id.main_btnBody:
                //setBodygraphColor(R.id.main_bodygraph_chest, "bodygraph_" + R.id.main_bodygraph_chest, R.drawable.chest_yellow);
                intent1.putExtra("part","body");
                startActivity(intent1);
                break;
            case R.id.main_btnLeftarm:
                //setBodygraphColor(R.id.main_bodygraph_biceps, "bodygraph_" + R.id.main_bodygraph_biceps, R.drawable.biceps_yellow);
                intent1.putExtra("part","arm");
                startActivity(intent1);
                break;
            case R.id.main_btnRightarm:
                //setBodygraphColor(R.id.main_bodygraph_triceps, "bodygraph_" + R.id.main_bodygraph_triceps, R.drawable.triceps_yellow);
                intent1.putExtra("part","arm");
                startActivity(intent1);
                break;
            case R.id.main_btnLowerbody:
                //setBodygraphColor(R.id.main_bodygraph_shoulders, "bodygraph_" + R.id.main_bodygraph_shoulders, R.drawable.shoulders_yellow);
                intent1.putExtra("part","leg");
                startActivity(intent1);
                break;
            case R.id.main_btnCalendar:

                startActivity(intent2);
                break;
            case R.id.main_btnCustom:

                startActivity(intent3);
                break;
            case R.id.main_btnSettings:

                startActivity(intent4);
                break;
            case R.id.main_btnReverse:
                changeVisibility();
                break;
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        applyPNG();
    }

    @Override
    protected void onDestroy()
    {
        v.saveTodayExerciseListToFile();
        super.onDestroy();
    }


    public void onClickExercise(View view) {
        Variables v = (Variables)getApplication();
        int id = view.getId();
    }

    //앱이 처음 시작할 때 저장된 오늘의 운동을 읽어오는 함수
    public void loadTodayExercise()
    {
        ExerciseList exerciseList;
        v = (Variables)getApplication();
        Calendar date = Calendar.getInstance();

        //오늘날짜 파일 열기
        String openFileName = String.format("%04d%02d%02d.bin", date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH));

        try {
            FileInputStream fis = new FileInputStream(Variables.path + openFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            exerciseList = (ExerciseList) ois.readObject();

            v.getSelectedExerciseList().clear();
            for(Exercise e : exerciseList.getExerciseArray())
            {
                v.SelectExercise(e.getName());
            }

            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
