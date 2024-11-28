package GFG;

/**
 * Solution class implements string to integer (atoi) conversion
 */
class Solution {

    /**
     * Converts a string to a 32-bit signed integer following these rules: 1.
     * Ignores leading whitespace 2. Handles optional '+' or '-' sign 3.
     * Converts subsequent digits to integer 4. Ignores any characters after the
     * digits 5. Handles overflow by clamping to INT_MIN/INT_MAX
     *
     * @param s Input string to convert
     * @return Converted integer value
     */
    public int myAtoi(String s) {
        int i = 0, n = s.length();
        // Skip leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Check for sign
        int sign = 1; // Default is positive
        if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // Read the digits and convert to integer
        long num = 0; // Use long to handle overflow before final conversion
        while (i < n && Character.isDigit(s.charAt(i))) {
            num = num * 10 + (s.charAt(i) - '0');
            // Check for overflow
            if (num * sign >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (num * sign <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            i++;
        }

        return (int) (num * sign);
    }

    /**
     * Test cases for myAtoi method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Basic positive number
        assert solution.myAtoi("42") == 42;

        // Test case 2: Negative number with spaces
        assert solution.myAtoi("   -42") == -42;

        // Test case 3: Number with text
        assert solution.myAtoi("4193 with words") == 4193;

        // Test case 4: Invalid input
        assert solution.myAtoi("words and 987") == 0;

        // Test case 5: Overflow test
        assert solution.myAtoi("2147483648") == Integer.MAX_VALUE;

        // Test case 6: Underflow test
        assert solution.myAtoi("-2147483649") == Integer.MIN_VALUE;

        // Test case 7: Plus sign
        assert solution.myAtoi("+1") == 1;

        System.out.println("All test cases passed!");
    }
}
