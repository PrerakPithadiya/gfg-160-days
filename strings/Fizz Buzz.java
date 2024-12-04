
import java.util.ArrayList;

/**
 * Solution class containing the FizzBuzz implementation
 */
class Solution {

    /**
     * Returns an ArrayList of strings where: - For numbers divisible by 3, add
     * "Fizz" - For numbers divisible by 5, add "Buzz" - For numbers divisible
     * by both 3 and 5, add "FizzBuzz" - For other numbers, add the number as a
     * string
     *
     * @param n The upper limit of numbers to process (inclusive)
     * @return ArrayList containing the FizzBuzz sequence
     */
    public static ArrayList<String> fizzBuzz(int n) {
        ArrayList<String> result = new ArrayList<>(n); // Initialize with capacity n

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(String.valueOf(i)); // Convert integer to string
            }
        }

        return result;
    }

    /**
     * Test cases for the FizzBuzz implementation
     */
    public static void main(String[] args) {
        // Test case 1: n = 15
        ArrayList<String> test1 = fizzBuzz(15);
        System.out.println("Test case 1 (n=15): " + test1);

        // Test case 2: n = 5
        ArrayList<String> test2 = fizzBuzz(5);
        System.out.println("Test case 2 (n=5): " + test2);

        // Test case 3: n = 3
        ArrayList<String> test3 = fizzBuzz(3);
        System.out.println("Test case 3 (n=3): " + test3);
    }
}
