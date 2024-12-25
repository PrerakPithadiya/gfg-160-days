
/**
 * Solution class for setting zeroes in a matrix
 * Time Complexity: O(M * N) where M is number of rows and N is number of columns
 * Space Complexity: O(1) as we use the first row and column as markers
 */
class Solution {

    /**
     * Sets entire row and column to zero if an element is zero Uses first row
     * and column as markers to optimize space usage
     *
     * Algorithm: 1. Store states of first row/column separately 2. Use first
     * row/column as markers for other cells 3. Process the matrix based on
     * markers 4. Handle first row/column separately
     *
     * @param mat Input matrix to be modified
     */
    public void setMatrixZeroes(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        boolean firstRow = false, firstCol = false;

        // Check if first row/col need to be zeroed
        for (int j = 0; j < cols; j++) {
            if (mat[0][j] == 0) {
                firstRow = true;
            }
        }
        for (int i = 0; i < rows; i++) {
            if (mat[i][0] == 0) {
                firstCol = true;
            }
        }

        // Use first row/col as markers
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (mat[i][j] == 0) {
                    mat[i][0] = 0;
                    mat[0][j] = 0;
                }
            }
        }

        // Zero rows and columns based on markers
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (mat[i][0] == 0 || mat[0][j] == 0) {
                    mat[i][j] = 0;
                }
            }
        }

        // Zero first row/col if needed
        if (firstRow) {
            for (int j = 0; j < cols; j++) {
                mat[0][j] = 0;
            }
        }
        if (firstCol) {
            for (int i = 0; i < rows; i++) {
                mat[i][0] = 0;
            }
        }
    }

    /**
     * Test cases to verify the functionality
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic matrix with single zero
        int[][] matrix1 = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        solution.setMatrixZeroes(matrix1);
        assert matrix1[0][1] == 0 && matrix1[1][0] == 0 && matrix1[1][2] == 0 && matrix1[2][1] == 0;

        // Test Case 2: Matrix with multiple zeros
        int[][] matrix2 = {
            {0, 1, 2, 0},
            {3, 4, 5, 2},
            {1, 3, 1, 5}
        };
        solution.setMatrixZeroes(matrix2);
        assert matrix2[0][0] == 0 && matrix2[0][3] == 0 && matrix2[1][0] == 0 && matrix2[1][3] == 0;

        // Test Case 3: Matrix with first row/column zeros
        int[][] matrix3 = {
            {1, 1, 1},
            {0, 1, 2},
            {1, 2, 0}
        };
        solution.setMatrixZeroes(matrix3);
        assert matrix3[0][2] == 0 && matrix3[1][0] == 0 && matrix3[1][2] == 0 && matrix3[2][0] == 0;

        System.out.println("All test cases passed!");
    }
}
