package com.dynamicprogramming;

import java.util.*;
// https://leetcode.com/problems/longest-increasing-subsequence/submissions/
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10,9,2,5,3,7,101,18);
        System.out.println("Length of Longest subsequence: " + findLISModerator(list));
        System.out.println("Length of Longest subsequence Memo: " + findLISModeratorMemo(list));
    }

    private static List<Integer> findLISModerator(List<Integer> list) {
        List<Integer> finalResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i ++) {
            List<Integer> temp = findLIS(list, i);
            if (temp.size() > finalResult.size())
                finalResult = temp;
        }
        return finalResult;
    }

    private static List<Integer> findLISModeratorMemo(List<Integer> list) {
        List<Integer> finalResult = new ArrayList<>();
        Map<Integer, List<Integer>> memo = new HashMap<>();
        for (int i = 0; i < list.size(); i ++) {
            List<Integer> temp = findLIS(list, i, memo);
            if (temp.size() > finalResult.size())
                finalResult = temp;
        }
        return finalResult;
    }

    private static List<Integer> findLIS(List<Integer> list, int currentIndex) {
        if (currentIndex >= list.size())
            return new ArrayList<>();

        List<Integer> finalResult = new ArrayList<>();

        for (int i = currentIndex + 1; i < list.size(); i++) {
            if (list.get(currentIndex) < list.get(i)) {
                List<Integer> temp = findLIS(list, i);
                if (!temp.isEmpty()) {
                    if (temp.size() > finalResult.size())
                        finalResult = new ArrayList<>(temp);
                }
            }
        }
        finalResult.add(list.get(currentIndex));
        return finalResult;
    }

    private static List<Integer> findLIS(List<Integer> list, int currentIndex, Map<Integer, List<Integer>> memo) {
        if(memo.containsKey(currentIndex))
            return memo.get(currentIndex);

        if (currentIndex >= list.size())
            return new ArrayList<>();

        List<Integer> finalResult = new ArrayList<>();

        for (int i = currentIndex + 1; i < list.size(); i++) {
            if (list.get(currentIndex) < list.get(i)) {
                List<Integer> temp = findLIS(list, i, memo);
                if (!temp.isEmpty()) {
                    if (temp.size() > finalResult.size())
                        finalResult = new ArrayList<>(temp);
                }
            }
        }
        finalResult.add(list.get(currentIndex));
        memo.put(currentIndex, finalResult);
        return finalResult;
    }

}
