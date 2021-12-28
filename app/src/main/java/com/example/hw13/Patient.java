package com.example.hw13;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient {
    private String name;
    private int age;
    public static String currentName;

    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
        currentName = name;
    }


    static class PressureData {
        private int heartRate;
        private boolean tachycardia;
        private int upperPressure;
        private int lowerPressure;
        private Date measureDate;

        public PressureData(int heartRate, boolean tachycardia, int upperPressure, int lowerPressure, Date measureDate) {
            this.heartRate = heartRate;
            this.tachycardia = tachycardia;
            this.upperPressure = upperPressure;
            this.lowerPressure = lowerPressure;
            this.measureDate = measureDate;
        }

        public int getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(int heartRate) {
            this.heartRate = heartRate;
        }

        public boolean isTachycardia() {
            return tachycardia;
        }

        public void setTachycardia(boolean tachycardia) { this.tachycardia = tachycardia; }

        public int getUpperPressure() {
            return upperPressure;
        }

        public void setUpperPressure(int upperPressure) {
            this.upperPressure = upperPressure;
        }

        public int getLowerPressure() {
            return lowerPressure;
        }

        public void setLowerPressure(int lowerPressure) {
            this.lowerPressure = lowerPressure;
        }
    }
    static class LifeParams {
        private float weight;
        private int steps;

        public LifeParams(float weight, int steps) {
            this.weight = weight;
            this.steps = steps;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public int getSteps() {
            return steps;
        }

        public void setSteps(int steps) {
            this.steps = steps;
        }
    }
    public List<PressureData> pressureDataList = new ArrayList<>();
    public List<LifeParams> lifeParamsList = new ArrayList<>();

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

}
