
/**
 * Solution class for solving Sudoku puzzles using backtracking algorithm.
 * This implementation solves a 9x9 Sudoku grid following standard Sudoku rules.
 */
class Solution {

    /**
     * Main entry point to solve the Sudoku puzzle.
     *
     * @param mat The 9x9 Sudoku grid to be solved, where 0 represents empty
     * cells
     * @return boolean indicating whether the Sudoku was successfully solved
     */
    static boolean solveSudoku(int[][] mat) {
        return solve(mat);
    }

    /**
     * Recursive function that implements the backtracking algorithm to solve
     * the Sudoku.
     *
     * @param mat The Sudoku grid being solved
     * @return boolean indicating whether the current configuration leads to a
     * solution
     */
    private static boolean solve(int[][] mat) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Find an empty cell
                if (mat[row][col] == 0) {
                    // Try digits from 1 to 9
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(mat, row, col, num)) {
                            mat[row][col] = num; // Place the number

                            // Recursively try to fill the next empty cell
                            if (solve(mat)) {
                                return true; // If successful, return true
                            }

                            // If not successful, reset the cell (backtrack)
                            mat[row][col] = 0;
                        }
                    }
                    return false; // If no number can be placed, return false
                }
            }
        }
        return true; // If all cells are filled successfully
    }

    /**
     * Checks if it's valid to place a number in a specific cell.
     *
     * @param mat The Sudoku grid
     * @param row The row index to check
     * @param col The column index to check
     * @param num The number to be placed
     * @return boolean indicating whether it's safe to place the number
     */
    private static boolean isSafe(int[][] mat, int row, int col, int num) {
        // Check the row
        for (int x = 0; x < 9; x++) {
            if (mat[row][x] == num) {
                return false;
            }
        }

        // Check the column
        for (int x = 0; x < 9; x++) {
            if (mat[x][col] == num) {
                return false;
            }
        }

        // Check the 3x3 sub-box
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mat[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true; // If all checks pass, it's safe to place the number
    }

    /**
     * Main method with test cases to demonstrate the Sudoku solver
     */
    public static void main(String[] args) {
        // Test Case 1: Solvable Sudoku
        int[][] sudoku1 = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Test Case 1 - Solvable Sudoku:");
        System.out.println("Before solving:");
        printSudoku(sudoku1);

        if (solveSudoku(sudoku1)) {
            System.out.println("\nAfter solving:");
            printSudoku(sudoku1);
        } else {
            System.out.println("No solution exists");
        }

        // Test Case 2: Unsolvable Sudoku
        int[][] sudoku2 = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {5, 0, 0, 1, 9, 5, 0, 0, 0}, // Note the duplicate 5 in first column
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("\nTest Case 2 - Unsolvable Sudoku:");
        System.out.println("Before solving:");
        printSudoku(sudoku2);

        if (solveSudoku(sudoku2)) {
            System.out.println("\nAfter solving:");
            printSudoku(sudoku2);
        } else {
            System.out.println("No solution exists");
        }
    }

    /**
     * Helper method to print the Sudoku grid
     *
     * @param mat The Sudoku grid to be printed
     */
    private static void printSudoku(int[][] mat) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("- - - - - - - - - - - -");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
