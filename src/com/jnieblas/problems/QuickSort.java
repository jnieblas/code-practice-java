package src.com.jnieblas.problems;

import java.util.Arrays;

public class QuickSort {


    /**
     * Executes recursively, if & only if the low index < high index
     * @param array
     * @param low
     * @param high
     */
    public static void quickSort(int[] array, int low, int high) {
        // If low >= high, then we have sorted the array, since we've merged the low & high pointers
        if (low < high) {
            int pivotIndex = sortSubArray(array, low, high);

            // Merge the sub arrays via recursion
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    /**
     * Loop through the array, swapping values lower than the pivot with current min index.
     * @param arr
     * @param lowIndex
     * @param highIndex
     * @return the index of the next pivot
     */
    public static int sortSubArray(int[] arr, int lowIndex, int highIndex) {
        // pivot on the "high" value
        int pivotValue = arr[highIndex];
        // Start replacing at the "low" value
        int nextLowIndex = lowIndex;

        // Loop from low to high, but don't deal with high value
        for (int currentIndex = lowIndex; currentIndex < highIndex; currentIndex++) {
            // if low val is less than the pivot
            int currentValue = arr[currentIndex];

            if (currentValue < pivotValue) {
                int previousValue = arr[nextLowIndex]; // get value at the min index right now, save it
                arr[nextLowIndex] = currentValue; // replace the old val with the new min val
                arr[currentIndex] = previousValue; // put the old val where the min val came from, aka j

                nextLowIndex++; // increment next min Index
            }
        }

        // Replace our lowest value with our highest.
        // if nextLowIndex != highIndex, then that means arr[highIndex] is lower than arr[nextLowIndex]
        // get val at nextLowIndex (should be highIndex, according to for loop
        int temp = arr[nextLowIndex];
        // replace val with the pivot
        arr[nextLowIndex] = arr[highIndex];
        arr[highIndex] = temp;

        // What does the pivot index tell us?
        // Ultimately, it tells us what we just solved for
        // It tells the parent method what we just swapped for & what we want to swap for next
        // If we swap for 3, then we want to do 0, 2 or 1, 3 next
        // that way, we can recursively swap values within the sub-array, ultimately solving at least one value per call
        return nextLowIndex;
    }

    public static void main(String[] args) {
        int[] array = {12, 4, 5, 6, 7, 3, 1, 15};
        int n = array.length;
        quickSort(array, 0, n - 1);

        System.out.println("Sorted array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
