package com.example.user.bodymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 2017-05-25.
 */

public class ExerciseActivity extends BodygraphActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox cb1;
    private CheckBox cb2;
    private boolean flag = false;
    Variables v;

    private CheckBox cb3;
    ArrayAdapter<String> adapter;
    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        v = (Variables) getApplication();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        applyPNG();

        ArrayList<Exercise> exlist;
        listView = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textView3);
        Intent intent = getIntent();
        String name = intent.getStringExtra("data");


        cb1 = (CheckBox)findViewById(R.id.checkBox1);
        cb2 = (CheckBox)findViewById(R.id.checkBox2);
        cb3 = (CheckBox)findViewById(R.id.checkBox3);

        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);

        adapter = new ArrayAdapter<String>(ExerciseActivity.this, R.layout.my_text_view, v.getSelectedExerciseList());
        listView.setAdapter(adapter);
        textView.setText(name);

        ArrayList<String> tempArray = v.getSelectedExerciseList();

        if(name.equals("몸 운동")){
            String str1 = "벤치 프레스 - 머신";
            String str2 = "벤치 프레스 - 바벨, 플랫";
            String str3 = "숄더 프레스 - 머신";
            cb1.setText(str1);
            cb2.setText(str2);
            cb3.setText(str3);
            for (String i : tempArray) {
                if ( i.equals(str1) ) {
                    cb1.setChecked(true);
                }
                else if ( i.equals(str2) ) {
                    cb2.setChecked(true);
                }
                else if ( i.equals(str3) ) {
                    cb3.setChecked(true);
                }
            }
        }
        else if(name.equals("팔 운동"))
        {
            String str1 = "비하인드 넥 프레스 - 스미스 머신";
            String str2 = "아놀드 프레스";
            String str3 = "컬 - 바벨";
            cb1.setText(str1);
            cb2.setText(str2);
            cb3.setText(str3);
            for (String i : tempArray) {
                if ( i.equals(str1) ) {
                    cb1.setChecked(true);
                }
                if ( i.equals(str2) ) {
                    cb2.setChecked(true);
                }
                if ( i.equals(str3) ) {
                    cb3.setChecked(true);
                }
            }
        }
        else if(name.equals("다리 운동"))
        {
            String str1 = "레그 컬 - 라잉";
            String str2 = "와이드 스쿼트";
            String str3 = "점프 스쿼트";
            cb1.setText(str1);
            cb2.setText(str2);
            cb3.setText(str3);
            for (String i : tempArray) {
                if ( i.equals(str1) ) {
                    cb1.setChecked(true);
                }
                if ( i.equals(str2) ) {
                    cb2.setChecked(true);
                }
                if ( i.equals(str3) ) {
                    cb3.setChecked(true);
                }
            }
        }

        flag = true;

    } // end onCreate()

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // 체크박스를 클릭해서 상태가 바꾸었을 경우 호출되는 콜백 메서드
        v = (Variables) getApplication();

        if ( !flag ) return;

        if (buttonView.getId() == cb1.getId()) {
            if(cb1.isChecked()) {
                v.SelectExercise(cb1.getText().toString());
                adapter.notifyDataSetChanged();
            }
            else {
                v.removeArrayList(cb1.getText().toString());
                adapter.notifyDataSetChanged();
            }
        }
        if (buttonView.getId() == cb2.getId()) {
            if(cb2.isChecked()) {
                v.SelectExercise(cb2.getText().toString());
                adapter.notifyDataSetChanged();
            }
            else {
                v.removeArrayList(cb2.getText().toString());
                adapter.notifyDataSetChanged();
            }
        }
        if (buttonView.getId() == cb3.getId()) {
            if(cb3.isChecked()) {
                v.SelectExercise(cb3.getText().toString());
                adapter.notifyDataSetChanged();
            }
            else {
                v.removeArrayList(cb3.getText().toString());
                adapter.notifyDataSetChanged();
            }
        }

        calculateDamage(v.getSelectedExerciseList());
        applyPNG();
    }

    @Override
    protected void onDestroy() {
        v.updateTodayExerciseList();
        v.updateMainListView();
        v.saveTodayExerciseListToFile();
        super.onDestroy();
    }

    public void onClick(View view) {
        Intent intent = new Intent(ExerciseActivity.this, ExplainActivity.class);
        switch (view.getId()) {
            case R.id.button1:
                intent.putExtra("exer",cb1.getText().toString());
                startActivity(intent);
                break;
            case R.id.button2:
                intent.putExtra("exer",cb2.getText().toString());
                startActivity(intent);
                break;
            case R.id.button3:
                intent.putExtra("exer",cb3.getText().toString());
                startActivity(intent);
                break;
            case R.id.main_btnReverse:
                changeVisibility();
                break;
        }
    }


    //ExerciseActivity의 운동 선택창 세팅
    public void updateExerciseListView(ArrayList<String> exerciseName) {
        // ExerciseListView에  데이터를 추가합니다------------------------------------------
        v.exerciseAdapter.clearItem();
        for(String i : exerciseName) {
            v.exerciseAdapter.addItem(i);
        }
        v.exerciseAdapter.notifyDataSetChanged();
    }   //oncreate의 운동.txt 읽어오는 구간에 ArrayList<String>을 추가하여 모든 운동을 다 읽으면 인자(exerciseName)로 준다.

}
