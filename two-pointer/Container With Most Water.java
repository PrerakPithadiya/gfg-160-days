
/**
 * Solution class for the Container With Most Water problem.
 * This class implements an algorithm to find the container that can hold the maximum amount of water
 * given an array representing heights of vertical lines.
 */
class Solution {

    /**
     * Finds the maximum area of water that can be contained between two
     * vertical lines. Uses the two-pointer technique to efficiently find the
     * optimal solution.
     *
     * Algorithm: 1. Initialize two pointers at the start and end of the array
     * 2. Calculate area between current lines using min height * width 3.
     * Update maximum area if current area is larger 4. Move the pointer
     * pointing to shorter line inward 5. Repeat until pointers meet
     *
     * Time Complexity: O(n) where n is the length of the input array Space
     * Complexity: O(1) as only constant extra space is used
     *
     * @param arr Array of integers representing heights of vertical lines
     * @return Maximum area of water that can be contained
     */
    public int maxWater(int arr[]) {
        // Initialize two pointers and maxArea
        int left = 0, right = arr.length - 1;
        int maxArea = 0;

        // Traverse until the pointers meet
        while (left < right) {
            // Calculate the area between the current lines
            int height = Math.min(arr[left], arr[right]);
            int width = right - left;
            int area = height * width;

            // Update maxArea if the current area is larger
            maxArea = Math.max(maxArea, area);

            // Move the pointer pointing to the shorter line inward
            if (arr[left] < arr[right]) {
                left++;
            } else {
                right--;
            }
        }

        // Return the maximum area found
        return maxArea;
    }

    /**
     * Test cases to verify the functionality of maxWater method.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Regular case with multiple heights
        int[] test1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        assert solution.maxWater(test1) == 49 : "Test Case 1 Failed";

        // Test Case 2: Array with only two elements
        int[] test2 = {1, 1};
        assert solution.maxWater(test2) == 1 : "Test Case 2 Failed";

        // Test Case 3: Array with decreasing heights
        int[] test3 = {5, 4, 3, 2, 1};
        assert solution.maxWater(test3) == 4 : "Test Case 3 Failed";

        // Test Case 4: Array with increasing heights
        int[] test4 = {1, 2, 3, 4, 5};
        assert solution.maxWater(test4) == 4 : "Test Case 4 Failed";

        // Test Case 5: Array with same heights
        int[] test5 = {4, 4, 4, 4};
        assert solution.maxWater(test5) == 12 : "Test Case 5 Failed";

        System.out.println("All test cases passed successfully!");
    }
}
