
class Node {

    int data;
    Node left;
    Node right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

/**
 * Solution class containing method to find height of a binary tree
 */
class Solution {

    /**
     * Calculates the height (or depth) of a binary tree. The height is defined
     * as the number of edges in the longest path from root to leaf.
     *
     * Time Complexity: O(n) where n is the number of nodes in the tree Space
     * Complexity: O(h) where h is the height of the tree due to recursion stack
     *
     * @param node Root node of the binary tree
     * @return Height of the binary tree, -1 if tree is empty
     */
    public int height(Node node) {
        // Base case: if the node is null, return -1 (no edges)
        if (node == null) {
            return -1;
        }

        // Recursively calculate the height of the left and right subtrees
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        // Return the maximum height of the left or right subtree, plus 1 for the current edge
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Test cases to verify the height calculation
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Empty tree
        Node emptyTree = null;
        assert solution.height(emptyTree) == -1 : "Empty tree should have height -1";

        // Test Case 2: Single node tree
        Node singleNode = new Node(1);
        assert solution.height(singleNode) == 0 : "Single node tree should have height 0";

        // Test Case 3: Complete binary tree of height 2
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        assert solution.height(root) == 2 : "Complete binary tree should have height 2";

        // Test Case 4: Skewed tree (left)
        Node skewedRoot = new Node(1);
        skewedRoot.left = new Node(2);
        skewedRoot.left.left = new Node(3);
        assert solution.height(skewedRoot) == 2 : "Left skewed tree should have height 2";

        System.out.println("All test cases passed successfully!");
    }
}
