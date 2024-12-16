
/**
 * Solution class to find the kth element in two sorted arrays.
 * This implementation uses a modified binary search approach with O(log(min(n1,n2))) time complexity.
 */
class Solution {

    /**
     * Finds the kth element in two sorted arrays.
     *
     * The algorithm works by: 1. Ensuring binary search is performed on the
     * smaller array for efficiency 2. Using binary search to find the correct
     * partition points in both arrays 3. Comparing boundary elements to verify
     * the partition is valid
     *
     * Time Complexity: O(log(min(n1,n2))) where n1 and n2 are lengths of input
     * arrays Space Complexity: O(1) as only constant extra space is used
     *
     * @param a First sorted array
     * @param b Second sorted array
     * @param k Position of the element to find (1-based)
     * @return The kth element in the merged sorted array
     * @throws IllegalArgumentException if input arrays or k value is invalid
     */
    public int kthElement(int[] a, int[] b, int k) {
        // Ensure that we always perform binary search on the smaller array
        if (a.length > b.length) {
            return kthElement(b, a, k);
        }

        int n1 = a.length;
        int n2 = b.length;

        // Define binary search range
        int low = Math.max(0, k - n2); // Minimum elements to take from a
        int high = Math.min(k, n1);   // Maximum elements to take from a

        while (low <= high) {
            int cut_a = (low + high) / 2; // Partition size in array a
            int cut_b = k - cut_a;        // Partition size in array b

            // Calculate boundary elements
            int left_a = (cut_a == 0) ? Integer.MIN_VALUE : a[cut_a - 1];
            int right_a = (cut_a == n1) ? Integer.MAX_VALUE : a[cut_a];
            int left_b = (cut_b == 0) ? Integer.MIN_VALUE : b[cut_b - 1];
            int right_b = (cut_b == n2) ? Integer.MAX_VALUE : b[cut_b];

            // Check if the partition is valid
            if (left_a <= right_b && left_b <= right_a) {
                // The maximum element in the left half is the k-th element
                return Math.max(left_a, left_b);
            }

            // Adjust the binary search range
            if (left_a > right_b) {
                // Move the partition in a[] to the left
                high = cut_a - 1;
            } else {
                // Move the partition in a[] to the right
                low = cut_a + 1;
            }
        }

        // This should never happen if the input is valid
        throw new IllegalArgumentException("Invalid input");
    }

    /**
     * Test cases to verify the functionality of kthElement method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Regular case
        int[] a1 = {2, 3, 6, 7, 9};
        int[] b1 = {1, 4, 8, 10};
        assert solution.kthElement(a1, b1, 5) == 6 : "Test Case 1 Failed";

        // Test Case 2: Arrays of equal length
        int[] a2 = {1, 3, 5};
        int[] b2 = {2, 4, 6};
        assert solution.kthElement(a2, b2, 4) == 4 : "Test Case 2 Failed";

        // Test Case 3: One empty array
        int[] a3 = {};
        int[] b3 = {1, 2, 3, 4};
        assert solution.kthElement(a3, b3, 2) == 2 : "Test Case 3 Failed";

        // Test Case 4: K is 1 (minimum element)
        int[] a4 = {1, 3};
        int[] b4 = {2, 4};
        assert solution.kthElement(a4, b4, 1) == 1 : "Test Case 4 Failed";

        // Test Case 5: K is maximum (last element)
        int[] a5 = {1, 2, 3};
        int[] b5 = {4, 5};
        assert solution.kthElement(a5, b5, 5) == 5 : "Test Case 5 Failed";

        System.out.println("All test cases passed!");
    }
}
