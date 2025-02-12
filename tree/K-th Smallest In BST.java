
/**
 * Solution class for finding the kth smallest element in a Binary Search Tree (BST)
 * using Morris Traversal algorithm.
 */
class Solution {

    /**
     * Node class representing a node in the Binary Search Tree
     */
    private static class Node {

        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Finds the kth smallest element in a Binary Search Tree. This
     * implementation uses Morris Traversal which achieves O(1) space complexity
     * by creating temporary links to traverse the tree without recursion or
     * stack.
     *
     * Time Complexity: O(n) where n is the number of nodes in the tree Space
     * Complexity: O(1) as it uses only constant extra space
     *
     * @param root The root node of the Binary Search Tree
     * @param k The position of the element to find (1-based indexing)
     * @return The kth smallest element in the BST, or -1 if k is invalid or
     * tree is empty
     * @throws IllegalArgumentException if k is less than 1
     */
    public int kthSmallest(Node root, int k) {
        if (k < 1) {
            throw new IllegalArgumentException("k must be greater than 0");
        }
        if (root == null) {
            return -1;
        }

        int count = 0;
        Node current = root;
        int kthSmallest = -1;

        while (current != null) {
            // If left child is null, visit current node
            if (current.left == null) {
                count++;
                // If we've found the kth element
                if (count == k) {
                    kthSmallest = current.data;
                    break;
                }
                current = current.right;
            } else {
                // Find the inorder predecessor
                Node predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                // Make current as right child of its inorder predecessor
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } // Revert the changes made in the tree
                else {
                    predecessor.right = null;
                    count++;
                    // If we've found the kth element
                    if (count == k) {
                        kthSmallest = current.data;
                        break;
                    }
                    current = current.right;
                }
            }
        }

        return kthSmallest;
    }

    /**
     * Test cases to verify the functionality of kthSmallest method
     */
    public void runTests() {
        // Test Case 1: Normal BST
        Node root1 = new Node(4);
        root1.left = new Node(2);
        root1.right = new Node(6);
        root1.left.left = new Node(1);
        root1.left.right = new Node(3);
        int result1 = kthSmallest(root1, 3);
        assert result1 == 3 : "Test Case 1 Failed";

        // Test Case 2: Left-skewed tree
        Node root2 = new Node(3);
        root2.left = new Node(2);
        root2.left.left = new Node(1);
        int result2 = kthSmallest(root2, 1);
        assert result2 == 1 : "Test Case 2 Failed";

        // Test Case 3: Right-skewed tree
        Node root3 = new Node(1);
        root3.right = new Node(2);
        root3.right.right = new Node(3);
        int result3 = kthSmallest(root3, 2);
        assert result3 == 2 : "Test Case 3 Failed";

        // Test Case 4: Single node tree
        Node root4 = new Node(1);
        int result4 = kthSmallest(root4, 1);
        assert result4 == 1 : "Test Case 4 Failed";

        // Test Case 5: Empty tree
        int result5 = kthSmallest(null, 1);
        assert result5 == -1 : "Test Case 5 Failed";

        // Test Case 6: Invalid k
        try {
            kthSmallest(root1, 0);
            assert false : "Test Case 6 Failed - Should throw IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            // Expected behavior
        }

        System.out.println("All test cases passed!");
    }
}
