package com.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoviesOnFlight {
    //https://leetcode.com/discuss/interview-question/313719/Amazon-or-Online-Assessment-2019-or-Movies-on-Flight
    public static void main(String[] args) {
       Integer[] movieDurations = {90, 85, 75, 60, 120, 150, 125};
       int d = 250;
       d = 250 - 30; // As we need to finish the movie before 30 mins
       List<Integer> list = Arrays.asList(movieDurations);
       System.out.println(findPair(Arrays.asList(movieDurations), d, 0));

    }

    private static List<Integer> findPair(List<Integer> list, int max, int current) {
        if (list.size() <= 0)
            return null;
        if(current > max)
            return null;
        if (current == max)
            return new ArrayList<Integer>();

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if(i == j) continue;
                current =  list.get(i) + list.get(j);

            }
        }

        return null;

    }

}
