
/**
 * Solution class to find a peak element in an array.
 * A peak element is one that is greater than or equal to its neighbors.
 * For corner elements, we only need to compare with the single neighbor.
 */
class Solution {

    /**
     * Finds a peak element in the given array using binary search approach.
     * Time Complexity: O(log n) where n is the length of array Space
     * Complexity: O(1)
     *
     * @param arr Input array in which to find a peak element
     * @return Index of any peak element in the array
     */
    public int peakElement(int[] arr) {
        int low = 0, high = arr.length - 1;

        // Binary search loop
        while (low < high) {
            int mid = low + (high - low) / 2;

            // Check if mid is a peak
            if ((mid == 0 || arr[mid] > arr[mid - 1])
                    && (mid == arr.length - 1 || arr[mid] > arr[mid + 1])) {
                return mid; // mid is a peak
            }

            // If the right neighbor is greater, move to the right half
            if (mid < arr.length - 1 && arr[mid] < arr[mid + 1]) {
                low = mid + 1;
            } // Otherwise, move to the left half
            else {
                high = mid - 1;
            }
        }

        // At the end, low == high, which is the peak
        return low;
    }

    /**
     * Test cases to verify the functionality of peakElement method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Array with single element
        int[] test1 = {1};
        assert solution.peakElement(test1) == 0 : "Test Case 1 Failed";

        // Test Case 2: Array with all elements same
        int[] test2 = {1, 1, 1, 1};
        int result2 = solution.peakElement(test2);
        assert result2 >= 0 && result2 < test2.length : "Test Case 2 Failed";

        // Test Case 3: Array with peak in middle
        int[] test3 = {1, 2, 3, 1};
        assert solution.peakElement(test3) == 2 : "Test Case 3 Failed";

        // Test Case 4: Array with peak at start
        int[] test4 = {5, 4, 3, 2, 1};
        assert solution.peakElement(test4) == 0 : "Test Case 4 Failed";

        // Test Case 5: Array with peak at end
        int[] test5 = {1, 2, 3, 4, 5};
        assert solution.peakElement(test5) == 4 : "Test Case 5 Failed";

        // Test Case 6: Array with multiple peaks
        int[] test6 = {1, 3, 2, 4, 1, 5};
        int result6 = solution.peakElement(test6);
        assert (result6 == 1 || result6 == 3 || result6 == 5) : "Test Case 6 Failed";

        System.out.println("All test cases passed successfully!");
    }
}
