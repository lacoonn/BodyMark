package com.example.user.bodymanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 2017-05-25.
 */

public class ExerciseActivity extends BodygraphActivity {

    boolean flag = false;
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
            v.updateExerciseListView(tmpStrList, "body");
            tmpStrList.clear();
        }
        else if(name.equals("arm")) {
            textView.setText("팔 운동");
            exlist = meManager.searchPart("arm");
            for (Exercise ex : exlist) {
                tmpStrList.add(ex.getName());
            }
            v.updateExerciseListView(tmpStrList, "arm");
            tmpStrList.clear();
        }
        else if(name.equals("leg")) {
            textView.setText("다리 운동");
            exlist = meManager.searchPart("leg");
            for (Exercise ex : exlist) {
                tmpStrList.add(ex.getName());
            }
            v.updateExerciseListView(tmpStrList, "leg");
        }


        // checkbox changed action
        v.exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ExerciseListViewItem item = (ExerciseListViewItem) parent.getItemAtPosition(position);

                String cbTitle = item.getTitle();
                Log.d("checked_item", "el1: " + cbTitle);
            }
        });

    } // end onCreate()

    public void onClick(View view) {
        Intent intent = new Intent(ExerciseActivity.this, ExplainActivity.class);
        switch (view.getId()) {
            case R.id.main_btnReverse:
                changeVisibility();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        v.updateTodayExerciseList();
        v.updateMainListView();
        v.saveTodayExerciseListToFile();
        super.onDestroy();
    }

    ///////////////////////////// Adapter
    class ExerciseListViewAdapter extends BaseAdapter {

        boolean[] checkBoxState;

        private class ViewHolder{
            CheckBox cb;
            Button btn;
        }

        private ArrayList<ExerciseListViewItem> exerciselistViewItemList = new ArrayList<ExerciseListViewItem>() ;

        // ListViewAdapter의 생성자
        public ExerciseListViewAdapter() {}
        public ExerciseListViewAdapter(ArrayList<ExerciseListViewItem> items)
        {
            this.exerciselistViewItemList = items;

            checkBoxState = new boolean[getCount()];
        }

        // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
        @Override
        public int getCount() {
            return exerciselistViewItemList.size();
        }

        // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
        @Override
        public Object getItem(int position) {
            return exerciselistViewItemList.get(position) ;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
        public void addItem(String title) {
            ExerciseListViewItem item = new ExerciseListViewItem();

            item.setTitle(title);

            exerciselistViewItemList.add(item);
        }

        public void clearItem() {
            exerciselistViewItemList.clear();
        }

        // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;

            final Context context = parent.getContext();

            // "listview_item" Layout을 inflate하여 convertView 참조 획득.
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.exercise_listview_item, parent, false);

                viewHolder = new ViewHolder();
                viewHolder.cb = (CheckBox) convertView.findViewById(R.id.exerciseitem_checkbox);
                viewHolder.btn = (Button) convertView.findViewById(R.id.button);

                convertView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
            final ViewHolder finalViewHolder = viewHolder;

            viewHolder.cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if ( !flag ) return;

                    if(checkBoxState[position] == true) {
                        checkBoxState[position] = false;
                        v.removeArrayList(finalViewHolder.cb.getText().toString());
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        checkBoxState[position] = true;
                        v.SelectExercise(finalViewHolder.cb.getText().toString());
                        adapter.notifyDataSetChanged();
                    }
                    calculateDamage(v.getSelectedExerciseList());
                    applyPNG();
                }
            });

            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("list_button", "clicked");
                    Intent intent = new Intent(v.getContext(), ExplainActivity.class);
                    intent.putExtra("exer",finalViewHolder.cb.getText().toString());
                    context.startActivity(intent);
                }
            });

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            ExerciseListViewItem exerciselistViewItem = exerciselistViewItemList.get(position);

            // 아이템 내 각 위젯에 데이터 반영
            viewHolder.cb.setText(exerciselistViewItem.getTitle());

            //체크박스 스크롤시 엉뚱한 곳 체크 되어있는것 막는 코드
            if(checkBoxState[position] == true){
                viewHolder.cb.setChecked(true);
            }
            else {
                viewHolder.cb.setChecked(false);
            }

            return convertView;
        }

    }
}
