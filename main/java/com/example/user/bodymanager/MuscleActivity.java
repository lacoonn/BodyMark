package com.example.user.bodymanager;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by User on 2017-05-25.
 */

public class MuscleActivity extends BodygraphActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle);

        Intent intent = getIntent();
    }
}
