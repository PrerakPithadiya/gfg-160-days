
import java.util.Arrays;

/**
 * Solution class containing method to count pairs with sum less than target
 */
class Solution {

    /**
     * Counts the number of pairs in a sorted array whose sum is less than the
     * target value using two-pointer technique.
     *
     * Time Complexity: O(n log n) due to sorting, where n is length of array
     * Space Complexity: O(1) as only constant extra space is used
     *
     * @param arr The input array of integers
     * @param target The target sum value
     * @return Number of pairs whose sum is less than target
     */
    int countPairs(int arr[], int target) {
        // Sort the array first for two-pointer approach
        // Using dual-pivot quicksort (Arrays.sort internal implementation)
        Arrays.sort(arr);

        int count = 0;
        int left = 0;
        int right = arr.length - 1;

        // Two-pointer approach
        while (left < right) {
            // If sum of current pair is less than target
            if (arr[left] + arr[right] < target) {
                // All pairs between left and right will also have sum less than target
                // because array is sorted
                count += right - left;
                left++;
            } else {
                // If current sum is >= target, move right pointer left
                right--;
            }
        }

        return count;
    }

    /**
     * Main method containing test cases
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case
        int[] arr1 = {1, 2, 3, 4, 5};
        int target1 = 5;
        System.out.println("Test Case 1: " + solution.countPairs(arr1, target1)); // Expected: 4

        // Test Case 2: Array with negative numbers
        int[] arr2 = {-2, -1, 0, 3, 4};
        int target2 = 2;
        System.out.println("Test Case 2: " + solution.countPairs(arr2, target2)); // Expected: 6

        // Test Case 3: Array with duplicate elements
        int[] arr3 = {1, 1, 1, 2, 2};
        int target3 = 3;
        System.out.println("Test Case 3: " + solution.countPairs(arr3, target3)); // Expected: 7

        // Test Case 4: Empty array
        int[] arr4 = {};
        int target4 = 5;
        System.out.println("Test Case 4: " + solution.countPairs(arr4, target4)); // Expected: 0

        // Test Case 5: Array with single element
        int[] arr5 = {1};
        int target5 = 5;
        System.out.println("Test Case 5: " + solution.countPairs(arr5, target5)); // Expected: 0
    }

}
