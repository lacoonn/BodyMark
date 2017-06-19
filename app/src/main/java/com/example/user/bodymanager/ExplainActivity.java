package com.example.user.bodymanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 2017-06-18.
 */

public class ExplainActivity extends Activity {
    Variables v = (Variables) getApplication();
    ArrayList<Exercise> exlist;
    ArrayList<String> part;
    ArrayList<String> seq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Variables v = (Variables) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        exlist = v.getExManager().lists();
        TextView view;
        view = (TextView) this.findViewById(R.id.textView);
        Exercise ex;
        Pic pic;
        int i;

        Intent intent = getIntent();

        String name;
        String part = "";

        name = intent.getStringExtra("exer");
        view.setText(name);

        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        pic = v.PsearchName(name);
        ex = v.getExManager().searchName(name);
        adapter.addItem(null, ex.getName()); // 이름
        for(i=0;i<ex.getPartNum();i++) {
            part = part + transPart(ex.getPart().get(i));
            if(i != ex.getPartNum()-1)
                part = part + ", ";
        }
        adapter.addItem(null, part);
        adapter.addItem(null, ex.getSimple());
        for(i=0;i<ex.getSeqNum();i++)
        {
            adapter.addItem(ContextCompat.getDrawable(this, pic.getPic(i)), ex.getSeq().get(i));
        }
        adapter.addItem(null, ex.getTip());
        adapter.addItem(null, transTired(ex.getTired()));
    }


    public String transTired(String n)
    {
        if(n.equals("3"))
        return "상급자용";
        else if (n.equals("2"))
        return "중급자용";
        else if (n.equals("1"))
        return "초급자용";

        return null;
    }

    public String transPart(String n)
    {
        if(n.equals("chest1"))
        return "대흉근";
        else if(n.equals("shoulder1"))
        return "삼각근";
        else if(n.equals("shoulder2"))
        return "승모근";
        else if(n.equals("uarm1"))
        return "삼두근";
        else if(n.equals("uarm2"))
        return "이두근";
        else if(n.equals("larm1"))
        return "굴곡근";
        else if(n.equals("larm2"))
        return "신전근";
        else if(n.equals("back1"))
        return "광배근";
        else if(n.equals("back2"))
        return "승모근";
        else if(n.equals("back3"))
        return "능형근";
        else if(n.equals("abdomen1"))
        return "복직근";
        else if(n.equals("abdomen2"))
        return "복사근";
        else if(n.equals("leg1"))
        return "둔근";
        else if(n.equals("leg2"))
        return "대퇴이두근";
        else if(n.equals("leg3"))
        return "대퇴사두근";
        else if(n.equals("leg4"))
        return "슬굴곡근";
        else if(n.equals("leg5"))
        return "비복근";

        return null;
    }
}