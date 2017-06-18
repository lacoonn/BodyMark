package com.example.user.bodymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by User on 2017-06-18.
 */

public class ExplainActivity extends Activity {
    Variables v = (Variables) getApplication();
    ArrayList<Exercise> exlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        exlist = v.getExManager().lists();

        Intent intent = getIntent();
    }
}