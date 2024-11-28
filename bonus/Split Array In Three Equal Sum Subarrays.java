
import java.util.List;

/**
 * Solution class to find indices that split an array into three equal sum
 * subarrays.
 */
class Solution {

    /**
     * Finds two indices that split the input array into three subarrays of
     * equal sum.
     *
     * @param arr The input array to be split
     * @return A List containing two indices [i, j] where i splits the first and
     * second parts, and j splits the second and third parts. Returns [-1, -1]
     * if no valid split exists.
     */
    public List<Integer> findSplit(int[] arr) {
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        // If total sum is not divisible by 3, return {-1, -1}
        if (totalSum % 3 != 0) {
            return List.of(-1, -1);
        }

        int targetSum = totalSum / 3;
        int currentSum = 0;
        int firstIndex = -1;
        int secondIndex = -1;

        // Find the first and second indices
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];

            // First part found
            if (currentSum == targetSum && firstIndex == -1) {
                firstIndex = i;
            }

            // Second part found
            if (currentSum == 2 * targetSum && firstIndex != -1) {
                secondIndex = i;
                break; // We can break since we only need one valid pair
            }
        }

        // Check if we found valid indices
        if (firstIndex != -1 && secondIndex != -1 && secondIndex < arr.length - 1) {
            return List.of(firstIndex, secondIndex);
        }

        return List.of(-1, -1);
    }

    /**
     * Test cases for the findSplit method.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Valid split possible
        int[] arr1 = {1, 3, 2, 4, 2, 3};  // Sum = 15, each part should sum to 5
        System.out.println("Test Case 1: " + solution.findSplit(arr1));  // Expected: [1, 3]

        // Test Case 2: Sum not divisible by 3
        int[] arr2 = {1, 2, 3, 4};  // Sum = 10
        System.out.println("Test Case 2: " + solution.findSplit(arr2));  // Expected: [-1, -1]

        // Test Case 3: Array too small
        int[] arr3 = {1, 2};
        System.out.println("Test Case 3: " + solution.findSplit(arr3));  // Expected: [-1, -1]

        // Test Case 4: Equal elements
        int[] arr4 = {3, 3, 3, 3, 3, 3};  // Sum = 18, each part should sum to 6
        System.out.println("Test Case 4: " + solution.findSplit(arr4));  // Expected: [1, 3]

        // Test Case 5: Zero elements
        int[] arr5 = {0, 0, 0, 0, 0, 0};  // Sum = 0, each part should sum to 0
        System.out.println("Test Case 5: " + solution.findSplit(arr5));  // Expected: [1, 3]
    }
}
