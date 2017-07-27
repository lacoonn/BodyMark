package com.example.user.bodymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by User on 2017-05-31.
 */

public class DialogActivity extends BodygraphActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_close :
                finish();
                break;
        }
    }

}
