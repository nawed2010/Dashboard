package com;

/**
 * Created by nawed2010 on 30/08/21.
 */
public class PractiseMain {


    public static void main(String... args) {
        System.out.println("Sorting Algos");
        int[] array = {6, 4, 8, 1, 3, 9, 2, 5, 7};
        print(array);
        // bubbleSort(array, array.length);
        // selectionSort(array, array.length);
        insertionSort(array, array.length);
        // mergeSort(array);
        print(array);
    }

    // Divide array in two part - left sorted and right unsorted
    private static void insertionSort(int[] arr, int n) {
        for(int i = 1; i < n ; i++) {
            int j = i - 1;
            int value = arr[i];
            while (j >= 0 && arr[j] > value) {
                // Swap
                int temp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }

    // Swap two adjacent elements resulting in biggest element ending up at the end
    private static void bubbleSort(int[] arr, int n) {
        for(int i = 1 ; i < n ; i ++) {
            boolean swapDone = false;
            for(int j = 0; j < n - i; j++) {
                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapDone = true;
                }
            }
            if(!swapDone) {
                return; // If in a pass even a single swap is not done then array is already sorted
            }
        }
    }


    // Find the smallest and keep in the left side -- left side is sorted always
    private static void selectionSort(int[] arr, int n) {
        for(int i = 0 ; i < n ; i++) {
            int smallestIndex = i;

            for(int j = i + 1; j < n; j++) {
                if(arr[j] < arr[smallestIndex]) {
                    smallestIndex = j;
                }
            }

            // Swap smallest with ith element
            int value = arr[i];
            arr[i] = arr[smallestIndex];
            arr[smallestIndex] = value;
        }
    }

    /**
     * Print the array
     * @param array
     */
    private static void print(int[] array) {
        for (int a : array) {
            System.out.print(" " + a);
        }
        System.out.println("\n");
    }
}
