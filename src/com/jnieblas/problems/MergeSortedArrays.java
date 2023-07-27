package src.com.jnieblas.problems;

import java.util.Arrays;

public class MergeSortedArrays {
    // non-decreasing order -> increasing order

    public static void main(String[] args) {
        MergeSortedArrays runner = new MergeSortedArrays();
        int[] main = new int[]{0,1,4,5,0,0,0};
        runner.merge(main, 4, new int[]{1,3,78}, 3);

        System.out.println("Actual: " + Arrays.toString(main));
        System.out.println("Expecting: [0, 1, 1, 3, 4, 5, 78]");
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // new array sorted in increasing order
        // nums1 should contain the answer, length = m + n
        // we need to take numbers from nums2 & insert them into nums1
        // numbers from nums1 may be replaced, or bumped over to the right
        // if a val gets inserted between two other vals, all vals to the right must be
        // pushed over.

        // can we swap values?
        // [1,2,3,0,0,0], [1,1,4]
        // [1,1,3,0,0,0], [2,1,4]
        // [1,1,1,0,0,0], [2,3,4], we hit zero, so add everything in second array in that order
        // [1,1,1,2,3,4], []


        // [1,4,6,0,0], [4,8]
        // [1,4,6,0,0], [4,8] - 1 < 4, so nothing happens
        // [1,4,6,0,0], [4,8] - 4 = 4, so nothing happens
        // [1,4,4,0,0], [6,8] - 6 > 4, so we swap
        // encountered zeros, so fill in last two spots

        // [3,5,7,0,0], [1,2]
        // [1,5,7,0,0], [3,2]
        // [1,2,7,0,0], [3,5]
        // [1,2,3,0,0], [7,5] <- then this becomes broken...


        // try comparing last of each, then filling in array backwards..
        // [3,5,7,0,0], [1,2]
        // [3,5,0,0,7], [1,2], compare 7 & 2
        // [3,0,0,5,7], [1,2], compare 5 & 2
        // this method preserves the values in first array, while allowing us to fill array in linear time

        /*
            what we need:
            two pointers: one for tracking movement through 1st, one for tracking movement through 2nd
            This will be done in linear time, so we need to use a counter to keep track of our index as we go through the array.
        */

        int p1 = m - 1; // m - 1, because we need to know where 1st ends, -1 for index
        int p2 = n - 1;

        for (int i = m + n - 1; i >= 0; i--) {
            // Need to check p1 & p2 >= 0, otherwise they have been exhausted
            if (p1 >= 0 && p2 >= 0) {
                int var1 = nums1[p1];
                int var2 = nums2[p2];

                // account for equals
                if (var1 > var2) {
                    nums1[i] = var1;
                    p1--;
                } else {
                    nums1[i] = var2;
                    p2--;
                }
            } else if (p2 >= 0){
                nums1[i] = nums2[p2];
                p2--;
            }

        }
    }
}
