
/**
 * Solution class provides methods to find the frequency of a target element in a sorted array.
 * The implementation uses binary search based approaches for efficient searching.
 */
class Solution {

    /**
     * Finds the first occurrence of a target element in a sorted array.
     *
     * @param arr The sorted array to search in
     * @param target The element to search for
     * @return The index of the first occurrence of target, or -1 if not found
     * @time_complexity O(log n) where n is the length of the array
     * @space_complexity O(1)
     */
    int findFirstOccurrence(int[] arr, int target) {
        int low = 0, high = arr.length - 1, result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                result = mid; // Record the index
                high = mid - 1; // Continue searching to the left
            } else if (arr[mid] > target) {
                high = mid - 1; // Target must be in the left half
            } else {
                low = mid + 1; // Target must be in the right half
            }
        }
        return result;
    }

    /**
     * Finds the last occurrence of a target element in a sorted array.
     *
     * @param arr The sorted array to search in
     * @param target The element to search for
     * @return The index of the last occurrence of target, or -1 if not found
     * @time_complexity O(log n) where n is the length of the array
     * @space_complexity O(1)
     */
    int findLastOccurrence(int[] arr, int target) {
        int low = 0, high = arr.length - 1, result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                result = mid; // Record the index
                low = mid + 1; // Continue searching to the right
            } else if (arr[mid] > target) {
                high = mid - 1; // Target must be in the left half
            } else {
                low = mid + 1; // Target must be in the right half
            }
        }
        return result;
    }

    /**
     * Counts the number of occurrences of a target element in a sorted array.
     *
     * @param arr The sorted array to search in
     * @param target The element to count occurrences of
     * @return The number of occurrences of target in the array
     * @time_complexity O(log n) where n is the length of the array
     * @space_complexity O(1)
     */
    int countFreq(int[] arr, int target) {
        int firstIndex = findFirstOccurrence(arr, target);
        if (firstIndex == -1) {
            return 0; // Target not found
        }
        int lastIndex = findLastOccurrence(arr, target);
        return lastIndex - firstIndex + 1; // Count of occurrences
    }

    /**
     * Test cases to verify the functionality of the Solution class.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Regular case with multiple occurrences
        int[] arr1 = {1, 1, 2, 2, 2, 2, 3};
        assert solution.countFreq(arr1, 2) == 4 : "Test Case 1 Failed";

        // Test Case 2: Element appears only once
        int[] arr2 = {1, 2, 3, 4, 5};
        assert solution.countFreq(arr2, 3) == 1 : "Test Case 2 Failed";

        // Test Case 3: Element not present in array
        int[] arr3 = {1, 2, 3, 4, 5};
        assert solution.countFreq(arr3, 6) == 0 : "Test Case 3 Failed";

        // Test Case 4: Empty array
        int[] arr4 = {};
        assert solution.countFreq(arr4, 1) == 0 : "Test Case 4 Failed";

        // Test Case 5: All elements are same
        int[] arr5 = {2, 2, 2, 2, 2};
        assert solution.countFreq(arr5, 2) == 5 : "Test Case 5 Failed";

        System.out.println("All test cases passed successfully!");
    }
}
