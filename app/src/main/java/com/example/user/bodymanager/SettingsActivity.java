package com.example.user.bodymanager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

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
            try {
                str = in.readLine();
                if(str.equals("0")) {
                    maleButton.setChecked(true);
                    maleButton.setBackgroundDrawable(getDrawable(R.drawable.male_color));
                }
                else {
                    femaleButton.setChecked(true);
                    femaleButton.setBackgroundDrawable(getDrawable(R.drawable.female_color));
                }
                str = in.readLine();
                height.setText(str);
                str = in.readLine();
                weight.setText(str);
                str = in.readLine();
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
            readInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        switch( view.getId() ) {
            case R.id.settings_male_btn:
                maleButton.setChecked(true);
                maleButton.setBackgroundDrawable(getDrawable(R.drawable.male_color));
                femaleButton.setChecked(false);
                femaleButton.setBackgroundDrawable(getDrawable(R.drawable.female_black));
                break;
            case R.id.settings_female_btn:
                maleButton.setChecked(false);
                maleButton.setBackgroundDrawable(getDrawable(R.drawable.male_black));
                femaleButton.setChecked(true);
                femaleButton.setBackgroundDrawable(getDrawable(R.drawable.female_color));
                break;
            case R.id.dialog_close:
                int sGender = 0;
                if (femaleButton.isChecked() )
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
