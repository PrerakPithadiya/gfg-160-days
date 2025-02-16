
import java.util.*;

/**
 * This class implements serialization and deserialization of a binary tree. The
 * tree is serialized into an ArrayList of integers and can be deserialized back
 * into a tree structure.
 */
class Tree {

    /**
     * Node class representing a binary tree node
     */
    static class Node {

        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    /**
     * Serializes a binary tree into an ArrayList of integers. Uses level-order
     * traversal (BFS) to convert the tree structure. Null nodes are represented
     * as -1 in the resulting list.
     *
     * @param root The root node of the binary tree to serialize
     * @return ArrayList containing the serialized representation of the tree
     */
    public ArrayList<Integer> serialize(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        serializeHelper(root, result);
        return result;
    }

    /**
     * Helper method for serialization that implements the BFS traversal.
     *
     * @param root The root node of the tree/subtree
     * @param result The ArrayList to store the serialized representation
     */
    private void serializeHelper(Node root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current == null) {
                result.add(-1);  // Using -1 to represent null nodes
                continue;
            }

            result.add(current.data);

            queue.offer(current.left);
            queue.offer(current.right);
        }

        // Remove trailing nulls from the end of the list
        while (!result.isEmpty() && result.get(result.size() - 1) == -1) {
            result.remove(result.size() - 1);
        }
    }

    /**
     * Deserializes an ArrayList of integers back into a binary tree structure.
     *
     * @param arr The ArrayList containing the serialized tree representation
     * @return The root node of the reconstructed binary tree
     */
    public Node deSerialize(ArrayList<Integer> arr) {
        if (arr == null || arr.isEmpty()) {
            return null;
        }

        Node root = new Node(arr.get(0));

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        for (int i = 1; i < arr.size(); i += 2) {
            Node current = queue.poll();
            if (current == null) {
                continue;
            }

            // Process left child
            if (i < arr.size() && arr.get(i) != -1) {
                current.left = new Node(arr.get(i));
                queue.offer(current.left);
            }

            // Process right child
            if (i + 1 < arr.size() && arr.get(i + 1) != -1) {
                current.right = new Node(arr.get(i + 1));
                queue.offer(current.right);
            }
        }

        return root;
    }

    /**
     * Main method containing test cases for the Tree
     * serialization/deserialization
     */
    public static void main(String[] args) {
        Tree tree = new Tree();

        // Test Case 1: Simple tree with three nodes
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        ArrayList<Integer> serialized1 = tree.serialize(root1);
        System.out.println("Test Case 1 - Serialized: " + serialized1);
        Node deserialized1 = tree.deSerialize(serialized1);
        System.out.println("Test Case 1 - Verification: " + tree.serialize(deserialized1));

        // Test Case 2: Complex tree with multiple levels
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.left = new Node(4);
        root2.right.right = new Node(5);
        ArrayList<Integer> serialized2 = tree.serialize(root2);
        System.out.println("Test Case 2 - Serialized: " + serialized2);
        Node deserialized2 = tree.deSerialize(serialized2);
        System.out.println("Test Case 2 - Verification: " + tree.serialize(deserialized2));

        // Test Case 3: Empty tree
        Node root3 = null;
        ArrayList<Integer> serialized3 = tree.serialize(root3);
        System.out.println("Test Case 3 - Serialized: " + serialized3);
        Node deserialized3 = tree.deSerialize(serialized3);
        System.out.println("Test Case 3 - Verification: " + tree.serialize(deserialized3));

        // Test Case 4: Single node tree
        Node root4 = new Node(1);
        ArrayList<Integer> serialized4 = tree.serialize(root4);
        System.out.println("Test Case 4 - Serialized: " + serialized4);
        Node deserialized4 = tree.deSerialize(serialized4);
        System.out.println("Test Case 4 - Verification: " + tree.serialize(deserialized4));
    }
}
