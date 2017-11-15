package ohtu;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "viikko " + week + ": " +
                "tehtyjä tehtäviä yhteensä: " + exercises.length +
                ", aikaa kului " + hours + " tuntia, " +
                "tehdyt tehtävät: " +
                Arrays.stream(exercises).mapToObj(i -> Integer.toString(i)).collect(Collectors.joining(" "));
    }

}