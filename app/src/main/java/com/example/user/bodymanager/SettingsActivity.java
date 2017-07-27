package com.example.user.bodymanager;

import android.content.Context;
<<<<<<< HEAD
import android.content.Intent;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
=======
import android.os.Bundle;
import android.view.View;
>>>>>>> f5c80f7f8143ad14dc21f44d97796b7eefcb495a
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

<<<<<<< HEAD
import java.io.BufferedInputStream;
=======
>>>>>>> f5c80f7f8143ad14dc21f44d97796b7eefcb495a
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by User on 2017-05-31.
 */

public class SettingsActivity extends BodygraphActivity {

    private ToggleButton maleButton;
    private ToggleButton femaleButton;
    private EditText height;
    private EditText weight;
    private EditText age;
    private static final String FILE_NAME = "info.txt";
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
/*
        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);
*/
        mContext = this;

        maleButton = (ToggleButton) this.findViewById(R.id.settings_male_btn);
        femaleButton = (ToggleButton) this.findViewById(R.id.settings_female_btn);
        height = (EditText) this.findViewById(R.id.settings_height);
        weight = (EditText) this.findViewById(R.id.settings_weight);
        age = (EditText) this.findViewById(R.id.settings_age);

        //Load settings
        BufferedReader in = null;
        String str = null;
        try {
            in = new BufferedReader(new FileReader(Variables.path +  FILE_NAME));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(in != null)
        {
            Toast.makeText(this, "File Opened", Toast.LENGTH_LONG).show();
            try {
                str = in.readLine();
                if(str.equals("0")) {
                    //Toast.makeText(this, "남자" + str, Toast.LENGTH_LONG).show();
                    maleButton.setChecked(true);
                }
                else {
                    //Toast.makeText(this, "여자" + str, Toast.LENGTH_LONG).show();
                    femaleButton.setChecked(true);
                }
                str = in.readLine();
                //Toast.makeText(this, "키:"+str, Toast.LENGTH_LONG).show();
                height.setText(str);
                str = in.readLine();
                //Toast.makeText(this, "몸무게:"+str, Toast.LENGTH_LONG).show();
                weight.setText(str);
                str = in.readLine();
                //Toast.makeText(this, "나이:"+str, Toast.LENGTH_LONG).show();
                age.setText(str);

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }


    public void save(String strData) {
        if (strData == null || strData.equals("")) {
            return;
        }
        FileOutputStream fosMemo = null;

        try {

            fosMemo = mContext.openFileOutput(FILE_NAME, 0);
            fosMemo.write(strData.getBytes());
            fosMemo.close();
            //Toast.makeText(this, mContext.getFilesDir().toString(), Toast.LENGTH_SHORT).show();
            readInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        switch( view.getId() ) {
            case R.id.settings_male_btn:
                if( maleButton.isChecked() ) {
                    femaleButton.setChecked(false);
                } else {
                    maleButton.setChecked(true);
                    femaleButton.setChecked(false);
                }
                break;
            case R.id.settings_female_btn:
                if( femaleButton.isChecked() ) {
                    maleButton.setChecked(false);
                } else {
                    maleButton.setChecked(false);
                    femaleButton.setChecked(true);
                }
                break;
            case R.id.dialog_close:
                int sGender = 0;
                if ( femaleButton.isChecked() )
                    sGender = 1;
                String sHegiht = height.getText().toString();
                String sWegiht = weight.getText().toString();
                String sAge= age.getText().toString();
                save(sGender + "\n" + sHegiht + "\n" + sWegiht + "\n" + sAge);

                finish();
                break;
        }
    }
}
