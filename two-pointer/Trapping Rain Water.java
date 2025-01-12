
/**
 * Solution class for the Trapping Rain Water problem.
 * This class provides a method to calculate the amount of water that can be trapped between bars of different heights.
 */
class Solution {

    /**
     * Calculates the maximum amount of water that can be trapped between bars.
     * Uses the two-pointer technique to efficiently compute the trapped water.
     *
     * Algorithm: 1. Initialize two pointers (left and right) at the start and
     * end of array 2. Keep track of maximum height seen from both left and
     * right sides 3. For the smaller of leftMax and rightMax, calculate trapped
     * water at that position 4. Move the pointer of the smaller maximum height
     * inward
     *
     * Time Complexity: O(n) where n is the length of the input array Space
     * Complexity: O(1) as only constant extra space is used
     *
     * @param arr Array representing heights of bars
     * @return Total units of water that can be trapped
     */
    public int maxWater(int arr[]) {
        int left = 0;
        int right = arr.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        while (left < right) {
            // Update the maximum height seen from left side
            leftMax = Math.max(leftMax, arr[left]);
            // Update the maximum height seen from right side
            rightMax = Math.max(rightMax, arr[right]);

            if (leftMax < rightMax) {
                // If leftMax is smaller, we can safely calculate water for left position
                water += Math.max(0, leftMax - arr[left]);
                left++;
            } else {
                // If rightMax is smaller, we can safely calculate water for right position
                water += Math.max(0, rightMax - arr[right]);
                right--;
            }
        }

        return water;
    }

    /**
     * Test cases to verify the functionality of maxWater method.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Regular case with trapped water
        int[] arr1 = {3, 0, 2, 0, 4};
        assert solution.maxWater(arr1) == 7 : "Test Case 1 Failed";

        // Test Case 2: No trapped water possible
        int[] arr2 = {1, 2, 3, 4, 5};
        assert solution.maxWater(arr2) == 0 : "Test Case 2 Failed";

        // Test Case 3: Single valley
        int[] arr3 = {4, 2, 3};
        assert solution.maxWater(arr3) == 1 : "Test Case 3 Failed";

        // Test Case 4: Multiple valleys
        int[] arr4 = {2, 0, 2, 1, 4, 0, 3};
        assert solution.maxWater(arr4) == 8 : "Test Case 4 Failed";

        // Test Case 5: Empty array
        int[] arr5 = {};
        assert solution.maxWater(arr5) == 0 : "Test Case 5 Failed";

        System.out.println("All test cases passed!");
    }
}
