
class Solution {

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
        last.next = head;

        return newHead;
    }
}
