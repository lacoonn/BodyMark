package com.example.user.bodymanager;

import java.util.ArrayList;
import java.util.HashSet;

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

    public MuscleExercise searchMuscle(String muscle) { // muscle 기반 탐색
        MuscleExercise me = new MuscleExercise();

        MuscleExercise temp = new MuscleExercise();
        temp.setMuscle(muscle);

        for (int idx = 0; idx<this.melist.size(); idx++) {
            if (this.compareToMuscle(temp, this.melist.get(idx))) {
                me = this.melist.get(idx);
                break;
            }
        }
        return me;
    }
    public ArrayList<Exercise> searchPart(String part) { // muscle 기반 탐색
        MuscleExercise me = new MuscleExercise();

        MuscleExercise temp = new MuscleExercise();
        temp.setPart(part);
        ArrayList<Exercise> exlist = new ArrayList<Exercise>();
        ArrayList<Exercise> templist = new ArrayList<Exercise>();

        for (int idx = 0; idx<this.melist.size(); idx++) {
            if (this.compareToPart(temp, this.melist.get(idx))) {
                me = this.melist.get(idx);
                templist = me.getExer();
                for(int j = 0; j<templist.size();j++)
                {
                    exlist.add(templist.get(j));
                }
            }
        }
        HashSet hs = new HashSet(exlist);
        ArrayList<Exercise> rtlist = new ArrayList<Exercise>(hs);

        return rtlist;
    }
    private boolean compareToMuscle(MuscleExercise o1, MuscleExercise o2) {
        return o1.getMuscle().toString().equals(o2.getMuscle().toString());
    }
    private boolean compareToPart(MuscleExercise o1, MuscleExercise o2) {
        return o1.getPart().toString().equals(o2.getPart().toString());
    }
}
