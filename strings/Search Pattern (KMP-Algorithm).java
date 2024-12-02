
import java.util.ArrayList;

/**
 * Solution class implements KMP (Knuth-Morris-Pratt) string matching algorithm
 * to find all occurrences of a pattern in a text string.
 */
class Solution {

    /**
     * Searches for all occurrences of pattern in text using KMP algorithm
     *
     * @param pat The pattern string to search for
     * @param txt The text string to search in
     * @return ArrayList containing indices of all pattern occurrences (1-based
     * indexing)
     */
    ArrayList<Integer> search(String pat, String txt) {
        ArrayList<Integer> result = new ArrayList<>();
        int m = pat.length(); // Length of pattern
        int n = txt.length(); // Length of text

        // Step 1: Compute the LPS array
        int[] lps = new int[m];
        computeLPSArray(pat, lps);

        // Step 2: Use KMP to search for the pattern
        int i = 0; // Pointer for txt
        int j = 0; // Pointer for pat

        while (i < n) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                // Match found
                result.add(i - j + 1); // Adding 1 for 1-based indexing
                j = lps[j - 1]; // Use LPS to skip redundant comparisons
            } else if (i < n && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1]; // Use LPS to skip
                } else {
                    i++; // Move the text pointer
                }
            }
        }

        return result;
    }

    /**
     * Computes the Longest Proper Prefix which is also Suffix (LPS) array
     *
     * @param pat The pattern string
     * @param lps Array to store LPS values
     */
    private void computeLPSArray(String pat, int[] lps) {
        int len = 0; // Length of the previous longest prefix suffix
        int i = 1;
        lps[0] = 0; // LPS[0] is always 0

        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // Use the previous LPS value
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    // Test cases
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic pattern matching
        String txt1 = "AABAACAADAABAAABAA";
        String pat1 = "AABA";
        ArrayList<Integer> result1 = solution.search(pat1, txt1);
        System.out.println("Test Case 1: Pattern '" + pat1 + "' found at positions: " + result1);
        // Expected output: [1, 13]

        // Test Case 2: Pattern occurs at the end
        String txt2 = "ABCDEFGABC";
        String pat2 = "ABC";
        ArrayList<Integer> result2 = solution.search(pat2, txt2);
        System.out.println("Test Case 2: Pattern '" + pat2 + "' found at positions: " + result2);
        // Expected output: [1, 8]

        // Test Case 3: Pattern not found
        String txt3 = "ABCDEFG";
        String pat3 = "XYZ";
        ArrayList<Integer> result3 = solution.search(pat3, txt3);
        System.out.println("Test Case 3: Pattern '" + pat3 + "' found at positions: " + result3);
        // Expected output: []

        // Test Case 4: Empty pattern
        String txt4 = "ABCDEFG";
        String pat4 = "";
        ArrayList<Integer> result4 = solution.search(pat4, txt4);
        System.out.println("Test Case 4: Empty pattern found at positions: " + result4);
        // Expected output: []
    }
}
