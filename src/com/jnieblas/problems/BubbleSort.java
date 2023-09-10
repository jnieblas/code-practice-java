package src.com.jnieblas.problems;

import java.util.Arrays;

public class BubbleSort {

    public void bubbleSort(int[] targetArr) {
        int currMax = targetArr.length;

        while (currMax > 1) {
            System.out.println("currMax: " + currMax);
            for (int i = 1; i < currMax; i++) {
                int currVal = targetArr[i];
                int prevVal = targetArr[i - 1];

                if (prevVal > currVal) {
                    targetArr[i] = prevVal;
                    targetArr[i - 1] = currVal;
                }
                System.out.println("i: " + i);
            }

            currMax--;
        }
    }

    public static void main(String[] args) {
        BubbleSort sorter = new BubbleSort();
        int[] arr1 = new int[]{94,2,1,3,347};
        sorter.bubbleSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
