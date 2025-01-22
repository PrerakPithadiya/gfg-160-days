
/**
 * Solution class for adding two numbers represented as linked lists
 */
class Solution {

    /**
     * Node class to represent each digit in the linked list
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
     * Adds two numbers represented as linked lists
     *
     * @param num1 First number as a linked list (each node contains a single
     * digit)
     * @param num2 Second number as a linked list (each node contains a single
     * digit)
     * @return Result of addition as a linked list
     */
    static Node addTwoLists(Node num1, Node num2) {
        // Remove leading zeros
        num1 = removeLeadingZeros(num1);
        num2 = removeLeadingZeros(num2);

        // Reverse both lists
        num1 = reverseList(num1);
        num2 = reverseList(num2);

        Node result = null;
        Node curr = null;
        int carry = 0;

        // Add numbers
        while (num1 != null || num2 != null || carry > 0) {
            int sum = carry;
            if (num1 != null) {
                sum += num1.data;
                num1 = num1.next;
            }
            if (num2 != null) {
                sum += num2.data;
                num2 = num2.next;
            }

            carry = sum / 10;
            Node newNode = new Node(sum % 10);

            if (result == null) {
                result = newNode;
                curr = result;
            } else {
                if (curr != null) {
                    curr.next = newNode;
                }
                if (curr != null) {
                    curr = curr.next;
                }
            }
        }

        // Reverse result
        return reverseList(result);
    }

    /**
     * Removes leading zeros from a number represented as a linked list
     *
     * @param head Head of the linked list
     * @return Head of the linked list with leading zeros removed
     */
    private static Node removeLeadingZeros(Node head) {
        while (head != null && head.data == 0 && head.next != null) {
            head = head.next;
        }
        return head;
    }

    /**
     * Reverses a linked list
     *
     * @param head Head of the linked list to be reversed
     * @return Head of the reversed linked list
     */
    private static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {
        // Test Case 1: 563 + 842 = 1405
        Node num1 = new Node(5);
        num1.next = new Node(6);
        num1.next.next = new Node(3);

        Node num2 = new Node(8);
        num2.next = new Node(4);
        num2.next.next = new Node(2);

        Node result = addTwoLists(num1, num2);
        printList("Test Case 1 (563 + 842): ", result);  // Expected: 1405

        // Test Case 2: Leading zeros
        Node num3 = new Node(0);
        num3.next = new Node(0);
        num3.next.next = new Node(5);

        Node num4 = new Node(0);
        num4.next = new Node(2);

        result = addTwoLists(num3, num4);
        printList("Test Case 2 (005 + 02): ", result);  // Expected: 7

        // Test Case 3: Different lengths
        Node num5 = new Node(1);
        num5.next = new Node(2);

        Node num6 = new Node(9);
        num6.next = new Node(9);
        num6.next.next = new Node(9);

        result = addTwoLists(num5, num6);
        printList("Test Case 3 (12 + 999): ", result);  // Expected: 1011
    }

    /**
     * Utility method to print a linked list
     *
     * @param message Message to display before printing the list
     * @param node Head of the linked list to be printed
     */
    private static void printList(String message, Node node) {
        System.out.print(message);
        while (node != null) {
            System.out.print(node.data);
            node = node.next;
        }
        System.out.println();
    }
}
