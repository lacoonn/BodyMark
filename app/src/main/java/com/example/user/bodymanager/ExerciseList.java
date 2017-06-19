package com.example.user.bodymanager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by woochan on 2017-06-18.
 */

public class ExerciseList implements Serializable {
    private int year;
    private int month;
    private int day;
    private ArrayList<Exercise> exercises = new ArrayList<Exercise>();

    public ExerciseList(int _year, int _month, int _day) {
        year = _year;
        month = _month;
        day = _day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public Exercise getExercise(int order) {
        return exercises.get(order);
    }

    public int getLength() {
        return exercises.size();
    }

    public void clearExerciseList() {
        exercises.clear();
    }

    public int getIndexByName(String name) {
        // 해당하는 이름의 운동이 없으면 -1을 return
        for(int i = 0; ; i++) {
            if(exercises.get(i).getName() == name)
                return i;
        }
  //      return  -1;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void removeExercise(int order) {
        exercises.remove(order);
    }
}