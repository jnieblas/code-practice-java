package src.com.jnieblas.problems;

import java.util.Arrays;

public class FindIndicesOfTarget {

  public static void main(String[] args) {
    FindIndicesOfTarget runner = new FindIndicesOfTarget();

    int firstTarget = 3;
    int[] firstNums = new int[]{1, 2, 3, 3, 3, 4, 5};
    int[] ans = runner.findIndicesOfTarget(firstTarget, firstNums);
    System.out.println(String.format("target: %d\nnums: %s\nans: %s", firstTarget, Arrays.toString(firstNums), Arrays.toString(ans)));

  }

  private int[] findIndicesOfTarget(int target, int[] nums) {
    int startIndex = -1;
    int endIndex = -1;

    int num;
    int index = 0;
    while (index < nums.length && (num = nums[index]) <= target) {
      if (num == target) {
        if (startIndex == -1) {
          startIndex = index;
        }

        endIndex = index;
      }

      index++;
    }
  
    return new int[]{startIndex, endIndex};
  }
}