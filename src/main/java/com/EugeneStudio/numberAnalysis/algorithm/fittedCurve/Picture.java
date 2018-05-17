package com.EugeneStudio.numberAnalysis.algorithm.fittedCurve;

import javax.swing.*;
import java.awt.Graphics;

public class Picture extends JFrame {
    static double timesx = 15, timesy = 15;//Eugene 放大倍数
    String formula;

    double F(double x) {//函数表达式
        String expression = this.formula.replaceAll("x", String.valueOf(x));
        double result = Calculator.conversion(expression);
        return result;
    }

    int x0, y0;
    static int W = 800, H = 600;
    static double L = -W / 2, R = W / 2;
    //static double L = 0, R = 20;
    Graphics G;

    private void setOrigin(int x, int y) {
        this.x0 = x;
        this.y0 = y;
        // show coordinate axis
        drawLine(-W / 2, 0, W / 2, 0);
        drawLine(0, -H / 2, 0, H / 2);
        drawString("X", W / 2 - 30, -20);
        drawString("Y", -20, H / 2 - 20);
        for (int i = 1; i <= 10; i++) {
            draw(W / 2 - i - 6, i);
            draw(W / 2 - i - 6, -i);
        }
        for (int i = 1; i <= 10; i++) {
            draw(-i, H / 2 - i);
            draw(i, H / 2 - i);
        }
    }

    public Picture(String formula) {
        this.formula = formula;
        add(new NewPanel());
    }

    public static void drawPicture(String formula) {
        Picture frame = new Picture(formula);
        frame.setTitle("Eugene function image");
        frame.setSize(W, H);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public class Coordinate2D {
        int x, y;

        public Coordinate2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getPixelPointX() {
            return x0 + x;
        }

        public int getPixelPointY() {
            return y0 - y;
        }
    }

    class NewPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            G = g;

            // in the following , draw what you want draw!
            setOrigin(W / 2, H / 2);
            for (int i = -W / 2; i <= W / 2; i++) {
                draw(i, work(i));
            }
            /*setOrigin(0, 0);
            for (int i = 0; i <= 20; i++) {
                draw(i, work(i));
            }*/
        }
    }

    int work(int x) {
        //timesx = 0.01;
        //timesy = 100;
        return (int) (F(x / timesx) * timesy);
    }

    public void draw(int x, int y) {
        int X = new Coordinate2D(x, y).getPixelPointX();
        int Y = new Coordinate2D(x, y).getPixelPointY();
        G.drawLine(X, Y, X, Y);
    }

    public void drawRec(int x1, int y1, int x2, int y2) {
        int dx = x1 < x2 ? 1 : -1;
        int dy = y1 < y2 ? 1 : -1;
        for (int i = x1; i != x2 + dx; i += dx) {
            for (int j = y1; j != y2 + dy; j += dy) {
                draw(i, j);
            }
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        int dx = x1 < x2 ? 1 : -1;
        if (x1 == x2) drawRec(x1, y1, x2, y2);
        else {
            double d = (double) (y2 - y1) / (x2 - x1);
            for (int i = x1; i != x2 + dx; i += dx) {
                draw(i, (int) (y1 + (i - x1) * d));
            }
        }
    }

    public void drawString(String s, int x, int y) {
        int X = new Coordinate2D(x, y).getPixelPointX();
        int Y = new Coordinate2D(x, y).getPixelPointY();
        G.drawString(s, X, Y);
    }

    public static void main(String[] args) {
        drawPicture("3.703396178604261E-18*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-7.3656891809754E-17*(x-0.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+6.216444373497318E-16*(x-0.0)*(x-1.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-3.729866624098391E-15*(x-0.0)*(x-1.0)*(x-2.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+1.5971420085727844E-14*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-5.767235981080779E-14*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+1.6138701792361406E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-3.5623037717393975E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+6.347942476965845E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-9.168099629592868E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+1.0115285826264663E-12*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-8.705552434425156E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+5.845699067287471E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-3.256417222466605E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+1.4577405863781525E-13*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-5.066245972330629E-14*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)+1.5832018663533213E-14*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-17.0)*(x-18.0)*(x-19.0)*(x-20.0)-4.151585212250219E-15*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-18.0)*(x-19.0)*(x-20.0)+7.661221018091544E-16*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-19.0)*(x-20.0)-8.878286066354277E-17*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-20.0)+4.492577162280196E-18*(x-0.0)*(x-1.0)*(x-2.0)*(x-3.0)*(x-4.0)*(x-5.0)*(x-6.0)*(x-7.0)*(x-8.0)*(x-9.0)*(x-10.0)*(x-11.0)*(x-12.0)*(x-13.0)*(x-14.0)*(x-15.0)*(x-16.0)*(x-17.0)*(x-18.0)*(x-19.0)");
    }
}


