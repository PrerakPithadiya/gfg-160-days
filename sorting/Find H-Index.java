
/**
 * Solution to find the H-index of a researcher based on their citation counts.
 * H-index is the largest value h such that the researcher has at least h papers
 * with at least h citations each.
 */
class Solution {

    /**
     * Calculates the H-index using bucket sort approach. Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param citations array containing number of citations for each paper
     * @return the H-index value
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n + 1];

        // Step 1: Populate the buckets
        for (int citation : citations) {
            if (citation >= n) {
                buckets[n]++;
            } else {
                buckets[citation]++;
            }
        }

        // Step 2: Compute the H-index
        int total = 0;
        for (int i = n; i >= 0; i--) {
            total += buckets[i];
            if (total >= i) {
                return i;
            }
        }

        return 0; // In case no valid H-index is found
    }

    /**
     * Main method to test the solution with various test cases.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Regular case
        int[] citations1 = {3, 0, 6, 1, 5};
        assert solution.hIndex(citations1) == 3 : "Test Case 1 Failed";

        // Test Case 2: All zeros
        int[] citations2 = {0, 0, 0, 0};
        assert solution.hIndex(citations2) == 0 : "Test Case 2 Failed";

        // Test Case 3: All same values
        int[] citations3 = {5, 5, 5, 5, 5};
        assert solution.hIndex(citations3) == 5 : "Test Case 3 Failed";

        // Test Case 4: Empty array
        int[] citations4 = {};
        assert solution.hIndex(citations4) == 0 : "Test Case 4 Failed";

        // Test Case 5: Single element
        int[] citations5 = {100};
        assert solution.hIndex(citations5) == 1 : "Test Case 5 Failed";

        System.out.println("All test cases passed!");
    }
}
