
import java.util.ArrayList;

/**
 * Solution class for generating valid IP addresses from a given string Time
 * Complexity: O(1) as we have a fixed number of possibilities (2^32) Space
 * Complexity: O(1) as we store a fixed number of valid IP addresses
 */
class Solution {

    /**
     * Generates all possible valid IP addresses from the input string
     *
     * @param s Input string containing only digits
     * @return ArrayList of all valid IP addresses that can be formed from the
     * input
     */
    public ArrayList<String> generateIp(String s) {
        ArrayList<String> result = new ArrayList<>();

        // Check if string length is valid for IP address (4-12 digits)
        if (s.length() < 4 || s.length() > 12) {
            return result;
        }

        backtrack(result, s, 0, new ArrayList<>());
        return result;
    }

    /**
     * Helper method that uses backtracking to generate valid IP addresses
     *
     * @param result List to store all valid IP addresses
     * @param s Input string
     * @param start Current position in the input string
     * @param current Current IP address segments being built
     */
    private void backtrack(ArrayList<String> result, String s, int start, ArrayList<String> current) {
        // If we have 4 parts and used all characters, we found a valid IP
        if (current.size() == 4 && start == s.length()) {
            result.add(String.join(".", current));
            return;
        }

        // If we have 4 parts but haven't used all characters, or vice versa
        if (current.size() == 4 || start == s.length()) {
            return;
        }

        // Try different lengths for the current segment
        for (int i = 1; i <= 3 && start + i <= s.length(); i++) {
            String segment = s.substring(start, start + i);

            // Skip if segment starts with 0 and length > 1
            if (segment.length() > 1 && segment.charAt(0) == '0') {
                continue;
            }

            // Convert to integer and check range
            int value = Integer.parseInt(segment);
            if (value >= 0 && value <= 255) {
                current.add(segment);
                backtrack(result, s, start + i, current);
                current.remove(current.size() - 1);  // backtrack
            }
        }
    }

    /**
     * Test cases to verify the functionality
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Normal valid input
        String test1 = "25525511135";
        System.out.println("Test 1 Input: " + test1);
        System.out.println("Output: " + solution.generateIp(test1));
        // Expected output: [255.255.11.135, 255.255.111.35]

        // Test Case 2: Input too short
        String test2 = "123";
        System.out.println("\nTest 2 Input: " + test2);
        System.out.println("Output: " + solution.generateIp(test2));
        // Expected output: []

        // Test Case 3: Input too long
        String test3 = "1234567890123";
        System.out.println("\nTest 3 Input: " + test3);
        System.out.println("Output: " + solution.generateIp(test3));
        // Expected output: []

        // Test Case 4: Input with leading zeros
        String test4 = "0000";
        System.out.println("\nTest 4 Input: " + test4);
        System.out.println("Output: " + solution.generateIp(test4));
        // Expected output: [0.0.0.0]

        // Test Case 5: Input with multiple valid combinations
        String test5 = "12345";
        System.out.println("\nTest 5 Input: " + test5);
        System.out.println("Output: " + solution.generateIp(test5));
        // Expected output: [1.2.3.45, 1.2.34.5, 1.23.4.5, 12.3.4.5]
    }
}
