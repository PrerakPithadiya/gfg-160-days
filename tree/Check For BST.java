    // Node class definition for Binary Tree

class Node {

    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

/**
 * Solution class containing methods to check if a Binary Tree is a Binary
 * Search Tree (BST)
 */
class Solution {

    /**
     * Main function to check whether a Binary Tree is BST or not. A Binary
     * Search Tree (BST) has the following properties: 1. The left subtree of a
     * node contains only nodes with values less than the node's value 2. The
     * right subtree of a node contains only nodes with values greater than the
     * node's value 3. Both the left and right subtrees must also be binary
     * search trees
     *
     * @param root The root node of the binary tree
     * @return true if the tree is a BST, false otherwise
     */
    boolean isBST(Node root) {
        return isBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Utility function that checks if the binary tree is BST using range
     * validation Each node's value must be within a valid range determined by
     * its position in the tree
     *
     * @param node The current node being checked
     * @param min The minimum value allowed for the current node
     * @param max The maximum value allowed for the current node
     * @return true if the subtree rooted at node is a BST, false otherwise
     */
    private boolean isBSTUtil(Node node, long min, long max) {
        // Base case: empty tree is a BST
        if (node == null) {
            return true;
        }

        // Check if current node's value is within valid range
        if (node.data <= min || node.data >= max) {
            return false;
        }

        // Recursively check left and right subtrees
        // Left subtree values must be in range (min, node.data)
        // Right subtree values must be in range (node.data, max)
        return isBSTUtil(node.left, min, node.data)
                && isBSTUtil(node.right, node.data, max);
    }

    /**
     * Test cases to verify the BST checking functionality
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Valid BST
        Node root1 = new Node(4);
        root1.left = new Node(2);
        root1.right = new Node(6);
        root1.left.left = new Node(1);
        root1.left.right = new Node(3);
        System.out.println("Test Case 1 (Valid BST): " + solution.isBST(root1));  // Expected: true

        // Test Case 2: Invalid BST (violates left subtree property)
        Node root2 = new Node(4);
        root2.left = new Node(5);  // violates BST property
        root2.right = new Node(6);
        System.out.println("Test Case 2 (Invalid BST): " + solution.isBST(root2));  // Expected: false

        // Test Case 3: Single node
        Node root3 = new Node(1);
        System.out.println("Test Case 3 (Single node): " + solution.isBST(root3));  // Expected: true

        // Test Case 4: Empty tree
        System.out.println("Test Case 4 (Empty tree): " + solution.isBST(null));  // Expected: true

        // Test Case 5: Complex valid BST
        Node root5 = new Node(8);
        root5.left = new Node(3);
        root5.right = new Node(10);
        root5.left.left = new Node(1);
        root5.left.right = new Node(6);
        root5.left.right.left = new Node(4);
        root5.left.right.right = new Node(7);
        System.out.println("Test Case 5 (Complex valid BST): " + solution.isBST(root5));  // Expected: true
    }

}
