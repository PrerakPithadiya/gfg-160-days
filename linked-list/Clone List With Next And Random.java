
/**
 * Node class represents a node in a linked list with next and random pointers.
 */
class Node {

    int data;
    Node next;    // Points to the next node in the sequence
    Node random;  // Points to any random node in the list

    /**
     * Constructor to create a new node with given data.
     *
     * @param x The data value to be stored in the node
     */
    Node(int x) {
        data = x;
        next = null;
        random = null;
    }
}

/**
 * Solution class containing method to clone a linked list with random pointers.
 */
class Solution {

    /**
     * Creates a deep copy of a linked list with random pointers. This method
     * uses a three-step approach: 1. Create interleaved clone nodes 2. Set
     * random pointers for clone nodes 3. Separate the clone list from original
     * list
     *
     * Time Complexity: O(n) where n is the number of nodes Space Complexity:
     * O(1) as no extra space is used
     *
     * @param head The head node of the original linked list
     * @return Head node of the cloned linked list
     */
    public Node cloneLinkedList(Node head) {
        if (head == null) {
            return null;
        }

        // Step 1: Create new nodes and interleave them with original nodes
        Node current = head;
        while (current != null) {
            Node newNode = new Node(current.data);
            newNode.next = current.next;
            current.next = newNode;
            current = newNode.next; // Move to the next original node
        }

        // Step 2: Set the random pointers for the cloned nodes
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next; // Set the random pointer
            }
            current = current.next.next; // Move to the next original node
        }

        // Step 3: Separate the cloned list from the original list
        Node original = head;
        Node copyHead = head.next; // The head of the cloned list
        Node copyCurrent = copyHead;

        while (original != null) {
            original.next = original.next.next; // Restore the original list
            if (copyCurrent.next != null) {
                copyCurrent.next = copyCurrent.next.next; // Move to the next cloned node
            }
            original = original.next;
            copyCurrent = copyCurrent.next;
        }

        return copyHead; // Return the head of the cloned list
    }

    /**
     * Test cases to verify the functionality of cloneLinkedList method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Empty list
        assert solution.cloneLinkedList(null) == null : "Empty list test failed";

        // Test Case 2: Single node
        Node single = new Node(1);
        Node clonedSingle = solution.cloneLinkedList(single);
        assert clonedSingle.data == 1 : "Single node test failed";

        // Test Case 3: Multiple nodes with random pointers
        Node head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);

        head.next = second;
        second.next = third;
        third.next = fourth;

        // Setting random pointers
        head.random = third;
        second.random = second;
        third.random = fourth;
        fourth.random = head;

        Node cloned = solution.cloneLinkedList(head);

        // Verify the cloned list
        assert cloned.data == 1 : "Data verification failed";
        assert cloned.random.data == 3 : "Random pointer verification failed";
        assert cloned != head : "Deep copy verification failed";

        System.out.println("All test cases passed successfully!");
    }
}
