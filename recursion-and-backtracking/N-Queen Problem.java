
import java.util.ArrayList;

/**
 * Solution class for solving the N-Queen problem The N-Queen problem is to
 * place N queens on an NxN chessboard such that no two queens can attack each
 * other
 */
class Solution {

    /**
     * Main method to solve N-Queen problem
     *
     * @param n Size of the chess board (n x n) and number of queens to be
     * placed
     * @return List of all possible solutions where each solution represents
     * queen positions
     */
    public ArrayList<ArrayList<Integer>> nQueen(int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int[] board = new int[n];
        // Initialize board with -1
        for (int i = 0; i < n; i++) {
            board[i] = -1;
        }
        solveNQueen(result, board, 0, n);
        return result;
    }

    /**
     * Recursive helper method to solve N-Queen problem using backtracking
     *
     * @param result List to store all valid solutions
     * @param board Current state of the board where index represents row and
     * value represents column
     * @param row Current row being processed
     * @param n Size of the board
     */
    private void solveNQueen(ArrayList<ArrayList<Integer>> result, int[] board, int row, int n) {
        // Base case: If all queens are placed successfully
        if (row == n) {
            ArrayList<Integer> solution = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                solution.add(board[i] + 1);
            }
            result.add(solution);
            return;
        }

        // Try placing queen in each column of current row
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row] = col;
                solveNQueen(result, board, row + 1, n);
                board[row] = -1;  // backtrack
            }
        }
    }

    /**
     * Helper method to check if placing a queen at given position is safe
     *
     * @param board Current state of the board
     * @param row Row to check
     * @param col Column to check
     * @param n Size of the board
     * @return true if position is safe, false otherwise
     */
    private boolean isSafe(int[] board, int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            // Check if queen exists in same column or diagonals
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Test cases for the N-Queen problem
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: n = 4
        ArrayList<ArrayList<Integer>> result1 = solution.nQueen(4);
        System.out.println("Solutions for 4-Queen problem:");
        System.out.println(result1);
        // Expected output: [[2, 4, 1, 3], [3, 1, 4, 2]]

        // Test Case 2: n = 1
        ArrayList<ArrayList<Integer>> result2 = solution.nQueen(1);
        System.out.println("\nSolutions for 1-Queen problem:");
        System.out.println(result2);
        // Expected output: [[1]]

        // Test Case 3: n = 8
        ArrayList<ArrayList<Integer>> result3 = solution.nQueen(8);
        System.out.println("\nNumber of solutions for 8-Queen problem: " + result3.size());
        // Expected output: 92 solutions
    }
}
