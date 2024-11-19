package GFG;

/**
 * Solution class to find the next lexicographically greater permutation of an
 * array
 */
class Solution {

    /**
     * Finds the next lexicographically greater permutation of the given array.
     * Modifies the array in-place to store the next permutation. If no greater
     * permutation exists, the array is sorted in ascending order.
     *
     * @param arr The input array to find next permutation for
     */
    void nextPermutation(int[] arr) {
        int n = arr.length;

        // Step 1: Find the first pair arr[i] < arr[i + 1] from the end
        int i = n - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        // Step 2: If such pair is found, find the smallest number larger than arr[i]
        if (i >= 0) {
            int j = n - 1;
            while (arr[j] <= arr[i]) {
                j--;
            }
            // Swap arr[i] and arr[j]
            swap(arr, i, j);
        }

        // Step 3: Reverse the elements after position i
        reverse(arr, i + 1, n - 1);
    }

    /**
     * Swaps two elements in an array
     *
     * @param arr The array containing elements to swap
     * @param i Index of first element
     * @param j Index of second element
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Reverses elements in an array between given indices
     *
     * @param arr The array to reverse elements in
     * @param i Starting index
     * @param j Ending index
     */
    private void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }

    }

    /**
     * Main method to test the next permutation implementation
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Normal case
        int[] arr1 = {1, 2, 3};
        solution.nextPermutation(arr1);
        System.out.println("Test Case 1: Expected [1, 3, 2]");
        printArray(arr1);

        // Test Case 2: Descending order
        int[] arr2 = {3, 2, 1};
        solution.nextPermutation(arr2);
        System.out.println("Test Case 2: Expected [1, 2, 3]");
        printArray(arr2);

        // Test Case 3: All same elements
        int[] arr3 = {1, 1, 1};
        solution.nextPermutation(arr3);
        System.out.println("Test Case 3: Expected [1, 1, 1]");
        printArray(arr3);

        // Test Case 4: Complex case
        int[] arr4 = {1, 1, 5, 4, 1};
        solution.nextPermutation(arr4);
        System.out.println("Test Case 4: Expected [1, 4, 1, 1, 5]");
        printArray(arr4);
    }

    /**
     * Helper method to print array
     */
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
