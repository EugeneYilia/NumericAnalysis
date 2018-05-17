package com.EugeneStudio.numberAnalysis.algorithm.hilbertMatrix;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class HilbertMatrix {
    public HilbertMatrix() {
    }

    public HilbertMatrix(int dimension) {//最后行和列的数目
        this.dimension = dimension;
        this.row = dimension;
        this.column = dimension + 1;
    }

    Double numerator = 0.0;
    Double denominator;
    int dimension = 6;
    int row = dimension;//行
    int column = dimension + 1;//列
    double[][] coefficientMatrix = new double[row][column];
    double[] results = new double[dimension];
    ArrayList<Double> coefficientArrayList = new ArrayList<Double>();

    public void setIterativeFormula(String formula) {//   h = 1/ (i + j - 1) or h = 1 / (i + j -1)
        if (!formula.contains("/")) {
            denominator = 1.0;//分母设置为1
        } else {
            StringTokenizer stringTokenizer = new StringTokenizer(formula, "/");
            String numerator = stringTokenizer.nextToken();
            if (!numerator.contains("i") && !numerator.contains("j")) {
                this.numerator = Double.parseDouble(numerator);
            } else {

            }
            String denominator = stringTokenizer.nextToken();
            if (!denominator.contains("i") && !denominator.contains("j")) {
                this.denominator = Double.parseDouble(denominator);
            } else {
                //假定分子的值为1，算出每一个点的分母的值
                setCoefficientMatrix(denominator);
                setExactCoefficientMatrix(Double.parseDouble(numerator));
                //Double minimalCommonMultiple = Utils.getMinimalCommonMultiple(coefficientArrayList);//从矩阵中提出来的系数为 1/minimalCommonMultiple
                //setNewCoefficientMartrix(minimalCommonMultiple);
                //System.out.println("最小公倍数为"+minimalCommonMultiple);
            }
            setInitValue();
            //printFullMatrix();
        }
    }

    private void setExactCoefficientMatrix(Double numerator) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                coefficientMatrix[i][j] = numerator / coefficientMatrix[i][j];
            }
        }
    }

    private void executeGaussMethod() {//00->01 02 03 04 05 06   11->12 13 14 15 16  22->23 24 25 26  33->34 35 36  44->45 46  55->56
        for (int i = 0; i < dimension; i++) {
            for (int j = i + 1; j < dimension; j++) {//coefficientMatrix[i][i]作为基准值,消掉之后的coefficientMatrix[i][j]
                ArrayList<Double> tempArrayList = new ArrayList<Double>();
                tempArrayList.add(coefficientMatrix[i][i]);
                tempArrayList.add(coefficientMatrix[i][j]);
                Double minimalCommonMultiple = Utils.getMinimalCommonMultiple(tempArrayList);
                //进行第i行和第j行元素的更新
                double tempCoefficient = coefficientMatrix[j][i] / coefficientMatrix[i][i];
                for (int k = i; k < column; k++) {//进行对应两行元素的更新
                    coefficientMatrix[j][k] = coefficientMatrix[j][k] - tempCoefficient * coefficientMatrix[i][k];
                    if (String.valueOf(coefficientMatrix[j][k]).equals("NaN")) {
                        coefficientMatrix[j][k] = 0.0;
                    } else if (Math.abs(coefficientMatrix[j][k]) < 1.0E-16) {
                        coefficientMatrix[j][k] = 0.0;
                    }
                    //printFullMatrix();
                }
            }
        }
        for (int i = dimension - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i; j < dimension; j++) {
                sum += results[j] * coefficientMatrix[i][j];
            }
            results[i] = (coefficientMatrix[i][column - 1] - sum) / coefficientMatrix[i][i];
        }
        printResults();
    }

    private void printResults() {
        for (int i = 0; i < dimension; i++) {
            System.out.println(results[i]);
        }
    }

    private void initResults() {
        for (int i = 0; i < dimension; i++) {
            results[i] = 0;
        }
    }

    private void executeJMethod() {
        initResults();
        double error[] = new double[dimension];
        double tempValue[] = new double[dimension];
        int count = 0;
        while (true) {
            count++;
            //System.out.println("第"+count+"次迭代");
            for (int i = 0; i < dimension; i++) {
                double sum = 0;
                for (int j = 0; j < dimension; j++) {
                    if (j == i) {
                        continue;
                    }
                    sum += results[j] * coefficientMatrix[i][j];
                }
                double newValue = (coefficientMatrix[i][column - 1] - sum) / coefficientMatrix[i][i];
                error[i] = Math.abs(newValue - results[i]);
                tempValue[i] = newValue;
            }
            for (int i = 0; i < dimension; i++) {
                results[i] = tempValue[i];
            }
            boolean isReachTermination = true;
            for (int i = 0; i < dimension; i++) {
                if (error[i] > 0.00005) {
                    //System.out.println(error[i]);
                    isReachTermination = false;
                    //break;
                }
            }

            if (isReachTermination) {
                System.out.println("共经过" + count + "次迭代");
                printResults();
                break;
            }

            boolean isInfinity = false;
            for (int i = 0; i < dimension; i++) {
                if (String.valueOf(results[i]).equalsIgnoreCase("Infinity")) {
                    isInfinity = true;
                    break;
                }
            }
            if (isInfinity) {
                System.out.println("Jacobi迭代法不收敛，无法求出需要的解");
                break;
            }
        }
    }

    private void executeGSMethod() {
        initResults();
        double error[] = new double[dimension];
        int count = 0;
        while (true) {
            count++;
            for (int i = 0; i < dimension; i++) {
                double sum = 0;
                for (int j = 0; j < dimension; j++) {
                    if (j == i) {
                        continue;
                    }
                    sum += results[j] * coefficientMatrix[i][j];
                }
                double newValue = (coefficientMatrix[i][column - 1] - sum) / coefficientMatrix[i][i];
                error[i] = Math.abs(newValue - results[i]);
                results[i] = newValue;
            }
            boolean isReachTermination = true;
            for (int i = 0; i < dimension; i++) {
                if (error[i] > 0.00005) {
                    //System.out.println(error[i]);
                    isReachTermination = false;
                    break;
                }
            }
            if (isReachTermination) {
                System.out.println("共经过" + count + "次迭代");
                printResults();
                break;
            }

        }
    }

    private void executeSORMethod() {
        initResults();
        double error[] = new double[dimension];
        int count = 0;
        while (true) {
            count++;
            for (int i = 0; i < dimension; i++) {
                double sum = 0;
                for (int j = 0; j < dimension; j++) {
                    sum += results[j] * coefficientMatrix[i][j];
                }
                double newValue = results[i] + (1.46 * (coefficientMatrix[i][column - 1] - sum)) / coefficientMatrix[i][i];
                error[i] = Math.abs(newValue - results[i]);
                results[i] = newValue;
            }
            boolean isReachTermination = true;
            for (int i = 0; i < dimension; i++) {
                if (error[i] > 0.00005) {
                    //System.out.println(error[i]);
                    isReachTermination = false;
                    break;
                }
            }
            if (isReachTermination) {
                System.out.println("共经过" + count + "次迭代");
                printResults();
                break;
            }
        }
    }

    private void printFullMatrix() {
        System.out.println("#########################################");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(coefficientMatrix[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("#########################################");
    }

    private void setInitValue() {


        for (int i = 0; i < dimension; i++) {
            coefficientMatrix[i][column - 1] = 1;
        }
    }

    private void setNewCoefficientMartrix(Double minimalCommonMultiple) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                coefficientMatrix[i][j] = minimalCommonMultiple / coefficientMatrix[i][j];
            }
        }
    }

    private Double executeMathematicOperation(Double firstNumber, char operation, Double secondNumber) {
        switch (operation) {
            case '+':
                return firstNumber + secondNumber;
            case '-':
                return firstNumber - secondNumber;
            case '*':
                return firstNumber * secondNumber;
            case '/':
                return firstNumber / secondNumber;
            default:
                return 0.0;
        }
    }

    private boolean isNumber(String input) {
        if (input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3")) {
            return true;
        } else if (input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7")) {
            return true;
        } else if (input.equals("8") || input.equals("9")) {
            return true;
        } else {
            return false;
        }
    }

    private void clearCoefficientMatrix() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                coefficientMatrix[i][j] = 0;
            }
        }
    }

    private void clearResults() {
        for (int i = 0; i < dimension; i++) {
            results[i] = 0;
        }
    }

    private void setCoefficientMatrix(String denominator) {
        clearCoefficientMatrix();
        clearResults();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                //先填入一行的元素，再填第二行的元素
                Double value = 0.0;
                Queue<Character> characters = new LinkedBlockingQueue<Character>();
                for (int w = 0; w < denominator.length(); w++) {
                    //System.out.println(denominator.charAt(w));
                    characters.add(denominator.charAt(w));
                }
                characters.add('#');
                //System.out.println();
                //showStack(characters);
                //showQueue(characters);
                char tempChar;
                Double number1 = 0.0;
                Double number2 = 0.0;
                char operator = '+';
                while (true) {
                    tempChar = characters.remove();
                    //System.out.println(tempChar);
                    // System.out.println(tempChar == '#');
                    if (tempChar == '#') {
                        //System.out.println(1);
                        break;
                    }

                    if (isNumber(String.valueOf(tempChar)) || tempChar == 'i' || tempChar == 'j') {
                        if (tempChar == 'i') {
                            number1 = Double.parseDouble(String.valueOf(i));
                        } else if (tempChar == 'j') {
                            number1 = Double.parseDouble(String.valueOf(j));
                        } else {
                            number1 = Double.parseDouble(String.valueOf(tempChar - '0'));
                        }
                    } else {
                        operator = tempChar;
                        tempChar = characters.remove();
                        if (tempChar == 'i') {
                            number2 = Double.parseDouble(String.valueOf(i));
                        } else if (tempChar == 'j') {
                            number2 = Double.parseDouble(String.valueOf(j));
                        } else {
                            number2 = Double.parseDouble(String.valueOf(tempChar - '0'));
                        }
                        double result = executeMathematicOperation(number1, operator, number2);
                        //System.out.println("result->" + result);
                        number1 = result;
                        //System.out.println("number1->" + number1);
                    }
                }
                //System.out.println("final->" + number1);
                coefficientMatrix[i][j] = number1 + 2;
                boolean isExist = false;
                for (double coefficient : coefficientArrayList) {
                    if (coefficient == coefficientMatrix[i][j]) {
                        isExist = true;
                    }
                }
                if (!isExist) {
                    coefficientArrayList.add(coefficientMatrix[i][j]);
                }
            }
        }
        //printCoefficientMatrix();
    }

    private void printCoefficientMatrix() {
//        System.out.println("系数矩阵中每一项的值为");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
//                System.out.print("coefficientMatrix[" + i + "][" + j + "]->" + coefficientMatrix[i][j]);
                System.out.print(coefficientMatrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private void showStack(Stack<Character> stack) {
        while (true) {
            char element = stack.pop();
            System.out.println(element);
            if (element == '#') {
                break;
            }
        }
    }

    private void showQueue(Queue<Character> queue) {
        while (true) {
            char element = queue.remove();
            System.out.println(element);
            if (element == '#') {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String dimension;
        System.out.println("请输入一个迭代公式，输入0则使用默认迭代公式(i/i+j-1)");
        input = scanner.nextLine();
        String formula;
        if (input.equals("0")) {
            formula = "1/i+j-1";
        } else {
            formula = input;
        }

        System.out.println("请输入迭代矩阵的维数，输入0则使用默认的维数(6)");
        input = scanner.nextLine();
        if (input.equals("0")) {
            dimension = "6";
        } else {
            dimension = input;
        }
        HilbertMatrix hilbertMatrix = new HilbertMatrix(Integer.parseInt(dimension));

        hilbertMatrix.setIterativeFormula(formula);

        hilbertMatrix.printFullMatrix();

        while (true) {
            System.out.println("输入你想要进行求解的方法，请输入以下的一个字母，G(Gauss)，J(Jacobi),GS(Gauss-Seidel),SOR,Q(quit)");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("G")) {
                hilbertMatrix.executeGaussMethod();
                hilbertMatrix.setIterativeFormula(formula);//还原
                //hilbertMatrix.printFullMatrix();
            } else if (input.equalsIgnoreCase("J")) {
                hilbertMatrix.executeJMethod();
                hilbertMatrix.setIterativeFormula(formula);//还原
                //hilbertMatrix.printFullMatrix();
            } else if (input.equalsIgnoreCase("GS")) {
                hilbertMatrix.executeGSMethod();
                hilbertMatrix.setIterativeFormula(formula);//还原
                //hilbertMatrix.printFullMatrix();
            } else if (input.equalsIgnoreCase("SOR")) {
                hilbertMatrix.executeSORMethod();
                hilbertMatrix.setIterativeFormula(formula);//还原
                //hilbertMatrix.printFullMatrix();
            } else if (input.equalsIgnoreCase("Q")) {
                System.out.println("bye bye");
                return;
            } else {
                System.out.println("输入的方法有误");
                continue;
            }
        }
        //hilbertMatrix.printFullMatrix();
    }
}
