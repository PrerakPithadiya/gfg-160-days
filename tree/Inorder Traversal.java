
import java.util.ArrayList;

/**
 * Class representing a node in a binary tree
 */
class Node {

    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

/**
 * This class represents a solution for performing inorder traversal of a binary
 * tree. The inorder traversal visits nodes in the order: left subtree -> root
 * -> right subtree.
 */
class Solution {

    /**
     * Returns a list containing the inorder traversal of the binary tree.
     *
     * @param root The root node of the binary tree
     * @return ArrayList containing node values in inorder sequence
     */
    ArrayList<Integer> inOrder(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    /**
     * Helper method that performs recursive inorder traversal of the binary
     * tree.
     *
     * @param node Current node being processed
     * @param result ArrayList to store the traversal result
     */
    private void inOrderTraversal(Node node, ArrayList<Integer> result) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, result);
        result.add(node.data);
        inOrderTraversal(node.right, result);
    }

    /**
     * Test cases to verify the inorder traversal implementation
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Empty tree
        Node emptyTree = null;
        ArrayList<Integer> result1 = solution.inOrder(emptyTree);
        System.out.println("Test Case 1 - Empty Tree: " + result1); // Expected: []

        // Test Case 2: Single node tree
        Node singleNode = new Node(1);
        ArrayList<Integer> result2 = solution.inOrder(singleNode);
        System.out.println("Test Case 2 - Single Node: " + result2); // Expected: [1]

        // Test Case 3: Complete binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        ArrayList<Integer> result3 = solution.inOrder(root);
        System.out.println("Test Case 3 - Complete Tree: " + result3); // Expected: [4, 2, 5, 1, 3]

        // Test Case 4: Left-skewed tree
        Node leftSkewed = new Node(1);
        leftSkewed.left = new Node(2);
        leftSkewed.left.left = new Node(3);
        ArrayList<Integer> result4 = solution.inOrder(leftSkewed);
        System.out.println("Test Case 4 - Left-skewed Tree: " + result4); // Expected: [3, 2, 1]

        // Test Case 5: Right-skewed tree
        Node rightSkewed = new Node(1);
        rightSkewed.right = new Node(2);
        rightSkewed.right.right = new Node(3);
        ArrayList<Integer> result5 = solution.inOrder(rightSkewed);
        System.out.println("Test Case 5 - Right-skewed Tree: " + result5); // Expected: [1, 2, 3]
    }
}
