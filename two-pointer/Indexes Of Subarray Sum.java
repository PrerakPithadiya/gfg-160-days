
import java.util.ArrayList;

/**
 * Solution class containing method to find subarray with given sum
 */
class Solution {

    /**
     * Finds the indices of a subarray that sums to the target value
     *
     * @param arr Input array of integers
     * @param target Target sum to find in subarray
     * @return ArrayList containing start and end indices (1-based) of subarray,
     * or [-1] if not found
     */
    static ArrayList<Integer> subarraySum(int[] arr, int target) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = arr.length;

        // Edge case: empty array
        if (n == 0) {
            result.add(-1);
            return result;
        }

        // Initialize window pointers and current sum
        int start = 0;
        int currentSum = 0;

        // Iterate through the array with end pointer
        for (int end = 0; end < n; end++) {
            // Add current element to window sum
            currentSum += arr[end];

            // Shrink window while sum is greater than target
            while (currentSum > target && start < end) {
                currentSum -= arr[start];
                start++;
            }

            // Check if current window sum equals target
            if (currentSum == target) {
                // Add 1-based indices to result
                result.add(start + 1);
                result.add(end + 1);
                return result;
            }
        }

        // No subarray found
        result.add(-1);
        return result;
    }

    /**
     * Test cases to verify the functionality
     */
    public static void main(String[] args) {
        // Test Case 1: Normal case with solution
        int[] arr1 = {1, 2, 3, 7, 5};
        int target1 = 12;
        ArrayList<Integer> result1 = subarraySum(arr1, target1);
        System.out.println("Test Case 1: " + result1); // Expected: [2, 4]

        // Test Case 2: No solution exists
        int[] arr2 = {1, 2, 3, 4, 5};
        int target2 = 20;
        ArrayList<Integer> result2 = subarraySum(arr2, target2);
        System.out.println("Test Case 2: " + result2); // Expected: [-1]

        // Test Case 3: Single element equals target
        int[] arr3 = {5};
        int target3 = 5;
        ArrayList<Integer> result3 = subarraySum(arr3, target3);
        System.out.println("Test Case 3: " + result3); // Expected: [1, 1]

        // Test Case 4: Empty array
        int[] arr4 = {};
        int target4 = 10;
        ArrayList<Integer> result4 = subarraySum(arr4, target4);
        System.out.println("Test Case 4: " + result4); // Expected: [-1]

        // Test Case 5: Target sum at the beginning
        int[] arr5 = {4, 2, 1, 1, 6};
        int target5 = 4;
        ArrayList<Integer> result5 = subarraySum(arr5, target5);
        System.out.println("Test Case 5: " + result5); // Expected: [1, 1]
    }
}
