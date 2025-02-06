
/**
 * Definition for a binary tree node.
 */
class Node {

    int data;
    Node left, right;

    Node(int key) {
        data = key;
        left = right = null;
    }
}

/**
 * Solution class for constructing a binary tree from inorder and preorder
 * traversals
 */
class Solution {

    /**
     * Index to track current position in preorder traversal
     */
    private static int preIndex = 0;

    /**
     * Map to store inorder indices for O(1) lookup
     */
    private static java.util.Map<Integer, Integer> inorderIndexMap;

    /**
     * Builds a binary tree from given inorder and preorder traversals
     *
     * @param inorder The inorder traversal array
     * @param preorder The preorder traversal array
     * @return Root node of the constructed binary tree
     * @throws IllegalArgumentException if input arrays are null or of different
     * lengths
     */
    public static Node buildTree(int inorder[], int preorder[]) {
        // Input validation
        if (inorder == null || preorder == null || inorder.length != preorder.length) {
            throw new IllegalArgumentException("Invalid input arrays");
        }

        // Initialize the hash map for inorder indices
        inorderIndexMap = new java.util.HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        preIndex = 0;
        return buildTreeHelper(inorder, preorder, 0, inorder.length - 1);
    }

    /**
     * Helper method to recursively construct the binary tree
     *
     * @param inorder The inorder traversal array
     * @param preorder The preorder traversal array
     * @param inStart Start index of current subtree in inorder array
     * @param inEnd End index of current subtree in inorder array
     * @return Root node of the current subtree
     */
    private static Node buildTreeHelper(int[] inorder, int[] preorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preIndex++];
        Node root = new Node(rootVal);
        int rootIndex = inorderIndexMap.get(rootVal);

        root.left = buildTreeHelper(inorder, preorder, inStart, rootIndex - 1);
        root.right = buildTreeHelper(inorder, preorder, rootIndex + 1, inEnd);

        return root;
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {
        // Test Case 1: Basic tree
        int[] inorder1 = {4, 2, 5, 1, 6, 3, 7};
        int[] preorder1 = {1, 2, 4, 5, 3, 6, 7};
        buildTree(inorder1, preorder1);
        System.out.println("Test Case 1 - Tree constructed successfully");

        // Test Case 2: Single node tree
        int[] inorder2 = {1};
        int[] preorder2 = {1};
        buildTree(inorder2, preorder2);
        System.out.println("Test Case 2 - Single node tree constructed successfully");

        // Test Case 3: Left-skewed tree
        int[] inorder3 = {3, 2, 1};
        int[] preorder3 = {1, 2, 3};
        buildTree(inorder3, preorder3);
        System.out.println("Test Case 3 - Left-skewed tree constructed successfully");

        // Test Case 4: Right-skewed tree
        int[] inorder4 = {1, 2, 3};
        int[] preorder4 = {1, 2, 3};
        buildTree(inorder4, preorder4);
        System.out.println("Test Case 4 - Right-skewed tree constructed successfully");

        try {
            // Test Case 5: Invalid input
            buildTree(null, null);
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 5 - Successfully caught invalid input");
        }
    }
}
