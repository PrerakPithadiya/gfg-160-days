package GFG;

/**
 * Solution class containing method to reverse an array in-place
 */
class Solution {

    /**
     * Reverses the elements of an array in-place Time Complexity: O(n/2) where
     * n is array length Space Complexity: O(1) as only constant extra space is
     * used
     *
     * @param arr The input array to be reversed
     */
    public void reverseArray(int arr[]) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            // Swap elements at indices left and right
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            // Move pointers
            left++;
            right--;
        }
    }

    /**
     * Test cases to verify reverseArray functionality
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Array with odd length
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 1 - Before: " + java.util.Arrays.toString(arr1));
        solution.reverseArray(arr1);
        System.out.println("Test Case 1 - After: " + java.util.Arrays.toString(arr1));

        // Test Case 2: Array with even length
        int[] arr2 = {1, 2, 3, 4};
        System.out.println("\nTest Case 2 - Before: " + java.util.Arrays.toString(arr2));
        solution.reverseArray(arr2);
        System.out.println("Test Case 2 - After: " + java.util.Arrays.toString(arr2));

        // Test Case 3: Array with single element
        int[] arr3 = {1};
        System.out.println("\nTest Case 3 - Before: " + java.util.Arrays.toString(arr3));
        solution.reverseArray(arr3);
        System.out.println("Test Case 3 - After: " + java.util.Arrays.toString(arr3));

        // Test Case 4: Empty array
        int[] arr4 = {};
        System.out.println("\nTest Case 4 - Before: " + java.util.Arrays.toString(arr4));
        solution.reverseArray(arr4);
        System.out.println("Test Case 4 - After: " + java.util.Arrays.toString(arr4));
    }
}
