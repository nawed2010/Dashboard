package com.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Practise {

    public static void main(String[] args) {
        amazonOnlineAssesmentQn();
    }

    private static void amazonOnlineAssesmentQn() {
        List<Integer> list = Arrays.asList(1, 5, 4);
        int k = 3;
        int count = 0;
        int smallest = 0;
        int largest = 0;
        for (int i = 0; i < list.size(); i++) {
            //count++;
            smallest = largest = list.get(i);
            for (int j = i; j < list.size(); j++) {
                if (list.get(j) > largest)
                    largest = list.get(j);
                if (list.get(j) < smallest)
                    smallest = list.get(j);
                System.out.println("Debugging (" + i + "," + j + ") smallest :" + smallest + " largest: " + largest + " Diff: " + (largest - smallest));
                if (largest - smallest < k) {
                    count++;
                    System.out.println("Finalised : (" + i + "," + j + ") smallest :" + smallest + " largest: " + largest);
                } else {
                    break;
                }
            }
        }
        System.out.println("Final Count: " + count);
    }

    private static List<Integer> bestSum(int target, int[] arr, Map<Integer, List<Integer>> memo) {
        if (memo.containsKey(target))
            return memo.get(target);
        if (target < 0)
            return null;
        if (target == 0)
            return new ArrayList<>();
        List<Integer> result = null;
        for (int num : arr) {
            List<Integer> temp = bestSum(target - num, arr, memo);
            if (temp != null) {
                List<Integer> combinations = new ArrayList<>(temp);
                combinations.add(num);
                if (result == null || result.size() > combinations.size())
                    result = combinations;
            }
        }
        memo.put(target, result);
        return result;
    }

    private static List<Integer> howSum(int target, int[] arr) {
        if (target == 0) {
            return new ArrayList<Integer>();
        }
        if (target < 0) {
            return null;
        }
        List<Integer> list = null;
        for (int num : arr) {
            List<Integer> temp = howSum(target - num, arr);
            if (temp != null) {
                List<Integer> result = new ArrayList<>(temp);
                result.add(num);
                return result;
            }
        }
        return list;
    }

    private static boolean canSum(int target, int[] arr) {
        if (target == 0)
            return true;
        if (target < 0)
            return false;
        boolean result = false;
        for (int num : arr) {
            result = canSum(target - num, arr);
            if (result)
                return result;
        }
        return result;
    }

}
