
import java.util.HashMap;
import java.util.Map;

/**
 * Solution class containing method to count subarrays with sum K Time
 * Complexity: O(n) where n is the length of input array Space Complexity: O(n)
 * for storing prefix sums in HashMap
 */
class Solution {

    /**
     * Counts the number of subarrays with sum equal to k
     *
     * @param arr Input array of integers
     * @param k Target sum to find in subarrays
     * @return Number of subarrays with sum equal to k
     *
     * Algorithm: 1. Use prefix sum technique with HashMap to store running sum
     * frequencies 2. For each element, check if (currentSum - k) exists in map
     * 3. Add frequency of (currentSum - k) to result count 4. Update frequency
     * map with current sum
     *
     * Example: arr = [1, 2, 3], k = 3 Returns 2 (subarrays [1,2] and [3])
     */
    public int countSubarrays(int arr[], int k) {
        Map<Integer, Integer> sumFreq = new HashMap<>();
        sumFreq.put(0, 1);  // Initialize for subarrays starting from index 0

        int count = 0;
        int currSum = 0;

        for (int num : arr) {
            currSum += num;

            // Check if (currSum - k) exists in map
            count += sumFreq.getOrDefault(currSum - k, 0);

            // Update frequency map
            sumFreq.put(currSum, sumFreq.getOrDefault(currSum, 0) + 1);
        }

        return count;
    }

    /**
     * Test cases to verify the functionality
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case
        int[] arr1 = {1, 2, 3};
        assert solution.countSubarrays(arr1, 3) == 2 : "Test Case 1 Failed";

        // Test Case 2: Array with negative numbers
        int[] arr2 = {1, -1, 0};
        assert solution.countSubarrays(arr2, 0) == 2 : "Test Case 2 Failed";

        // Test Case 3: Empty array
        int[] arr3 = {};
        assert solution.countSubarrays(arr3, 5) == 0 : "Test Case 3 Failed";

        // Test Case 4: Array with all same numbers
        int[] arr4 = {1, 1, 1};
        assert solution.countSubarrays(arr4, 2) == 2 : "Test Case 4 Failed";

        // Test Case 5: Large numbers
        int[] arr5 = {10, 20, 30, 40};
        assert solution.countSubarrays(arr5, 50) == 2 : "Test Case 5 Failed";

        System.out.println("All test cases passed!");
    }
}
