
import java.util.HashMap;

/**
 * Solution class containing method to find first non-repeating character in a
 * string
 */
class Solution {

    /**
     * Function to find the first non-repeating character in a string.
     *
     * @param s Input string to process
     * @return First non-repeating character, or '$' if none found
     */
    static char nonRepeatingChar(String s) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();

        // Count the frequency of each character
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Find the first non-repeating character
        for (char c : s.toCharArray()) {
            if (frequencyMap.get(c) == 1) {
                return c;
            }
        }

        // If no non-repeating character found, return '$'
        return '$';
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {
        // Test Case 1: String with non-repeating character
        String test1 = "hello";
        System.out.println("Test 1: Input = " + test1);
        System.out.println("Output = " + nonRepeatingChar(test1)); // Should print 'h'

        // Test Case 2: String with all repeating characters
        String test2 = "aabb";
        System.out.println("Test 2: Input = " + test2);
        System.out.println("Output = " + nonRepeatingChar(test2)); // Should print '$'

        // Test Case 3: Empty string
        String test3 = "";
        System.out.println("Test 3: Input = " + test3);
        System.out.println("Output = " + nonRepeatingChar(test3)); // Should print '$'

        // Test Case 4: String with single character
        String test4 = "x";
        System.out.println("Test 4: Input = " + test4);
        System.out.println("Output = " + nonRepeatingChar(test4)); // Should print 'x'
    }
}
