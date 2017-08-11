package com.example.user.bodymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    private boolean flag = false;
    Variables v;

    ArrayAdapter<String> adapter;
    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        v = (Variables) getApplication();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        applyPNG();

        listView = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textView3);
        Intent intent = getIntent();
        String name = intent.getStringExtra("part");

        adapter = new ArrayAdapter<String>(ExerciseActivity.this, R.layout.my_text_view, v.getSelectedExerciseList());
        listView.setAdapter(adapter);


        ArrayList<String> tempArray = v.getSelectedExerciseList();

        v.exerciseListView = (ListView) findViewById(R.id.exercise_listView);
        v.exerciseAdapter = new ExerciseListViewAdapter();
        v.exerciseListView.setAdapter(v.exerciseAdapter);

        flag = true;

        //listview 생성

        MuscleExerciseManager meManager = v.getMeManager();
        MuscleExercise me;
        ArrayList<String> tmpStrList = new ArrayList<String>();
        ArrayList<Exercise> exlist = new ArrayList<Exercise>();

        if(name.equals("body")) {
            textView.setText("몸 운동");
            exlist = meManager.searchPart("body");
            for (Exercise ex : exlist) {
                tmpStrList.add(ex.getName());
            }
            v.updateExerciseListView(tmpStrList);
            tmpStrList.clear();
        }
        else if(name.equals("arm")) {
            textView.setText("팔 운동");
            exlist = meManager.searchPart("arm");
            for (Exercise ex : exlist) {
                tmpStrList.add(ex.getName());
            }
            v.updateExerciseListView(tmpStrList);
            tmpStrList.clear();
        }
        else if(name.equals("leg")) {
            textView.setText("다리 운동");
            exlist = meManager.searchPart("leg");
            for (Exercise ex : exlist) {
                tmpStrList.add(ex.getName());
            }
            v.updateExerciseListView(tmpStrList);
        }


        /*
        //checkbox click handler
        v.exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                 item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;
            }
        }) ;


        */

    } // end onCreate()

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // 체크박스를 클릭해서 상태가 바꾸었을 경우 호출되는 콜백 메서드
        /*
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
        }*/

        calculateDamage(v.getSelectedExerciseList());
        applyPNG();
    }

    public void onClick(View view) {
        Intent intent = new Intent(ExerciseActivity.this, ExplainActivity.class);
        switch (view.getId()) {
            case R.id.main_btnReverse:
                changeVisibility();
                break;
        }
        /*
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
        */
    }


    @Override
    protected void onDestroy() {
        v.updateTodayExerciseList();
        v.updateMainListView();
        v.saveTodayExerciseListToFile();
        super.onDestroy();
    }




}
