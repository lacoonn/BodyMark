package com.example.user.bodymanager;

import java.util.ArrayList;

/**
 * Created by Seok on 2017-06-17.
 */

public class ExerciseManager {
    private static ExerciseManager exManager = new ExerciseManager();
    private ArrayList<Exercise> exlist = new ArrayList<Exercise>();

    private ExerciseManager() {
    }
    public static ExerciseManager getInstance() {
        return exManager;
    }

    public void add(Exercise user) { // 저장
        this.exlist.add(user);
    }

    public int count() { // 갯수확인
        return this.exlist.size();
    }

    public ArrayList<Exercise> lists() { //자료출력
        return this.exlist;
    }


    public Exercise searchName(String name) { // name 기반 탐색
        Exercise ex = null;

        Exercise temp = new Exercise();
        temp.setName(name);

        for (int idx = 0; idx<this.exlist.size(); idx++) {
            if (this.compareToName(temp, this.exlist.get(idx)))
                ex = this.exlist.get(idx);
        }

        return ex;
    }
    public Exercise searchPart(String part) { // part 기반 탐색
        Exercise ex = null;

        for (int idx = 0; idx<this.exlist.size(); idx++) {
            if (exlist.get(idx).getPart().contains(part)) // 만약 part를 포함하고 있으면
                ex = this.exlist.get(idx);
        }

        return ex;
    }
    private boolean compareToName(Exercise o1, Exercise o2) {
        return o1.getName().toLowerCase().equals(o2.getName().toLowerCase());
    }

}
