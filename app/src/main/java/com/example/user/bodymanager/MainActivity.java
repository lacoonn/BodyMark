package com.example.user.bodymanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends BodygraphActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //InitiateBodygraphColors();

//variables initializing ==================|
Variables v = (Variables) getApplication();

        Muscle []m = new Muscle[3];
        m[0] = new Muscle("abs_green");
        m[1] = new Muscle("adductor_green");
        m[2] = new Muscle("biceps_green");

        m[0].setDamage(50);
        m[1].setDamage(255);
        m[2].setDamage(455);

        m[0].setResource_num(bodygraphDrawable[0]);
        m[1].setResource_num(bodygraphDrawable[1]);
        m[2].setResource_num(bodygraphDrawable[2]);

        v.setMuscles(m);



//=========================================|
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, SplashActivity.class));

    //image view
        if(storageWritable()) {
            createBMP();
            Toast.makeText(this, "storage Access Succeed", Toast.LENGTH_SHORT).show();
        }
        else if(storageReadable()) {
            Toast.makeText(this, "storage write Denied", Toast.LENGTH_SHORT).show();
        }

        if(storageReadable()) {
        for(int i=0 ; i < m.length ; ++i) {
            File imgFile = new File(m[i].getName());
            ImageView imgview;

            if(imgFile.exists()){
                /*
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());  //파일에서 읽어옴
                ImageView myImage = (ImageView) findViewById(m[i].getResource_num());   //이미지 설정
                myImage.setImageBitmap(myBitmap);   //읽어온 bitmap으로 이미지 변경
                */
                try{
                    imgview = (ImageView)findViewById(m[i].getResource_num());
                    String imgpath = "data/data/com.example.user.body/files/"+ m[i].getResource_num();
                    Bitmap bm = BitmapFactory.decodeFile(imgpath);
                    imgview.setImageBitmap(bm);
                }catch(Exception e){Toast.makeText(getApplicationContext(), "load error", Toast.LENGTH_SHORT).show();}
            }
            else
                Toast.makeText(this, "Image doesn't exist", Toast.LENGTH_SHORT).show();

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
