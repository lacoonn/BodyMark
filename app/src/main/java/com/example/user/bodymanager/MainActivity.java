package com.example.user.bodymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

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
        v.todayadapter = new TodayListViewAdapter();
        v.todayListView.setAdapter(v.todayadapter);

        readInfo();
//=========================================|

        setMuscleExercise();
        readDamage();
        applyPNG();

    }

    public void onClick(View view) {
        Intent intent1 = new Intent(MainActivity.this, ExerciseActivity.class);
        Intent intent2 = new Intent(MainActivity.this, CalenderActivity.class);
        Intent intent3 = new Intent(MainActivity.this, CustomActivity.class);
        Intent intent4 = new Intent(MainActivity.this, SettingsActivity.class);

        switch (view.getId()) {
            case R.id.main_btnBody:
                //setBodygraphColor(R.id.main_bodygraph_chest, "bodygraph_" + R.id.main_bodygraph_chest, R.drawable.chest_yellow);
                intent1.putExtra("data","몸 운동");
                startActivity(intent1);
                break;
            case R.id.main_btnLeftarm:
                //setBodygraphColor(R.id.main_bodygraph_biceps, "bodygraph_" + R.id.main_bodygraph_biceps, R.drawable.biceps_yellow);
                intent1.putExtra("data","팔 운동");
                startActivity(intent1);
                break;
            case R.id.main_btnRightarm:
                //setBodygraphColor(R.id.main_bodygraph_triceps, "bodygraph_" + R.id.main_bodygraph_triceps, R.drawable.triceps_yellow);
                intent1.putExtra("data","팔 운동");
                startActivity(intent1);
                break;
            case R.id.main_btnLowerbody:
                //setBodygraphColor(R.id.main_bodygraph_shoulders, "bodygraph_" + R.id.main_bodygraph_shoulders, R.drawable.shoulders_yellow);
                intent1.putExtra("data","다리 운동");
                startActivity(intent1);
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



    public void onClickExercise(View view) {
        Variables v = (Variables)getApplication();

        int id = view.getId();
        Toast.makeText(this, "운동", Toast.LENGTH_SHORT).show();

    }


}
