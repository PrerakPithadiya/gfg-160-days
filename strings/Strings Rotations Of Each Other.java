
/**
 * Solution class to check if two strings are rotations of each other.
 * For example:
 * - "ABCD" and "CDAB" are rotations of each other
 * - "ABCD" and "ACBD" are not rotations of each other
 */
class Solution {

    /**
     * Function to check if two strings are rotations of each other or not. Time
     * Complexity: O(n) where n is the length of strings Space Complexity: O(n)
     * for concatenated string
     *
     * @param s1 First string
     * @param s2 Second string
     * @return true if s2 is a rotation of s1, false otherwise
     *
     * Example: areRotations("ABACD", "CDABA") returns true
     * areRotations("HELLO", "LOHEL") returns true areRotations("HELLO",
     * "WORLD") returns false
     */
    public static boolean areRotations(String s1, String s2) {
        // Check if lengths are equal
        if (s1.length() != s2.length()) {
            return false;
        }

        // Concatenate s1 with itself
        String doubledS1 = s1 + s1;

        // Check if s2 is a substring of the doubled string
        return isSubstring(doubledS1, s2);
    }

    /**
     * Function to check if str2 is a substring of str1 using KMP algorithm.
     * Time Complexity: O(m + n) where m and n are lengths of strings Space
     * Complexity: O(n) for LPS array
     *
     * @param str1 The main string
     * @param str2 The pattern string to search for
     * @return true if str2 is a substring of str1, false otherwise
     */
    private static boolean isSubstring(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // Create lps array for KMP
        int[] lps = new int[n];
        computeLPSArray(str2, lps);

        int i = 0; // index for str1
        int j = 0; // index for str2

        while (i < m) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            }
            if (j == n) {
                return true; // found str2 in str1
            } else if (i < m && str1.charAt(i) != str2.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1]; // use lps to avoid unnecessary comparisons
                } else {
                    i++;
                }
            }
        }
        return false; // not found
    }

    /**
     * Function to compute the LPS (Longest Prefix Suffix) array for KMP
     * algorithm. Time Complexity: O(n) where n is the length of string Space
     * Complexity: O(1) as array is passed as parameter
     *
     * @param str The pattern string
     * @param lps The array to store LPS values
     */
    private static void computeLPSArray(String str, int[] lps) {
        int length = 0; // length of the previous longest prefix suffix
        lps[0] = 0; // lps[0] is always 0
        int i = 1;

        while (i < str.length()) {
            if (str.charAt(i) == str.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {
        // Test Case 1: Basic rotation
        assert areRotations("ABACD", "CDABA") == true : "Test Case 1 Failed";

        // Test Case 2: Same strings
        assert areRotations("HELLO", "HELLO") == true : "Test Case 2 Failed";

        // Test Case 3: Different lengths
        assert areRotations("ABC", "ABCD") == false : "Test Case 3 Failed";

        // Test Case 4: Not a rotation
        assert areRotations("HELLO", "WORLD") == false : "Test Case 4 Failed";

        // Test Case 5: Empty strings
        assert areRotations("", "") == true : "Test Case 5 Failed";

        // Test Case 6: Single character
        assert areRotations("A", "A") == true : "Test Case 6 Failed";

        System.out.println("All test cases passed!");
    }
}
