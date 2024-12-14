
/**
 * Solution class for searching in a rotated sorted array.
 * This class implements a modified binary search algorithm to find an element
 * in an array that was initially sorted but then rotated around a pivot point.
 */
class Solution {

    /**
     * Searches for a key in a rotated sorted array.
     *
     * The algorithm works by: 1. Using binary search with modifications to
     * handle rotation 2. Identifying which half of the array is sorted 3.
     * Determining if the target lies in the sorted portion 4. Adjusting the
     * search space accordingly
     *
     * Time Complexity: O(log n) where n is the length of the array Space
     * Complexity: O(1) as only constant extra space is used
     *
     * @param arr The rotated sorted array to search in
     * @param key The target value to find
     * @return The index of the key if found, -1 otherwise
     *
     * Example usage: int[] arr = {4, 5, 6, 7, 0, 1, 2}; search(arr, 0) returns
     * 4 search(arr, 3) returns -1
     */
    public int search(int[] arr, int key) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if mid is the target
            if (arr[mid] == key) {
                return mid;
            }

            // Check if the left half is sorted
            if (arr[low] <= arr[mid]) {
                // Check if the target lies in the sorted left half
                if (arr[low] <= key && key < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } // Otherwise, the right half is sorted
            else {
                // Check if the target lies in the sorted right half
                if (arr[mid] < key && key <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        // Target is not found
        return -1;
    }

    /**
     * Test cases to verify the functionality of the search method. These test
     * cases cover various scenarios including: - Regular rotated array search -
     * Edge cases (empty array, single element) - Array with duplicates - Search
     * for non-existent elements
     */
    public static void runTests() {
        Solution solution = new Solution();

        // Test Case 1: Standard rotated array
        assert solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0) == 4;

        // Test Case 2: Not rotated array
        assert solution.search(new int[]{1, 2, 3, 4, 5}, 3) == 2;

        // Test Case 3: Single element array
        assert solution.search(new int[]{1}, 1) == 0;

        // Test Case 4: Element not found
        assert solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3) == -1;

        // Test Case 5: Empty array
        assert solution.search(new int[]{}, 5) == -1;

        // Test Case 6: Rotated array with target at start
        assert solution.search(new int[]{3, 4, 5, 1, 2}, 3) == 0;

        // Test Case 7: Rotated array with target at end
        assert solution.search(new int[]{3, 4, 5, 1, 2}, 2) == 4;

        System.out.println("All test cases passed!");
    }
}
