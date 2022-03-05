package com.dynamicprogramming;

import java.util.*;

public class BestSum {

    public static void main(String[] args) {
        System.out.println(bestSum(12, new int[]{4, 2}));
        System.out.println(bestSumMemo(12, new int[]{4, 2}, new HashMap<Integer, List<Integer>>()));
    }

    // Time Complexity O(n) = n ^ m * m where n = arr.length and m = target
    // Space Complexity O(n) = m ^ 2 where n = arr.length and m = target assume arr contains element 1
    private static List<Integer> bestSum(int target, int[] arr) {
        if (target == 0) {
            return new ArrayList<Integer>();
        }
        if (target < 0)
            return null;

        List<List<Integer>> holder = new ArrayList<>();
        List<Integer> result = null;

        for (int num : arr) {
            List<Integer> temp = bestSum(target - num, arr);
            if (temp != null) {
                temp.add(num);
                holder.add(temp);
            }
        }

        if (!holder.isEmpty()) {
            for (List<Integer> list : holder) {
                if (result == null || list.size() < result.size())
                    result = list;
            }
        }

        return result;
    }

    private static List<Integer> bestSumMemo(int target, int[] arr, Map<Integer, List<Integer>> memo) {
        if (memo.containsKey(target))
            return memo.get(target);

        if (target == 0) {
            return new ArrayList<Integer>();
        }
        if (target < 0)
            return null;

        //List<List<Integer>> holder = new ArrayList<>();
        List<Integer> result = null;

        for (int num : arr) {
            List<Integer> temp = bestSumMemo(target - num, arr, memo);
            if (temp != null) {
                List<Integer> combination = new ArrayList<>(temp);
                combination.add(num);
                if((result == null || combination.size() < result.size()))
                    result = combination;
            }
        }

        memo.put(target, Collections.unmodifiableList(result));
        System.out.println(memo);
        return result;
    }

}
