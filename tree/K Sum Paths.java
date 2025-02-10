
import java.util.HashMap;

/**
 * Definition for binary tree node.
 */
class Node {

    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

/**
 * Solution class to find the number of paths in a binary tree that sum to a
 * given value k
 */
class Solution {

    /**
     * Counter to keep track of valid paths
     */
    private int count = 0;

    /**
     * Modulo constant to handle large numbers
     */
    private final int MOD = 1000000007;

    /**
     * Finds the total number of paths in the binary tree that sum to k
     *
     * @param root The root node of the binary tree
     * @param k The target sum to find
     * @return The count of paths that sum to k
     */
    public int sumK(Node root, int k) {
        // HashMap to store the prefix sum and its frequency
        HashMap<Long, Integer> prefixSum = new HashMap<>();
        // Initialize with 0 sum having frequency 1
        prefixSum.put(0L, 1);

        findPaths(root, k, 0L, prefixSum);
        return count;
    }

    /**
     * Recursive helper method to find paths with sum k
     *
     * @param node Current node in traversal
     * @param k Target sum to find
     * @param currentSum Running sum from root to current node
     * @param prefixSum HashMap storing prefix sums and their frequencies
     */
    private void findPaths(Node node, int k, long currentSum, HashMap<Long, Integer> prefixSum) {
        if (node == null) {
            return;
        }

        // Add current node's value to sum
        currentSum += node.data;

        // Check if we have any prefix sum that gives us k
        // currentSum - x = k, where x is the prefix sum we're looking for
        // Therefore, x = currentSum - k
        count = (count + prefixSum.getOrDefault(currentSum - k, 0)) % MOD;

        // Add current sum to prefix sums
        prefixSum.put(currentSum, prefixSum.getOrDefault(currentSum, 0) + 1);

        // Recurse for left and right children
        findPaths(node.left, k, currentSum, prefixSum);
        findPaths(node.right, k, currentSum, prefixSum);

        // Remove current sum from prefix sums when backtracking
        prefixSum.put(currentSum, prefixSum.get(currentSum) - 1);
        if (prefixSum.get(currentSum) == 0) {
            prefixSum.remove(currentSum);
        }
    }

    /**
     * Test cases for the K Sum Paths solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Simple tree with one path
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        assert solution.sumK(root1, 3) == 2 : "Test Case 1 Failed";

        // Test Case 2: Tree with multiple paths
        Node root2 = new Node(1);
        root2.left = new Node(3);
        root2.left.left = new Node(2);
        root2.left.right = new Node(1);
        root2.right = new Node(2);
        root2.right.right = new Node(1);
        assert solution.sumK(root2, 5) == 3 : "Test Case 2 Failed";

        // Test Case 3: Empty tree
        assert solution.sumK(null, 5) == 0 : "Test Case 3 Failed";

        // Test Case 4: Single node tree
        Node root4 = new Node(5);
        assert solution.sumK(root4, 5) == 1 : "Test Case 4 Failed";

        System.out.println("All test cases passed!");
    }
}
