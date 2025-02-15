
/**
 * Definition for a binary tree node.
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
 * Solution class to find the Lowest Common Ancestor (LCA) in a Binary Search
 * Tree (BST)
 */
class Solution {

    /**
     * Finds the Lowest Common Ancestor of two nodes in a BST
     *
     * @param root The root node of the BST
     * @param n1 First node to find LCA for
     * @param n2 Second node to find LCA for
     * @return Node that is the LCA of n1 and n2
     *
     * Time Complexity: O(h) where h is height of tree Space Complexity: O(h)
     * for recursion stack
     */
    public Node lowestCommonAncestor(Node root, Node n1, Node n2) {
        // Base case
        if (root == null) {
            return null;
        }

        // Get values for easier comparison
        int val = root.data;
        int val1 = n1.data;
        int val2 = n2.data;

        // If both n1 and n2 are greater than current node,
        // LCA must be in right subtree
        if (val < val1 && val < val2) {
            return lowestCommonAncestor(root.right, n1, n2);
        }

        // If both n1 and n2 are smaller than current node,
        // LCA must be in left subtree
        if (val > val1 && val > val2) {
            return lowestCommonAncestor(root.left, n1, n2);
        }

        // If one value is smaller and other is greater,
        // or if current node equals either value,
        // current node is the LCA
        return root;
    }

    /**
     * Test cases to verify the LCA implementation
     */
    public void testLCA() {
        // Test Case 1: Basic BST
        //       6
        //     /   \
        //    2     8
        //   / \   / \
        //  0   4 7   9
        Node root = new Node(6);
        root.left = new Node(2);
        root.right = new Node(8);
        root.left.left = new Node(0);
        root.left.right = new Node(4);
        root.right.left = new Node(7);
        root.right.right = new Node(9);

        // Test Case 1: LCA of 2 and 8 should be 6
        assert lowestCommonAncestor(root, root.left, root.right).data == 6;

        // Test Case 2: LCA of 0 and 4 should be 2
        assert lowestCommonAncestor(root, root.left.left, root.left.right).data == 2;

        // Test Case 3: LCA of 7 and 9 should be 8
        assert lowestCommonAncestor(root, root.right.left, root.right.right).data == 8;

        // Test Case 4: Empty tree
        assert lowestCommonAncestor(null, new Node(1), new Node(2)) == null;

        // Test Case 5: Single node tree
        Node singleRoot = new Node(1);
        assert lowestCommonAncestor(singleRoot, singleRoot, singleRoot) == singleRoot;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.testLCA();
        System.out.println("All test cases passed successfully!");
    }
}
