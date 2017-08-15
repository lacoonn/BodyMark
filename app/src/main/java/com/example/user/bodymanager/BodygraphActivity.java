package com.example.user.bodymanager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by User on 2017-05-31.
 */

public abstract class BodygraphActivity extends Activity {

    private ImageView imageView;
    Variables v;
    public static String bodyGraphName[] = {
            "bicep",
            "chest",
            "deltoid",
            "extensor",
            "flexor",
            "oblique",
            "quadricep",
            "rectus",
            "trapezius",         // forward 9
            "deltoid2",
            "glute",
            "hamstring",
            "latissimus",
            "rhomboid",
            "soleus",
            "trapezius2",
            "tricep"              // backward 8
    };

    public static int bodygraphDrawable[] = {
            R.drawable.bicep,
            R.drawable.chest,
            R.drawable.deltoid,
            R.drawable.extensor,
            R.drawable.flexor,
            R.drawable.oblique,
            R.drawable.quadricep,
            R.drawable.rectus,
            R.drawable.trapezius,         // forward 9

            R.drawable.deltoid2,
            R.drawable.glute,
            R.drawable.hamstring,
            R.drawable.latissimus,
            R.drawable.rhomboid,
            R.drawable.soleus,
            R.drawable.trapezius2,
            R.drawable.tricep,

            R.drawable.calves2
    };
    public static int bodygraphDrawable2[] = {
            R.drawable.bicep_y,
            R.drawable.chest_y,
            R.drawable.deltoid_y,
            R.drawable.extensor_y,
            R.drawable.flexor_y,
            R.drawable.oblique_y,
            R.drawable.quadricep_y,
            R.drawable.rectus_y,
            R.drawable.trapezius_y,         // forward 9

            R.drawable.deltoid2_y,
            R.drawable.glute_y,
            R.drawable.hamstring_y,
            R.drawable.latissimus_y,
            R.drawable.rhomboid_y,
            R.drawable.soleus_y,
            R.drawable.trapezius2_y,
            R.drawable.tricep_y,

            R.drawable.calves2
    };
    public static int bodygraphDrawable3[] = {
            R.drawable.bicep_r,
            R.drawable.chest_r,
            R.drawable.deltoid_r,
            R.drawable.extensor_r,
            R.drawable.flexor_r,
            R.drawable.oblique_r,
            R.drawable.quadricep_r,
            R.drawable.rectus_r,
            R.drawable.trapezius_r,         // forward 9

            R.drawable.deltoid2_r,
            R.drawable.glute_r,
            R.drawable.hamstring_r,
            R.drawable.latissimus_r,
            R.drawable.rhomboid_r,
            R.drawable.soleus_r,
            R.drawable.trapezius2_r,
            R.drawable.tricep_r,

            R.drawable.calves2
    };
    public static int bodygraphId[] = {
            R.id.main_bodygraph_bicep,
            R.id.main_bodygraph_chest,
            R.id.main_bodygraph_deltoid,
            R.id.main_bodygraph_extensor,
            R.id.main_bodygraph_flexor,
            R.id.main_bodygraph_oblique,
            R.id.main_bodygraph_quadricep,
            R.id.main_bodygraph_rectus,
            R.id.main_bodygraph_trapezius,

            R.id.main_bodygraph_deltoid2,
            R.id.main_bodygraph_glute,
            R.id.main_bodygraph_hamstring,
            R.id.main_bodygraph_latissimus,
            R.id.main_bodygraph_rhomboid,
            R.id.main_bodygraph_soleus,
            R.id.main_bodygraph_trapezius2,
            R.id.main_bodygraph_tricep,

            R.id.main_bodygraph_clave
    };


    public static int[] damage = new int[bodygraphDrawable.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = (Variables) getApplication();
    }

