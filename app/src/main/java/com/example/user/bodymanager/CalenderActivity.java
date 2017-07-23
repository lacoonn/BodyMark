package com.example.user.bodymanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.CalendarView;

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
        v = (Variables) getApplication();

        // ?뚯뒪?몃? ?꾪빐 ?뚯씪???앹꽦?쒕떎
        try {
            FileOutputStream fos = context.openFileOutput("20170615.bin", 0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ExerciseList el1 = new ExerciseList(2017, 06, 15);
            el1.addExercise(v.getExManager().lists().get(0));
            el1.addExercise(v.getExManager().lists().get(1));
            ArrayList<Exercise> tr = el1.getExerciseArray();
            for(Exercise i : tr) {
                Log.d("濡쒓렇?앹꽦", "el1: " + i.getName());
            }
            //el1.addExercise(new Exercise("?몄떆??, 0, null, null, 0, null, null, null, null));
            //el1.addExercise(new Exercise("?쀫じ?쇱쑝?ㅺ린", 0, null, null, 0, null, null, null, null));
            oos.writeObject(el1);
            fos.close();

            fos = context.openFileOutput("20170616.bin", 0);
            oos = new ObjectOutputStream(fos);
            ExerciseList el2 = new ExerciseList(2017, 06, 16);
            el2.addExercise(v.getExManager().lists().get(1));
            el2.addExercise(v.getExManager().lists().get(2));
            tr = el2.getExerciseArray();
            for(Exercise i : tr) {
                Log.d("濡쒓렇?앹꽦", "el2: " + i.getName());
            }
            //el2.addExercise(new Exercise("?뚮옲??, 0, null, null, 0, null, null, null, null));
            //el2.addExercise(new Exercise("踰ㅼ튂", 0, null, null, 0, null, null, null, null));
            oos.writeObject(el2);
            fos.close();

            fos = context.openFileOutput("20170617.bin", 0);
            oos = new ObjectOutputStream(fos);
            ExerciseList el3 = new ExerciseList(2017, 06, 17);
            el3.addExercise(v.getExManager().lists().get(2));
            el3.addExercise(v.getExManager().lists().get(3));
            tr = el3.getExerciseArray();
            for(Exercise i : tr) {
                Log.d("濡쒓렇?앹꽦", "el3: " + i.getName());
            }
            //el3.addExercise(new Exercise("臾쇨뎄?섎Т", 0, null, null, 0, null, null, null, null));
            //el3.addExercise(new Exercise("?꾨ぐ??, 0, null, null, 0, null, null, null, null));
            oos.writeObject(el3);
            fos.close();

            fos = context.openFileOutput("20170618.bin", 0);
            oos = new ObjectOutputStream(fos);
            ExerciseList el4 = new ExerciseList(2017, 06, 18);
            el4.addExercise(v.getExManager().lists().get(3));
            el4.addExercise(v.getExManager().lists().get(4));
            tr = el4.getExerciseArray();
            for(Exercise i : tr) {
                Log.d("濡쒓렇?앹꽦", "el4: " + i.getName());
            }
            oos.writeObject(el4);
            fos.close();

            fos = context.openFileOutput("20170619.bin", 0);
            oos = new ObjectOutputStream(fos);
            ExerciseList el5 = new ExerciseList(2017, 06, 17);
            el5.addExercise(v.getExManager().lists().get(4));
            el5.addExercise(v.getExManager().lists().get(5));
            tr = el5.getExerciseArray();
            for(Exercise i : tr) {
                Log.d("濡쒓렇?앹꽦", "el5: " + i.getName());
            }
            oos.writeObject(el5);
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