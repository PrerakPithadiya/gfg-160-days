package GFG;

/**
 * Solution class containing method to move all zeros to the end of an array
 * while maintaining the relative order of non-zero elements.
 */
class Solution {

    /**
     * Moves all zeros to the end of the array while maintaining the relative
     * order of non-zero elements.
     *
     * @param arr The input array to be modified
     */
    void pushZerosToEnd(int[] arr) {
        int lastNonZeroIndex = 0; // Pointer for the position of the last non-zero element

        // Move non-zero elements to the front
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[lastNonZeroIndex] = arr[i];
                lastNonZeroIndex++;
            }
        }

        // Fill the remaining positions with zeros
        for (int i = lastNonZeroIndex; i < arr.length; i++) {
            arr[i] = 0;
        }
    }

    /**
     * Main method to test the pushZerosToEnd functionality
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Array with zeros in between
        int[] arr1 = {1, 0, 3, 0, 4, 5, 0};
        System.out.println("Test Case 1 - Before: java.util.Arrays.toString(arr1)");
        solution.pushZerosToEnd(arr1);
        System.out.println("Test Case 1 - After: " + java.util.Arrays.toString(arr1));

        // Test Case 2: Array with all zeros
        int[] arr2 = {0, 0, 0, 0};
        System.out.println("\nTest Case 2 - Before: " + java.util.Arrays.toString(arr2));
        solution.pushZerosToEnd(arr2);
        System.out.println("Test Case 2 - After: " + java.util.Arrays.toString(arr2));

        // Test Case 3: Array with no zeros
        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("\nTest Case 3 - Before: " + java.util.Arrays.toString(arr3));
        solution.pushZerosToEnd(arr3);
        System.out.println("Test Case 3 - After: " + java.util.Arrays.toString(arr3));

        // Test Case 4: Empty array
        int[] arr4 = {};
        System.out.println("\nTest Case 4 - Before: " + java.util.Arrays.toString(arr4));
        solution.pushZerosToEnd(arr4);
        System.out.println("Test Case 4 - After: " + java.util.Arrays.toString(arr4));
    }
}
