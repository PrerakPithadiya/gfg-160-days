
import java.util.Arrays;

/**
 * Solution class for merging two sorted arrays without using extra space Time
 * Complexity: O((n+m) * log(n+m)) Space Complexity: O(1)
 */
class Solution {

    /**
     * Merges two sorted arrays without using extra space using the Gap
     * algorithm The Gap algorithm is a variation of Shell sort that works
     * efficiently for merging
     *
     * @param a First sorted array that will be modified
     * @param b Second sorted array that will be modified
     *
     * Algorithm steps: 1. Calculate initial gap as ceiling of (n+m)/2 2.
     * Compare and swap elements with gap distance 3. Reduce gap by formula: gap
     * = (gap == 1) ? 0 : (gap + 1) / 2 4. Repeat until gap becomes 0
     */
    public void mergeArrays(int a[], int b[]) {
        int n = a.length;
        int m = b.length;
        int gap = (n + m + 1) / 2; // Initial gap

        while (gap > 0) {
            int i = 0;
            int j = gap;

            // Compare elements within a[]
            while (j < n) {
                if (a[i] > a[j]) {
                    // Swap elements
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
                i++;
                j++;
            }

            // Compare elements across a[] and b[]
            j -= n; // Move j to start of b[] if it has crossed a[]
            while (i < n && j < m) {
                if (a[i] > b[j]) {
                    // Swap elements
                    int temp = a[i];
                    a[i] = b[j];
                    b[j] = temp;
                }
                i++;
                j++;
            }

            // Compare elements within b[]
            i = 0;
            while (j < m) {
                if (b[i] > b[j]) {
                    // Swap elements
                    int temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
                i++;
                j++;
            }

            // Reduce gap
            gap = (gap == 1) ? 0 : (gap + 1) / 2;
        }
    }

    /**
     * Test cases to verify the mergeArrays function
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic test with positive numbers
        int[] a1 = {1, 3, 5, 7};
        int[] b1 = {2, 4, 6, 8};
        solution.mergeArrays(a1, b1);
        assert Arrays.equals(a1, new int[]{1, 2, 3, 4}) : "Test case 1 failed for array a";
        assert Arrays.equals(b1, new int[]{5, 6, 7, 8}) : "Test case 1 failed for array b";

        // Test Case 2: Arrays with duplicate elements
        int[] a2 = {1, 2, 2, 3};
        int[] b2 = {2, 3, 3, 4};
        solution.mergeArrays(a2, b2);
        assert Arrays.equals(a2, new int[]{1, 2, 2, 2}) : "Test case 2 failed for array a";
        assert Arrays.equals(b2, new int[]{3, 3, 3, 4}) : "Test case 2 failed for array b";

        // Test Case 3: Arrays with negative numbers
        int[] a3 = {-5, -3, 1, 3};
        int[] b3 = {-4, -2, 0, 2};
        solution.mergeArrays(a3, b3);
        assert Arrays.equals(a3, new int[]{-5, -4, -3, -2}) : "Test case 3 failed for array a";
        assert Arrays.equals(b3, new int[]{0, 1, 2, 3}) : "Test case 3 failed for array b";

        // Test Case 4: Arrays of different sizes
        int[] a4 = {1, 3, 5};
        int[] b4 = {2, 4, 6, 8, 10};
        solution.mergeArrays(a4, b4);
        assert Arrays.equals(a4, new int[]{1, 2, 3}) : "Test case 4 failed for array a";
        assert Arrays.equals(b4, new int[]{4, 5, 6, 8, 10}) : "Test case 4 failed for array b";

        System.out.println("All test cases passed successfully!");
    }
}
