package com.example.user.bodymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;
import android.content.Intent;

/**
 * Created by User on 2017-05-25.
 */

public class CalenderActivity extends BodygraphActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_calender);
        setContentView(R.layout.activity_calendarview);

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