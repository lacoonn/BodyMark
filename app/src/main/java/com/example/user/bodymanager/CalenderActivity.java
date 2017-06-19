package com.example.user.bodymanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;
import android.content.Intent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by User on 2017-05-25.
 */

public class CalenderActivity extends BodygraphActivity {
    Context context = null;
    Variables v = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendarview);
        context = this;

        // 테스트를 위해 파일을 생성한다
        try {
            Toast.makeText(this, "15, 16, 17일 파일을 저장합니다!!!", Toast.LENGTH_SHORT).show();
            FileOutputStream fos = context.openFileOutput("20170615.bin", 0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ExerciseList el1 = new ExerciseList(2017, 06, 15);
            el1.addExercise(new Exercise("푸시업", 0, null, null, 0, null, null, null, null));
            el1.addExercise(new Exercise("윗몸일으키기", 0, null, null, 0, null, null, null, null));
            oos.writeObject(el1);
            fos.close();

            fos = context.openFileOutput("20170616.bin", 0);
            oos = new ObjectOutputStream(fos);
            ExerciseList el2 = new ExerciseList(2017, 06, 16);
            el2.addExercise(new Exercise("플랭크", 0, null, null, 0, null, null, null, null));
            el2.addExercise(new Exercise("벤치", 0, null, null, 0, null, null, null, null));
            oos.writeObject(el2);
            fos.close();

            fos = context.openFileOutput("20170617.bin", 0);
            oos = new ObjectOutputStream(fos);
            ExerciseList el3 = new ExerciseList(2017, 06, 17);
            el3.addExercise(new Exercise("물구나무", 0, null, null, 0, null, null, null, null));
            el3.addExercise(new Exercise("아몰랑", 0, null, null, 0, null, null, null, null));
            oos.writeObject(el3);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // CalendarView
        CalendarView calendar = (CalendarView)findViewById(R.id.calendar);
        CalendarView.OnDateChangeListener temp = new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                //CalendarPopup cp = new CalendarPopup(year, month, dayOfMonth);
                //startActivity(new Intent(CalenderActivity.this, cp.getClass()));
                Intent intent = new Intent(CalenderActivity.this, CalendarPopup.class);
                //Toast.makeText(CalenderActivity.this, year+"/"+(month+1)+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", dayOfMonth);
                startActivity(intent);
            }
        };
        calendar.setOnDateChangeListener(temp);
    }

}