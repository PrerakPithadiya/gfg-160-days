
/**
 * Solution class to check if a sentence is a palindrome.
 * A sentence is considered a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 */
class Solution {

    /**
     * Checks if the given sentence is a palindrome.
     *
     * @param s The input sentence to check
     * @return true if the sentence is a palindrome, false otherwise
     *
     * Example: Input: "A man, a plan, a canal: Panama" Output: true
     * Explanation: After converting to lowercase and removing non-alphanumeric
     * characters, "amanaplanacanalpanama" is a palindrome.
     */
    public boolean sentencePalindrome(String s) {
        StringBuilder normalized = new StringBuilder();

        // Normalize the string: convert to lowercase and keep only alphanumeric characters
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                normalized.append(Character.toLowerCase(c));
            }
        }

        // Check if the normalized string is a palindrome
        String normalizedStr = normalized.toString();
        int left = 0;
        int right = normalizedStr.length() - 1;

        while (left < right) {
            if (normalizedStr.charAt(left) != normalizedStr.charAt(right)) {
                return false; // Not a palindrome
            }
            left++;
            right--;
        }

        return true; // It is a palindrome
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        String[] testCases = {
            "A man, a plan, a canal: Panama",
            "race a car",
            "Was it a car or a cat I saw?",
            "hello",
            "",
            "12321",
            "Never odd or even"
        };

        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Is Palindrome: " + solution.sentencePalindrome(test));
            System.out.println();
        }
    }
}
