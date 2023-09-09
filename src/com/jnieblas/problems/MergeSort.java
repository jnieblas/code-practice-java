package src.com.jnieblas.problems;

import java.util.Arrays;

/**
 * General Idea:
 * Two functions to solve:
 * mergeSort() & merge()
 * mergeSort() will recursively subdivide arrays
 * merge() will take the results & combine them into current array for the
 * current level within the recursive substack.
 */
public class MergeSort {

    public void mergeSort(int[] targetArr) {
        int targetArrLength = targetArr.length;

        if (targetArrLength == 1) {
            return;
        }

        int mid = targetArrLength / 2;
        int[] left = new int[mid];
        int[] right = new int[targetArrLength - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = targetArr[i];
        }

        for (int i = mid; i < targetArrLength; i++) {
            right[i - mid] = targetArr[i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(targetArr, left, right);
    }

    public void merge(int[] currentArr, int[] left, int[] right) {
        int l = 0, r = 0, curr = 0;
        int leftLength = left.length;
        int rightLength = right.length;

        while (l < leftLength && r < rightLength) {
            if (left[l] < right[r]) {
                currentArr[curr++] = left[l++];
            } else {
                currentArr[curr++] = right[r++];
            }
        }

        while (l < leftLength) {
            currentArr[curr++] = left[l++];
        }

        while (r < rightLength) {
            currentArr[curr++] = right[r++];
        }
    }

    public static void main(String[] args) {
        MergeSort sorter = new MergeSort();
        int[] sample1 = new int[]{347,3,1,94,2};
        sorter.mergeSort(sample1);
        System.out.println(Arrays.toString(sample1));
    }
}
