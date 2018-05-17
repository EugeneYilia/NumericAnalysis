package com.EugeneStudio.numberAnalysis.algorithm.hilbertMatrix;


import java.util.ArrayList;
import java.util.Collections;

public class Utils {
    private Utils() {}

    public static Double getMinimalCommonMultiple(ArrayList<Double> numbers) {
        if (numbers.size() == 0) {
            return -1.0;
        }
        Collections.sort(numbers);//升序排序
        if (numbers.size() == 1) {
            return numbers.get(0);
        } else if (numbers.size() == 2) {
            Double maxNumber1 = numbers.get(numbers.size() - 1);
            Double maxNumber2 = numbers.get(numbers.size() - 2);
            if (maxNumber1 % maxNumber2 == 0) {
                return maxNumber2;
            } else {
                return numbers.get(0) * numbers.get(1);
            }
        } else {
            Double maxNumber1 = numbers.get(numbers.size() - 1);
            Double maxNumber2 = getSecondNumber(maxNumber1, numbers);
            if (maxNumber2 == -1) {
                return maxNumber1;
            } else {
                for (int i = 1; ; i++) {
                    Double minimalCommonMultiple = i * maxNumber1 * maxNumber2;
                    if (isMinimalCommonMultiple(minimalCommonMultiple, numbers)) {
                        return minimalCommonMultiple;
                    }
                }
            }
        }
    }

    private static Double getSecondNumber(Double maxNumber, ArrayList<Double> numbers) {//找出用于求最小公倍数的第二个数字
        for (int i = numbers.size() - 2; i >= 0; i--) {
            if (maxNumber % numbers.get(i) != 0) {
                return numbers.get(i);
            }
        }
        return -1.0;
    }

    private static boolean isMinimalCommonMultiple(Double number, ArrayList<Double> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (number % numbers.get(i) != 0) {
                return false;
            }
        }
        return true;
    }

    private void TestMinimalCommonMultiple(){
        ArrayList arrayList = new ArrayList();
        arrayList.add(1L);
        arrayList.add(2L);
        arrayList.add(3L);
        arrayList.add(4L);
        arrayList.add(5L);
        arrayList.add(6L);
        arrayList.add(7L);
        arrayList.add(8L);
        arrayList.add(9L);
        arrayList.add(10L);
        arrayList.add(11L);
        System.out.println(getMinimalCommonMultiple(arrayList));
    }


}
