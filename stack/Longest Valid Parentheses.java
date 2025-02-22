
import java.util.Stack;

/**
 * Solution class for finding the longest valid parentheses substring
 *
 * Problem: Given a string of parentheses, find length of longest valid
 * substring Time Complexity: O(n) where n is length of input string Space
 * Complexity: O(n) for stack storage
 */
class Solution {

    /**
     * Finds the length of the longest valid parentheses substring
     *
     * @param s Input string containing only '(' and ')' characters
     * @return Length of longest valid parentheses substring
     *
     * Algorithm: 1. Use stack to store indices of parentheses 2. Initialize
     * stack with -1 as base index 3. For each character: - If '(': push index
     * to stack - If ')': pop top element and: > If stack empty: push current
     * index (new base) > Else: update maxLen if current valid length is larger
     */
    static int maxLength(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // Initialize with -1 as base index
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                stack.push(i);
            } else {
                // Pop the top element for ')'
                stack.pop();

                // If stack becomes empty, push current index
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    // Calculate length of valid substring
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
            "((())", // Expected: 4
            ")()())", // Expected: 4
            "()()", // Expected: 4
            "())()", // Expected: 2
            "(()", // Expected: 2
            ")", // Expected: 0
            "((((", // Expected: 0
            "" // Expected: 0
        };

        // Run test cases
        for (int i = 0; i < testCases.length; i++) {
            String test = testCases[i];
            int result = maxLength(test);
            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("Input: %s\n", test);
            System.out.printf("Output: %d\n", result);
            System.out.println("--------------------");
        }
    }
}
