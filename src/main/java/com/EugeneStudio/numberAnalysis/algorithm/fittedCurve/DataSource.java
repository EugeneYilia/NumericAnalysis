package com.EugeneStudio.numberAnalysis.algorithm.fittedCurve;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static com.EugeneStudio.numberAnalysis.algorithm.fittedCurve.FittedCurveConstants.DATA_SOURCE;

public class DataSource {
    private DataSource() {
    }

    private static ArrayList<Point> points = new ArrayList<Point>();

    static {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(DATA_SOURCE)));
            String readLine;
            StringTokenizer stringTokenizer;
            while ((readLine = bufferedReader.readLine()) != null) {
                stringTokenizer = new StringTokenizer(readLine, " ");
                double location = Double.parseDouble(stringTokenizer.nextToken());
                double value = Double.parseDouble(stringTokenizer.nextToken());
                points.add(new Point(location, value));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Point> getPoints() {
        return points;
    }

    public static double getFirst(){
        return points.get(0).getLocation();
    }

    public static double getLast(){
        return points.get(points.size()-1).getLocation();
    }

    public static double getSize(){return points.size();}

    public static void main(String[] args) {
        System.out.println("getFirst->"+getFirst());
        System.out.println("getLast->"+getLast());
    }
}
