
/**
 * Solution class for the Word Search problem.
 * This class implements an algorithm to find if a given word exists in a 2D character grid.
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells
 * are horizontally or vertically neighboring.
 */
class Solution {

    // Directions for moving to adjacent cells (up, down, left, right)
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * Checks if a given word exists in the character matrix.
     *
     * @param mat The input character matrix
     * @param word The word to search for
     * @return true if the word exists in the matrix, false otherwise
     * @throws IllegalArgumentException if matrix is null or empty, or if word
     * is null
     */
    static public boolean isWordExist(char[][] mat, String word) {
        // Input validation
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be null or empty");
        }
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }

        int n = mat.length;
        int m = mat[0].length;

        // Early termination: If the word is longer than the total number of cells, return false
        if (word.length() > n * m) {
            return false;
        }

        // Iterate through each cell in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Start DFS from the current cell if it matches the first character of the word
                if (mat[i][j] == word.charAt(0) && dfs(mat, word, i, j, 0)) {
                    return true;
                }
            }
        }

        // If no path matches the word, return false
        return false;
    }

    /**
     * Performs depth-first search to find the word in the matrix starting from
     * position (i,j).
     *
     * @param mat The input character matrix
     * @param word The word to search for
     * @param i Current row position
     * @param j Current column position
     * @param index Current index in the word being matched
     * @return true if word is found starting from current position, false
     * otherwise
     */
    private static boolean dfs(char[][] mat, String word, int i, int j, int index) {
        // If the entire word is matched, return true
        if (index == word.length()) {
            return true;
        }

        // Check if the current cell is out of bounds or doesn't match the current character
        if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length || mat[i][j] != word.charAt(index)) {
            return false;
        }

        // Temporarily mark the current cell as visited by changing its value
        char temp = mat[i][j];
        mat[i][j] = '#'; // Mark as visited

        // Explore all 4 directions
        for (int[] dir : DIRECTIONS) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (dfs(mat, word, newI, newJ, index + 1)) {
                mat[i][j] = temp; // Restore the cell before returning true
                return true;
            }
        }

        // Backtrack: Restore the original value of the cell
        mat[i][j] = temp;

        // If no path matches, return false
        return false;
    }

    /**
     * Test cases to verify the functionality of the Word Search algorithm.
     */
    public static void main(String[] args) {
        // Test Case 1: Basic case
        char[][] grid1 = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        assert isWordExist(grid1, "ABCCED") == true;
        assert isWordExist(grid1, "SEE") == true;
        assert isWordExist(grid1, "ABCB") == false;

        // Test Case 2: Single character grid
        char[][] grid2 = {{'A'}};
        assert isWordExist(grid2, "A") == true;
        assert isWordExist(grid2, "B") == false;

        // Test Case 3: Word longer than grid size
        char[][] grid3 = {
            {'A', 'B'},
            {'C', 'D'}
        };
        assert isWordExist(grid3, "ABCDEF") == false;

        // Test Case 4: Empty word
        assert isWordExist(grid3, "") == true;

        // Test Case 5: Snake-like path
        char[][] grid4 = {
            {'A', 'B', 'C'},
            {'F', 'E', 'D'},
            {'G', 'H', 'I'}
        };
        assert isWordExist(grid4, "ABCDEFGHI") == true;

        System.out.println("All test cases passed!");
    }
}
