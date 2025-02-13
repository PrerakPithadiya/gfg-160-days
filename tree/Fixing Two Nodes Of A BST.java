
/**
 * This class provides a solution to fix a BST in which two nodes have been swapped.
 * It includes comprehensive documentation and test cases.
 */
class Solution {

    private Node first = null;   // First node that violates the BST property
    private Node second = null;  // Second node that violates the BST property
    private Node prev = null;    // Previously visited node in inorder traversal

    /**
     * Fixes the given BST by finding the two swapped nodes and swapping their
     * values.
     *
     * @param root the root of the BST
     */
    public void correctBST(Node root) {
        // Reset state before each operation
        first = null;
        second = null;
        prev = null;
        // Perform inorder traversal to find violations
        findViolations(root);
        // Swap values if violations were found
        if (first != null && second != null) {
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
        }
    }

    /**
     * Performs an inorder traversal to detect nodes that violate the BST
     * properties.
     *
     * @param root the current node in the traversal
     */
    private void findViolations(Node root) {
        if (root == null) {
            return;
        }
        findViolations(root.left);
        if (prev != null && root.data < prev.data) {
            if (first == null) {
                first = prev;
                second = root;
            } else {
                second = root;
            }
        }
        prev = root;
        findViolations(root.right);
    }

    /**
     * Auxiliary method to perform an inorder traversal and print the node
     * values.
     *
     * @param root the root of the BST
     */
    private void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.data + " ");
        inorderTraversal(root.right);
    }

    /**
     * Node class representing an element in the BST.
     */
    public static class Node {

        int data;
        Node left, right;

        /**
         * Constructs a Node with the specified data.
         *
         * @param data the integer value of the node
         */
        public Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    /**
     * Main method contains test cases to validate the correction of the BST.
     *
     * Test case: Construct a BST that in correct order should be: 1, 2, 3, 4, 5
     * Here, nodes with values 2 and 4 are swapped.
     */
    public static void main(String[] args) {
        // Build the BST with swapped nodes
        //
        //         3
        //        / \
        //       4   5
        //      /
        //     1
        //      \
        //       2
        //
        Node root = new Node(3);
        root.left = new Node(4);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.left.right = new Node(2);

        Solution sol = new Solution();
        System.out.println("Inorder Traversal before correction:");
        sol.inorderTraversal(root);
        System.out.println();

        sol.correctBST(root);

        System.out.println("Inorder Traversal after correction:");
        sol.inorderTraversal(root);
        System.out.println();
    }
}
