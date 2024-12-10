
import java.util.Arrays;

/**
 * This class provides a solution for finding the minimum number of intervals
 * that need to be removed to make all remaining intervals non-overlapping.
 *
 * Problem Description: Given an array of intervals where intervals[i] =
 * [starti, endi], return the minimum number of intervals you need to remove to
 * make the rest of the intervals non-overlapping.
 *
 * Approach: 1. Sort intervals by end time to optimize the selection of
 * non-overlapping intervals 2. Iterate through sorted intervals, keeping track
 * of the last included interval 3. Count non-overlapping intervals that can be
 * kept 4. Return the difference between total intervals and non-overlapping
 * count
 *
 * Time Complexity: O(n log n) due to sorting Space Complexity: O(1) as we only
 * use constant extra space
 */
class Solution {

    static int minRemoval(int[][] intervals) {
        // Sort intervals by their end times
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0; // Number of non-overlapping intervals
        int lastEnd = Integer.MIN_VALUE; // End of the last included interval

        for (int[] interval : intervals) {
            if (interval[0] >= lastEnd) {
                // If the current interval does not overlap with the last included interval
                count++;
                lastEnd = interval[1];
            }
        }

        // The number of intervals to remove is the total intervals minus the non-overlapping count
        return intervals.length - count;
    }

    // Test cases
    public static void main(String[] args) {
        // Test Case 1: Basic overlapping intervals
        int[][] test1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        assert minRemoval(test1) == 1 : "Test Case 1 Failed";

        // Test Case 2: All overlapping intervals
        int[][] test2 = {{1, 4}, {1, 4}, {1, 4}};
        assert minRemoval(test2) == 2 : "Test Case 2 Failed";

        // Test Case 3: No overlapping intervals
        int[][] test3 = {{1, 2}, {3, 4}, {5, 6}};
        assert minRemoval(test3) == 0 : "Test Case 3 Failed";

        // Test Case 4: Complex overlapping case
        int[][] test4 = {{1, 5}, {2, 3}, {3, 4}, {4, 6}, {5, 7}, {6, 8}};
        assert minRemoval(test4) == 3 : "Test Case 4 Failed";

        // Test Case 5: Empty array
        int[][] test5 = {};
        assert minRemoval(test5) == 0 : "Test Case 5 Failed";

        System.out.println("All test cases passed!");
    }
}
