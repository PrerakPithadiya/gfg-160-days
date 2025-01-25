
class Node {

    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}

/**
 * Solution class containing method to find first node of a loop in linked list
 */
class Solution {

    /**
     * Finds the first node of a loop in a linked list if one exists
     *
     * Algorithm: 1. Uses Floyd's Cycle-Finding Algorithm (tortoise and hare) 2.
     * Uses two pointers - slow (moves 1 step) and fast (moves 2 steps) 3. If
     * loop exists, pointers will meet at some point inside loop 4. Reset slow
     * pointer to head and move both pointers at same speed 5. Point where they
     * meet again is start of loop
     *
     * Time Complexity: O(n) where n is number of nodes Space Complexity: O(1)
     * as only two pointers are used
     *
     * @param head The head node of the linked list
     * @return First node of the loop if exists, null otherwise
     */
    public static Node findFirstNode(Node head) {
        // Step 1: Detect if a loop exists using Floyd's Cycle-Finding Algorithm
        Node slow = head;
        Node fast = head;

        // Find meeting point in the loop
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // Loop detected
            if (slow == fast) {
                // Step 2: Find the start of the loop
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                // Return the first node of the loop
                return slow;
            }
        }

        // No loop found
        return null;
    }

    /**
     * Test cases to verify the functionality
     */
    public static void main(String[] args) {
        // Test Case 1: List with loop
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = head1.next; // Creating loop at node with value 2
        Node result1 = findFirstNode(head1);
        System.out.println("Test Case 1 - Loop starts at node with value: " + result1.data); // Expected: 2

        // Test Case 2: List without loop
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        Node result2 = findFirstNode(head2);
        System.out.println("Test Case 2 - No loop exists: " + (result2 == null)); // Expected: true

        // Test Case 3: Single node with self loop
        Node head3 = new Node(1);
        head3.next = head3;
        Node result3 = findFirstNode(head3);
        System.out.println("Test Case 3 - Loop starts at node with value: " + result3.data); // Expected: 1

        // Test Case 4: Empty list
        Node result4 = findFirstNode(null);
        System.out.println("Test Case 4 - Empty list: " + (result4 == null)); // Expected: true
    }
}
