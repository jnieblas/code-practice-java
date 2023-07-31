package src.com.jnieblas.problems;

import java.util.Arrays;

public class RemoveElements {

    public static void main(String[] args) {
        System.out.println();

        RemoveElements runner = new RemoveElements();
        int[] arr1 = new int[]{3,3,3,3};
        int[] copy1 = arr1.clone();
        int val1 = 3;
        int res1 = runner.removeElement(arr1, val1);
        System.out.printf("input: %s, %s\noutput: %s, %s%n%n",
                val1, Arrays.toString(copy1), res1, Arrays.toString(arr1));

        int[] arr2 = new int[]{3,2,2,3};
        int[] copy2 = arr2.clone();
        int val2 = 3;
        int res2 = runner.removeElement(arr2, val2);
        System.out.printf("input: %s, %s\noutput: %s, %s%n%n",
                val2, Arrays.toString(copy2), res2, Arrays.toString(arr2));

        int[] arr3 = new int[]{0,1,2,2,3,0,4,2};
        int[] copy3 = arr3.clone();
        int val3 = 2;
        int res3 = runner.removeElement(arr3, val3);
        System.out.printf("input: %s, %s\noutput: %s, %s%n%n",
                val3, Arrays.toString(copy3), res3, Arrays.toString(arr3));
    }

    public int removeElement(int[] nums, int val) {
        int length = nums.length;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                nums[i] = -1;
                length--;
            } else {
                shiftLeft(nums, i, nums[i]);
            }
        }


        return length;
    }

    private void shiftLeft(int[] nums, int currentIndex, int val) {
        for (int i = currentIndex - 1; i >= 0; i--) {
            if (nums[i] != -1) {
                nums[i + 1] = val;
                if (i + 1 != currentIndex) {
                    nums[currentIndex] = -1;
                }
                break;
            } else if (nums[i] == -1 && i == 0) {
                nums[i] = val;
                nums[currentIndex] = -1;
                break;
            }
        }
    }
}
