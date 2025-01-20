
/**
 * Solution class for merging two sorted linked lists
 * Time Complexity: O(n + m) where n and m are lengths of the input lists
 * Space Complexity: O(1) as we only use constant extra space
 */
class Solution {

    /**
     * Node class representing each element in the linked list
     */
    static class Node {

        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    /**
     * Merges two sorted linked lists into a single sorted linked list
     *
     * @param head1 The head of the first sorted linked list
     * @param head2 The head of the second sorted linked list
     * @return Node The head of the merged sorted linked list
     */
    Node sortedMerge(Node head1, Node head2) {
        // Handle edge cases where either list is empty
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        // Initialize the result head and a pointer to build the merged list
        Node result;
        Node current;

        // Choose the smaller head as the start of our merged list
        if (head1.data <= head2.data) {
            result = head1;
            head1 = head1.next;
        } else {
            result = head2;
            head2 = head2.next;
        }

        // Set current to start building the rest of the list
        current = result;

        // Continue while both lists have nodes
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                current.next = head1;
                head1 = head1.next;
            } else {
                current.next = head2;
                head2 = head2.next;
            }
            current = current.next;
        }

        // Attach remaining nodes from either list
        if (head1 != null) {
            current.next = head1;
        }
        if (head2 != null) {
            current.next = head2;
        }

        return result;
    }

    /**
     * Utility method to create a linked list from an array
     *
     * @param arr Array of integers
     * @return Node Head of the created linked list
     */
    public static Node createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * Utility method to print a linked list
     *
     * @param head Head of the linked list to print
     */
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Regular merge
        System.out.println("Test Case 1: Regular merge");
        Node list1 = createList(new int[]{1, 3, 5, 7});
        Node list2 = createList(new int[]{2, 4, 6, 8});
        System.out.println("List 1:");
        printList(list1);
        System.out.println("List 2:");
        printList(list2);
        Node merged1 = solution.sortedMerge(list1, list2);
        System.out.println("Merged List:");
        printList(merged1);
        System.out.println();

        // Test Case 2: One empty list
        System.out.println("Test Case 2: One empty list");
        Node list3 = createList(new int[]{1, 2, 3});
        Node list4 = null;
        System.out.println("List 1:");
        printList(list3);
        System.out.println("List 2:");
        printList(list4);
        Node merged2 = solution.sortedMerge(list3, list4);
        System.out.println("Merged List:");
        printList(merged2);
        System.out.println();

        // Test Case 3: Lists with duplicate values
        System.out.println("Test Case 3: Lists with duplicate values");
        Node list5 = createList(new int[]{1, 2, 2, 3});
        Node list6 = createList(new int[]{2, 2, 4});
        System.out.println("List 1:");
        printList(list5);
        System.out.println("List 2:");
        printList(list6);
        Node merged3 = solution.sortedMerge(list5, list6);
        System.out.println("Merged List:");
        printList(merged3);
    }
}
