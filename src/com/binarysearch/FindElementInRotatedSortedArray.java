package com.binarysearch;

public class FindElementInRotatedSortedArray {
    public static void main(String[] args) {
        int arr[] = {4, 5, 6, 7, 8, 0, 1, 2};
        int target = 0;
        System.out.println(findElement(arr, target));

        // Another simple solution
        // Find the pivot element of the array -- pivot also means max element
        // Search in left side of max
        // Search in right side of max
    }

    private static int findElement(int[] arr, int target) {
        int ans = -1;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                ans = mid;
                break;
            }

            if (arr[mid] > target) {
                if (arr[start] > target) {
                    // Go to right

                } else {
                    // Go to left
                }
            }

        }
        return ans;
    }
}
