
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solution class for merging overlapping intervals. This class provides
 * functionality to merge overlapping intervals in a sorted array.
 */
class Solution {

    /**
     * Merges overlapping intervals in the given 2D array.
     *
     * @param arr A 2D array where each inner array represents an interval with
     * start and end times
     * @return List<int[]> A list of merged intervals with no overlaps
     *
     * Algorithm: 1. First checks if input array is empty 2. Sorts intervals
     * based on start time 3. Iterates through sorted intervals and merges
     * overlapping ones 4. Returns the final list of merged intervals
     *
     * Time Complexity: O(n log n) due to sorting Space Complexity: O(n) to
     * store the merged intervals
     *
     * Example Usage: int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};
     * List<int[]> merged = mergeOverlap(intervals); Result: [{1,6}, {8,10},
     * {15,18}]
     */
    public List<int[]> mergeOverlap(int[][] arr) {
        // If there are no intervals, return an empty list
        if (arr.length == 0) {
            return new ArrayList<>();
        }

        // Step 1: Sort the intervals based on the starting time
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Create a list to hold the merged intervals
        List<int[]> merged = new ArrayList<>();

        // Start with the first interval
        int[] currentInterval = arr[0];
        merged.add(currentInterval);

        // Step 3: Iterate through the sorted intervals
        for (int i = 1; i < arr.length; i++) {
            int[] interval = arr[i];

            // If the current interval overlaps with the last merged interval
            if (interval[0] <= currentInterval[1]) {
                // Merge them by updating the end time
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            } else {
                // If they do not overlap, add the current interval to the merged list
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        return merged;
    }

    /**
     * Test cases to verify the functionality of mergeOverlap method
     */
    public void runTests() {
        // Test Case 1: Normal overlapping intervals
        int[][] test1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        assert Arrays.deepEquals(mergeOverlap(test1).toArray(new int[0][]), new int[][]{{1, 6}, {8, 10}, {15, 18}});

        // Test Case 2: Empty array
        int[][] test2 = {};
        assert mergeOverlap(test2).isEmpty();

        // Test Case 3: Single interval
        int[][] test3 = {{1, 1}};
        assert Arrays.deepEquals(mergeOverlap(test3).toArray(new int[0][]), new int[][]{{1, 1}});

        // Test Case 4: Completely overlapping intervals
        int[][] test4 = {{1, 4}, {2, 3}};
        assert Arrays.deepEquals(mergeOverlap(test4).toArray(new int[0][]), new int[][]{{1, 4}});

        // Test Case 5: Non-overlapping intervals
        int[][] test5 = {{1, 2}, {3, 4}, {5, 6}};
        assert Arrays.deepEquals(mergeOverlap(test5).toArray(new int[0][]), new int[][]{{1, 2}, {3, 4}, {5, 6}});

        System.out.println("All test cases passed!");
    }
}
