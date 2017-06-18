package com.example.user.bodymanager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by User on 2017-05-31.
 */

public class BodygraphActivity extends Activity {

    private ImageView imageView;

    public static int bodygraphDrawable[] = {
            R.drawable.abs_green,
            R.drawable.adductor_green,
            R.drawable.biceps_green,
            R.drawable.calves_green,
            R.drawable.chest_green,
            R.drawable.obliques_green,
            R.drawable.quads_green,
            R.drawable.shoulders_green,
            R.drawable.traps_green,         // forward 9

            R.drawable.calves2_green,
            R.drawable.glutes_green,
            R.drawable.hamstrings_green,
            R.drawable.lats_green,
            R.drawable.shoulders2_green,
            R.drawable.traps2_green,
            R.drawable.triceps_green        // backward 7
    };
    public static int bodygraphId[] = {
            R.id.main_bodygraph_abs,
            R.id.main_bodygraph_adductor,
            R.id.main_bodygraph_biceps,
            R.id.main_bodygraph_calves,
            R.id.main_bodygraph_chest,
            R.id.main_bodygraph_obliques,
            R.id.main_bodygraph_quads,
            R.id.main_bodygraph_shoulders,
            R.id.main_bodygraph_traps,
            R.id.main_bodygraph_calves2,
            R.id.main_bodygraph_hamstrings,
            R.id.main_bodygraph_lats,
            R.id.main_bodygraph_shoulders2,
            R.id.main_bodygraph_traps2,
            R.id.main_bodygraph_triceps
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void InitiateBodygraphColors() {
        int begin = R.id.main_bodygraph_abs;
        for (int i = R.id.main_bodygraph_abs; i < R.id.main_bodygraph_end; i++) {
            if (i == R.id.main_bodygraph_body2) continue;
            setBodygraphColor(i, bodygraphDrawable[i - begin]);
        }
    }

    public void setBodygraphColor(int id, int drawable) {      // 데이터를 저장하고 바디그래프에 반영한다
        imageView = (ImageView) findViewById(id);
        imageView.setImageResource(drawable);
    }
/*
    public int getBodygraphColor(int id) {     // 저장된 데이터를 가져와 바디그래프에 반영한다
        int begin = R.id.main_bodygraph_abs;
        imageView = (ImageView) findViewById(id);
        imageView.setImageResource(bodygraphDrawable[id - begin]);
        return bodygraphDrawable[id - begin];
    }
*/
    public void changeVisibility() {
        for (int i = R.id.main_bodygraph_body; i < R.id.main_bodygraph_end; i++) {
            imageView = (ImageView) findViewById(i);
            if (imageView.getVisibility() == View.VISIBLE) {
                imageView.setVisibility(View.INVISIBLE);
            } else {
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void createBMP(Context context)    //바디그래프 근육 색깔 계산 -> 색깔 바꾼 png파일 생성
    {

        Variables v = (Variables) getApplication();
        Muscle[] m = v.getMuscles();

        ImageView img;
        Bitmap bmIn;
        for (int i = 0; i < m.length; ++i) {

            bmIn = BitmapFactory.decodeResource(getResources(), m[i].getResource_num());

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
                    if(m[i].getDamage()<255) {
                        R = m[i].getDamage();
                        G = 255;
                    }
                    else{
                        R=255;
                        G=510-m[i].getDamage();
                    }
                        B = 0;

                        // set pixel color to output bitmap
                        bmOut.setPixel(x, y, Color.argb(A, R, G, B));

                }
            }

            //save to SDcard

            FileOutputStream out=null;
            try {
                //android.os.Environment.getExternalStorageDirectory().getAbsolutePath()
                //"/data/data/com.example.user.bodymanager/files/"+ m[i].getName() + ".png"
                //context.getFilesDir().getPath() + "/" + m[i].getName() + ".png"

                out = context.openFileOutput(m[i].getName() + ".png", 0);
                bmOut.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
Toast.makeText(this, context.getFilesDir().toString(), Toast.LENGTH_SHORT).show();

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
}