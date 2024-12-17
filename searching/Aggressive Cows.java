
import java.util.Arrays;

/**
 * Solution class containing methods to solve the Aggressive Cows problem.
 * Problem Statement: Given an array of stall positions and number of cows, find
 * the largest minimum distance possible between any two cows.
 */
class Solution {

    /**
     * Finds the largest minimum distance at which k cows can be placed in
     * stalls. Uses binary search approach to efficiently find the optimal
     * distance.
     *
     * @param stalls Array containing positions of stalls
     * @param k Number of cows to be placed
     * @return Largest minimum distance possible between any two cows
     * @throws IllegalArgumentException if stalls array is null or empty, or if
     * k is less than 2
     */
    public static int aggressiveCows(int[] stalls, int k) {
        if (stalls == null || stalls.length == 0) {
            throw new IllegalArgumentException("Stalls array cannot be null or empty");
        }
        if (k < 2) {
            throw new IllegalArgumentException("Number of cows must be at least 2");
        }

        // Step 1: Sort the stalls array
        Arrays.sort(stalls);

        // Step 2: Initialize binary search boundaries
        int low = 1; // Minimum possible distance
        int high = stalls[stalls.length - 1] - stalls[0]; // Maximum possible distance
        int result = 0;

        // Step 3: Binary search for the largest minimum distance
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canPlaceCows(stalls, k, mid)) {
                result = mid; // Update the result if feasible
                low = mid + 1; // Search for a larger distance
            } else {
                high = mid - 1; // Search for a smaller distance
            }
        }

        return result;
    }

    /**
     * Helper function to check if cows can be placed with a given minimum
     * distance.
     *
     * @param stalls Array containing positions of stalls
     * @param k Number of cows to be placed
     * @param mid Minimum distance to be checked
     * @return true if cows can be placed with given minimum distance, false
     * otherwise
     */
    private static boolean canPlaceCows(int[] stalls, int k, int mid) {
        int count = 1; // Place the first cow in the first stall
        int lastPosition = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPosition >= mid) {
                count++;
                lastPosition = stalls[i]; // Update last position where cow was placed

                if (count == k) {
                    return true; // All cows have been placed successfully
                }
            }
        }

        return false; // Not possible to place all cows with this minimum distance
    }

    /**
     * Test cases to verify the functionality of aggressiveCows method.
     */
    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[] stalls1 = {1, 2, 4, 8, 9};
        int k1 = 3;
        assert aggressiveCows(stalls1, k1) == 3;
        System.out.println("Test Case 1 Passed");

        // Test Case 2: Minimum distance case
        int[] stalls2 = {1, 2, 3, 4, 5};
        int k2 = 5;
        assert aggressiveCows(stalls2, k2) == 1;
        System.out.println("Test Case 2 Passed");

        // Test Case 3: Maximum distance case
        int[] stalls3 = {1, 10};
        int k3 = 2;
        assert aggressiveCows(stalls3, k3) == 9;
        System.out.println("Test Case 3 Passed");

        // Test Case 4: Large numbers
        int[] stalls4 = {1, 5, 8, 11, 13, 15, 18};
        int k4 = 4;
        assert aggressiveCows(stalls4, k4) == 4;
        System.out.println("Test Case 4 Passed");

        try {
            // Test Case 5: Invalid input - empty array
            aggressiveCows(new int[]{}, 2);
            System.out.println("Test Case 5 Failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 5 Passed");
        }

        try {
            // Test Case 6: Invalid input - invalid number of cows
            aggressiveCows(stalls1, 1);
            System.out.println("Test Case 6 Failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 6 Passed");
        }
    }
}
