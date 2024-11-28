
/**
 * Solution class to find single digit by repeatedly adding digits of a number
 * Time Complexity: O(log n) - since we reduce the number in each iteration
 * Space Complexity: O(1) - using constant extra space
 */
class Solution {

    /**
     * Reduces a number to a single digit by repeatedly adding all its digits
     *
     * @param n The input number to be reduced
     * @return The final single digit obtained after repeated addition
     */
    public int singleDigit(int n) {
        while (n >= 10) {
            int sum = 0;
            while (n > 0) {
                sum += n % 10; // Add last digit
                n /= 10;       // Remove last digit
            }
            n = sum; // Update n to the sum of its digits
        }
        return n; // Return the single-digit result
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Single digit number
        System.out.println("Test Case 1: Input = 5");
        System.out.println("Output = " + solution.singleDigit(5));  // Expected output: 5

        // Test Case 2: Two digit number
        System.out.println("\nTest Case 2: Input = 49");
        System.out.println("Output = " + solution.singleDigit(49)); // Expected output: 4

        // Test Case 3: Large number
        System.out.println("\nTest Case 3: Input = 98765");
        System.out.println("Output = " + solution.singleDigit(98765)); // Expected output: 8

        // Test Case 4: Number requiring multiple iterations
        System.out.println("\nTest Case 4: Input = 999999999");
        System.out.println("Output = " + solution.singleDigit(999999999)); // Expected output: 9
    }
}
