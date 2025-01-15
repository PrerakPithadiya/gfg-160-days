
/**
 * Solution class for finding the longest subarray with a given sum.
 * This class implements an efficient algorithm using prefix sum and HashMap approach.
 * Time Complexity: O(n) where n is the length of the input array
 * Space Complexity: O(n) for storing prefix sums in HashMap
 */
import java.util.*;

class Solution {

    /**
     * Finds the length of the longest subarray with sum equal to k.
     *
     * @param arr The input array of integers
     * @param k The target sum to find in subarrays
     * @return The length of the longest subarray with sum k
     *
     * Algorithm: 1. Uses prefix sum technique to calculate running sum 2. Uses
     * HashMap to store first occurrence of each prefix sum 3. For each index i,
     * checks three cases: - If current prefix sum equals k - If (prefixSum - k)
     * exists in map - Stores new prefix sum if not already in map
     */
    public int longestSubarray(int[] arr, int k) {
        // HashMap to store the first occurrence of each prefix sum
        Map<Integer, Integer> prefixSumMap = new HashMap<>();

        int prefixSum = 0; // Tracks the running prefix sum
        int maxLength = 0; // Tracks the length of the longest subarray with sum k

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];

            // Case 1: Check if the prefix sum equals k
            if (prefixSum == k) {
                maxLength = i + 1; // Entire array from 0 to i has sum k
            }

            // Case 2: Check if the difference (prefixSum - k) exists in the map
            if (prefixSumMap.containsKey(prefixSum - k)) {
                int subarrayLength = i - prefixSumMap.get(prefixSum - k);
                maxLength = Math.max(maxLength, subarrayLength);
            }

            // Case 3: Store the prefix sum in the map if it's not already present
            if (!prefixSumMap.containsKey(prefixSum)) {
                prefixSumMap.put(prefixSum, i);
            }
        }

        return maxLength; // Return the length of the longest subarray
    }

    /**
     * Test cases to verify the functionality of longestSubarray method.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case with positive numbers
        int[] arr1 = {1, 2, 3, 1, 1, 1, 1};
        int k1 = 3;
        assert solution.longestSubarray(arr1, k1) == 3; // Should return 3 ([1, 1, 1])

        // Test Case 2: Array with negative numbers
        int[] arr2 = {-1, 2, -3, 4, 5, -9, 6, 1};
        int k2 = 0;
        assert solution.longestSubarray(arr2, k2) == 4; // Should return 4 ([2, -3, 4, -3])

        // Test Case 3: Empty array
        int[] arr3 = {};
        int k3 = 5;
        assert solution.longestSubarray(arr3, k3) == 0; // Should return 0

        // Test Case 4: Single element array
        int[] arr4 = {5};
        int k4 = 5;
        assert solution.longestSubarray(arr4, k4) == 1; // Should return 1

        // Test Case 5: No subarray with sum k exists
        int[] arr5 = {1, 2, 3, 4};
        int k5 = 10;
        assert solution.longestSubarray(arr5, k5) == 0; // Should return 0

        System.out.println("All test cases passed!");
    }
}
