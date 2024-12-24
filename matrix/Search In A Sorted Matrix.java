
/**
 * Solution class for searching a target element in a row-column sorted matrix.
 * The matrix is sorted in ascending order both row-wise and column-wise.
 */
class Solution {

    /**
     * Searches for a given number in a row-column sorted matrix using binary
     * search. The matrix is treated as a flattened sorted array for binary
     * search implementation.
     *
     * Time Complexity: O(log(n*m)) where n and m are dimensions of matrix Space
     * Complexity: O(1) as only constant extra space is used
     *
     * @param mat The input matrix where elements are sorted in ascending order
     * @param x The target element to search for
     * @return true if element is found, false otherwise
     */
    public boolean searchMatrix(int[][] mat, int x) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return false;
        }

        int n = mat.length;
        int m = mat[0].length;

        // Perform binary search on the matrix treating it as a sorted array
        int left = 0;
        int right = n * m - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Convert mid index to matrix coordinates
            int row = mid / m;
            int col = mid % m;

            if (mat[row][col] == x) {
                return true;
            }

            if (mat[row][col] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    /**
     * Test cases to verify the functionality of searchMatrix method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Normal case with element present
        int[][] matrix1 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        assert solution.searchMatrix(matrix1, 3) == true;

        // Test Case 2: Element not present
        assert solution.searchMatrix(matrix1, 13) == false;

        // Test Case 3: Empty matrix
        int[][] matrix2 = {};
        assert solution.searchMatrix(matrix2, 1) == false;

        // Test Case 4: Null matrix
        assert solution.searchMatrix(null, 1) == false;

        // Test Case 5: Single element matrix
        int[][] matrix3 = {{1}};
        assert solution.searchMatrix(matrix3, 1) == true;

        // Test Case 6: Search for minimum element
        assert solution.searchMatrix(matrix1, 1) == true;

        // Test Case 7: Search for maximum element
        assert solution.searchMatrix(matrix1, 60) == true;

        System.out.println("All test cases passed successfully!");
    }
}
