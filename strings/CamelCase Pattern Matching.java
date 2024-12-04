
import java.util.ArrayList;
import java.util.List;

/**
 * Solution class for matching CamelCase patterns in strings This class provides
 * functionality to find strings that match a given uppercase pattern
 */
class Solution {

    /**
     * Finds all strings from the input array that match the given pattern
     * Pattern matching is done by comparing uppercase letters at the start
     *
     * @param arr Array of strings to search through
     * @param pat Pattern to match (uppercase letters only)
     * @return List of strings that match the pattern
     */
    public List<String> camelCase(String[] arr, String pat) {
        List<String> result = new ArrayList<>();

        for (String word : arr) {
            String uppercase = extractUppercase(word);
            if (matchesPattern(uppercase, pat)) {
                result.add(word);
            }
        }

        return result;
    }

    /**
     * Extracts uppercase letters from a given word
     *
     * @param word Input string to extract uppercase letters from
     * @return String containing only uppercase letters from the input
     */
    private String extractUppercase(String word) {
        StringBuilder uppercase = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) {
                uppercase.append(c);
            }
        }
        return uppercase.toString();
    }

    /**
     * Checks if the extracted uppercase string matches the given pattern
     *
     * @param uppercase String of uppercase letters extracted from word
     * @param pat Pattern to match against
     * @return true if pattern matches, false otherwise
     */
    private boolean matchesPattern(String uppercase, String pat) {
        if (pat.length() > uppercase.length()) {
            return false;
        }
        for (int i = 0; i < pat.length(); i++) {
            if (pat.charAt(i) != uppercase.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Main method to test the camelCase pattern matching
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Basic matching
        String[] arr1 = {"WelcomeGeek", "WelcomeToGeeksForGeeks", "GeeksForGeeks"};
        String pat1 = "WTG";
        System.out.println("Test 1: " + solution.camelCase(arr1, pat1)); // Should print: [WelcomeToGeeksForGeeks]

        // Test case 2: No matches
        String[] arr2 = {"Hi", "Hello", "HelloWorld"};
        String pat2 = "HW";
        System.out.println("Test 2: " + solution.camelCase(arr2, pat2)); // Should print: [HelloWorld]

        // Test case 3: Multiple matches
        String[] arr3 = {"InterviewBit", "InternalBase", "IndiaBook", "IndiaGate"};
        String pat3 = "IB";
        System.out.println("Test 3: " + solution.camelCase(arr3, pat3)); // Should print: [InterviewBit, InternalBase, IndiaBook]
    }
}
