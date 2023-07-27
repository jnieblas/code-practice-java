package src.com.jnieblas.problems;

import java.util.Arrays;

public class MultiplyTwoStrings {

    public static void main(String[] args) {
        MultiplyTwoStrings solver = new MultiplyTwoStrings();
        solver.multiply("1234", "5678");
    }

    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        // Create array that is equal to length of first num + second num as strings
        // at most, product will not exceed m + n
        int[] pos = new int[m + n];

        // As we go from m-1,n-1 to 0, we fill out the array right to left.
        // iterate through first num's length, starting at the end of the string (aka the first digit of the num)
        for(int i = m - 1; i >= 0; i--) {
            // iterate through second num's length, starting at the end of the string (aka the first digit of the num)
            for(int j = n - 1; j >= 0; j--) {
                System.out.printf("for i = %s, j = %s%n", i, j);
                // for each digit in the first num, multiply by each digit of the second num
                // use charAt() - '0' trick to extract int, multiply for each
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j; // new place that we're solving for going from ones, to tens, to thousands, etc.
                int p2 = i + j + 1; // previous place that we solved for (adding the previous place to our new
                // take what we just multiplied, add it to the p2 that was set in previous loop
                int sum = mul + pos[p2];

                System.out.println("mul: " + mul);
                System.out.println("sum: " + sum);
                pos[p1] += sum / 10; // divide because the remainder will go to our last place, while tens represents our current place
                pos[p2] = sum % 10; // modulus because the last place of our sum represent the new value for the previous place

                System.out.println(Arrays.toString(pos));
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int p : pos) {
            if(!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
