
/**
 * Solution class to sort an array containing only 0s, 1s, and 2s (Dutch National Flag problem)
 * Time Complexity: O(n) where n is the size of the array
 * Space Complexity: O(1) as it uses constant extra space
 */
class Solution {

    /**
     * Sorts an array containing only 0s, 1s, and 2s using the Dutch National
     * Flag algorithm
     *
     * @param arr The input array to be sorted
     */
    public void sort012(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;

        while (mid <= high) {
            switch (arr[mid]) {
                case 0 -> {
                    // Swap arr[low] and arr[mid], increment both
                    swap(arr, low, mid);
                    low++;
                    mid++;
                }
                case 1 -> // Move mid pointer
                    mid++;
                default -> {
                    // Swap arr[mid] and arr[high], decrement high
                    swap(arr, mid, high);
                    high--;
                }
            }
        }
    }

    /**
     * Helper function to swap two elements in the array
     *
     * @param arr The array containing elements to be swapped
     * @param i Index of first element
     * @param j Index of second element
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Main method to test the sort012 function
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Random array
        int[] arr1 = {2, 0, 1, 2, 1, 0};
        solution.sort012(arr1);
        assert java.util.Arrays.equals(arr1, new int[]{0, 0, 1, 1, 2, 2});

        // Test Case 2: Already sorted array
        int[] arr2 = {0, 0, 1, 1, 2, 2};
        solution.sort012(arr2);
        assert java.util.Arrays.equals(arr2, new int[]{0, 0, 1, 1, 2, 2});

        // Test Case 3: Reverse sorted array
        int[] arr3 = {2, 2, 1, 1, 0, 0};
        solution.sort012(arr3);
        assert java.util.Arrays.equals(arr3, new int[]{0, 0, 1, 1, 2, 2});

        // Test Case 4: All same elements
        int[] arr4 = {1, 1, 1};
        solution.sort012(arr4);
        assert java.util.Arrays.equals(arr4, new int[]{1, 1, 1});

        // Test Case 5: Empty array
        int[] arr5 = {};
        solution.sort012(arr5);
        assert java.util.Arrays.equals(arr5, new int[]{});

        System.out.println("All test cases passed!");
    }
}
