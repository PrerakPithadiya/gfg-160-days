
import java.util.ArrayList;

/**
 * Solution class for spiral traversal of a matrix Time Complexity: O(m*n) where
 * m is number of rows and n is number of columns Space Complexity: O(m*n) to
 * store the result
 */
class Solution {

    /**
     * Returns a list of integers representing the spiral traversal of the input
     * matrix. The traversal starts from the top-left corner and moves in a
     * clockwise spiral pattern.
     *
     * Algorithm: 1. Initialize four boundaries: top, bottom, left, right 2.
     * While boundaries don't cross: a) Traverse from left to right (top row) b)
     * Traverse from top to bottom (right column) c) Traverse from right to left
     * (bottom row) d) Traverse from bottom to top (left column) 3. Update
     * boundaries after each traversal
     *
     * @param mat 2D integer array representing the input matrix
     * @return ArrayList<Integer> containing elements in spiral order
     */
    public ArrayList<Integer> spirallyTraverse(int mat[][]) {
        ArrayList<Integer> result = new ArrayList<>();
        if (mat == null || mat.length == 0) {
            return result;
        }

        int n = mat.length;     // Number of rows
        int m = mat[0].length;  // Number of columns

        // Define the boundaries
        int top = 0, bottom = n - 1, left = 0, right = m - 1;

        // Spiral traversal
        while (top <= bottom && left <= right) {
            // Traverse from left to right along the top row
            for (int i = left; i <= right; i++) {
                result.add(mat[top][i]);
            }
            top++; // Move the top boundary down

            // Traverse from top to bottom along the right column
            for (int i = top; i <= bottom; i++) {
                result.add(mat[i][right]);
            }
            right--; // Move the right boundary left

            // Traverse from right to left along the bottom row (if rows remain)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(mat[bottom][i]);
                }
                bottom--; // Move the bottom boundary up
            }

            // Traverse from bottom to top along the left column (if columns remain)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(mat[i][left]);
                }
                left++; // Move the left boundary right
            }
        }

        return result;
    }

    /**
     * Test cases to verify the functionality of spirallyTraverse method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Regular matrix
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Test Case 1 - 3x3 Matrix:");
        System.out.println("Expected: [1, 2, 3, 6, 9, 8, 7, 4, 5]");
        System.out.println("Actual: " + solution.spirallyTraverse(matrix1));

        // Test Case 2: Single row matrix
        int[][] matrix2 = {{1, 2, 3, 4}};
        System.out.println("\nTest Case 2 - Single Row Matrix:");
        System.out.println("Expected: [1, 2, 3, 4]");
        System.out.println("Actual: " + solution.spirallyTraverse(matrix2));

        // Test Case 3: Single column matrix
        int[][] matrix3 = {{1}, {2}, {3}};
        System.out.println("\nTest Case 3 - Single Column Matrix:");
        System.out.println("Expected: [1, 2, 3]");
        System.out.println("Actual: " + solution.spirallyTraverse(matrix3));

        // Test Case 4: Empty matrix
        int[][] matrix4 = {};
        System.out.println("\nTest Case 4 - Empty Matrix:");
        System.out.println("Expected: []");
        System.out.println("Actual: " + solution.spirallyTraverse(matrix4));

        // Test Case 5: Rectangular matrix
        int[][] matrix5 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        System.out.println("\nTest Case 5 - 3x4 Rectangular Matrix:");
        System.out.println("Expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]");
        System.out.println("Actual: " + solution.spirallyTraverse(matrix5));
    }
}
