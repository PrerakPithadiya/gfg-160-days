package GFG;

import java.util.Arrays;

/**
 * Solution class to minimize the difference between heights Problem: Given an
 * array arr[] denoting heights of N towers and a positive integer K, modify the
 * heights of each tower either by adding or subtracting K. The task is to
 * minimize the difference between the maximum height and minimum height.
 */
class Solution {

    /**
     * Calculates the minimum difference between maximum and minimum heights
     * after modifications
     *
     * @param arr Array of heights
     * @param k Value that can be added or subtracted from each element
     * @return Minimum difference possible between maximum and minimum heights
     */
    public int getMinDiff(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);

        int ans = arr[n - 1] - arr[0]; // Initial difference

        for (int i = 1; i < n; i++) {
            int smallest = Math.min(arr[0] + k, arr[i] - k);
            int largest = Math.max(arr[n - 1] - k, arr[i - 1] + k);

            if (smallest < 0) {
                continue; // Skip if smallest becomes negative
            }

            ans = Math.min(ans, largest - smallest);
        }

        return ans;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1
        int[] arr1 = {1, 5, 8, 10};
        int k1 = 2;
        System.out.println("Test Case 1: " + solution.getMinDiff(arr1, k1)); // Expected output: 5

        // Test Case 2
        int[] arr2 = {3, 9, 12, 16, 20};
        int k2 = 3;
        System.out.println("Test Case 2: " + solution.getMinDiff(arr2, k2)); // Expected output: 11

        // Test Case 3
        int[] arr3 = {6, 1, 9, 1, 1, 7, 9, 5, 2, 10};
        int k3 = 4;
        System.out.println("Test Case 3: " + solution.getMinDiff(arr3, k3)); // Expected output: 5
    }
}
