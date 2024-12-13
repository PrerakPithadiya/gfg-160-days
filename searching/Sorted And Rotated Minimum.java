
/**
 * Solution class to find the minimum element in a sorted and rotated array.
 * The array may contain duplicates.
 */
class Solution {

    /**
     * Finds the minimum element in a sorted and rotated array using modified
     * binary search.
     *
     * Algorithm: 1. Use binary search with two pointers (low and high) 2.
     * Compare middle element with high element to determine which half contains
     * minimum 3. Handle three cases: - If mid element < high element: minimum is in left half (including mid)
     *    - If mid element > high element: minimum is in right half (excluding mid)
     * - If mid element = high element: cannot determine, reduce search space
     *
     * Time Complexity: O(log n) in best/average case, O(n) in worst case with
     * duplicates Space Complexity: O(1)
     *
     * @param arr The input array that is sorted and possibly rotated
     * @return The minimum element in the array
     * @throws IllegalArgumentException if the input array is null or empty
     */
    public int findMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty");
        }

        int low = 0, high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // If the middle element is less than the last element, the pivot is in the left half
            if (arr[mid] < arr[high]) {
                high = mid; // Minimum lies in the left half, including mid
            } // If the middle element is greater than the last element, the pivot is in the right half
            else if (arr[mid] > arr[high]) {
                low = mid + 1; // Minimum lies in the right half, excluding mid
            } // When arr[mid] == arr[high], we cannot determine the side; reduce the search space
            else {
                high--; // Move high one step left to skip the duplicate
            }
        }

        // After the loop, low == high, which is the index of the minimum element
        return arr[low];
    }

    /**
     * Test cases to verify the functionality of findMin method.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Regular sorted and rotated array
        int[] test1 = {4, 5, 6, 7, 0, 1, 2};
        assert solution.findMin(test1) == 0 : "Test Case 1 Failed";

        // Test Case 2: Array with duplicates
        int[] test2 = {2, 2, 2, 0, 1};
        assert solution.findMin(test2) == 0 : "Test Case 2 Failed";

        // Test Case 3: Already sorted array
        int[] test3 = {1, 2, 3, 4, 5};
        assert solution.findMin(test3) == 1 : "Test Case 3 Failed";

        // Test Case 4: Array with all elements same
        int[] test4 = {1, 1, 1, 1};
        assert solution.findMin(test4) == 1 : "Test Case 4 Failed";

        // Test Case 5: Small array
        int[] test5 = {2, 1};
        assert solution.findMin(test5) == 1 : "Test Case 5 Failed";

        // Test Case 6: Single element array
        int[] test6 = {1};
        assert solution.findMin(test6) == 1 : "Test Case 6 Failed";

        System.out.println("All test cases passed!");
    }
}
