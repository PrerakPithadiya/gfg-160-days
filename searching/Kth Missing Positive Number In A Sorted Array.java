
/**
 * Solution class to find the kth missing positive number in a sorted array.
 * Time Complexity: O(log n) where n is the length of the input array
 * Space Complexity: O(1) as we only use constant extra space
 */
class Solution {

    /**
     * Finds the kth missing positive integer in a sorted array using binary
     * search.
     *
     * @param arr The input sorted array of positive integers
     * @param k The position of the missing number we want to find
     * @return The kth missing positive integer
     *
     * Example 1: Input: arr = [2,3,4,7,11], k = 5 Output: 9 Explanation: The
     * missing positive integers are [1,5,6,8,9,10,...] The 5th missing positive
     * integer is 9.
     *
     * Example 2: Input: arr = [1,2,3,4], k = 2 Output: 6 Explanation: The
     * missing positive integers are [5,6,7,...] The 2nd missing positive
     * integer is 6.
     *
     * Algorithm: 1. Use binary search to find the position where the count of
     * missing numbers becomes greater than or equal to k 2. For each middle
     * position, calculate missing numbers before it using formula: missing =
     * arr[mid] - (mid + 1) 3. If missing < k, search in right half, else search
     * in left half 4. Final answer will be k + left, where left is the position
     * after binary search
     */
    public int kthMissing(int[] arr, int k) {
        int left = 0, right = arr.length - 1;

        // Perform binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Calculate the number of missing elements before arr[mid]
            int missing = arr[mid] - (mid + 1);

            if (missing < k) {
                left = mid + 1; // Move to the right half
            } else {
                right = mid - 1; // Move to the left half
            }
        }

        // After binary search, 'left' points to the index where missing >= k
        // The kth missing number is in the gap created by 'left'
        return k + left;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1
        int[] arr1 = {2, 3, 4, 7, 11};
        assert solution.kthMissing(arr1, 5) == 9 : "Test Case 1 Failed";

        // Test Case 2
        int[] arr2 = {1, 2, 3, 4};
        assert solution.kthMissing(arr2, 2) == 6 : "Test Case 2 Failed";

        // Test Case 3: Empty array
        int[] arr3 = {};
        assert solution.kthMissing(arr3, 1) == 1 : "Test Case 3 Failed";

        // Test Case 4: Single element array
        int[] arr4 = {5};
        assert solution.kthMissing(arr4, 2) == 2 : "Test Case 4 Failed";

        // Test Case 5: Large missing gap
        int[] arr5 = {1, 100};
        assert solution.kthMissing(arr5, 50) == 51 : "Test Case 5 Failed";

        System.out.println("All test cases passed!");
    }
}
