
import java.util.Arrays;
import java.util.HashSet;

/**
 * Solution class containing methods to find if a pair with given sum exists in
 * an array
 */
class Solution {

    /**
     * Finds if there exists a pair of numbers in the array that sum to target
     * Uses HashSet approach with O(n) time complexity and O(n) space complexity
     *
     * @param arr Input array of integers
     * @param target Target sum to find
     * @return true if pair exists, false otherwise
     */
    boolean twoSum(int arr[], int target) {
        // Using HashSet for O(1) lookup
        HashSet<Integer> set = new HashSet<>();

        // Iterate through array once
        for (int num : arr) {
            // Check if complement (target - num) exists in set
            if (set.contains(target - num)) {
                return true;
            }
            // Add current number to set
            set.add(num);
        }

        // No pair found
        return false;
    }

    /**
     * Alternative solution using Two Pointer approach Requires sorting the
     * array first Time complexity: O(nlogn) due to sorting Space complexity:
     * O(1) as it uses constant extra space
     *
     * @param arr Input array of integers
     * @param target Target sum to find
     * @return true if pair exists, false otherwise
     */
    boolean twoSumTwoPointer(int arr[], int target) {
        // First sort the array
        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    /**
     * Test cases to verify the functionality of both approaches
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case with solution
        int[] arr1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Test Case 1 (HashSet): " + solution.twoSum(arr1, target1));      // Expected: true
        System.out.println("Test Case 1 (TwoPointer): " + solution.twoSumTwoPointer(arr1, target1));  // Expected: true

        // Test Case 2: No solution exists
        int[] arr2 = {2, 7, 11, 15};
        int target2 = 25;
        System.out.println("Test Case 2 (HashSet): " + solution.twoSum(arr2, target2));      // Expected: false
        System.out.println("Test Case 2 (TwoPointer): " + solution.twoSumTwoPointer(arr2, target2));  // Expected: false

        // Test Case 3: Array with negative numbers
        int[] arr3 = {-2, 1, -1, 2};
        int target3 = 0;
        System.out.println("Test Case 3 (HashSet): " + solution.twoSum(arr3, target3));      // Expected: true
        System.out.println("Test Case 3 (TwoPointer): " + solution.twoSumTwoPointer(arr3, target3));  // Expected: true

        // Test Case 4: Empty array
        int[] arr4 = {};
        int target4 = 0;
        System.out.println("Test Case 4 (HashSet): " + solution.twoSum(arr4, target4));      // Expected: false
        System.out.println("Test Case 4 (TwoPointer): " + solution.twoSumTwoPointer(arr4, target4));  // Expected: false

        // Test Case 5: Array with duplicates
        int[] arr5 = {3, 3, 4, 5};
        int target5 = 6;
        System.out.println("Test Case 5 (HashSet): " + solution.twoSum(arr5, target5));      // Expected: true
        System.out.println("Test Case 5 (TwoPointer): " + solution.twoSumTwoPointer(arr5, target5));  // Expected: true
    }
}
