
/**
 * Solution class for searching in a row-wise sorted matrix.
 * A row-wise sorted matrix is a 2D array where each row is sorted in ascending order.
 */
class Solution {

    /**
     * Searches for a given number in a row-column sorted matrix. The algorithm
     * uses a combination of range checking and binary search for efficient
     * searching.
     *
     * Time Complexity: O(n * log m), where n is number of rows and m is number
     * of columns Space Complexity: O(1), as only constant extra space is used
     *
     * @param mat The input matrix where each row is sorted in ascending order
     * @param x The target number to search for in the matrix
     * @return true if the number is found, false otherwise
     */
    public boolean searchRowMatrix(int[][] mat, int x) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return false;
        }

        int n = mat.length;
        int m = mat[0].length;

        // Iterate through each row
        for (int i = 0; i < n; i++) {
            // Check if x is within the range of current row
            if (x >= mat[i][0] && x <= mat[i][m - 1]) {
                // Perform binary search on current row
                int left = 0;
                int right = m - 1;

                while (left <= right) {
                    int mid = left + (right - left) / 2;

                    if (mat[i][mid] == x) {
                        return true;
                    }

                    if (mat[i][mid] < x) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Test cases to verify the functionality of searchRowMatrix method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic sorted matrix
        int[][] matrix1 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 50}
        };
        assert solution.searchRowMatrix(matrix1, 3) == true;    // Should return true
        assert solution.searchRowMatrix(matrix1, 13) == false;  // Should return false

        // Test Case 2: Single row matrix
        int[][] matrix2 = {{1, 3, 5, 7}};
        assert solution.searchRowMatrix(matrix2, 5) == true;    // Should return true

        // Test Case 3: Single column matrix
        int[][] matrix3 = {{1}, {3}, {5}};
        assert solution.searchRowMatrix(matrix3, 3) == true;    // Should return true

        // Test Case 4: Empty matrix
        int[][] matrix4 = {};
        assert solution.searchRowMatrix(matrix4, 1) == false;   // Should return false

        // Test Case 5: Matrix with duplicate values
        int[][] matrix5 = {
            {1, 2, 2, 3},
            {4, 5, 5, 6},
            {7, 8, 8, 9}
        };
        assert solution.searchRowMatrix(matrix5, 5) == true;    // Should return true

        System.out.println("All test cases passed successfully!");
    }
}
