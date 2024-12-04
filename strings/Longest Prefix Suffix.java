
class Solution {

    /**
     * Finds the length of the longest proper prefix which is also a proper
     * suffix in the given string.
     *
     * @param s The input string to find longest prefix suffix
     * @return Length of the longest proper prefix which is also a proper suffix
     */
    int longestPrefixSuffix(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0; // length of the previous longest prefix suffix
        int i = 1; // start from the second character

        // Build the LPS array
        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // backtrack the length
                } else {
                    lps[i] = 0; // no prefix-suffix match
                    i++;
                }
            }
        }

        // Return the last value in the LPS array
        return lps[n - 1];
    }

    // Test cases
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Regular string with prefix suffix
        String test1 = "abcabc";
        System.out.println("Test 1: " + solution.longestPrefixSuffix(test1)); // Expected output: 3

        // Test case 2: String with no prefix suffix
        String test2 = "abcd";
        System.out.println("Test 2: " + solution.longestPrefixSuffix(test2)); // Expected output: 0

        // Test case 3: String with multiple possible prefix suffixes
        String test3 = "aaaa";
        System.out.println("Test 3: " + solution.longestPrefixSuffix(test3)); // Expected output: 3

        // Test case 4: Empty string
        String test4 = "";
        System.out.println("Test 4: " + solution.longestPrefixSuffix(test4)); // Expected output: 0
    }
}
