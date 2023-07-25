package src.com.jnieblas.problems;

public class MaxContiguousSubarray {
  public static void main(String[] args) {
    int[] first = new int[]{-2,1,-3,4,-1,2,1,-5,4};
    int[] second = new int[]{1};
    int[] third = new int[]{5,4,-1,7,8};

    System.out.println(maxContiguousSubarray(first) + " should be " + "6");
    System.out.println(maxContiguousSubarray(second) + " should be " + "1");
    System.out.println(maxContiguousSubarray(third) + " should be " + "23");


  }

  public static int maxContiguousSubarray(int[] nums) {
    int max = Integer.MIN_VALUE;
    int current_max = 0;

    for (int i = 0; i < nums.length; i++) {
      int sum = nums[i] + current_max;

      max = Math.max(max, sum);
      current_max = sum > 0 ? sum : 0;
    }

    return max;
  }
}
