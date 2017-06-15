package com.example.user.bodymanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static android.R.attr.bitmap;
import static android.R.attr.start;

/**
 * Created by User on 2017-05-31.
 */

public class BodygraphActivity extends AppCompatActivity {

    ImageView imageView;

    int bodygraphDrawable[] = {
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
                if ( i == R.id.main_bodygraph_body2) continue;
                setBodygraphColor(i, bodygraphDrawable[i-begin]);
            }
    }

    public void setBodygraphColor(int id, int drawable) {      // 데이터를 저장하고 바디그래프에 반영한다
        imageView = (ImageView) findViewById(id);
        imageView.setImageResource(drawable);
    }

    public int getBodygraphColor(int id) {     // 저장된 데이터를 가져와 바디그래프에 반영한다
        int begin = R.id.main_bodygraph_abs;
        imageView = (ImageView) findViewById(id);
        imageView.setImageResource(bodygraphDrawable[id-begin]);
        return bodygraphDrawable[id-begin];
    }

    public void changeVisibility() {
        for (int i = R.id.main_bodygraph_body; i < R.id.main_bodygraph_end; i++) {
            imageView = (ImageView) findViewById(i);
            if (imageView.getVisibility() == View.VISIBLE) {
                imageView.setVisibility(View.INVISIBLE);
            }
            else {
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }
}
