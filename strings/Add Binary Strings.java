
/**
 * Solution class for adding two binary strings.
 * Time Complexity: O(max(n1, n2)) where n1, n2 are lengths of input strings
 * Space Complexity: O(max(n1, n2)) for storing result
 */
class Solution {

    /**
     * Adds two binary strings and returns their sum as a binary string.
     *
     * @param s1 First binary string
     * @param s2 Second binary string
     * @return Sum of the two binary strings
     */
    public String addBinary(String s1, String s2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;

        // Pointers for s1 and s2
        int i = s1.length() - 1;
        int j = s2.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int bit1 = (i >= 0) ? s1.charAt(i) - '0' : 0; // Get bit from s1 or 0 if out of bounds
            int bit2 = (j >= 0) ? s2.charAt(j) - '0' : 0; // Get bit from s2 or 0 if out of bounds

            // Calculate the sum of bits and carry
            int sum = bit1 + bit2 + carry;
            result.append(sum % 2); // Append the result bit (0 or 1)
            carry = sum / 2; // Calculate the new carry

            // Move to the next bits
            i--;
            j--;
        }

        // The result is in reverse order, reverse it
        result.reverse();

        // Convert to string and remove leading zeros
        String finalResult = result.toString();
        return finalResult.replaceFirst("^0+(?!$)", ""); // Remove leading zeros, but keep "0" if the string is empty
    }

    /**
     * Test cases to verify the addBinary method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Basic addition
        assert solution.addBinary("11", "1").equals("100") : "Test case 1 failed";

        // Test case 2: Equal length strings
        assert solution.addBinary("1010", "1011").equals("10101") : "Test case 2 failed";

        // Test case 3: Different length strings
        assert solution.addBinary("111", "1").equals("1000") : "Test case 3 failed";

        // Test case 4: One empty string
        assert solution.addBinary("1", "").equals("1") : "Test case 4 failed";

        // Test case 5: Both zero strings
        assert solution.addBinary("0", "0").equals("0") : "Test case 5 failed";

        // Test case 6: Large numbers
        assert solution.addBinary("11111111", "11111111").equals("111111110") : "Test case 6 failed";

        System.out.println("All test cases passed!");
    }
}
