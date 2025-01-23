
/**
 * Node class represents a node in a linked list
 * Each node contains an integer data value and a reference to the next node
 */
class Node {

    int data;
    Node next;

    /**
     * Constructor to create a new node
     *
     * @param d The integer data to be stored in the node
     */
    Node(int d) {
        data = d;
        next = null;
    }
}

/**
 * Solution class containing methods to detect a loop in a linked list
 */
class Solution {

    /**
     * Detects if there is a loop/cycle in a linked list using Floyd's
     * Cycle-Finding Algorithm Also known as the "tortoise and hare" algorithm
     *
     * Time Complexity: O(n) where n is the number of nodes in the linked list
     * Space Complexity: O(1) as only two pointers are used
     *
     * @param head The head node of the linked list
     * @return boolean Returns true if a loop exists, false otherwise
     */
    public static boolean detectLoop(Node head) {
        if (head == null) {
            return false; // An empty list has no loop
        }

        Node slow = head; // Initialize slow pointer (tortoise)
        Node fast = head; // Initialize fast pointer (hare)

        while (fast != null && fast.next != null) {
            slow = slow.next;     // Move slow pointer by 1
            fast = fast.next.next; // Move fast pointer by 2

            // If slow and fast meet, there is a loop
            if (slow == fast) {
                return true;
            }
        }

        return false; // If we reach here, there is no loop
    }

    /**
     * Test cases to verify the detectLoop function
     */
    public static void main(String[] args) {
        // Test Case 1: List with no loop
        Node test1 = new Node(1);
        test1.next = new Node(2);
        test1.next.next = new Node(3);
        System.out.println("Test Case 1 (No Loop): " + detectLoop(test1)); // Expected: false

        // Test Case 2: List with a loop
        Node test2 = new Node(1);
        test2.next = new Node(2);
        test2.next.next = new Node(3);
        test2.next.next.next = test2.next; // Creating loop pointing to 2
        System.out.println("Test Case 2 (With Loop): " + detectLoop(test2)); // Expected: true

        // Test Case 3: Empty list
        Node test3 = null;
        System.out.println("Test Case 3 (Empty List): " + detectLoop(test3)); // Expected: false

        // Test Case 4: Single node with no loop
        Node test4 = new Node(1);
        System.out.println("Test Case 4 (Single Node): " + detectLoop(test4)); // Expected: false

        // Test Case 5: Single node with self loop
        Node test5 = new Node(1);
        test5.next = test5;
        System.out.println("Test Case 5 (Self Loop): " + detectLoop(test5)); // Expected: true
    }
}
