package GFG;

class Solution {

    /**
     * Rotates an array by d elements in counter-clockwise direction. Time
     * Complexity: O(n) where n is the size of array Space Complexity: O(1) as
     * it uses constant extra space
     *
     * @param arr The input array to be rotated
     * @param d Number of positions to rotate the array by
     */
    static void rotateArr(int arr[], int d) {
        int n = arr.length;
        d = d % n; // Handle cases where d > n
        reverse(arr, 0, d - 1); // Reverse the first part
        reverse(arr, d, n - 1); // Reverse the second part
        reverse(arr, 0, n - 1); // Reverse the entire array
    }

    /**
     * Helper function to reverse elements in an array between given indices
     *
     * @param arr The array to perform reversal on
     * @param start Starting index of the portion to be reversed
     * @param end Ending index of the portion to be reversed
     */
    static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // Test cases
    public static void main(String[] args) {
        // Test Case 1: Normal rotation
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 1:");
        System.out.print("Original Array: ");
        printArray(arr1);
        rotateArr(arr1, 2);
        System.out.print("After rotating by 2: ");
        printArray(arr1);

        // Test Case 2: Rotation when d > n
        int[] arr2 = {10, 20, 30, 40, 50};
        System.out.println("\nTest Case 2:");
        System.out.print("Original Array: ");
        printArray(arr2);
        rotateArr(arr2, 7);
        System.out.print("After rotating by 7: ");
        printArray(arr2);

        // Test Case 3: Single element array
        int[] arr3 = {1};
        System.out.println("\nTest Case 3:");
        System.out.print("Original Array: ");
        printArray(arr3);
        rotateArr(arr3, 1);
        System.out.print("After rotating by 1: ");
        printArray(arr3);
    }

    /**
     * Helper function to print array elements
     *
     * @param arr The array to be printed
     */
    static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
