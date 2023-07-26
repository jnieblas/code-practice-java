package src.com.jnieblas.problems;

import java.util.ArrayList;
import java.util.List;

public class SplitArrayIntoFibonacciSequence {

    public static void main(String[] args) {
        SplitArrayIntoFibonacciSequence solver = new SplitArrayIntoFibonacciSequence();

        String num1 = "1101111";
        List<Integer> ans = solver.splitIntoFibonacci(num1);
        System.out.println(num1 + ": " + ans);
    }

    /*
        We don't need anything fancy, we just need to have the array, the num var, and the current index for which we're building off of.
        There are a few conditions we need to break on internally, but otherwise, we are doing recursive backtracking.
    */
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> ans = new ArrayList<>();
        buildFibSeq(num, ans, 0);
        return ans;
    }

    public boolean buildFibSeq(String num, List<Integer> ans, int currentIndex) {
        // base case
        if (currentIndex == num.length() && ans.size() >= 3) {
            return true;
        }

        for (int i = currentIndex; i < num.length(); i++) {
            // i > currentIndex meaning that if true, 0 is now a leading 0, which isn't allowed
            // num.charAt(currentIndex) because currentIndex is the start of our curr num
            if (num.charAt(currentIndex) == '0' && i > currentIndex) {
                return false;
            }

            // gets next iteration of curr num
            long curr = Long.parseLong(num.substring(currentIndex, i + 1));

            // Return false if the int we're building has exceeded MAX_INT
            if (curr > Integer.MAX_VALUE) {
                return false;
            }

            int size = ans.size();

            // need to compensate for the fact that we need to actually populate the array with two values first
            if (size < 2 || ans.get(size - 1) + ans.get(size - 2) == curr) {
                ans.add(Math.toIntExact(curr));

                // i + 1 because we need to move to next character in num string, since i is a part of curr
                if (buildFibSeq(num, ans, i + 1)) {
                    return true;
                }

                // if not true, then we backtracked to this point & need to remove what we just added
                // use size var to remove, since size = ans.size() - 1 now (since we added to it since size instantiation)
                ans.remove(size);
            }
        }

        return false; // return false by default, meaning we weren't successful in finding an answer
    }
}
