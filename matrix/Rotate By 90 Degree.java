
/**
 * Solution class containing matrix rotation functionality
 */
class Solution {

    /**
     * Rotates a square matrix anticlockwise by 90 degrees in-place. The
     * rotation is performed in two steps: 1. Transpose the matrix (swap
     * elements across the main diagonal) 2. Reverse each column from top to
     * bottom
     *
     * Time Complexity: O(n^2) where n is the size of the matrix Space
     * Complexity: O(1) as rotation is done in-place
     *
     * @param mat The input square matrix to be rotated
     */
    static void rotateby90(int mat[][]) {
        int n = mat.length;

        // Step 1: Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Swap mat[i][j] with mat[j][i]
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        // Step 2: Reverse each column
        for (int j = 0; j < n; j++) {
            int top = 0, bottom = n - 1;
            while (top < bottom) {
                // Swap mat[top][j] with mat[bottom][j]
                int temp = mat[top][j];
                mat[top][j] = mat[bottom][j];
                mat[bottom][j] = temp;
                top++;
                bottom--;
            }
        }
    }

    /**
     * Main method to test the matrix rotation functionality
     */
    public static void main(String[] args) {
        // Test Case 1: 3x3 matrix
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Test Case 1 - Original Matrix:");
        printMatrix(matrix1);
        rotateby90(matrix1);
        System.out.println("After 90° Anticlockwise Rotation:");
        printMatrix(matrix1);

        // Test Case 2: 4x4 matrix
        int[][] matrix2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        System.out.println("\nTest Case 2 - Original Matrix:");
        printMatrix(matrix2);
        rotateby90(matrix2);
        System.out.println("After 90° Anticlockwise Rotation:");
        printMatrix(matrix2);

        // Test Case 3: 2x2 matrix
        int[][] matrix3 = {
            {1, 2},
            {3, 4}
        };
        System.out.println("\nTest Case 3 - Original Matrix:");
        printMatrix(matrix3);
        rotateby90(matrix3);
        System.out.println("After 90° Anticlockwise Rotation:");
        printMatrix(matrix3);
    }

    /**
     * Utility method to print a matrix
     *
     * @param matrix The matrix to be printed
     */
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
