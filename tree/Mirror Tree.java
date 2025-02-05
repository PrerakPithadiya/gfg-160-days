
/**
 * Definition for a binary tree node.
 */
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
 * Solution class containing method to convert a binary tree into its mirror
 * tree
 */
class Solution {

    /**
     * Converts a binary tree into its mirror tree by swapping left and right
     * children recursively for all nodes.
     *
     * A mirror tree is a tree where every left child becomes right child and
     * vice versa.
     *
     * Time Complexity: O(n) where n is the number of nodes Space Complexity:
     * O(h) where h is the height of tree due to recursion stack
     *
     * @param node Root node of the binary tree to be mirrored
     */
    void mirror(Node node) {
        // Base case: if the node is null, return
        if (node == null) {
            return;
        }

        // Swap the left and right children
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;

        // Recursively mirror the left and right subtrees
        mirror(node.left);
        mirror(node.right);
    }

    /**
     * Test cases to verify the mirror function
     */
    void testMirror() {
        // Test Case 1: Empty tree
        Node emptyTree = null;
        mirror(emptyTree); // Should handle null case

        // Test Case 2: Single node tree
        Node singleNode = new Node(1);
        mirror(singleNode); // Should remain unchanged

        // Test Case 3: Complete binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        mirror(root); // Should create mirror image

        // Test Case 4: Skewed tree
        Node skewedRoot = new Node(1);
        skewedRoot.left = new Node(2);
        skewedRoot.left.left = new Node(3);
        mirror(skewedRoot); // Should convert left skewed to right skewed

        // Test Case 5: Asymmetric tree
        Node asymRoot = new Node(1);
        asymRoot.left = new Node(2);
        asymRoot.right = new Node(3);
        asymRoot.left.right = new Node(4);
        mirror(asymRoot); // Should properly handle asymmetric structure
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.testMirror();
    }
}
