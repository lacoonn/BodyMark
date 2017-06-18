package com.example.user.bodymanager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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

    public int getBodygraphColor(int id) {     // 저장된 데이터를 가져와 바디그래프에 반영한다
        int begin = R.id.main_bodygraph_abs;
        imageView = (ImageView) findViewById(id);
        imageView.setImageResource(bodygraphDrawable[id - begin]);
        return bodygraphDrawable[id - begin];
    }

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
    public void createBMP()    //바디그래프 근육 색깔 계산 -> 색깔 바꾼 png파일 생성
    {
        Variables v = (Variables) getApplication();
        Muscle[] m = new Muscle[v.getMuscles().length];

        Bitmap bmIn;
        for (int i = 0; i < m.length; ++i) {
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

                    //흰색의 색상을 바꿔준다.
                    if (R == 255 && G == 255 && B == 255) {
                        // round-off color offset
                        R = ((R + (m[i].getDamage() / 2)) - ((R + (m[i].getDamage()/ 2)) % m[i].getDamage()) - 1);
                        if (R < 0) {
                            R = 0;
                        }
                        G = 255 - R;
                        if (G < 0) {
                            G = 0;
                        }
                        B = 100;

                        // set pixel color to output bitmap
                        bmOut.setPixel(x, y, Color.argb(A, R, G, B));
                    }
                }
            }

            //save to SDcard
            try {
                FileOutputStream outf = new FileOutputStream(android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+m[i].getName());
                bmOut.compress(Bitmap.CompressFormat.PNG, 100, outf);
                outf.flush();
                outf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}