
/**
 * This class provides three different solutions for reversing nodes in a linked list in groups of k.
 * Each solution has its own advantages and trade-offs in terms of space and time complexity.
 */
// Solution 1: Iterative Approach with O(1) space
import java.util.Stack;

/**
 * Iterative solution that reverses nodes in groups of k using constant extra
 * space. Time Complexity: O(n), where n is the number of nodes Space
 * Complexity: O(1), only uses a constant amount of extra space
 */
class Solution {

    /**
     * Reverses nodes in groups of k. If the number of remaining nodes is less
     * than k, they are also reversed.
     *
     * @param head The head node of the linked list
     * @param k The size of groups to reverse
     * @return The head of the modified linked list
     */
    public static Node reverseKGroup(Node head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        // Count total nodes
        int count = 0;
        Node curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        // Dummy node for easier handling of head
        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;
        Node next;

        // Process all groups
        while (count >= k) {
            curr = prev.next; // First node of current group
            next = curr.next; // Second node of current group

            // Reverse current group
            for (int i = 1; i < k; i++) {
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
                next = curr.next;
            }

            prev = curr;
            count -= k;
        }

        // If remaining nodes exist, reverse them
        if (count > 0) {
            curr = prev.next;
            Node tail = curr;
            next = curr.next;

            while (next != null) {
                Node temp = next.next;
                next.next = curr;
                curr = next;
                next = temp;
            }

            tail.next = null;
            prev.next = curr;
        }

        return dummy.next;
    }
}

/**
 * Recursive solution for reversing nodes in groups of k. Time Complexity: O(n),
 * where n is the number of nodes Space Complexity: O(n/k) for recursion stack
 */
class RecursiveSolution {

    /**
     * Recursively reverses nodes in groups of k.
     *
     * @param head The head node of the linked list
     * @param k The size of groups to reverse
     * @return The head of the modified linked list
     */
    public static Node reverseKGroup(Node head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        // Count k nodes
        Node curr = head;
        int count = 0;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }

        // If k nodes exist
        if (count == k) {
            curr = reverseKGroup(curr, k); // Reverse remaining list

            // Reverse current k nodes
            while (count-- > 0) {
                Node temp = head.next;
                head.next = curr;
                curr = head;
                head = temp;
            }
            head = curr;
        }

        return head;
    }
}

/**
 * Stack-based solution for reversing nodes in groups of k. Time Complexity:
 * O(n), where n is the number of nodes Space Complexity: O(k) for the stack
 */
class StackSolution {

    /**
     * Reverses nodes in groups of k using a stack.
     *
     * @param head The head node of the linked list
     * @param k The size of groups to reverse
     * @return The head of the modified linked list
     */
    public static Node reverseKGroup(Node head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        Stack<Node> stack = new Stack<>();
        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;
        Node curr = head;

        int count = 0;
        while (curr != null) {
            count++;
            stack.push(curr);
            curr = curr.next;

            if (count == k) {
                while (!stack.isEmpty()) {
                    prev.next = stack.pop();
                    prev = prev.next;
                }
                prev.next = curr;
                count = 0;
            }
        }

        // Handle remaining nodes
        if (count > 0) {
            Node tail = stack.get(0);
            while (!stack.isEmpty()) {
                prev.next = stack.pop();
                prev = prev.next;
            }
            prev.next = null;
        }

        return dummy.next;
    }

    /**
     * Main method with test cases to demonstrate the functionality
     */
    public static void main(String[] args) {
        // Test case 1: Normal case with complete groups
        Node head1 = createList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("Test Case 1 - Before: ");
        printList(head1);
        head1 = Solution.reverseKGroup(head1, 3);
        System.out.println("After (k=3): ");
        printList(head1);

        // Test case 2: List with incomplete group at end
        Node head2 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("\nTest Case 2 - Before: ");
        printList(head2);
        head2 = RecursiveSolution.reverseKGroup(head2, 2);
        System.out.println("After (k=2): ");
        printList(head2);

        // Test case 3: Single node
        Node head3 = createList(new int[]{1});
        System.out.println("\nTest Case 3 - Before: ");
        printList(head3);
        head3 = StackSolution.reverseKGroup(head3, 2);
        System.out.println("After (k=2): ");
        printList(head3);

        // Test case 4: Empty list
        Node head4 = null;
        System.out.println("\nTest Case 4 - Empty List (k=2): ");
        head4 = Solution.reverseKGroup(head4, 2);
        printList(head4);
    }

    /**
     * Helper method to create a linked list from an array
     */
    private static Node createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Node head = new Node(arr[0]);
        Node curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new Node(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    /**
     * Helper method to print a linked list
     */
    private static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }
}

/**
 * Node class representing a single node in the linked list
 */
class Node {

    int val;
    Node next;

    Node(int val) {
        this.val = val;
    }
}
