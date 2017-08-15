package com.example.user.bodymanager;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ToggleButton;

/**
 * Created by User on 2017-05-31.
 */

public class SettingsActivity extends BodygraphActivity {

    ToggleButton maleButton;
    ToggleButton femaleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);
        */

        setContentView(R.layout.activity_settings);

        maleButton = (ToggleButton) this.findViewById(R.id.settings_male_btn);
        femaleButton = (ToggleButton) this.findViewById(R.id.settings_female_btn);

    }

    public void onClick(View view) {
        switch( view.getId() ) {
            case R.id.settings_male_btn:
                if( maleButton.isChecked() ) {
                    femaleButton.setChecked(false);
                } else {
                    femaleButton.setChecked(true);
                }
                break;
            case R.id.settings_female_btn:
                if( femaleButton.isChecked() ) {
                    maleButton.setChecked(false);
                } else {
                    maleButton.setChecked(true);
                }
                break;
            case R.id.dialog_close:
                finish();
                break;
        }
    }

}