    public void changeVisibility() {
        for (int i : bodygraphId) {
            imageView = (ImageView) findViewById(i);
            if (imageView.getVisibility() == View.VISIBLE) {
                imageView.setVisibility(View.INVISIBLE);
            } else {
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }
    public void readInfo() {
        v = (Variables) getApplication();

        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(Variables.path + "info.txt"));

            if (Integer.parseInt(in.readLine()) == 0)
                v.gender = 0;
            else
                v.gender = 10;

            v.age= Integer.parseInt(in.readLine());
            v.height = Integer.parseInt(in.readLine());
            v.weight = Integer.parseInt(in.readLine());
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // 데이터를 저장하고 바디그래프에 반영한다
    public void applyPNG() {
        for(int i=0 ; i < bodygraphDrawable.length ; ++i) {
            ImageView imgview = (ImageView)findViewById(bodygraphId[i]);

            Log.d("damage", "" + damage[i]);
            if(damage[i] <= 70)
                imgview.setImageResource(bodygraphDrawable[i]);
            else if(damage[i] <= 100)
                imgview.setImageResource(bodygraphDrawable2[i]);
            else
                imgview.setImageResource(bodygraphDrawable3[i]);
        }
    }

    public void calculateDamage(ArrayList<String> a){
        //체크박스 클릭할 때 - 오늘 선택한 운동 전체에 대해 수행
        //readPreviousDamage - 저장된 운동의 목록에 대해 수행
        //운동 이름의 ArrayList를 인자로 필요함

        int result = 0;

        //초기화
        damage = (int[])v.getSavedPreviousDamage().clone();

        for(String str : a) {
                Exercise ex = v.getExManager().searchName(str);

                result = v.gender;

                if (v.age >= 0 && v.age < 20) result += 6;
                else if (v.age >= 20 && v.age < 30) result += 7;
                else if (v.age >= 30 && v.age < 30) result += 8;
                else if (v.age >= 40 && v.age < 30) result += 9;
                else if (v.age >= 50) result += 10;

                if (v.height >= 0 && v.height < 150) result += 10;
                else if (v.height >= 150 && v.height < 160) result += 9;
                else if (v.height >= 160 && v.height < 170) result += 8;
                else if (v.height >= 170 && v.height < 180) result += 7;
                else if (v.height >= 180) result += 6;

                if (v.weight >= 0 && v.weight < 50) result += 10;
                else if (v.weight >= 50 && v.weight < 60) result += 8;
                else if (v.weight >= 60 && v.weight < 70) result += 6;
                else if (v.weight >= 70 && v.weight < 80) result += 7;
                else if (v.weight >= 80) result += 9;

                //강도
                result *= Integer.parseInt(ex.getTired());

                // 횟수
                    //없음

                //계산된 피로를 부위마다 적용
                ArrayList<String> tmplist = ex.getPart();

                for(String p : tmplist) {
                    damage[mapName(p)] += result;
                }
            }
    }

    public void readPreviousDamage()
    {
        ExerciseList exerciseList;
        Calendar date = Calendar.getInstance();

        for(int i = 1 ;i <= 4; i++)//-1 일 전 날짜부터, 총 -7일까지 계산
        {

            //해당 파일 열기
            String openFileName = String.format("%04d%02d%02d.bin", date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH));

            try {
                FileInputStream fis = new FileInputStream(Variables.path + openFileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                exerciseList = (ExerciseList) ois.readObject();


                //임시로 arraylist 생성
                ArrayList<String> tmp = new ArrayList<String>();
                for(Exercise e : exerciseList.getExerciseArray())
                {
                    tmp.add(e.getName());
                }

                calculateDamage(tmp);
                //계산된 피로도를 임시로 저장
                v.setsavedPreviousDamage(damage,i);

                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        damage = v.getSavedPreviousDamage().clone();

        date.add(Calendar.DATE, -1); //하루 이전 날짜로 지정
    }

    //근육 번호와 이름을 대응시키는 함수
    public int mapName(String str)
    {
        if(str.equals("chest1"))
            return 1;
        else if(str.equals("shoulder1"))
            return 2;
        else if(str.equals("shoulder2"))
            return 8;
        else if(str.equals("uarm1"))
            return 17;
        else if(str.equals("uarm2"))
            return 0;
        else if(str.equals("larm1"))
            return 4;
        else if(str.equals("larm2"))
            return 3;
        else if(str.equals("back1"))
            return 13;
        else if(str.equals("back2"))
            return 16;
        else if(str.equals("back3"))
            return 14;
        else if(str.equals("waist1"))
            return 15;
        else if(str.equals("abdomen1"))
            return 7;
        else if(str.equals("abdomen2"))
            return 5;
        else if(str.equals("leg1"))
            return 11;
        else if(str.equals("leg2"))
            return 12;
        else if(str.equals("leg3"))
            return 6;
        else if(str.equals("leg4"))
            return 10;
        else
            return 9;   // 안쓰는 부위
    }

    //=================================================================================================================================== 운동/ 근육 추가시 하드코딩 필요한 부분
    protected static void loadingMuscleExercise(MuscleExerciseManager meManager,ExerciseManager exManager)
    {
        ArrayList<Exercise> exlist = new ArrayList<Exercise>();
        exlist = exManager.lists();

        meManager.add(addingMuscleExercise("chest1", "body", exlist));  //(muscle, part, exlist)
        meManager.add(addingMuscleExercise("shoulder1", "body", exlist));
        meManager.add(addingMuscleExercise("shoulder2", "body", exlist));
        meManager.add(addingMuscleExercise("uarm1", "arm", exlist));
        meManager.add(addingMuscleExercise("uarm2", "arm", exlist));
        meManager.add(addingMuscleExercise("larm1", "arm", exlist));
        meManager.add(addingMuscleExercise("larm2", "arm", exlist));
        meManager.add(addingMuscleExercise("back1", "body", exlist));
        meManager.add(addingMuscleExercise("back2", "body", exlist));
        meManager.add(addingMuscleExercise("back3", "body", exlist));
        meManager.add(addingMuscleExercise("waist1", "body", exlist));
        meManager.add(addingMuscleExercise("abdomen1", "body", exlist));
        meManager.add(addingMuscleExercise("abdomen2", "body", exlist));
        meManager.add(addingMuscleExercise("leg1", "leg", exlist));
        meManager.add(addingMuscleExercise("leg2", "leg", exlist));
        meManager.add(addingMuscleExercise("leg3", "leg", exlist));
        meManager.add(addingMuscleExercise("leg4", "leg", exlist));
        meManager.add(addingMuscleExercise("leg5", "leg", exlist));

    }
    //==================================================================================================================================================
    protected static MuscleExercise addingMuscleExercise(String muscle, String part, ArrayList<Exercise> exlist)
    {
        MuscleExercise me = new MuscleExercise();
        int i, j, count = 0;
        ArrayList<Exercise> templist = new ArrayList<Exercise>();
        ArrayList<String> temppart = new ArrayList<String>();

        me.setMuscle(muscle);
        me.setPart(part);

        for(i=0;i<exlist.size();i++)
        {
            temppart = exlist.get(i).getPart();
            for(j=0;j<exlist.get(i).getPartNum();j++) {
                if (temppart.get(j).equals(muscle)) {
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

            String simpleExercise ="";//
            int seqnum = 0;

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
                    ex = new Exercise(nameExercise, partnum, part, simpleExercise, seqnum, seq,
                            tipExercise, kcalExercise, tiredExercise);
                    exlist.add(ex);
                    part = new ArrayList<String>();
                    seq = new ArrayList<String>();
                }
            }

            br.close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public void setMuscleExercise() {

        loadingExercise(v.getExManager());
        loadingMuscleExercise(v.getMeManager(), v.getExManager());
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    /*
    public boolean storageWritable()
    {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state))
            return true;
        return false;
    }
    public boolean storageReadable()
    {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)||Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
            return true;
        return false;
    }
    */
    /*
    //bmp방식으로 적용하는법
    public void createBMP(Context context)    //바디그래프 근육 색깔 계산 -> 색깔 바꾼 png파일 생성
    {

        Variables v = (Variables) getApplication();

        ImageView img;
        Bitmap bmIn;
        for (int i = 0; i < bodygraphDrawable.length; ++i) {

            bmIn = BitmapFactory.decodeResource(getResources(), bodygraphDrawable[i]);

            // get image size
            int width = bmIn.getWidth();
            int height = bmIn.getHeight();
            // create output bitmap
            Bitmap bmOut = Bitmap.createBitmap(width, height, bmIn.getConfig());
            // color information
            int A, R, G, B;
            int pixel;

            // scan through all pixels
            // calculate and change color
            for (int x = 0; x < width; ++x) {
                for (int y = 0; y < height; ++y) {

                    // get pixel color
                    pixel = bmIn.getPixel(x, y);
                    A = Color.alpha(pixel);
                    R = Color.red(pixel);
                    G = Color.green(pixel);
                    B = Color.blue(pixel);

                    if(R<=4 && G<=4 && B<=4)
                        continue;

                    // round-off color offset
                    if(damage[i]<255) {
                        R = damage[i];
                        G = 255;
                    }
                    else{
                        R=255;
                        G=510-damage[i];
                    }
                    if(R>255)
                        R=255;
                    if(G<0)
                        G=0;

                        B = 0;

                        // set pixel color to output bitmap
                        bmOut.setPixel(x, y, Color.argb(A, R, G, B));
                }
            }

            //save to SDcard
            FileOutputStream out=null;
            try {
                out = context.openFileOutput(bodyGraphName[i] + ".png", 0);
                bmOut.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }finally
            {
                    try {
                        if(out!=null)
                            out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public void applyBMP()
    {
        Variables v = (Variables) getApplication();

        for(int i=0 ; i < bodygraphDrawable.length ; ++i) {
            ImageView imgview;

            try{
                imgview = (ImageView)findViewById(bodygraphId[i]);
                String imgpath = v.path + bodyGraphName[i] + ".png";
                Bitmap bm = BitmapFactory.decodeFile(imgpath);
                imgview.setImageBitmap(bm);
            }catch(Exception e){

        }

    }
    */
}