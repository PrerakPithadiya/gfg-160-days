
/**
 * Solution class to find the maximum sum of a circular subarray
 */
class Solution {

    /**
     * Function to find maximum circular subarray sum. This function handles two
     * cases: 1. The maximum sum subarray is not circular (using standard
     * Kadane's algorithm) 2. The maximum sum subarray is circular (by finding
     * the minimum subarray sum)
     *
     * @param arr Input array of integers
     * @return Maximum circular subarray sum
     */
    public int circularSubarraySum(int arr[]) {
        int n = arr.length;

        // Step 1: Find the maximum subarray sum using Kadane's algorithm
        int maxKadane = kadane(arr);

        // Step 2: Find the total sum of the array
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        // Step 3: Find the minimum subarray sum using Kadane's algorithm
        // We can invert the values in the array to find the minimum subarray sum
        for (int i = 0; i < n; i++) {
            arr[i] = -arr[i];
        }

        int minKadane = kadane(arr);

        // Step 4: Calculate the maximum circular subarray sum
        int maxCircular = totalSum + minKadane; // since minKadane is negative, we add it

        // Step 5: The result is the maximum of the two cases
        // We need to check if maxCircular is valid (not equal to totalSum)
        if (maxCircular == 0) {
            return maxKadane; // If all elements are negative, return the maxKadane
        }

        return Math.max(maxKadane, maxCircular);
    }

    /**
     * Helper function to implement Kadane's algorithm for finding maximum
     * subarray sum
     *
     * @param arr Input array of integers
     * @return Maximum subarray sum
     */
    private int kadane(int[] arr) {
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];

        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    /**
     * Test cases to verify the functionality
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Normal array with positive and negative numbers
        int[] arr1 = {8, -8, 9, -9, 10, -11, 12};
        System.out.println("Test Case 1: " + solution.circularSubarraySum(arr1)); // Expected: 22

        // Test Case 2: All positive numbers
        int[] arr2 = {10, 5, 3, 8, 4};
        System.out.println("Test Case 2: " + solution.circularSubarraySum(arr2)); // Expected: 30

        // Test Case 3: All negative numbers
        int[] arr3 = {-1, -2, -3, -4};
        System.out.println("Test Case 3: " + solution.circularSubarraySum(arr3)); // Expected: -1

        // Test Case 4: Single element array
        int[] arr4 = {5};
        System.out.println("Test Case 4: " + solution.circularSubarraySum(arr4)); // Expected: 5

        // Test Case 5: Array with zeros
        int[] arr5 = {0, 0, 1, -2, 3};
        System.out.println("Test Case 5: " + solution.circularSubarraySum(arr5)); // Expected: 4
    }
}
