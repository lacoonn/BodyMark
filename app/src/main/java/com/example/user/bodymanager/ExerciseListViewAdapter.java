package com.example.user.bodymanager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

/**
* Created by jum on 2017-08-09.
*/

public class ExerciseListViewAdapter extends BaseAdapter{


    private ArrayList<ExerciseListViewItem> exerciselistViewItemList = new ArrayList<ExerciseListViewItem>() ;

    private boolean isChecked;
    // ListViewAdapter의 생성자
    public ExerciseListViewAdapter() {}

    public void setCheckbox(boolean c)
    {
        isChecked = c;
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return exerciselistViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.exercise_listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        final CheckBox cb = (CheckBox) convertView.findViewById(R.id.exerciseitem_checkbox);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        // button part
        Button buttonView = (Button) convertView.findViewById(R.id.button);

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("list_button", "clicked");
                Intent intent = new Intent(v.getContext(), ExplainActivity.class);
                intent.putExtra("exer",cb.getText().toString());
                context.startActivity(intent);
            }
        });


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ExerciseListViewItem exerciselistViewItem = exerciselistViewItemList.get(position);


        // 아이템 내 각 위젯에 데이터 반영
        cb.setText(exerciselistViewItem.getTitle());

        //On Click Listener


        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {

        return exerciselistViewItemList.get(position) ;
    }

    @Override
    public long getItemId(int i) {
        return 0;
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
}

