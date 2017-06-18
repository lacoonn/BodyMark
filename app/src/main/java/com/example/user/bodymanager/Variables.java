package com.example.user.bodymanager;

import android.app.Application;

/**
 * Created by jum on 2017-06-16.
 */

public class Variables extends Application {
//Variables v = (Variables) getApplication()  으로 선언한 후, v에 저장된 변수를 사용 가능
    static String path = "data/user/0/com.example.user.bodymanager/files/";
    private Muscle[] muscles;
    public ExerciseList todayExerciseList;


    public Muscle[] getMuscles() {
        return muscles;
    }

    public void setMuscles(Muscle[] muscles) {
        this.muscles = muscles;
    }
}