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


    //image view
        if(storageWritable()) {
            createBMP(this);
            Toast.makeText(this, "storage Access Succeed", Toast.LENGTH_SHORT).show();
        }
        else if(storageReadable()) {
            Toast.makeText(this, "storage write Denied", Toast.LENGTH_SHORT).show();
        }

        if(storageReadable()) {
        for(int i=0 ; i < m.length ; ++i) {
        ImageView imgview;
                /*
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());  //파일에서 읽어옴
                ImageView myImage = (ImageView) findViewById(m[i].getResource_num());   //이미지 설정
                myImage.setImageBitmap(myBitmap);   //읽어온 bitmap으로 이미지 변경
                */
                try{
                    imgview = (ImageView)findViewById(bodygraphId[i]);
                    String imgpath = "data/user/0/com.example.user.bodymanager/files/" + m[i].getName() + ".png";
                    Bitmap bm = BitmapFactory.decodeFile(imgpath);
                    imgview.setImageBitmap(bm);
                }catch(Exception e){Toast.makeText(getApplicationContext(), "load error", Toast.LENGTH_LONG).show();}


            //ImageView myImage = (ImageView) findViewById(m[i].getResource_num());
        }

        }
        else {
            Toast.makeText(this, "storage Access Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.main_btnBody:
                Toast.makeText(this, "몸입니다", Toast.LENGTH_SHORT).show();
                //setBodygraphColor(R.id.main_bodygraph_chest, "bodygraph_" + R.id.main_bodygraph_chest, R.drawable.chest_yellow);
                startActivity(new Intent(MainActivity.this, ExerciseActivity.class));
                v.todayadapter.addItem("exercise123") ;
                break;
            case R.id.main_btnLeftarm:
                Toast.makeText(this, "팔입니다", Toast.LENGTH_SHORT).show();
                //setBodygraphColor(R.id.main_bodygraph_biceps, "bodygraph_" + R.id.main_bodygraph_biceps, R.drawable.biceps_yellow);
                startActivity(new Intent(MainActivity.this, ExerciseActivity.class));
                break;
            case R.id.main_btnRightarm:
                Toast.makeText(this, "팔입니다", Toast.LENGTH_SHORT).show();
                //setBodygraphColor(R.id.main_bodygraph_triceps, "bodygraph_" + R.id.main_bodygraph_triceps, R.drawable.triceps_yellow);
                startActivity(new Intent(MainActivity.this, ExerciseActivity.class));
                break;
            case R.id.main_btnLowerbody:
                Toast.makeText(this, "다리입니다", Toast.LENGTH_SHORT).show();
                //setBodygraphColor(R.id.main_bodygraph_shoulders, "bodygraph_" + R.id.main_bodygraph_shoulders, R.drawable.shoulders_yellow);
                startActivity(new Intent(MainActivity.this, ExerciseActivity.class));
                break;
            case R.id.main_btnCalendar:
                Toast.makeText(this, "운동 계획표", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, CalenderActivity.class));
                break;
            case R.id.main_btnCustom:
                Toast.makeText(this, "나만의 운동", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, CustomActivity.class));
                break;
            case R.id.main_btnSettings:
                Toast.makeText(this, "설정", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
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
