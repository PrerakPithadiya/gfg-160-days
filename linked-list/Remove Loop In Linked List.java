
/**
 * Solution class for removing loops in a linked list
 * Time Complexity: O(n) where n is the number of nodes in the linked list
 * Space Complexity: O(1) as only constant extra space is used
 */
class Solution {

    /**
     * Removes a loop from the given linked list if one exists. Uses Floyd's
     * Cycle-Finding Algorithm (also known as tortoise and hare algorithm) to
     * detect and remove the loop.
     *
     * Algorithm: 1. Use two pointers (slow and fast) to detect the loop 2. If
     * loop exists, find the starting point of the loop 3. Remove the loop by
     * setting the last node's next pointer to null
     *
     * @param head The head node of the linked list
     */
    public static void removeLoop(Node head) {
        if (head == null || head.next == null) {
            return; // No loop if the list is empty or has only one node
        }

        Node slow = head;
        Node fast = head;

        // Step 1: Detect the loop using Floyd's Cycle-Finding Algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If slow meets fast, a loop is detected
            if (slow == fast) {
                break;
            }
        }

        // If no loop is found, return
        if (fast == null || fast.next == null) {
            return;
        }

        // Step 2: Find the starting point of the loop
        slow = head;

        // Special case: If the loop starts at the head
        if (slow == fast) {
            while (fast.next != slow) {
                fast = fast.next;
            }
            fast.next = null; // Remove the loop
            return;
        }

        // General case: Loop does not start at the head
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Step 3: Remove the loop by setting the last node's next to null
        while (fast.next != slow) {
            fast = fast.next;
        }
        fast.next = null;
    }

    /**
     * Test cases to verify the removeLoop function
     */
    public static void main(String[] args) {
        // Test Case 1: List with no loop
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        removeLoop(head1);
        // Expected: 1->2->3->null

        // Test Case 2: List with loop at the head
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = head2;
        removeLoop(head2);
        // Expected: 1->2->3->null

        // Test Case 3: List with loop in the middle
        Node head3 = new Node(1);
        head3.next = new Node(2);
        head3.next.next = new Node(3);
        head3.next.next.next = new Node(4);
        head3.next.next.next.next = head3.next;
        removeLoop(head3);
        // Expected: 1->2->3->4->null

        // Test Case 4: Empty list
        removeLoop(null);
        // Expected: null

        // Test Case 5: Single node list
        Node head5 = new Node(1);
        removeLoop(head5);
        // Expected: 1->null
    }
}

/**
 * Node class representing a node in the linked list
 */
class Node {

    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}
