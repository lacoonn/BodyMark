package com.example.user.bodymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by User on 2017-05-25.
 */

public class ExerciseActivity extends BodygraphActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;

    ArrayAdapter<String> adapter;
    ListView listView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Variables v = (Variables) getApplication();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        listView = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textView_name);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");


        cb1 = (CheckBox)findViewById(R.id.checkBox1);
        cb2 = (CheckBox)findViewById(R.id.checkBox2);
        cb3 = (CheckBox)findViewById(R.id.checkBox3);
        cb4 = (CheckBox)findViewById(R.id.checkBox4);

        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
        cb4.setOnCheckedChangeListener(this);

        adapter = new ArrayAdapter<String>(ExerciseActivity.this, R.layout.my_text_view, v.getArrayList());
        listView.setAdapter(adapter);
        textView.setText(name);

        setMuscleExercise();
        //setMuscleExercise2();

    } // end onCreate()

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // 체크박스를 클릭해서 상태가 바꾸었을 경우 호출되는 콜백 메서드
        Variables v = (Variables) getApplication();

        if(cb1.isChecked()) {
            v.addArrayList(cb1.getText().toString());
            adapter.notifyDataSetChanged();
        }
        else if(!cb1.isChecked()) {
            v.removeArrayList(cb1.getText().toString());
            adapter.notifyDataSetChanged();
        }
        else if(cb2.isChecked()) {
            v.addArrayList(cb2.getText().toString());
            adapter.notifyDataSetChanged();

        }
        else if(!cb2.isChecked()) {
            v.removeArrayList(cb2.getText().toString());
            adapter.notifyDataSetChanged();
        }
        else if(cb3.isChecked()) {
            v.addArrayList(cb3.getText().toString());
            adapter.notifyDataSetChanged();

        }
        else if(!cb3.isChecked()) {
            v.removeArrayList(cb3.getText().toString());
            adapter.notifyDataSetChanged();
        }
        else if(cb4.isChecked()) {
            v.addArrayList(cb4.getText().toString());
            adapter.notifyDataSetChanged();

        }
        else if(!cb4.isChecked()) {
            v.removeArrayList(cb4.getText().toString());
            adapter.notifyDataSetChanged();
        }
    }


    public void onClick(View view) {
        Intent intent = new Intent(ExerciseActivity.this, ExplainActivity.class);
        switch (view.getId()) {
            case R.id.button1:
                startActivity(intent);
                intent.putExtra("exer",cb1.getText().toString());
                break;
            case R.id.button2:
                startActivity(intent);
                intent.putExtra("exer",cb2.getText().toString());
                break;
            case R.id.button3:
                startActivity(intent);
                intent.putExtra("exer",cb3.getText().toString());
                break;
            case R.id.button4:
                startActivity(intent);
                intent.putExtra("exer",cb4.getText().toString());
                break;
            case R.id.main_btnReverse:
                changeVisibility();
                break;
        }
    }

    protected static void viewExercise(Exercise ex) // view explain
    {
        int i;
        System.out.println(ex.getName());
        for(i=0;i<ex.getPartNum();i++)
            System.out.println(ex.getPart().get(i));
        System.out.println(ex.getSimple());
        for(i=0;i<ex.getSeqNum();i++)
            System.out.println(ex.getSeq().get(i));
        System.out.println(ex.getTip());
        System.out.println(ex.getKcal());
        System.out.println(ex.getTired());
    }
    protected static void loadingMuscleExercise(MuscleExerciseManager meManager,ExerciseManager exManager)
    {
        ArrayList<Exercise> exlist = new ArrayList<Exercise>();
        exlist = exManager.lists();

        meManager.add(addingMuscleExercise("chest1", exlist));
        meManager.add(addingMuscleExercise("shoulder1", exlist));
        meManager.add(addingMuscleExercise("shoulder2", exlist));
        meManager.add(addingMuscleExercise("uarm1", exlist));
        meManager.add(addingMuscleExercise("uarm2", exlist));
        meManager.add(addingMuscleExercise("larm1", exlist));
        meManager.add(addingMuscleExercise("larm2", exlist));
        meManager.add(addingMuscleExercise("back1", exlist));
        meManager.add(addingMuscleExercise("back2", exlist));
        meManager.add(addingMuscleExercise("back3", exlist));
        meManager.add(addingMuscleExercise("waist1", exlist));
        meManager.add(addingMuscleExercise("abdomen1", exlist));
        meManager.add(addingMuscleExercise("abdomen2", exlist));
        meManager.add(addingMuscleExercise("leg1", exlist));
        meManager.add(addingMuscleExercise("leg2", exlist));
        meManager.add(addingMuscleExercise("leg3", exlist));
        meManager.add(addingMuscleExercise("leg4", exlist));
        meManager.add(addingMuscleExercise("leg5", exlist));
    }
    protected static MuscleExercise addingMuscleExercise(String part ,ArrayList<Exercise> exlist)
    {
        MuscleExercise me = new MuscleExercise();
        int i, j, count = 0;
        ArrayList<Exercise> templist = new ArrayList<Exercise>();
        ArrayList<String> temppart = new ArrayList<String>();

        me.setName(part);
        for(i=0;i<exlist.size();i++)
        {
            temppart = exlist.get(i).getPart();
            for(j=0;j<exlist.get(i).getPartNum();j++) {
                if (temppart.get(i).equals(part)) {
                    templist.add(exlist.get(i));
                    count++;
                    break;
                }
            }
        }
        me.setExer(templist);
        me.setMaxexer(count);

        return me;
    }
    protected void loadingExercise(ExerciseManager exlist) // load to file and insert ExManager
    {
        int linenum = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.exercise)));
            Exercise ex;
            int partnum = 0, i;
            ArrayList<String> part = new ArrayList<String>();
            ArrayList<String> seq = new ArrayList<String>();

            String line;
            String nameExercise =""; //
            ArrayList<String> partExercise = new ArrayList<String>(); //
            String simpleExercise ="";//
            int seqnum = 0;
            ArrayList<String> seqExercise = new ArrayList<String>(); //
            String tipExercise =""; //
            String kcalExercise =""; //
            String tiredExercise =""; //
            while ((line = br.readLine()) != null) {
                switch(linenum) {
                    case 0: nameExercise = line;
                        break;
                    case 1:
                        partnum = Integer.parseInt(line); // atoi
                        for(i=0;i<partnum;i++)
                        {
                            line = br.readLine();
                            part.add(line);
                        }
                        partExercise = part;
                        break;
                    case 2: simpleExercise = line;
                        break;
                    case 3:
                        seqnum = Integer.parseInt(line);
                        for(i=0;i<seqnum;i++)
                        {
                            line = br.readLine();
                            seq.add(line);
                        }
                        seqExercise = seq;
                        break;
                    case 4: tipExercise = line;
                        break;
                    case 5: kcalExercise = line;
                        break;
                    case 6: tiredExercise = line;
                        break;
                    default:
                        break;
                }
                linenum++;
                if(linenum == 7) {
                    linenum = 0;
                    ex = new Exercise(nameExercise, partnum, partExercise, simpleExercise, seqnum, seqExercise,
                           tipExercise, kcalExercise, tiredExercise);
                   exlist.add(ex);
                }
            }

            br.close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    protected void loadingExercise2(ExerciseManager exlist) // load to file and insert ExManager
    {
        int linenum = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.exercise2)));
            Exercise ex;
            int partnum = 0, i;
            ArrayList<String> part = new ArrayList<String>();
            ArrayList<String> seq = new ArrayList<String>();

            String line;
            String nameExercise =""; //
            ArrayList<String> partExercise = new ArrayList<String>(); //
            String simpleExercise ="";//
            int seqnum = 0;
            ArrayList<String> seqExercise = new ArrayList<String>(); //
            String tipExercise =""; //
            String kcalExercise =""; //
            String tiredExercise =""; //
            while ((line = br.readLine()) != null) {
                switch(linenum) {
                    case 0: nameExercise = line;
                        break;
                    case 1:
                        partnum = Integer.parseInt(line); // atoi
                        for(i=0;i<partnum;i++)
                        {
                            line = br.readLine();
                            part.add(line);
                        }
                        partExercise = part;
                        break;
                    case 2: simpleExercise = line;
                        break;
                    case 3:
                        seqnum = Integer.parseInt(line);
                        for(i=0;i<seqnum;i++)
                        {
                            line = br.readLine();
                            seq.add(line);
                        }
                        seqExercise = seq;
                        break;
                    case 4: tipExercise = line;
                        break;
                    case 5: kcalExercise = line;
                        break;
                    case 6: tiredExercise = line;
                        break;
                    default:
                        break;
                }
                linenum++;
                if(linenum == 7) {
                    linenum = 0;
                    ex = new Exercise(nameExercise, partnum, partExercise, simpleExercise, seqnum, seqExercise,
                            tipExercise, kcalExercise, tiredExercise);
                    exlist.add(ex);
                }
            }

            br.close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    protected void loadingExercise3(ExerciseManager exlist) // load to file and insert ExManager
    {
        int linenum = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.exercise3)));
            Exercise ex;
            int partnum = 0, i;
            ArrayList<String> part = new ArrayList<String>();
            ArrayList<String> seq = new ArrayList<String>();

            String line;
            String nameExercise =""; //
            ArrayList<String> partExercise = new ArrayList<String>(); //
            String simpleExercise ="";//
            int seqnum = 0;
            ArrayList<String> seqExercise = new ArrayList<String>(); //
            String tipExercise =""; //
            String kcalExercise =""; //
            String tiredExercise =""; //
            while ((line = br.readLine()) != null) {
                switch(linenum) {
                    case 0: nameExercise = line;
                        break;
                    case 1:
                        partnum = Integer.parseInt(line); // atoi
                        for(i=0;i<partnum;i++)
                        {
                            line = br.readLine();
                            part.add(line);
                        }
                        partExercise = part;
                        break;
                    case 2: simpleExercise = line;
                        break;
                    case 3:
                        seqnum = Integer.parseInt(line);
                        for(i=0;i<seqnum;i++)
                        {
                            line = br.readLine();
                            seq.add(line);
                        }
                        seqExercise = seq;
                        break;
                    case 4: tipExercise = line;
                        break;
                    case 5: kcalExercise = line;
                        break;
                    case 6: tiredExercise = line;
                        break;
                    default:
                        break;
                }
                linenum++;
                if(linenum == 7) {
                    linenum = 0;
                    ex = new Exercise(nameExercise, partnum, partExercise, simpleExercise, seqnum, seqExercise,
                            tipExercise, kcalExercise, tiredExercise);
                    exlist.add(ex);
                }
            }

            br.close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    protected void loadingPic(ArrayList<Pic> piclist, ExerciseManager exManager)
    {
        ArrayList<Exercise> exlist = exManager.lists();
        int i,j;

        for(i=0;i<exManager.count();i++)
        {
            if(exlist.get(i).getName().equals("벤치 프레스 - 머신"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.bench_press_mech1);
                pic[maxpic++] = (R.drawable.bench_press_mech2);
                pic[maxpic++] = (R.drawable.bench_press_mech3);
                pic[maxpic++] = (R.drawable.bench_press_mech4);

                temp.setName("벤치 프레스 - 머신");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("벤치 프레스 - 바벨, 플랫"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.bench_press_ba_plat1);
                pic[maxpic++] = (R.drawable.bench_press_ba_plat2);
                pic[maxpic++] = (R.drawable.bench_press_ba_plat3);
                pic[maxpic++] = (R.drawable.bench_press_ba_plat4);

                temp.setName("벤치 프레스 - 바벨, 플랫");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("풀오버 - 덤벨, 플랫"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.pullover_dumb_plat1);
                pic[maxpic++] = (R.drawable.pullover_dumb_plat2);
                pic[maxpic++] = (R.drawable.pullover_dumb_plat3);
                pic[maxpic++] = (R.drawable.pullover_dumb_plat4);

                temp.setName("풀오버 - 덤벨, 플랫");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("숄더 프레스 - 머신"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.shoulder_mech1);
                pic[maxpic++] = (R.drawable.shoulder_mech2);
                pic[maxpic++] = (R.drawable.shoulder_mech3);

                temp.setName("숄더 프레스 - 머신");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("비하인드 넥 프레스 - 스미스 머신"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.behind_neck_press1);
                pic[maxpic++] = (R.drawable.behind_neck_press2);
                pic[maxpic++] = (R.drawable.behind_neck_press3);
                pic[maxpic++] = (R.drawable.behind_neck_press4);

                temp.setName("비하인드 넥 프레스 - 스미스 머신");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("아놀드 프레스 - 머신"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.anold_press1);
                pic[maxpic++] = (R.drawable.anold_press2);
                pic[maxpic++] = (R.drawable.anold_press3);
                pic[maxpic++] = (R.drawable.anold_press4);

                temp.setName("아놀드 프레스");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("컬 - 바벨"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.culr_babel1);
                pic[maxpic++] = (R.drawable.culr_babel2);
                pic[maxpic++] = (R.drawable.culr_babel3);

                temp.setName("컬 - 바벨");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("컬 - 덤벨"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.culr_dumbell1);
                pic[maxpic++] = (R.drawable.culr_dumbell2);
                pic[maxpic++] = (R.drawable.culr_dumbell3);

                temp.setName("컬 - 덤벨");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("컬 프레스 - 덤벨"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.curl_press_dumb1);
                pic[maxpic++] = (R.drawable.curl_press_dumb2);
                pic[maxpic++] = (R.drawable.curl_press_dumb3);
                pic[maxpic++] = (R.drawable.curl_press_dumb4);

                temp.setName("컬 프레스 - 덤벨");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("트라이셉스 익스텐션 - 덤벨, 라잉"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.tryseps_extension_dumb1);
                pic[maxpic++] = (R.drawable.tryseps_extension_dumb2);
                pic[maxpic++] = (R.drawable.tryseps_extension_dumb3);

                temp.setName("트라이셉스 익스텐션 - 덤벨, 라잉");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("리버스 리스트 컬 - 바벨"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.reverse_list_curl_ba1);
                pic[maxpic++] = (R.drawable.reverse_list_curl_ba2);
                pic[maxpic++] = (R.drawable.reverse_list_curl_ba3);
                pic[maxpic++] = (R.drawable.reverse_list_curl_ba4);

                temp.setName("리버스 리스트 컬 - 바벨");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("조트맨 컬 - 덤벨"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.jotman_dumb1);
                pic[maxpic++] = (R.drawable.jotman_dumb2);
                pic[maxpic++] = (R.drawable.jotman_dumb3);
                pic[maxpic++] = (R.drawable.jotman_dumb4);

                temp.setName("조트맨 컬 - 덤벨");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("랫 풀 다운 - 머신"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.let_pull_down__mech1);
                pic[maxpic++] = (R.drawable.let_pull_down__mech2);
                pic[maxpic++] = (R.drawable.let_pull_down_mech3);
                pic[maxpic++] = (R.drawable.let_pull_down_mech4);

                temp.setName("랫 풀 다운 - 머신");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("로우 - 티바"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.low_t1);
                pic[maxpic++] = (R.drawable.low_t2);
                pic[maxpic++] = (R.drawable.low_t3);

                temp.setName("로우 - 티바");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("데드리프트 - 덤벨"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.deadlift___dumb1);
                pic[maxpic++] = (R.drawable.deadlift___dumb2);
                pic[maxpic++] = (R.drawable.deadlift___dumb3);

                temp.setName("데드리프트 - 덤벨");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("백 익스텐션"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.back_extension1);
                pic[maxpic++] = (R.drawable.back_extension2);
                pic[maxpic++] = (R.drawable.back_extension3);
                pic[maxpic++] = (R.drawable.back_extension4);

                temp.setName("백 익스텐션");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("싯업"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.shitup1);
                pic[maxpic++] = (R.drawable.shitup2);
                pic[maxpic++] = (R.drawable.shitup3);
                pic[maxpic++] = (R.drawable.shitup4);

                temp.setName("싯업");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("크로스 크런치"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.cross_crunch1);
                pic[maxpic++] = (R.drawable.cross_crunch2);
                pic[maxpic++] = (R.drawable.cross_crunch3);
                pic[maxpic++] = (R.drawable.cross_crunch4);

                temp.setName("크로스 크런치");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("V업"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.vup1);
                pic[maxpic++] = (R.drawable.vup2);
                pic[maxpic++] = (R.drawable.vup3);

                temp.setName("V업");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("사이드 힙 킥"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.side_heap_kick1);
                pic[maxpic++] = (R.drawable.side_heap_kick2);
                pic[maxpic++] = (R.drawable.side_heap_kick3);
                pic[maxpic++] = (R.drawable.side_heap_kick4);

                temp.setName("사이드 힙 킥");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("점프 스쿼트"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.jump_squart1);
                pic[maxpic++] = (R.drawable.jump_squart2);
                pic[maxpic++] = (R.drawable.jump_squart3);
                pic[maxpic++] = (R.drawable.jump_squart4);

                temp.setName("점프 스쿼트");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("와이드 스쿼트"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.wide_squart1);
                pic[maxpic++] = (R.drawable.wide_squart2);
                pic[maxpic++] = (R.drawable.wide_squart3);

                temp.setName("와이드 스쿼트");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("레그 컬 - 라잉"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.leg_curl___lying1);
                pic[maxpic++] = (R.drawable.leg_curl___lying2);
                pic[maxpic++] = (R.drawable.leg_culr___lying3);

                temp.setName("레그 컬 - 라잉");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("멀티힙"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.multiheap1);
                pic[maxpic++] = (R.drawable.multiheap2);
                pic[maxpic++] = (R.drawable.multiheap3);
                pic[maxpic++] = (R.drawable.multiheap4);

                temp.setName("멀티힙");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
            else if(exlist.get(i).getName().equals("카프 레이즈 - 싱글 레그"))
            {
                Pic temp = new Pic();
                int[] pic = new int[4];
                int maxpic = 0;

                pic[maxpic++] = (R.drawable.carp_lays_single1);
                pic[maxpic++] = (R.drawable.carp_lays_single2);
                pic[maxpic++] = (R.drawable.carp_lays_single3);
                pic[maxpic++] = (R.drawable.carp_lays_single4);

                temp.setName("카프 레이즈 - 싱글 레그");
                temp.setPic(pic);
                temp.setMaxpic(maxpic);
                piclist.add(temp);
            }
        }
    }

    public void setMuscleExercise() {
        Variables v = (Variables) getApplication();
        ArrayList<MuscleExercise> melist = new ArrayList<MuscleExercise>();
        Exercise ex;
        //Loading
        loadingExercise(v.getExManager());
        loadingMuscleExercise(v.getMeManager(), v.getExManager());
        loadingPic(v.getPicManager(),v.getExManager());
        //

        ex = v.getExManager().searchName("벤치 프레스 - 머신");
        // viewExercise(ex);
        ex = v.getExManager().searchName("벤치 프레스 - 바벨, 플랫");
        //viewExercise(ex);
        melist = v.getMeManager().lists();
        Toast.makeText(this,melist.get(0).getName() + "\n" + melist.get(0).getMaxexer() + "\n" + melist.get(0).getExer().get(0).getName() + "\n" + melist.get(0).getExer().get(1).getName(),Toast.LENGTH_LONG).show();
        Toast.makeText(this,melist.get(1).getName() + "\n" + melist.get(1).getMaxexer() + "\n" + melist.get(1).getExer().get(0).getName() + "\n" + melist.get(1).getExer().get(1).getName(),Toast.LENGTH_LONG).show();
    }


    public void setMuscleExercise2()
    {
        Variables v = (Variables) getApplication();

        loadingExercise2(v.getExManager());
        loadingExercise3(v.getExManager());
    }
}
