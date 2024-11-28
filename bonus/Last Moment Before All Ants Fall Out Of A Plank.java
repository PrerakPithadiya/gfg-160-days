
/**
 * Solution for finding the last moment before all ants fall off a plank
 *
 * Problem:
 * We have a plank of length n units with ants moving at the same speed.
 * Some ants move to the left, others to the right.
 * When two ants meet, they change directions and continue moving.
 * Find the last moment when any ant is still on the plank.
 *
 * Time Complexity: O(max(left.length, right.length))
 * Space Complexity: O(1)
 */
class Solution {

    /**
     * Calculates the last moment before all ants fall off the plank
     *
     * @param n Length of the plank (0 to n)
     * @param left Array of positions of ants moving left
     * @param right Array of positions of ants moving right
     * @return The last moment when any ant is still on the plank
     */
    public int getLastMoment(int n, int left[], int right[]) {
        int maxTime = 0;

        // Calculate the time for ants moving to the left
        for (int position : left) {
            maxTime = Math.max(maxTime, position); // Time to fall off the left edge
        }

        // Calculate the time for ants moving to the right
        for (int position : right) {
            maxTime = Math.max(maxTime, n - position); // Time to fall off the right edge
        }

        return maxTime; // The moment when the last ant falls off
    }

    // Test cases
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case
        assert solution.getLastMoment(4, new int[]{4, 3}, new int[]{0, 1}) == 4;

        // Test Case 2: All ants moving left
        assert solution.getLastMoment(7, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, new int[]{}) == 7;

        // Test Case 3: All ants moving right
        assert solution.getLastMoment(7, new int[]{}, new int[]{0, 1, 2, 3, 4, 5, 6, 7}) == 7;

        // Test Case 4: Empty arrays
        assert solution.getLastMoment(9, new int[]{}, new int[]{}) == 0;

        // Test Case 5: Single ant cases
        assert solution.getLastMoment(5, new int[]{5}, new int[]{}) == 5;
        assert solution.getLastMoment(5, new int[]{}, new int[]{0}) == 5;

        System.out.println("All test cases passed!");
    }
}
