
import java.util.HashMap;

/**
 * Solution class containing method to count pairs with given sum
 */
class Solution {

    /**
     * Counts the number of pairs in an array that sum to a target value
     *
     * Time Complexity: O(n) where n is length of input array Space Complexity:
     * O(n) for storing frequency map
     *
     * @param arr Input array of integers
     * @param target Target sum to find pairs for
     * @return Number of pairs that sum to target
     */
    int countPairs(int arr[], int target) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int count = 0;

        for (int num : arr) {
            count += freq.getOrDefault(target - num, 0);
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        return count;
    }

    /**
     * Test cases to verify countPairs functionality
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case
        int[] arr1 = {1, 5, 7, 1};
        int target1 = 6;
        assert solution.countPairs(arr1, target1) == 2 : "Test Case 1 Failed";

        // Test Case 2: No valid pairs
        int[] arr2 = {1, 2, 3};
        int target2 = 9;
        assert solution.countPairs(arr2, target2) == 0 : "Test Case 2 Failed";

        // Test Case 3: Multiple same pairs
        int[] arr3 = {1, 1, 1, 1};
        int target3 = 2;
        assert solution.countPairs(arr3, target3) == 6 : "Test Case 3 Failed";

        // Test Case 4: Negative numbers
        int[] arr4 = {-2, -1, 0, 1, 2};
        int target4 = 0;
        assert solution.countPairs(arr4, target4) == 2 : "Test Case 4 Failed";

        // Test Case 5: Empty array
        int[] arr5 = {};
        int target5 = 5;
        assert solution.countPairs(arr5, target5) == 0 : "Test Case 5 Failed";

        System.out.println("All test cases passed!");
    }

}
