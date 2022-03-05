package com.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CanSum {

    public static void main(String[] args) {
        System.out.println(canSum(7, new int[]{5, 3, 4, 7}));
        System.out.println(canSumMemo(7, new int[]{5, 3, 4, 7}, new HashMap<Integer, Boolean>()));
    }

    // Time Complexity O(n) = n ^ m where n = arr.length and m = target
    // Space Complexity O(n) = m where n = arr.length and m = target assume arr contains element 1
    private static boolean canSum(int target, int[] arr) {
        if (target == 0)
            return true;
        if (target < 0)
            return false;

        for (int num : arr) {
            if (canSum(target - num, arr)) {
                return true; // Exit early if condition met.
            }
        }

        return false;
    }

    // Time Complexity O(n) = n * m where n = arr.length and m = target
    // Space Complexity O(n) = m where n = arr.length and m = target assume arr contains element 1
    private static boolean canSumMemo(int target, int[] arr, Map<Integer, Boolean> memo) {
        if (memo.containsKey(target))
            return memo.get(target);
        if (target == 0)
            return true;
        if (target < 0)
            return false;

        for (int num : arr) {
            boolean result = canSum(target - num, arr);
            memo.put(target, result);
            if (result) {
                return true; // Exit early if true
            }
        }
        memo.put(target, false);
        return false;
    }


}
