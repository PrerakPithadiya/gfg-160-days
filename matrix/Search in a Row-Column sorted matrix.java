
/**
 * Solution class for searching in a row-column sorted matrix.
 * A row-column sorted matrix has the following properties:
 * 1. All elements in each row are sorted in ascending order from left to right
 * 2. All elements in each column are sorted in ascending order from top to bottom
 */
class Solution {

    /**
     * Searches for a target element in a row-column sorted matrix. Uses an
     * efficient approach by starting from the top-right corner and eliminating
     * rows/columns based on comparisons.
     *
     * Time Complexity: O(n + m) where n is number of rows and m is number of
     * columns Space Complexity: O(1) as only constant extra space is used
     *
     * @param mat The input matrix where elements are sorted row-wise and
     * column-wise
     * @param x The target element to search for in the matrix
     * @return true if element is found, false otherwise
     */
    public static boolean matSearch(int mat[][], int x) {
        int n = mat.length; // Number of rows
        if (n == 0) {
            return false;
        }
        int m = mat[0].length; // Number of columns
        if (m == 0) {
            return false;
        }

        // Start from the top-right corner of the matrix
        int row = 0;
        int col = m - 1;

        // Traverse the matrix
        while (row < n && col >= 0) {
            if (mat[row][col] == x) {
                return true; // Element found
            } else if (mat[row][col] > x) {
                col--; // Move left in the row
            } else {
                row++; // Move down in the column
            }
        }

        return false; // Element not found
    }

    /**
     * Test cases to verify the functionality of matSearch method
     */
    public static void main(String[] args) {
        // Test Case 1: Basic test with element present
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        assert matSearch(matrix1, 5) == true : "Test Case 1 Failed";

        // Test Case 2: Element not present
        assert matSearch(matrix1, 10) == false : "Test Case 2 Failed";

        // Test Case 3: Empty matrix
        int[][] matrix2 = {};
        assert matSearch(matrix2, 1) == false : "Test Case 3 Failed";

        // Test Case 4: Matrix with empty rows
        int[][] matrix3 = {{}};
        assert matSearch(matrix3, 1) == false : "Test Case 4 Failed";

        // Test Case 5: Larger matrix with element present
        int[][] matrix4 = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {27, 29, 37, 48},
            {32, 33, 39, 50}
        };
        assert matSearch(matrix4, 29) == true : "Test Case 5 Failed";

        System.out.println("All test cases passed successfully!");
    }
}
