
/**
 * Solution class to count inversions in an array.
 * An inversion occurs when a[i] > a[j] where i < j.
 */
class Solution {

    /**
     * Function to count inversions in the array.
     *
     * @param arr Input array to count inversions
     * @return Total number of inversions in the array
     */
    static int inversionCount(int arr[]) {
        int n = arr.length;
        int temp[] = new int[n]; // Temporary array for merge process
        return mergeSortAndCount(arr, temp, 0, n - 1);
    }

    /**
     * Recursive function that uses merge sort to count inversions
     *
     * @param arr Input array
     * @param temp Temporary array for merging
     * @param left Left index of current subarray
     * @param right Right index of current subarray
     * @return Number of inversions in current subarray
     */
    private static int mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        int count = 0;

        if (left < right) {
            int mid = left + (right - left) / 2;

            // Count inversions in left subarray
            count += mergeSortAndCount(arr, temp, left, mid);

            // Count inversions in right subarray
            count += mergeSortAndCount(arr, temp, mid + 1, right);

            // Count inversions during merge
            count += mergeAndCount(arr, temp, left, mid, right);
        }

        return count;
    }

    /**
     * Merges two sorted subarrays and counts split inversions
     *
     * @param arr Input array
     * @param temp Temporary array for merging
     * @param left Left index of first subarray
     * @param mid Middle index separating two subarrays
     * @param right Right index of second subarray
     * @return Number of split inversions
     */
    private static int mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;     // Starting index for left subarray
        int j = mid + 1;  // Starting index for right subarray
        int k = left;     // Starting index to merge into temp
        int count = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];

                // All remaining elements in the left subarray are greater than arr[j]
                count += (mid - i + 1);
            }
        }

        // Copy remaining elements of the left subarray
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy remaining elements of the right subarray
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Copy back to the original array
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        return count;
    }

    // Test cases
    public static void main(String[] args) {
        // Test Case 1: Array with no inversions
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 1: Expected 0, Got " + inversionCount(arr1));

        // Test Case 2: Array with all inversions
        int[] arr2 = {5, 4, 3, 2, 1};
        System.out.println("Test Case 2: Expected 10, Got " + inversionCount(arr2));

        // Test Case 3: Array with some inversions
        int[] arr3 = {2, 4, 1, 3, 5};
        System.out.println("Test Case 3: Expected 3, Got " + inversionCount(arr3));

        // Test Case 4: Array with duplicate elements
        int[] arr4 = {2, 3, 3, 1, 2};
        System.out.println("Test Case 4: Expected 4, Got " + inversionCount(arr4));

        // Test Case 5: Empty array
        int[] arr5 = {};
        System.out.println("Test Case 5: Expected 0, Got " + inversionCount(arr5));
    }
}
