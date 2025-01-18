
/**
 * Solution class containing methods to reverse a linked list
 * This class provides both iterative and recursive implementations
 */
class Solution {

    /**
     * Node class representing a single node in the linked list Contains data
     * and reference to next node
     */
    static class Node {

        Node next;
        int data;

        /**
         * Constructor to create a new node
         *
         * @param data The integer value to be stored in the node
         */
        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    /**
     * Reverses a linked list using iterative approach Time Complexity: O(n)
     * where n is number of nodes Space Complexity: O(1) as only constant extra
     * space is used
     *
     * @param head The head node of the linked list to be reversed
     * @return The new head node of the reversed linked list
     */
    Node reverseList(Node head) {
        // Handle base cases
        if (head == null || head.next == null) {
            return head;
        }

        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            // Store next
            next = current.next;

            // Reverse current node's pointer
            current.next = prev;

            // Move pointers ahead
            prev = current;
            current = next;
        }

        // prev is the new head
        return prev;
    }

    /**
     * Reverses a linked list using recursive approach Time Complexity: O(n)
     * where n is number of nodes Space Complexity: O(n) due to recursion stack
     *
     * @param head The head node of the linked list to be reversed
     * @return The new head node of the reversed linked list
     */
    Node reverseListRecursive(Node head) {
        // Base case
        if (head == null || head.next == null) {
            return head;
        }

        // Recursive call
        Node rest = reverseListRecursive(head.next);

        // Reverse the links
        head.next.next = head;
        head.next = null;

        return rest;
    }

    /**
     * Main method to test the linked list reversal implementations
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Normal linked list
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        System.out.println("Test Case 1 - Iterative:");
        printList(head1);
        head1 = solution.reverseList(head1);
        printList(head1);

        // Test Case 2: Single node
        Node head2 = new Node(1);
        System.out.println("\nTest Case 2 - Recursive:");
        printList(head2);
        head2 = solution.reverseListRecursive(head2);
        printList(head2);

        // Test Case 3: Empty list
        Node head3 = null;
        System.out.println("\nTest Case 3 - Iterative:");
        printList(head3);
        head3 = solution.reverseList(head3);
        printList(head3);

        // Test Case 4: Two nodes
        Node head4 = new Node(1);
        head4.next = new Node(2);
        System.out.println("\nTest Case 4 - Recursive:");
        printList(head4);
        head4 = solution.reverseListRecursive(head4);
        printList(head4);
    }

    /**
     * Utility method to print the linked list
     *
     * @param head The head node of the linked list to be printed
     */
    private static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
