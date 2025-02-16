
import java.util.*;

/**
 * This class provides functionality to serialize and deserialize binary trees.
 * Serialization converts a binary tree into a list representation that can be
 * easily stored or transmitted. Deserialization reconstructs the binary tree
 * from this list representation.
 */
class Tree {

    /**
     * Serializes a binary tree into an ArrayList of integers. The tree is
     * traversed in pre-order (root-left-right) fashion. Null nodes are
     * represented by -1 in the serialized list.
     *
     * @param root The root node of the binary tree to be serialized
     * @return ArrayList containing the serialized representation of the tree
     */
    public ArrayList<Integer> serialize(Node root) {
        ArrayList<Integer> list = new ArrayList<>();
        serializeHelper(root, list);
        return list;
    }

    /**
     * Helper method for serialization that performs the recursive pre-order
     * traversal.
     *
     * @param root The current node being processed
     * @param list The list to store the serialized representation
     */
    private void serializeHelper(Node root, ArrayList<Integer> list) {
        if (root == null) {
            list.add(-1); // -1 represents null node
            return;
        }
        list.add(root.data);
        serializeHelper(root.left, list);
        serializeHelper(root.right, list);
    }

    /**
     * Deserializes an ArrayList of integers back into a binary tree. The list
     * should be in the format produced by the serialize method.
     *
     * @param arr The ArrayList containing the serialized tree representation
     * @return The root node of the reconstructed binary tree
     */
    public Node deSerialize(ArrayList<Integer> arr) {
        int[] index = new int[]{0};
        return deSerializeHelper(arr, index);
    }

    /**
     * Helper method for deserialization that recursively constructs the tree.
     *
     * @param arr The ArrayList containing the serialized tree
     * @param index Array containing single index to track current position in
     * list
     * @return The current node in the reconstructed tree
     */
    private Node deSerializeHelper(ArrayList<Integer> arr, int[] index) {
        if (index[0] >= arr.size() || arr.get(index[0]) == -1) {
            index[0]++;
            return null;
        }
        Node node = new Node(arr.get(index[0]));
        index[0]++;
        node.left = deSerializeHelper(arr, index);
        node.right = deSerializeHelper(arr, index);
        return node;
    }

    /**
     * Test cases to verify the functionality of serialization and
     * deserialization.
     */
    public static void main(String[] args) {
        Tree tree = new Tree();

        // Test Case 1: Empty tree
        Node emptyRoot = null;
        ArrayList<Integer> serializedEmpty = tree.serialize(emptyRoot);
        Node deserializedEmpty = tree.deSerialize(serializedEmpty);
        assert deserializedEmpty == null : "Empty tree test failed";

        // Test Case 2: Single node tree
        Node singleRoot = new Node(1);
        ArrayList<Integer> serializedSingle = tree.serialize(singleRoot);
        Node deserializedSingle = tree.deSerialize(serializedSingle);
        assert deserializedSingle.data == 1 : "Single node tree test failed";

        // Test Case 3: Complete binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        ArrayList<Integer> serialized = tree.serialize(root);
        Node deserialized = tree.deSerialize(serialized);

        // Verify the structure of deserialized tree
        assert deserialized.data == 1 : "Root node mismatch";
        assert deserialized.left.data == 2 : "Left child mismatch";
        assert deserialized.right.data == 3 : "Right child mismatch";
        assert deserialized.left.left.data == 4 : "Left-left child mismatch";
        assert deserialized.left.right.data == 5 : "Left-right child mismatch";

        System.out.println("All test cases passed successfully!");
    }
}

/**
 * Node class representing a node in the binary tree. Each node contains an
 * integer value and references to left and right child nodes.
 */
class Node {

    int data;
    Node left, right;

    /**
     * Constructs a new Node with the given data value.
     *
     * @param data The integer value to be stored in the node
     */
    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
