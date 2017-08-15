package com.example.user.bodymanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 2017-06-18.
 */

public class ExplainActivity extends Activity {
    Variables v;
    ArrayList<Exercise> exlist;
    ListView listview;
    ListViewAdapter adapter;
    Exercise ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        v = (Variables) getApplication();
        exlist = v.getExManager().lists();


        TextView view = (TextView) this.findViewById(R.id.textView_exer);
        Intent intent = getIntent();
        int i;

        String name;
        String part = "";

        name = intent.getStringExtra("exer");
        view.setText(name);

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_explain);
        listview.setAdapter(adapter);

        ex = v.getExManager().searchName(name);

        for(i=0;i<ex.getPartNum();i++) {
            part = part + transPart(ex.getPart().get(i));
            if(i != ex.getPartNum()-1)
                part = part + ", ";
        }

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.dummy), false, "운동 부위");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.dummy), false, part);
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.dummy), false, "운동 개요");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.dummy), false, ex.getSimple());
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.dummy), false, "운동 순서");
        printPicture(name, part);
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.dummy), false, "주의 사항");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.dummy), false, ex.getTip());
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.dummy), false, "난이도 : "+ transTired(ex.getTired()));

    }

    public void onClick(View view) {
        Intent intent2 = new Intent(ExplainActivity.this, WebActivity.class);
        switch (view.getId()) {
            case R.id.button_explain3:
                intent2.putExtra("exer",ex.getName());
                startActivity(intent2);
                break;
        }
    }

    public void printPicture(String n, String part)
    {
        if(n.equals("벤치 프레스 - 머신"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bench_press_mech1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bench_press_mech2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bench_press_mech3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bench_press_mech4), true, ex.getSeq().get(3));
        }
        else if(n.equals("벤치 프레스 - 바벨, 플랫"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bench_press_ba_plat1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bench_press_ba_plat2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bench_press_ba_plat3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bench_press_ba_plat4), true, ex.getSeq().get(3));
        }
        else if(n.equals("풀오버 - 덤벨, 플랫"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.pullover_dumb_plat1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.pullover_dumb_plat2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.pullover_dumb_plat3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.pullover_dumb_plat4), true, ex.getSeq().get(3));
        }
        else if(n.equals("숄더 프레스 - 머신"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.shoulder_mech1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.shoulder_mech2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.shoulder_mech3), true, ex.getSeq().get(2));
        }
        else if(n.equals("비하인드 넥 프레스 - 스미스 머신"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.behind_neck_press1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.behind_neck_press2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.behind_neck_press3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.behind_neck_press4), true, ex.getSeq().get(3));
        }
        else if(n.equals("아놀드 프레스"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.anold_press1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.anold_press2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.anold_press3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.anold_press4), true, ex.getSeq().get(3));
        }
        else if(n.equals("컬 - 바벨"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.culr_babel1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.culr_babel2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.culr_babel3), true, ex.getSeq().get(2));
        }
        else if(n.equals("컬 - 덤벨"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.culr_dumbell1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.culr_dumbell2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.culr_dumbell3), true, ex.getSeq().get(2));
        }
        else if(n.equals("컬 프레스 - 덤벨"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.curl_press_dumb1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.curl_press_dumb2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.curl_press_dumb3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.curl_press_dumb4), true, ex.getSeq().get(3));
        }
        else if(n.equals("트라이셉스 익스텐션 - 덤벨, 라잉"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.tryseps_extension_dumb1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.tryseps_extension_dumb2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.tryseps_extension_dumb3), true, ex.getSeq().get(2));
        }
        else if(n.equals("리버스 리스트 컬 - 바벨"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.reverse_list_curl_ba1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.reverse_list_curl_ba2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.reverse_list_curl_ba3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.reverse_list_curl_ba4), true, ex.getSeq().get(3));
        }
        else if(n.equals("조트맨 컬 - 덤벨"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.jotman_dumb1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.jotman_dumb2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.jotman_dumb3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.jotman_dumb4), true, ex.getSeq().get(3));
        }
        else if(n.equals("랫 풀 다운 - 머신"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.let_pull_down__mech1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.let_pull_down__mech2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.let_pull_down_mech3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.let_pull_down_mech4), true, ex.getSeq().get(3));
        }
        else if(n.equals("로우 - 티바"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.low_t1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.low_t2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.low_t3), true, ex.getSeq().get(2));
        }
        else if(n.equals("데드리프트 - 덤벨"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.deadlift___dumb1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.deadlift___dumb2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.deadlift___dumb3), true, ex.getSeq().get(2));
        }
        else if(n.equals("백 익스텐션"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.back_extension1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.back_extension2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.back_extension3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.back_extension4), true, ex.getSeq().get(3));
        }
        else if(n.equals("싯업"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.shitup1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.shitup2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.shitup3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.shitup4), true, ex.getSeq().get(3));
        }
        else if(n.equals("크로스 크런치"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.cross_crunch1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.cross_crunch2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.cross_crunch3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.cross_crunch4), true, ex.getSeq().get(3));
        }
        else if(n.equals("V업"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.vup1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.vup2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.vup3), true, ex.getSeq().get(2));

        }
        else if(n.equals("사이드 힙 킥"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.side_heap_kick1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.side_heap_kick2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.side_heap_kick3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.side_heap_kick4), true, ex.getSeq().get(3));
        }
        else if(n.equals("점프 스쿼트"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.jump_squart1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.jump_squart2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.jump_squart3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.jump_squart4), true, ex.getSeq().get(3));
        }
        else if(n.equals("와이드 스쿼트"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.wide_squart1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.wide_squart2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.wide_squart3), true, ex.getSeq().get(2));
        }
        else if(n.equals("레그 컬 - 라잉"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.leg_curl___lying1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.leg_curl___lying2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.leg_culr___lying3), true, ex.getSeq().get(2));
        }
        else if(n.equals("멀티힙"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.multiheap1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.multiheap2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.multiheap3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.multiheap4), true, ex.getSeq().get(3));
        }
        else if(n.equals("카프 레이즈 - 싱글 레그"))
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.carp_lays_single1), true, ex.getSeq().get(0));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.carp_lays_single2), true, ex.getSeq().get(1));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.carp_lays_single3), true, ex.getSeq().get(2));
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.carp_lays_single4), true, ex.getSeq().get(3));
        }
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
        else if(n.equals("waist1"))
            return "기립근";
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