
/**
 * Solution class to find minimum characters needed to add at the beginning to make a string palindrome
 */
class Solution {

    /**
     * Finds the minimum number of characters needed to be added at the
     * beginning of the string to make it a palindrome
     *
     * @param s Input string
     * @return Minimum number of characters needed
     */
    public static int minChar(String s) {
        // Concatenate the original string with a special delimiter and its reverse
        String temp = s + "#" + new StringBuilder(s).reverse().toString();

        // Compute the LPS (Longest Proper Prefix which is also Suffix) array
        int[] lps = computeLPSArray(temp);

        // The last value in LPS array gives the length of the longest prefix 
        // that is also a suffix in the modified string
        return s.length() - lps[lps.length - 1];
    }

    /**
     * Computes the Longest Proper Prefix Suffix (LPS) array using KMP algorithm
     *
     * @param pattern Input string pattern
     * @return Array containing LPS values
     */
    private static int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;  // length of the previous longest prefix suffix
        int i = 1;

        // The first element of LPS array is always 0
        lps[0] = 0;

        // Compute LPS array
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                // If characters don't match
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    /**
     * Main method to test the solution
     */
    public static void main(String[] args) {
        // Test Case 1: Already a palindrome
        String test1 = "aba";
        System.out.println("Test 1: " + test1 + " -> " + minChar(test1));  // Expected: 0

        // Test Case 2: Single character
        String test2 = "a";
        System.out.println("Test 2: " + test2 + " -> " + minChar(test2));  // Expected: 0

        // Test Case 3: Two different characters
        String test3 = "ab";
        System.out.println("Test 3: " + test3 + " -> " + minChar(test3));  // Expected: 1

        // Test Case 4: Complex case
        String test4 = "aacecaaa";
        System.out.println("Test 4: " + test4 + " -> " + minChar(test4));  // Expected: 2

        // Test Case 5: Empty string
        String test5 = "";
        System.out.println("Test 5: " + test5 + " -> " + minChar(test5));  // Expected: 0
    }
}
