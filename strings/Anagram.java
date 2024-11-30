
class Solution {

    /**
     * Checks whether two strings are anagrams of each other. Two strings are
     * considered anagrams if they contain the same characters with the same
     * frequencies, regardless of order.
     *
     * @param s1 The first string to compare
     * @param s2 The second string to compare
     * @return true if the strings are anagrams, false otherwise
     */
    public static boolean areAnagrams(String s1, String s2) {
        // Early exit: if lengths are different, not anagrams
        if (s1.length() != s2.length()) {
            return false;
        }

        // Array to store character frequencies for 26 lowercase letters
        int[] charCount = new int[26];

        // Update frequencies for both strings
        for (int i = 0; i < s1.length(); i++) {
            charCount[s1.charAt(i) - 'a']++; // Increment for s1
            charCount[s2.charAt(i) - 'a']--; // Decrement for s2
        }

        // Check if all frequencies are zero
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }

        return true; // Strings are anagrams
    }

    public static void main(String[] args) {
        // Test case 1: Simple anagrams
        System.out.println("Test 1: 'listen' and 'silent'");
        System.out.println("Expected: true");
        System.out.println("Result: " + areAnagrams("listen", "silent"));

        // Test case 2: Different lengths
        System.out.println("\nTest 2: 'hello' and 'world'");
        System.out.println("Expected: false");
        System.out.println("Result: " + areAnagrams("hello", "world"));

        // Test case 3: Same length but not anagrams
        System.out.println("\nTest 3: 'hello' and 'world'");
        System.out.println("Expected: false");
        System.out.println("Result: " + areAnagrams("hello", "world"));

        // Test case 4: Empty strings
        System.out.println("\nTest 4: '' and ''");
        System.out.println("Expected: true");
        System.out.println("Result: " + areAnagrams("", ""));

        // Test case 5: Repeated characters
        System.out.println("\nTest 5: 'aaab' and 'baaa'");
        System.out.println("Expected: true");
        System.out.println("Result: " + areAnagrams("aaab", "baaa"));
    }
}
