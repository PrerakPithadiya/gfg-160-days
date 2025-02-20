import java.util.List;
import java.util.PriorityQueue;

/**
 * Node class representing a node in a linked list.
 */
class Node {
    int data;
    Node next;
    
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * Solution class to merge K sorted linked lists.
 */
class Solution {
    /**
     * Function to merge K sorted linked lists.
     * 
     * @param arr the list of head nodes of the K sorted linked lists
     * @return the head node of the merged sorted linked list
     */
    Node mergeKLists(List<Node> arr) {
        // Create a min heap to store nodes based on their values
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.data - b.data);
        
        // Add the first node from each list to the heap
        for (Node head : arr) {
            if (head != null) {
                minHeap.offer(head);
            }
        }
        
        // Create dummy node for result list
        Node dummy = new Node(0);
        Node current = dummy;
        
        // Process nodes from heap until empty
        while (!minHeap.isEmpty()) {
            // Get the smallest node
            Node node = minHeap.poll();
            current.next = node;
            current = current.next;
            
            // Add the next node from the same list if exists
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }
        
        return dummy.next;
    }

    /**
     * Main method to run test cases.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1
        Node list1 = new Node(1);
        list1.next = new Node(4);
        list1.next.next = new Node(5);
        
        Node list2 = new Node(1);
        list2.next = new Node(3);
        list2.next.next = new Node(4);
        
        Node list3 = new Node(2);
        list3.next = new Node(6);
        
        List<Node> arr1 = List.of(list1, list2, list3);
        Node mergedList1 = solution.mergeKLists(arr1);
        printList(mergedList1); // Expected: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
        
        // Test case 2
        Node list4 = new Node(2);
        list4.next = new Node(3);
        list4.next.next = new Node(8);
        
        Node list5 = new Node(1);
        list5.next = new Node(7);
        
        List<Node> arr2 = List.of(list4, list5);
        Node mergedList2 = solution.mergeKLists(arr2);
        printList(mergedList2); // Expected: 1 -> 2 -> 3 -> 7 -> 8
    }

    /**
     * Helper method to print the linked list.
     * 
     * @param head the head node of the linked list
     */
    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }
}
