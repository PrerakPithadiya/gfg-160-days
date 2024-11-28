
/**
 * Solution class to find the maximum number of consecutive 1's after flipping at most k zeros.
 * Uses the sliding window technique to efficiently solve the problem.
 */
class Solution {

    /**
     * Finds the maximum number of consecutive 1's that can be obtained by
     * flipping at most k zeros.
     *
     * @param arr The input array containing 0's and 1's
     * @param k The maximum number of zeros allowed to flip
     * @return The length of the longest sequence of consecutive 1's possible
     * after flipping at most k zeros
     */
    public int maxOnes(int arr[], int k) {
        int left = 0; // Left pointer of the window
        int right = 0; // Right pointer of the window
        int zeroCount = 0; // Count of zeros in the current window
        int maxLength = 0; // Maximum length of consecutive 1's found

        while (right < arr.length) {
            // If we encounter a zero, increment the zero count
            if (arr[right] == 0) {
                zeroCount++;
            }

            // If the zero count exceeds k, shrink the window from the left
            while (zeroCount > k) {
                if (arr[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Calculate the maximum length of the window
            maxLength = Math.max(maxLength, right - left + 1);
            right++; // Move the right pointer to expand the window
        }

        return maxLength; // Return the maximum length found
    }

    /**
     * Main method to test the maxOnes function with various test cases.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case with k = 2
        int[] arr1 = {1, 0, 0, 1, 1, 0, 1};
        int k1 = 2;
        System.out.println("Test Case 1: " + solution.maxOnes(arr1, k1)); // Expected output: 6

        // Test Case 2: Array with all zeros
        int[] arr2 = {0, 0, 0, 0};
        int k2 = 2;
        System.out.println("Test Case 2: " + solution.maxOnes(arr2, k2)); // Expected output: 2

        // Test Case 3: Array with all ones
        int[] arr3 = {1, 1, 1, 1};
        int k3 = 2;
        System.out.println("Test Case 3: " + solution.maxOnes(arr3, k3)); // Expected output: 4

        // Test Case 4: k = 0 (no flips allowed)
        int[] arr4 = {1, 1, 0, 0, 1, 1};
        int k4 = 0;
        System.out.println("Test Case 4: " + solution.maxOnes(arr4, k4)); // Expected output: 2

        // Test Case 5: k equals array length
        int[] arr5 = {0, 0, 1, 0, 0};
        int k5 = 5;
        System.out.println("Test Case 5: " + solution.maxOnes(arr5, k5)); // Expected output: 5
    }
}
