
import java.util.*;

/**
 * Solution class containing method to group anagrams together from an array of
 * strings.
 *
 * An anagram is a word or phrase formed by rearranging the letters of another
 * word or phrase, typically using all the original letters exactly once.
 */
class Solution {

    /**
     * Groups anagrams together from an array of strings.
     *
     * @param arr Array of strings to be grouped into anagrams
     * @return ArrayList of ArrayList containing groups of anagrams, sorted
     * lexicographically
     *
     * Time Complexity: O(N * K * log K) where N is the length of input array
     * and K is the maximum length of a string in input Space Complexity: O(N *
     * K) to store the grouped anagrams
     *
     * Example 1: Input: ["cat", "dog", "tac", "god", "act"] Output: [[act, cat,
     * tac], [dog, god]]
     *
     * Example 2: Input: ["no", "on", "is"] Output: [[is], [no, on]]
     */
    public ArrayList<ArrayList<String>> anagrams(String[] arr) {
        // HashMap to store sorted string as key and list of anagrams as value
        Map<String, ArrayList<String>> map = new HashMap<>();

        for (String str : arr) {
            // Generate the key by sorting the characters in the string
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedKey = new String(charArray);

            // Add the original string to the appropriate anagram group
            map.putIfAbsent(sortedKey, new ArrayList<>());
            map.get(sortedKey).add(str);
        }

        // Extract values from the map into a list and sort lexicographically
        ArrayList<ArrayList<String>> result = new ArrayList<>(map.values());
        result.sort((group1, group2) -> {
            if (group1.isEmpty() || group2.isEmpty()) {
                return 0;
            }
            return group1.get(0).compareTo(group2.get(0));
        });

        return result;
    }

    /**
     * Test cases to verify the functionality of anagrams method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic anagram groups
        String[] test1 = {"cat", "dog", "tac", "god", "act"};
        System.out.println("Test Case 1: " + solution.anagrams(test1));
        // Expected: [[act, cat, tac], [dog, god]]

        // Test Case 2: No anagrams
        String[] test2 = {"cat", "dog", "rat"};
        System.out.println("Test Case 2: " + solution.anagrams(test2));
        // Expected: [[cat], [dog], [rat]]

        // Test Case 3: Empty array
        String[] test3 = {};
        System.out.println("Test Case 3: " + solution.anagrams(test3));
        // Expected: []

        // Test Case 4: Single character anagrams
        String[] test4 = {"a", "b", "a"};
        System.out.println("Test Case 4: " + solution.anagrams(test4));
        // Expected: [[a, a], [b]]

        // Test Case 5: Case sensitivity
        String[] test5 = {"Tea", "Eat", "ate"};
        System.out.println("Test Case 5: " + solution.anagrams(test5));
        // Expected: [[Eat], [Tea], [ate]]
    }
}
