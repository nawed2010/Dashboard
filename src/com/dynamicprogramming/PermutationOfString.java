package com.dynamicprogramming;

import java.util.List;

public class PermutationOfString {

    public static void main(String[] args) {
        System.out.println();
        permutation("", "ABC");
    }

    private static void permutation(String perm, String word) {
        if (word == null || word.isEmpty())
            System.out.println(perm);

        for (int i = 0; i < word.length(); i++) {
            permutation(perm + word.charAt(i), word.substring(0, i) + word.substring(i + 1, word.length()));
        }
    }

    private static void permutation(List<String> result, String word) {
        if (word == null || word.isEmpty())
            System.out.println(result);

        for (int i = 0; i < word.length(); i++) {
            result.add(String.valueOf(word.charAt(i)));
            permutation(result, word.substring(0, i) + word.substring(i + 1, word.length()));
        }
    }
}
