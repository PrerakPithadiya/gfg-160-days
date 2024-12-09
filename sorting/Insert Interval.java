
import java.util.ArrayList;

/**
 * Solution class containing methods for interval manipulation
 */
class Solution {

    /**
     * Inserts a new interval into a sorted list of non-overlapping intervals
     * and merges overlapping intervals
     *
     * @param intervals Array of intervals where each interval is represented as
     * int[2] with start and end times
     * @param newInterval New interval to be inserted, represented as int[2]
     * with start and end times
     * @return ArrayList containing merged intervals after insertion
     *
     * Time Complexity: O(n) where n is the number of intervals Space
     * Complexity: O(n) to store the result
     */
    static ArrayList<int[]> insertInterval(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Add all intervals that end before the new interval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval); // Add the merged interval

        // Add the remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result;
    }

    /**
     * Main method containing test cases for insertInterval method
     */
    public static void main(String[] args) {
        // Test Case 1: Insert interval in the middle with merging
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        ArrayList<int[]> result1 = insertInterval(intervals1, newInterval1);
        System.out.println("Test Case 1:");
        printIntervals(result1); // Expected: [[1,5], [6,9]]

        // Test Case 2: Insert interval that overlaps multiple existing intervals
        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        ArrayList<int[]> result2 = insertInterval(intervals2, newInterval2);
        System.out.println("Test Case 2:");
        printIntervals(result2); // Expected: [[1,2], [3,10], [12,16]]

        // Test Case 3: Insert interval at the beginning
        int[][] intervals3 = {{3, 5}, {6, 9}};
        int[] newInterval3 = {1, 2};
        ArrayList<int[]> result3 = insertInterval(intervals3, newInterval3);
        System.out.println("Test Case 3:");
        printIntervals(result3); // Expected: [[1,2], [3,5], [6,9]]

        // Test Case 4: Insert interval at the end
        int[][] intervals4 = {{1, 2}, {3, 5}};
        int[] newInterval4 = {6, 7};
        ArrayList<int[]> result4 = insertInterval(intervals4, newInterval4);
        System.out.println("Test Case 4:");
        printIntervals(result4); // Expected: [[1,2], [3,5], [6,7]]

        // Test Case 5: Empty intervals array
        int[][] intervals5 = {};
        int[] newInterval5 = {1, 2};
        ArrayList<int[]> result5 = insertInterval(intervals5, newInterval5);
        System.out.println("Test Case 5:");
        printIntervals(result5); // Expected: [[1,2]]
    }

    /**
     * Helper method to print intervals for testing
     *
     * @param intervals ArrayList of intervals to print
     */
    private static void printIntervals(ArrayList<int[]> intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.size(); i++) {
            System.out.print("[" + intervals.get(i)[0] + "," + intervals.get(i)[1] + "]");
            if (i < intervals.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
