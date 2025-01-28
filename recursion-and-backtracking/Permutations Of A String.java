
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Solution class to find all permutations of a given string Time Complexity:
 * O(n! * n) where n is the length of the string Space Complexity: O(n) for
 * recursion stack
 */
class Solution {

    /**
     * Finds all unique permutations of the input string in lexicographically
     * sorted order
     *
     * @param s The input string to find permutations for
     * @return ArrayList containing all unique permutations
     */
    public ArrayList<String> findPermutation(String s) {
        ArrayList<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        Arrays.sort(chars); // Sort to handle duplicates and get lexicographic order
        boolean[] used = new boolean[s.length()];
        backtrack(result, new StringBuilder(), chars, used);
        return result;
    }

    /**
     * Helper method that uses backtracking to generate permutations
     *
     * @param result ArrayList to store all permutations
     * @param temp StringBuilder to build current permutation
     * @param chars Character array of sorted input string
     * @param used Boolean array to track used characters
     */
    private void backtrack(ArrayList<String> result, StringBuilder temp, char[] chars, boolean[] used) {
        // Base case: if current permutation length equals input length
        if (temp.length() == chars.length) {
            result.add(temp.toString());
            return;
        }

        // Try each character as the next character in permutation
        for (int i = 0; i < chars.length; i++) {
            // Skip if character is used or if it's a duplicate and previous occurrence not used
            if (used[i] || (i > 0 && chars[i] == chars[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            temp.append(chars[i]);
            backtrack(result, temp, chars, used);
            used[i] = false;
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic string
        String test1 = "ABC";
        System.out.println("Test Case 1 - Input: " + test1);
        System.out.println("Output: " + solution.findPermutation(test1));
        // Expected: [ABC, ACB, BAC, BCA, CAB, CBA]

        // Test Case 2: String with duplicates
        String test2 = "AAB";
        System.out.println("\nTest Case 2 - Input: " + test2);
        System.out.println("Output: " + solution.findPermutation(test2));
        // Expected: [AAB, ABA, BAA]

        // Test Case 3: Single character
        String test3 = "A";
        System.out.println("\nTest Case 3 - Input: " + test3);
        System.out.println("Output: " + solution.findPermutation(test3));
        // Expected: [A]

        // Test Case 4: Empty string
        String test4 = "";
        System.out.println("\nTest Case 4 - Input: " + test4);
        System.out.println("Output: " + solution.findPermutation(test4));
        // Expected: []
    }
}
