package com.example.user.bodymanager;

/**
 * Created by woochan on 2017-06-17.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CalendarPopup extends Activity {
    private int year;
    private int month;
    private int day;
    private Context context = null;
    ExerciseList exerciseList = null;

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.activity_calendar_popup);
        context = this;


        // 팝업창의 날짜를 가지고 온다.
        Intent intent = getIntent();
        year = intent.getIntExtra("year", 0);
        month = intent.getIntExtra("month", 0);
        day = intent.getIntExtra("day", 0);
        //Toast.makeText(CalendarPopup.this, year+"/"+(month+1)+"/"+day, Toast.LENGTH_SHORT).show();
        TextView date = (TextView) findViewById(R.id.textView);
        date.setText(year+"/"+(month+1)+"/"+day);


        // 읽어온 날짜를 이용해서 해당 날의 운동 목록을 가지고 온다
        //String openFileName = "" + year + (month + 1) + day + ".bin";
        String openFileName = String.format("%4d%02d%02d.bin", year, month + 1, day);
        try {
            //Toast.makeText(CalendarPopup.this, Variables.path + openFileName, Toast.LENGTH_SHORT).show();
            FileInputStream fis = new FileInputStream(Variables.path + openFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            //Toast.makeText(CalendarPopup.this, "오브젝트인풋스트림 생성", Toast.LENGTH_SHORT).show();
            exerciseList = (ExerciseList) ois.readObject();
            //Toast.makeText(CalendarPopup.this, "오브젝트인풋스트림에서 오브젝트 추출", Toast.LENGTH_SHORT).show();

            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // exerciseList가 null인지 검사하고 아니라면 포함된 운동의 이름을 꺼낸다
        ArrayList<String> LIST_MENU = new ArrayList<String>();
        if(exerciseList != null) {
            //Toast.makeText(CalendarPopup.this, "ExerciseList에서 운동 이름을 뽑아냅니다!", Toast.LENGTH_SHORT).show();
            for(int i = 0; i < exerciseList.getLength(); i++) {
                LIST_MENU.add(exerciseList.getExerciseByIndex(i).getName());
            }
        }
        else {
            Toast.makeText(CalendarPopup.this, "해당 날짜의 파일이 없습니다!!!", Toast.LENGTH_SHORT).show();
        }

        // 그날의 운동 목록을 출력하는 리스트뷰
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, LIST_MENU);
        ListView listview = (ListView) findViewById(R.id.exercise_list) ;
        listview.setAdapter(adapter) ;
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get TextView's Text.
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(CalendarPopup.this, strText, Toast.LENGTH_SHORT).show();

                // TODO : use strText
            }
        }) ;


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.popup_close :
                finish();
                break;
            case R.id.popup_duplicate:
                Toast.makeText(CalendarPopup.this, "위의 운동들을 오늘의 운동 목록에 복사합니다.", Toast.LENGTH_SHORT).show();
                Variables v = (Variables) getApplication();
                v.todayExerciseList.setExerciseArray(exerciseList.getExerciseArray());

                // todayExerciseList의 인자들을 arrayList에 복사한다 -------------------------------
                ArrayList<Exercise> tempExerciseList = v.todayExerciseList.getExerciseArray();
                v.getArrayList().clear();
                for(Exercise i : tempExerciseList) {
                    v.addArrayList(i.getName());
                }
                // updateListView를 호출한다 -------------------------------------------------------
                v.updateListView();

                // 액티비티 종료 -------------------------------------------------------------------
                finish();
                break;
        }
    }


}