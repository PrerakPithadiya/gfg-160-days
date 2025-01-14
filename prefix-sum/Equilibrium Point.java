
/**
 * Solution class containing method to find equilibrium point in an array.
 * An equilibrium point is an index where sum of elements to its left equals sum of elements to its right.
 */
class Solution {

    /**
     * Finds the equilibrium point in an array where sum of elements on left
     * equals sum on right.
     *
     * @param arr Input array of integers
     * @return Index of equilibrium point if found, -1 otherwise
     *
     * Time Complexity: O(n) where n is length of array Space Complexity: O(1)
     * as only constant extra space is used
     *
     * Example 1: Input: arr = [1, 3, 5, 2, 2] Output: 2 Explanation: Left sum
     * at index 2 = 1 + 3 = 4 Right sum at index 2 = 2 + 2 = 4
     *
     * Example 2: Input: arr = [1, 2, 3] Output: -1 Explanation: No equilibrium
     * point exists
     *
     * Example 3: Input: arr = [20] Output: 0 Explanation: Single element array
     * has equilibrium at index 0
     */
    public static int findEquilibrium(int arr[]) {
        int n = arr.length;

        // Calculate total sum
        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += arr[i];
        }

        // Track running sum from left side
        long leftSum = 0;

        // Check each index
        for (int i = 0; i < n; i++) {
            // Right sum is total sum minus current element and left sum
            long rightSum = totalSum - arr[i] - leftSum;

            // If left sum equals right sum, we found equilibrium point
            if (leftSum == rightSum) {
                return i;
            }

            // Add current element to left sum for next iteration
            leftSum += arr[i];
        }

        // No equilibrium point found
        return -1;
    }

    /**
     * Test cases to verify the functionality of findEquilibrium method
     */
    public static void main(String[] args) {
        // Test Case 1: Regular array with equilibrium point
        int[] test1 = {1, 3, 5, 2, 2};
        assert findEquilibrium(test1) == 2 : "Test Case 1 Failed";

        // Test Case 2: Array with no equilibrium point
        int[] test2 = {1, 2, 3};
        assert findEquilibrium(test2) == -1 : "Test Case 2 Failed";

        // Test Case 3: Single element array
        int[] test3 = {20};
        assert findEquilibrium(test3) == 0 : "Test Case 3 Failed";

        // Test Case 4: Array with negative numbers
        int[] test4 = {-7, 1, 5, 2, -4, 3, 0};
        assert findEquilibrium(test4) == 3 : "Test Case 4 Failed";

        // Test Case 5: Empty array
        int[] test5 = {};
        assert findEquilibrium(test5) == -1 : "Test Case 5 Failed";

        System.out.println("All test cases passed successfully!");
    }
}
