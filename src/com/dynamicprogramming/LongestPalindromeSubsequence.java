package com.dynamicprogramming;

//https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromeSubsequence {

    public static void main(String[] args) {
        String str = "babad";
        int length = 1;
        for (int i = 1; i < str.length(); i++) {
            int l = findLPS(str, 1, i);
            if (l > length)
                length = l;
        }

        System.out.println(length);
    }

    private static int findLPS(String str, int appender, int i) {
        int length = 1;
        if (i - appender >= 0 && i + appender + 1 < str.length()) {
            String temp = str.substring(i - appender, i + appender + 1);
            if (isPalindrome(temp)) {
                if (temp.length() > length)
                    length = temp.length();
                if (i - appender + 1 >= 0 && i + appender + 1 + 1 < str.length()) {
                    int newL = findLPS(str, appender + 1, i);
                    if (newL > length)
                        length = temp.length();
                }
            }
        }

        return length;
    }

    private static boolean isPalindrome(String str) {
        StringBuffer sbf = new StringBuffer(str);
        return sbf.reverse().toString() == str;
    }
}
