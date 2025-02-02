
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Node class represents a node in a binary tree
 */
class Node {

    int data;
    Node left, right;

    /**
     * Constructor to create a new node
     *
     * @param item The data value to be stored in the node
     */
    Node(int item) {
        data = item;
        left = right = null;
    }
}

/**
 * Solution class contains the implementation for level order traversal of a
 * binary tree
 */
class Solution {

    /**
     * Performs level order traversal of a binary tree
     *
     * @param root The root node of the binary tree
     * @return ArrayList of ArrayLists containing integers representing
     * level-wise node values
     */
    public ArrayList<ArrayList<Integer>> levelOrder(Node root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            ArrayList<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                currentLevel.add(currentNode.data);

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    /**
     * Main method to test the level order traversal implementation
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Normal binary tree
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        System.out.println("Test Case 1 Result: " + solution.levelOrder(root1));
        // Expected output: [[1], [2, 3], [4, 5]]

        // Test Case 2: Empty tree
        Node root2 = null;
        System.out.println("Test Case 2 Result: " + solution.levelOrder(root2));
        // Expected output: []

        // Test Case 3: Single node tree
        Node root3 = new Node(1);
        System.out.println("Test Case 3 Result: " + solution.levelOrder(root3));
        // Expected output: [[1]]

        // Test Case 4: Left-skewed tree
        Node root4 = new Node(1);
        root4.left = new Node(2);
        root4.left.left = new Node(3);
        root4.left.left.left = new Node(4);
        System.out.println("Test Case 4 Result: " + solution.levelOrder(root4));
        // Expected output: [[1], [2], [3], [4]]

        // Test Case 5: Right-skewed tree
        Node root5 = new Node(1);
        root5.right = new Node(2);
        root5.right.right = new Node(3);
        root5.right.right.right = new Node(4);
        System.out.println("Test Case 5 Result: " + solution.levelOrder(root5));
        // Expected output: [[1], [2], [3], [4]]
    }
}
