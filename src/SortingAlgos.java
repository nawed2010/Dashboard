import com.sun.source.tree.BinaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nawed2010 on 16/07/19.
 */
public class SortingAlgos {

    public static void main(String... args) {
        System.out.println("Sorting Algos");
        int[] array = {6, 4, 8, 1, 3, 9, 2, 5, 7};
        print(array);
        //bubbleSort(array, array.length);
        //selectionSort(array, array.length);
        //insertionSort(array, array.length);
        mergeSort(array);
        print(array);
    }

    /**
     * Check for adjacent element, in each pass largest element will come to its due location, with each pass
     * range of inner loop should decrease as largest element is geeting placed at its position
     *
     * @param
     */
    private static void bubbleSort(int[] a, int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n-i; j++) {
                if(a[j-1] > a[j]) {
                    //Swap as left element is greater than right element
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }

    }

    /**
     * Pick the smallest and swap with head, increment the counter resulting in head increment
     * @param a
     * @param n
     */
    private static void selectionSort(int[] a, int n) {
        for(int i = 0; i < n; i++) {
            int smallestIndex = i;
            for (int j = i+1; j < n; j++) {
                if (a[smallestIndex] > a[j]) {
                    smallestIndex = j;
                }
            }
            //Swap element at smallestIndex with Head which is at i
            int temp = a[i];
            a[i] = a[smallestIndex];
            a[smallestIndex] = temp;
        }
    }

    /***
     * Divide array in two parts, left part is sorted, right is unsorted, while moving an element from right
     * to left it will get in its due position on the left side
     * @param a
     * @param n
     */
    public static void insertionSort(int[] a, int n) {

        for(int i = 1 ; i < n; i++) {
            int key = a[i];
            int j = i-1;
            while(j > -1 && a[j] > key) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = key; //At this point j is already decreased i.e. less then one of its due position
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

    /*** 0 1 2 3 4 5 --Mid = 5/2 = 2 or 6/2 = 3
     * Merge sort
     */
    private static void mergeSort(int[] arr) {
        int n = arr.length;

        if(n < 2) {
            return;
        }

        int mid = n / 2;   //Find mid point for dividing the array

        int left[] = new int[mid];
        int right[] = new int[n-mid];

        for(int i = 0; i < mid ; i++) {
            left[i] = arr[i];
        }

        for(int i = mid; i < n ; i++) {
            right[i-mid] = arr[i];
        }

        mergeSort(left);
        mergeSort(right);
        merge(left, right, arr);
    }

    private static void merge(int[] left, int[] right, int[] arr) {
        int l = 0, r = 0, k = 0;

        while(l < left.length && r < right.length) {
            if(left[l] <= right[r]) {
                arr[k] = left[l];
                l++;
            } else {
                arr[k] = right[r];
                r++;
            }
            k++;
        }

        //If any of the list elements are still left
        while(l < left.length) {
            arr[k] = left[l];
            k++;
            l++;
        }

        while(r < right.length) {
            arr[k] = right[r];
            k++;
            r++;
        }
    }
}