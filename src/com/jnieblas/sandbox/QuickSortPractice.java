package src.com.jnieblas.sandbox;

import java.util.Arrays;

/**
 * Idea is to partition the array into sub problems.
 * Sort should happen in place regardless of recursive substack level,
 * so this is not a multi-thread-able algorithm.
 */
public class QuickSortPractice {
    public void quicksort(int[] targetArr, int low, int high) {
        if (low < high) {
            // partition should return the pivot index, dictating where our low / high goes
            int pivot = partition(targetArr, low, high);
            // will need to figure out which value we pivot on exactly
            quicksort(targetArr, low, pivot - 1);
            quicksort(targetArr, pivot + 1, high);
        }
    }

    private int partition(int[] targetArr, int low, int high) {
        int pivotVal = targetArr[high];
        int ans = low;

        for (int i = low; i < high; i++) {
            int currVal = targetArr[i];

            if(currVal < pivotVal) {
                int temp = targetArr[ans];
                targetArr[ans] = currVal;
                targetArr[i] = temp;
                ans++;
            }
        }

        int temp = targetArr[ans];
        targetArr[ans] = high;
        targetArr[high] = temp;

        return ans;
    }

    public static void main(String[] args) {
        QuickSortPractice sorter = new QuickSortPractice();
        int[] arr1 = new int[]{94,2,1,3,347};
        sorter.quicksort(arr1, 0, arr1.length - 1);

        System.out.println(Arrays.toString(arr1));
    }
}
