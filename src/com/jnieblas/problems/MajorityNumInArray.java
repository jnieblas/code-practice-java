package src.com.jnieblas.problems;

import java.util.HashMap;

public class MajorityNumInArray {

    public static void main(String[] args) {
        class Solution {
            public int majorityElement(int[] nums) {
                HashMap<Integer, Integer> numToFrequency = new HashMap<>();

                for (int n : nums) {
                    int freq = 0;

                    if (numToFrequency.containsKey(n)) {
                        freq = numToFrequency.get(n);
                    }

                    freq++;

                    // need to compensate for modulus. Since if we divide an odd, we might technically trigger the return for a frequency that is less than half. SO we need to round up, since we're dealing with integers.
                    if (freq >= (nums.length / 2) + (nums.length % 2)) {
                        return n;
                    }

                    numToFrequency.put(n, freq);
                }

                return 0;
            }
        }
    }
}
