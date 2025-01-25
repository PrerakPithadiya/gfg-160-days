
/**
 * Solution class for counting subarrays with exactly K distinct integers.
 * This implementation uses the sliding window technique with two pointers.
 */
class Solution {

    /**
     * Counts the number of subarrays containing exactly k distinct integers.
     * Uses the principle: exactly(k) = atMost(k) - atMost(k-1)
     *
     * @param arr Input array of integers
     * @param k Number of distinct integers required
     * @return Count of subarrays with exactly k distinct integers
     * @throws IllegalArgumentException if k is negative or array is null
     */
    static int exactlyK(int arr[], int k) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (k < 0) {
            throw new IllegalArgumentException("K cannot be negative");
        }
        return atMostK(arr, k) - atMostK(arr, k - 1);
    }

    /**
     * Helper method to count subarrays with at most k distinct integers. Uses
     * sliding window approach to maintain a window with at most k distinct
     * elements.
     *
     * @param arr Input array of integers
     * @param k Maximum number of distinct integers allowed
     * @return Count of subarrays with at most k distinct integers
     */
    static int atMostK(int arr[], int k) {
        int n = arr.length;
        int[] freq = new int[n + 1];
        int distinctCount = 0;
        int left = 0;
        int result = 0;

        for (int right = 0; right < n; right++) {
            // Expand window
            if (freq[arr[right]]++ == 0) {
                distinctCount++;
            }

            // Shrink window if distinct count exceeds k
            while (distinctCount > k) {
                if (--freq[arr[left]] == 0) {
                    distinctCount--;
                }
                left++;
            }

            // Add count of subarrays ending at right
            result += right - left + 1;
        }

        return result;
    }

    /**
     * Test cases to verify the functionality of the solution.
     */
    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[] arr1 = {1, 2, 1, 2, 3};
        int k1 = 2;
        assert exactlyK(arr1, k1) == 7 : "Test case 1 failed";

        // Test Case 2: Array with all same elements
        int[] arr2 = {1, 1, 1, 1};
        int k2 = 1;
        assert exactlyK(arr2, k2) == 10 : "Test case 2 failed";

        // Test Case 3: K equals array length
        int[] arr3 = {1, 2, 3, 4};
        int k3 = 4;
        assert exactlyK(arr3, k3) == 1 : "Test case 3 failed";

        // Test Case 4: Empty array
        int[] arr4 = {};
        int k4 = 1;
        assert exactlyK(arr4, k4) == 0 : "Test case 4 failed";

        // Test Case 5: K greater than distinct elements
        int[] arr5 = {1, 2, 1, 2};
        int k5 = 3;
        assert exactlyK(arr5, k5) == 0 : "Test case 5 failed";

        System.out.println("All test cases passed!");
    }
}
