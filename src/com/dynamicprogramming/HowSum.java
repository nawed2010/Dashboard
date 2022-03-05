package com.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HowSum {

    public static void main(String[] args) {
        System.out.println(howSum(11, new int[]{5, 3, 4, 7}));
        System.out.println(howSumMemo(7, new int[]{5, 3, 4, 7}, new HashMap<Integer, List<Integer>>()));
    }

    // Time Complexity O(n) = n ^ m where n = arr.length and m = target
    // Space Complexity O(n) = m where n = arr.length and m = target assume arr contains element 1
    private static List<Integer> howSum(int target, int[] arr) {
        if(target == 0)
            return new ArrayList<Integer>();
        if(target < 0)
            return null;
        List<Integer> result = null;

        for(int num : arr) {
            List<Integer> temp = howSum(target - num, arr);
            if(temp != null) {
                temp.add(num);
                return temp;
            }
        }

        return result;
    }


    private static List<Integer> howSumMemo(int target, int[] arr, Map<Integer, List<Integer>> memo) {
        if(memo.containsKey(target))
            return memo.get(target);

        if(target == 0)
            return new ArrayList<Integer>();
        if(target < 0)
            return null;
        List<Integer> result = null;

        for(int num : arr) {
            List<Integer> temp = howSumMemo(target - num, arr, memo);
            if(temp != null) {
                temp.add(num);
                memo.put(target, temp);
                return temp;
            }
        }
        memo.put(target, result);
        return result;
    }


}
