package com.example.user.bodymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Variables v = (Variables) getApplication();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        listView = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent();


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

        setMuscleExercise();

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
                intent.putExtra("exer1",cb1.getText().toString());
                break;
            case R.id.button2:
                startActivity(intent);
                intent.putExtra("exer2",cb2.getText().toString());
                break;
            case R.id.button3:
                startActivity(intent);
                intent.putExtra("exer3",cb3.getText().toString());
                break;
            case R.id.button4:
                startActivity(intent);
                intent.putExtra("exer4",cb4.getText().toString());
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
        int i, count = 0;
        ArrayList<Exercise> templist = new ArrayList<Exercise>();

        me.setName(part);
        for(i=0;i<exlist.size();i++)
        {
            if(exlist.get(i).getPart().contains(part))
            {
                templist.add(exlist.get(i));
                count++;
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
    public void setMuscleExercise() {
        Variables v = (Variables) getApplication();
        ArrayList<MuscleExercise> melist = new ArrayList<MuscleExercise>();
        Exercise ex;
        //Loading
        loadingExercise(v.getExManager());
        loadingMuscleExercise(v.getMeManager(), v.getExManager());
        //
        ex = v.getExManager().searchName("벤치 프레스 - 머신");
        viewExercise(ex);
        ex = v.getExManager().searchName("벤치 프레스 - 바벨, 플랫");
        viewExercise(ex);
        melist = v.getMeManager().lists();
        Toast.makeText(this,melist.get(0).getName() + "\n" + melist.get(0).getMaxexer() + "\n" + melist.get(0).getExer().get(0).getName() + "\n" + melist.get(0).getExer().get(1).getName(),Toast.LENGTH_LONG).show();
        Toast.makeText(this,melist.get(1).getName() + "\n" + melist.get(1).getMaxexer() + "\n" + melist.get(1).getExer().get(0).getName() + "\n" + melist.get(1).getExer().get(1).getName(),Toast.LENGTH_LONG).show();
    }
}
