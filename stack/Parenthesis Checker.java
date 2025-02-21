
import java.util.Stack;

/**
 * Solution for validating balanced parentheses expressions Time Complexity:
 * O(n) where n is string length Space Complexity: O(n) for stack storage
 */
class Solution {

    static boolean isBalanced(String s) {
        if (s == null) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();
                if ((ch == ')' && top != '(')
                        || (ch == '}' && top != '{')
                        || (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // Test driver
    public static void main(String[] args) {
        // Test case structure: {input string, expected result}
        Object[][] testCases = {
            // Valid cases
            {"()", true},
            {"(){}[]", true},
            {"({[]})", true},
            {"", true},
            {"((()))", true},
            {"(()())(())", true},
            {"{()}[]", true},
            // Invalid cases
            {"(", false},
            {")", false},
            {"({)}", false},
            {"((())", false},
            {"())", false},
            {"][", false},
            {"{[}]", false},
            {"({[}])", false},
            {null, false},
            // Complex valid cases
            {"{}()[](({}[]))", true},
            {"({})[{()}][]", true},
            // Complex invalid cases
            {"({[])})", false},
            {"(((){}[])(]", false}
        };

        int passed = 0;
        int total = testCases.length;

        // Run all test cases
        for (Object[] test : testCases) {
            String input = (String) test[0];
            boolean expected = (boolean) test[1];
            boolean result = isBalanced(input);

            boolean testPassed = (result == expected);
            passed += testPassed ? 1 : 0;

            // Print test results
            System.out.printf("Test Case: %-20s Expected: %-7b Got: %-7b %s%n",
                    input == null ? "null" : "\"" + input + "\"",
                    expected,
                    result,
                    testPassed ? "✓" : "✗");
        }

        // Print summary
        System.out.println("\nTest Summary:");
        System.out.printf("Passed: %d/%d (%.2f%%)%n",
                passed,
                total,
                (passed * 100.0 / total));
    }
}
