
import java.util.HashSet;

/**
 * Solution class to find the longest consecutive subsequence in an array. Time
 * Complexity: O(n) where n is the length of input array Space Complexity: O(n)
 * to store elements in HashSet
 */
class Solution {

    /**
     * Finds the length of the longest consecutive subsequence in the given
     * array. A consecutive subsequence is a sequence of consecutive integers
     * (numbers that differ by 1).
     *
     * @param arr Input array of integers
     * @return Length of the longest consecutive subsequence
     *
     * Example 1: Input: [100, 4, 200, 1, 3, 2] Output: 4 Explanation: The
     * longest consecutive subsequence is [1, 2, 3, 4]
     *
     * Example 2: Input: [0, 3, 7, 2, 5, 8, 4, 6, 1] Output: 9 Explanation: The
     * entire array forms a consecutive subsequence [0,1,2,3,4,5,6,7,8]
     *
     * Example 3: Input: [] Output: 0 Explanation: Empty array has no
     * consecutive subsequence
     */
    public int longestConsecutive(int[] arr) {
        // Use HashSet for O(1) lookups
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        int maxLength = 0;

        // Check each possible sequence
        for (int num : set) {
            // Only start checking sequences from their smallest number
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;

                // Count consecutive numbers
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }

                maxLength = Math.max(maxLength, currentLength);
            }
        }

        return maxLength;
    }

    /**
     * Test cases to verify the functionality of longestConsecutive method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Standard case with non-consecutive numbers
        int[] test1 = {100, 4, 200, 1, 3, 2};
        assert solution.longestConsecutive(test1) == 4 : "Test Case 1 Failed";

        // Test Case 2: All consecutive numbers
        int[] test2 = {0, 3, 7, 2, 5, 8, 4, 6, 1};
        assert solution.longestConsecutive(test2) == 9 : "Test Case 2 Failed";

        // Test Case 3: Empty array
        int[] test3 = {};
        assert solution.longestConsecutive(test3) == 0 : "Test Case 3 Failed";

        // Test Case 4: Array with duplicate elements
        int[] test4 = {1, 2, 0, 1};
        assert solution.longestConsecutive(test4) == 3 : "Test Case 4 Failed";

        // Test Case 5: Array with single element
        int[] test5 = {5};
        assert solution.longestConsecutive(test5) == 1 : "Test Case 5 Failed";

        System.out.println("All test cases passed successfully!");
    }
}
