package com.example.user.bodymanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends BodygraphActivity {

    Variables v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, SplashActivity.class));

//variables initializing ==================|
        v = (Variables) getApplication();


        Muscle []m = new Muscle[3];
        m[0] = new Muscle("bicep");
        m[1] = new Muscle("chest");
        m[2] = new Muscle("deltoid");


        m[0].setDamage(150);
        m[1].setDamage(255);
        m[2].setDamage(455);


        m[0].setResource_num(bodygraphDrawable[0]);
        m[1].setResource_num(bodygraphDrawable[1]);
        m[2].setResource_num(bodygraphDrawable[2]);

        v.setMuscles(m);
        v.todayListView = (ListView) findViewById(R.id.main_todaylistview);
        v.todayadapter = new TodayListViewAdapter();
        v.todayListView.setAdapter(v.todayadapter);

//=========================================|

        createBMP(this);
        applyBMP();



    }

    public void onClick(View view) {
        Intent intent1 = new Intent(MainActivity.this, ExerciseActivity.class);
        Intent intent2 = new Intent(MainActivity.this, CalenderActivity.class);
        Intent intent3 = new Intent(MainActivity.this, CustomActivity.class);
        Intent intent4 = new Intent(MainActivity.this, SettingsActivity.class);

        switch (view.getId()) {
            case R.id.main_btnBody:
                Toast.makeText(this, "몸입니다", Toast.LENGTH_SHORT).show();
                //setBodygraphColor(R.id.main_bodygraph_chest, "bodygraph_" + R.id.main_bodygraph_chest, R.drawable.chest_yellow);
                startActivity(intent1);
                //intent1.putExtra("data","몸 운동");

                v.todayadapter.addItem("exercise123") ;

                break;
            case R.id.main_btnLeftarm:
                Toast.makeText(this, "팔입니다", Toast.LENGTH_SHORT).show();
                //setBodygraphColor(R.id.main_bodygraph_biceps, "bodygraph_" + R.id.main_bodygraph_biceps, R.drawable.biceps_yellow);
                startActivity(intent1);
                intent1.putExtra("data","팔 운동");
                break;
            case R.id.main_btnRightarm:
                Toast.makeText(this, "팔입니다", Toast.LENGTH_SHORT).show();
                //setBodygraphColor(R.id.main_bodygraph_triceps, "bodygraph_" + R.id.main_bodygraph_triceps, R.drawable.triceps_yellow);
                startActivity(intent1);
                intent1.putExtra("data","팔 운동");
                break;
            case R.id.main_btnLowerbody:
                Toast.makeText(this, "다리입니다", Toast.LENGTH_SHORT).show();
                //setBodygraphColor(R.id.main_bodygraph_shoulders, "bodygraph_" + R.id.main_bodygraph_shoulders, R.drawable.shoulders_yellow);
                startActivity(intent1);
                intent1.putExtra("data","다리 운동");
                break;
            case R.id.main_btnCalendar:
                Toast.makeText(this, "운동 계획표", Toast.LENGTH_SHORT).show();
                startActivity(intent2);
                break;
            case R.id.main_btnCustom:
                Toast.makeText(this, "나만의 운동", Toast.LENGTH_SHORT).show();
                startActivity(intent3);
                break;
            case R.id.main_btnSettings:
                Toast.makeText(this, "설정", Toast.LENGTH_SHORT).show();
                startActivity(intent4);
                break;
            case R.id.main_btnReverse:
                changeVisibility();
                break;
        }
    }

    public void onClickExercise(View view) {
        Variables v = (Variables)getApplication();

        int id = view.getId();
        Toast.makeText(this, "운동", Toast.LENGTH_SHORT).show();

    }


}
