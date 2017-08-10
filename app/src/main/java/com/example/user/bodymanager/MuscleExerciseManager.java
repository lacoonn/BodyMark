package com.example.user.bodymanager;

import java.util.ArrayList;

/**
 * Created by Seok on 2017-06-17.
 */

public class MuscleExerciseManager {
    private static MuscleExerciseManager meManager = new MuscleExerciseManager();
    private ArrayList<MuscleExercise> melist = new ArrayList<MuscleExercise>();

    private MuscleExerciseManager() {
    }
    public static MuscleExerciseManager getInstance() {
        return meManager;
    }

    public void add(MuscleExercise user) { // 저장
        this.melist.add(user);
    }

    public int count() { // 갯수확인
        return this.melist.size();
    }

    public ArrayList<MuscleExercise> lists() { //자료출력
        return this.melist;
    }

}
