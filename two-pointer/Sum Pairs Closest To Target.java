
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solution class to find the pair of numbers in an array whose sum is closest
 * to a target value. Time Complexity: O(n log n) due to sorting Space
 * Complexity: O(1) excluding the output list
 */
class Solution {

    /**
     * Finds a pair of integers from the given array whose sum is closest to the
     * target value. If multiple pairs have the same closest difference, returns
     * the pair with larger absolute difference between elements.
     *
     * @param arr The input array of integers
     * @param target The target sum value
     * @return List containing the pair of integers whose sum is closest to
     * target. Returns empty list if array has less than 2 elements.
     */
    public List<Integer> sumClosest(int[] arr, int target) {
        List<Integer> result = new ArrayList<>();
        int n = arr.length;

        // Check if array has less than 2 elements
        if (n < 2) {
            return result;
        }

        // Sort the array first
        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;
        int minDiff = Integer.MAX_VALUE;

        // Variables to store the best pair
        int bestLeft = -1;
        int bestRight = -1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            int currentDiff = Math.abs(sum - target);

            // If we found a close pair or same difference with larger absolute difference
            if (currentDiff < minDiff || (currentDiff == minDiff && Math.abs(arr[right] - arr[left]) > Math.abs(arr[bestRight] - arr[bestLeft]))) {
                minDiff = currentDiff;
                bestLeft = left;
                bestRight = right;
            }

            // Move pointer based on sum comparison
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                break;
            }
        }

        // Add the best pair to result if found
        if (bestLeft != -1) {
            result.add(arr[bestLeft]);
            result.add(arr[bestRight]);
        }

        return result;
    }

    /**
     * Test cases to verify the functionality of sumClosest method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Normal case
        int[] arr1 = {1, 4, 5, 7};
        int target1 = 10;
        assert solution.sumClosest(arr1, target1).equals(Arrays.asList(4, 5)) : "Test case 1 failed";

        // Test Case 2: Exact sum exists
        int[] arr2 = {2, 3, 5, 8, 9};
        int target2 = 7;
        assert solution.sumClosest(arr2, target2).equals(Arrays.asList(2, 5)) : "Test case 2 failed";

        // Test Case 3: Empty array
        int[] arr3 = {};
        int target3 = 5;
        assert solution.sumClosest(arr3, target3).isEmpty() : "Test case 3 failed";

        // Test Case 4: Single element array
        int[] arr4 = {1};
        int target4 = 5;
        assert solution.sumClosest(arr4, target4).isEmpty() : "Test case 4 failed";

        // Test Case 5: Negative numbers
        int[] arr5 = {-5, -3, -1, 2, 4, 6};
        int target5 = 0;
        assert solution.sumClosest(arr5, target5).equals(Arrays.asList(-1, 2)) : "Test case 5 failed";

        // Test Case 6: Same difference but larger absolute difference
        int[] arr6 = {1, 2, 3, 4, 5};
        int target6 = 7;
        assert solution.sumClosest(arr6, target6).equals(Arrays.asList(2, 5)) : "Test case 6 failed";

        System.out.println("All test cases passed!");
    }
}
