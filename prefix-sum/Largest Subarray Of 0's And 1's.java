
/**
 * Solution class to find the largest subarray containing equal numbers of 0s and 1s.
 *
 * Problem Description:
 * Given a binary array (containing only 0s and 1s), find the length of the largest subarray
 * that contains an equal number of 0s and 1s.
 *
 * Approach:
 * 1. Convert 0s to -1s to transform the problem into finding longest subarray with sum = 0
 * 2. Use prefix sum technique with HashMap to track running sums
 * 3. When same sum is encountered again, it means subarray between those indices has sum = 0
 *
 * Time Complexity: O(n) where n is the length of input array
 * Space Complexity: O(n) for storing sums in HashMap
 */
import java.util.HashMap;
import java.util.Map;

class Solution {

    /**
     * Finds the length of largest subarray with equal number of 0s and 1s
     *
     * @param arr Input binary array containing only 0s and 1s
     * @return Length of the largest subarray with equal 0s and 1s
     */
    public int maxLen(int[] arr) {
        // Convert 0s to -1s to make sum=0 represent equal 0s and 1s
        int[] nums = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = arr[i] == 0 ? -1 : 1;
        }

        // Map to store running sum and its first occurrence index
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1);  // Initialize with sum 0 at index -1

        int maxLen = 0;
        int sum = 0;

        // Calculate running sum and check for equal counts
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sumMap.containsKey(sum)) {
                // If we've seen this sum before, we found a valid subarray
                maxLen = Math.max(maxLen, i - sumMap.get(sum));
            } else {
                // Store first occurrence of this sum
                sumMap.put(sum, i);
            }
        }

        return maxLen;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case with equal 0s and 1s
        int[] test1 = {0, 1, 0, 1};
        assert solution.maxLen(test1) == 4 : "Test Case 1 Failed";

        // Test Case 2: No equal subarray
        int[] test2 = {0, 0, 0, 0};
        assert solution.maxLen(test2) == 0 : "Test Case 2 Failed";

        // Test Case 3: Multiple valid subarrays
        int[] test3 = {0, 0, 1, 1, 0};
        assert solution.maxLen(test3) == 4 : "Test Case 3 Failed";

        // Test Case 4: Empty array
        int[] test4 = {};
        assert solution.maxLen(test4) == 0 : "Test Case 4 Failed";

        // Test Case 5: Single element
        int[] test5 = {1};
        assert solution.maxLen(test5) == 0 : "Test Case 5 Failed";

        System.out.println("All test cases passed!");
    }
}
