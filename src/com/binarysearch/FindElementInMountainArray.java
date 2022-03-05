package com.binarysearch;

public class FindElementInMountainArray {

    public static void main(String[] args) {
        int arr[] = {1, 2, 5, 8, 12, 9, 7, 3};
        int target = 4;

        int peakIndex = findPeakIndex(arr);
        System.out.println("Peak Index: " + peakIndex);

        // find the element in both side
        int ascIndex = binarySearch(arr, target, 0, peakIndex, true);
        int descIndex = binarySearch(arr, target, peakIndex + 1, arr.length - 1, false);
        System.out.println("Asc Index: " + ascIndex + " Desc Index: " + descIndex);
        int finalAns = -1;
        if (ascIndex != -1)
            finalAns = ascIndex;
        if (descIndex != -1)
            finalAns = descIndex;
        System.out.println("Final Answer: " + finalAns);
    }

    private static int binarySearch(int[] arr, int target, int s, int e, boolean isAscPart) {
        int ans = -1;
        int start = s;
        int end = e;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                ans = mid;
                break;
            }
            if (arr[mid] > target) {
                if (isAscPart) {
                    // Go to left
                    end = mid - 1;
                } else {
                    // Go to right
                    start = mid + 1;
                }
            } else {
                if (isAscPart) {
                    // Go to the right
                    start = mid + 1;
                } else {
                    // Go to the left
                    end = mid - 1;
                }
            }
        }

        return ans;
    }

    private static int findPeakIndex(int[] arr) {
        int start = 0;
        int end = arr.length;
        while (start < end) {
            int mid = (start + end) / 2;
            // If in asc part of the arr move to the right
            // If in desc part of the arr move to the left

            if (arr[mid] < arr[mid + 1]) {
                // In the asc part -- then move to the right
                start = mid + 1; // +1 because mid can never be the answer as the answer should be the highest element in the arr
            } else {
                // In the desc part -- then move to the left
                end = mid; // mid can be the possible answer hence not skipping it by doing +1
            }
        }
        return start; // as in this point start and end is equal both pointing to the peak index
    }

    // Wrong answer to do in a single pass
    private static int findElement(int[] arr, int target) {
        int ans = -1;
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {

            int mid = (start + end) / 2;

            //Check if it's a match then exit
            if (arr[mid] == target) {
                ans = mid;
                break;
            }

            // First figure out which part of the array we are in
            boolean isAscPart = true;
            if (arr[mid] > arr[mid + 1])
                isAscPart = false;

            if (arr[mid] > target) {
                // Go to the left if in asc part
                if (isAscPart) {
                    end = mid - 1;
                } else {
                    start = mid + 1; // Go to right
                }
            } else {
                // Go to the right if in asc part
                if (isAscPart) {
                    start = mid + 1;
                } else {
                    end = mid - 1; // Go to left
                }
            }
        }

        return ans;
    }
}
