package com.EugeneStudio.numberAnalysis.algorithm.fittedCurve;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LagrangeInterpolationPolynomial {

    public String formFunction() {
        String formula = "";
        double coefficient;
        double product = 1;//乘积
        ArrayList<Point> points = DataSource.getPoints();
        for (int i = 0; i < points.size(); i++) {
            for (int j = 0; j < points.size(); j++) {
                if (i == j) {
                    continue;
                }
                product *= points.get(i).getLocation() - points.get(j).getLocation();
            }
            coefficient = points.get(i).getValue() / product;
            formula += coefficient;
            for (int j = 0; j < points.size(); j++) {
                if (i == j) {
                    continue;
                }
                formula += "*(x-" + points.get(j).getLocation() + ")";
                /*if (j != (points.size() - 1)) {
                    formula += "*";
                }*/
            }
            if (i != (points.size() - 1)) {
                formula += "+";
            }
            product = 1;
        }
        formula = formula.replaceAll("\\+-", "-");
        return formula;
    }

    //只能对完整的一项进行求导数
    public String getDifferentialCoefficient(String formula) {//3.703396178604261E-18(x-1.0)(x-2.0)(x-3.0)(x-4.0)(x-5.0)(x-6.0)(x-7.0)(x-8.0)(x-9.0)(x-10.0)(x-11.0)(x-12.0)(x-13.0)(x-14.0)(x-15.0)(x-16.0)(x-17.0)(x-18.0)(x-19.0)(x-20.0)
        String formulaBackup = formula;
        ArrayList<String> unknownItems = new ArrayList<String>();
        String differentialCoefficient = "";
        while (true) {
            if (formula.indexOf("(") < 0) {
                break;
            }
            String unknownItem = formula.substring(formula.indexOf("("), formula.indexOf(")") + 1);
            unknownItems.add(unknownItem);
            formula = formula.replace(unknownItem, "");
        }
        for (int i = 0; i < unknownItems.size(); i++) {
            differentialCoefficient += formulaBackup.replace(unknownItems.get(i), "");
            if (i != (unknownItems.size() - 1)) {
                differentialCoefficient += "+";
            }
        }
        return differentialCoefficient;
    }

    @Test
    public void testDifferentialCoefficient() {
        LagrangeInterpolationPolynomial lagrangeInterpolationPolynomial = new LagrangeInterpolationPolynomial();
        String formula = "3.703396178604261E-18*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-7.3656891809754E-17*(x-0.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+6.216444373497318E-16*(x-0.0)*(x-1.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-3.729866624098391E-15*(x-0.0)*(x-1.0)*(x-2.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+1.5971420085727844E-14*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-5.767235981080779E-14*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+1.6138701792361406E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-3.5623037717393975E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+6.347942476965845E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-9.168099629592868E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+1.0115285826264663E-12*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-8.705552434425156E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+5.845699067287471E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-3.256417222466605E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+1.4577405863781525E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-5.066245972330629E-14*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+1.5832018663533213E-14*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-4.151585212250219E-15*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-18.0)*(x-19.0)*(x-20.0)+7.661221018091544E-16*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-19.0)*(x-20.0)-8.878286066354277E-17*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-20.0)+4.492577162280196E-18*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)";
        String differentialCoefficient = getDifferentialCoefficient(formula);
        System.out.println(differentialCoefficient);
    }

    public double getLength(String formula) {
        //double count;
        double length = 0;
        double start = DataSource.getFirst();
        double end = DataSource.getLast();
        double size = DataSource.getSize();//划分的段数为点的数目减一
        //double stepLength = (end - start) / (100000000);//10000 已进行37.10999999999702%  length->816.5576133237367
        double partition = 1;
        double stepLength = (end - start) / ((size - 1) * partition);
        DecimalFormat decimalFormat = new DecimalFormat("###.##################################################");
        for (double i = start; i < end; i = i + stepLength) {
            //count = i;
            //System.out.println("已进行" + decimalFormat.format(100 * (count / (end - start))) + "%  length->" + length);
            //if (100 * (count / (end - start)) > 15 && 100 * (count / (end - start)) < 85)
            double increasedLength = Math.sqrt(Math.pow(stepLength, 2) + Math.pow(((getValue(formula, i)) - getValue(formula, i + stepLength)), 2));
//            System.out.println(1.25 / partition);
            if (increasedLength < 7 / partition)//1.25 / partition
                length += Math.sqrt(Math.pow(stepLength, 2) + Math.pow(((getValue(formula, i)) - getValue(formula, i + stepLength)), 2));
            //System.out.println(i);
            //System.out.println(getValue(formula, i));
            //System.out.println(getValue(formula, i + stepLength));
            //System.out.println(getValue(formula, i) - getValue(formula, i + stepLength));
            //System.out.println(getValue(formula, i));
            //System.out.println(getValue(formula, i + stepLength));
            //System.out.println(Math.pow((getValue(formula, i) - getValue(formula, i + stepLength)), 2));
            //System.out.println(Math.sqrt(Math.pow(stepLength, 2) + Math.pow(((getValue(formula, i)) - getValue(formula, i + stepLength)), 2)));
        }
        return length;
    }

    double getValue(String formula, double x) {//函数表达式
        String expression1 = formula.replaceAll("x", String.valueOf(x));
        double result = Calculator.conversion(expression1);
        return result;
    }

    public static void main(String[] args) {
        LagrangeInterpolationPolynomial lagrangeInterpolationPolynomial = new LagrangeInterpolationPolynomial();
        String formula = lagrangeInterpolationPolynomial.formFunction();
        System.out.println("#############FORMULA START#############");
        System.out.println(formula);
        System.out.println("#############FORMULA END###############");
        System.out.println("曲线的的长度为" + lagrangeInterpolationPolynomial.getLength(formula));
        Picture.drawPicture(formula);
        /*for (double i = 0; i <= 20; i = i + 0.5) {
            System.out.println("在x=" + i + "点处的函数值" + lagrangeInterpolationPolynomial.getValue(formula, i));
        }*/
        /*
        ArrayList<String> unknownItems = new ArrayList<String>();
        StringTokenizer stringTokenizer = new StringTokenizer(formula, "+");
        while (stringTokenizer.hasMoreTokens()) {
            unknownItems.add(stringTokenizer.nextToken());
        }
        String differentialCoefficient = "";
        for (int i = 0; i < unknownItems.size(); i++) {
            differentialCoefficient += lagrangeInterpolationPolynomial.getDifferentialCoefficient(unknownItems.get(i));
            if (i != (unknownItems.size() - 1)) {
                differentialCoefficient += "+";
            }
        }
        System.out.println("###########DIFFERENTIAL COEFFICIENT START###########");
        System.out.println(differentialCoefficient);
        System.out.println("###########DIFFERENTIAL COEFFICIENT END#############");
        */
    }
}
