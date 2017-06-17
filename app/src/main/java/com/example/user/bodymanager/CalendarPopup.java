package com.example.user.bodymanager;

/**
 * Created by woochan on 2017-06-17.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class CalendarPopup extends Activity {
    private int year;
    private int month;
    private int day;
    static final String[] LIST_MENU = {"푸시업", "윗몸일으키기", "플랭크"} ;

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.activity_calendar_popup);

        /*
        팝업창의 날짜를 가지고 온다.
         */
        Intent intent = getIntent();
        year = intent.getIntExtra("year", 0);
        month = intent.getIntExtra("month", 0);
        day = intent.getIntExtra("day", 0);
        Toast.makeText(CalendarPopup.this, year+"/"+(month+1)+"/"+day, Toast.LENGTH_SHORT).show();
        TextView date = (TextView) findViewById(R.id.textView);
        date.setText(year+"/"+(month+1)+"/"+day);

        /*
        그날의 운동 목록을 출력하는 리스트뷰
         */
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU) ;
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
                break;
        }
    }
}
