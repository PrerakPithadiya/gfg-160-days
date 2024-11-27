package GFG;

/**
 * Solution class to find the smallest positive missing number in an array
 */
class Solution {

    /**
     * Function to find the smallest positive number missing from the array.
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * @param arr Input array of integers
     * @return The smallest positive integer missing from the array
     */
    public int missingNumber(int[] arr) {
        int n = arr.length;

        // Step 1: Rearrange the array
        for (int i = 0; i < n; i++) {
            // Place arr[i] in its right position if it's a valid positive integer
            while (arr[i] > 0 && arr[i] <= n && arr[arr[i] - 1] != arr[i]) {
                // Swap arr[i] with arr[arr[i] - 1]
                int temp = arr[i];
                arr[i] = arr[temp - 1];
                arr[temp - 1] = temp;
            }
        }

        // Step 2: Find the first missing positive integer
        for (int i = 0; i < n; i++) {
            if (arr[i] != i + 1) {
                return i + 1; // The smallest missing positive number
            }
        }

        // If all positions are filled correctly, return n + 1
        return n + 1;
    }

    /**
     * Main method to test the solution with various test cases
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic array with missing number
        int[] arr1 = {1, 2, 3, 5};
        System.out.println("Test Case 1: Expected = 4, Got = " + solution.missingNumber(arr1));

        // Test Case 2: Array with negative numbers
        int[] arr2 = {-1, -3, 1, 3, 4};
        System.out.println("Test Case 2: Expected = 2, Got = " + solution.missingNumber(arr2));

        // Test Case 3: Array with duplicates
        int[] arr3 = {1, 1, 2, 2};
        System.out.println("Test Case 3: Expected = 3, Got = " + solution.missingNumber(arr3));

        // Test Case 4: Array with all numbers present
        int[] arr4 = {1, 2, 3};
        System.out.println("Test Case 4: Expected = 4, Got = " + solution.missingNumber(arr4));

        // Test Case 5: Array with only negative numbers
        int[] arr5 = {-1, -2, -3};
        System.out.println("Test Case 5: Expected = 1, Got = " + solution.missingNumber(arr5));
    }

}
