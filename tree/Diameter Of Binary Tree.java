
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
 * Solution class to find the diameter of a binary tree. The diameter of a
 * binary tree is the length of the longest path between any two nodes in a
 * tree. This path may or may not pass through the root.
 */
class Solution {

    /**
     * Helper function to calculate the height of the tree and update the
     * diameter. Uses a post-order traversal approach to calculate heights
     * bottom-up.
     *
     * @param node Current node being processed
     * @param diameter Array to store the maximum diameter found so far
     * @return Height of the current subtree
     */
    int height(Node node, int[] diameter) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left, diameter);
        int rightHeight = height(node.right, diameter);

        // Update the diameter if the path through the current node is larger
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight + 1);

        // Return the height of the current node
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Main function to calculate the diameter of the binary tree.
     *
     * @param root Root node of the binary tree
     * @return The diameter of the binary tree
     */
    int diameter(Node root) {
        // Use an array to hold the diameter value since Java passes primitives by value
        int[] diameter = new int[1];
        diameter[0] = 0;

        // Calculate the height and update the diameter
        height(root, diameter);

        // The diameter is the number of edges, which is the number of nodes minus 1
        return diameter[0] - 1;
    }

    /**
     * Test cases for the diameter calculation
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Empty tree
        Node root1 = null;
        System.out.println("Test Case 1 - Empty tree: " + solution.diameter(root1));  // Expected: -1

        // Test Case 2: Single node
        Node root2 = new Node(1);
        System.out.println("Test Case 2 - Single node: " + solution.diameter(root2));  // Expected: 0

        // Test Case 3: Linear tree
        Node root3 = new Node(1);
        root3.left = new Node(2);
        root3.left.left = new Node(3);
        System.out.println("Test Case 3 - Linear tree: " + solution.diameter(root3));  // Expected: 2

        // Test Case 4: Complete binary tree
        Node root4 = new Node(1);
        root4.left = new Node(2);
        root4.right = new Node(3);
        root4.left.left = new Node(4);
        root4.left.right = new Node(5);
        root4.right.left = new Node(6);
        root4.right.right = new Node(7);
        System.out.println("Test Case 4 - Complete binary tree: " + solution.diameter(root4));  // Expected: 4

        // Test Case 5: Skewed tree with longest path not passing through root
        Node root5 = new Node(1);
        root5.left = new Node(2);
        root5.left.left = new Node(3);
        root5.left.right = new Node(4);
        root5.left.right.right = new Node(5);
        System.out.println("Test Case 5 - Skewed tree: " + solution.diameter(root5));  // Expected: 3
    }
}
