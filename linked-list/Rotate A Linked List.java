
/**
 * Solution class for rotating a linked list by k positions
 */
class Solution {

    /**
     * Node class to represent each element in the linked list
     */
    static class Node {

        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Rotates a linked list by k positions
     *
     * @param head The head node of the linked list
     * @param k The number of positions to rotate
     * @return The new head of the rotated linked list
     *
     * Time Complexity: O(n) where n is the length of the list Space Complexity:
     * O(1) as we only use a constant amount of extra space
     *
     * Example 1: Input: 1->2->3->4->5, k = 2 Output: 4->5->1->2->3
     *
     * Example 2: Input: 1->2->3, k = 0 Output: 1->2->3
     *
     * Example 3: Input: null, k = 5 Output: null
     */
    public Node rotate(Node head, int k) {
        // If list is empty or has only one node or k = 0
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // First, find the length of the list and the last node
        Node current = head;
        Node last = null;
        int length = 0;

        while (current != null) {
            length++;
            last = current;
            current = current.next;
        }

        // Adjust k if it's larger than length
        k = k % length;

        // If k = 0 after modulo, no rotation needed
        if (k == 0) {
            return head;
        }

        // Find the (k)th node from start
        current = head;
        for (int i = 1; i < k; i++) {
            current = current.next;
        }

        // Adjust pointers to rotate the list
        Node newHead = current.next;
        current.next = null;
        if (last != null) {
            last.next = head;
        }

        return newHead;
    }

    /**
     * Test cases to verify the rotate function
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Normal case
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        Node result1 = solution.rotate(head1, 2);
        // Expected: 4->5->1->2->3

        // Test Case 2: k = 0
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        Node result2 = solution.rotate(head2, 0);
        // Expected: 1->2->3

        // Test Case 3: Empty list
        Node result3 = solution.rotate(null, 5);
        // Expected: null

        // Test Case 4: Single node
        Node head4 = new Node(1);
        Node result4 = solution.rotate(head4, 3);
        // Expected: 1

        // Test Case 5: k > length
        Node head5 = new Node(1);
        head5.next = new Node(2);
        Node result5 = solution.rotate(head5, 5);
        // Expected: 2->1
    }
}
