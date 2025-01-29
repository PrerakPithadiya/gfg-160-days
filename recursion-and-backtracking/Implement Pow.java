
/**
 * Solution class implements power calculation using recursion
 */
class Solution {

    /**
     * Calculates power of a number using divide and conquer approach Time
     * Complexity: O(log n) where n is the exponent Space Complexity: O(log n)
     * due to recursive call stack
     *
     * @param b The base number (can be positive or negative)
     * @param e The exponent (can be positive or negative)
     * @return Result of b raised to power e
     */
    public double power(double b, int e) {
        if (e == 0) {
            return 1.0;
        }
        double half = power(b, e / 2);
        if (e % 2 == 0) {
            return half * half;
        } else if (e > 0) {
            return half * half * b;
        } else {
            return half * half / b;
        }
    }

    /**
     * Main method to test the power function with various test cases
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Positive base, positive exponent
        assert solution.power(2.0, 3) == 8.0;

        // Test Case 2: Positive base, negative exponent
        assert Math.abs(solution.power(2.0, -2) - 0.25) < 1e-10;

        // Test Case 3: Negative base, even exponent
        assert solution.power(-2.0, 2) == 4.0;

        // Test Case 4: Negative base, odd exponent
        assert solution.power(-2.0, 3) == -8.0;

        // Test Case 5: Zero exponent
        assert solution.power(5.0, 0) == 1.0;

        // Test Case 6: Base 1, any exponent
        assert solution.power(1.0, 100) == 1.0;

        // Test Case 7: Fractional base
        assert Math.abs(solution.power(0.5, 2) - 0.25) < 1e-10;

        System.out.println("All test cases passed successfully!");
    }
}
