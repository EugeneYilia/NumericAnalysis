package com.EugeneStudio.numberAnalysis.algorithm.fittedCurve;

public class Point {
    private double location;
    private double value;

    public Point() {
    }

    public Point(double location, double value) {
        this.location = location;
        this.value = value;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getLocation() {
        return location;
    }

    public double getValue() {
        return value;
    }
}
