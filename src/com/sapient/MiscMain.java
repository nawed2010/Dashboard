package com.sapient;

public class MiscMain {

    public static void main(String[] args) {
        int targetSum = 13;
        int[] arr = {3, 2};
        System.out.println(canSum(targetSum, arr));
    }

    private static boolean canSum(int target, int[] arr) {
        if (target == 0)
            return true;
        if(target < 0)
            return false;

        boolean result = false;

        for (int i = 0; i < arr.length; i++) {
//            if (target % arr[i] == 0) {
//                return true;
//            } else {
//                return canSum(target - arr[i], arr);
//            }

            result = result || canSum(target - arr[i], arr);
        }
        return result;
    }
}
