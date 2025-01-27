
import java.util.*;

/**
 * LRUCache - Implements a Least Recently Used (LRU) cache with a fixed capacity
 *
 * This implementation uses a HashMap for O(1) lookups and a doubly linked list
 * to maintain the order of elements based on their access time.
 *
 * Time Complexity: - get(): O(1) - put(): O(1)
 *
 * Space Complexity: O(capacity)
 */
class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final DoublyLinkedList dll;

    /**
     * Node class representing an entry in the cache Contains key-value pair and
     * pointers for the doubly linked list
     */
    private static class Node {

        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * DoublyLinkedList implementation to maintain the order of cache entries
     * Uses dummy head and tail nodes for easier list manipulation
     */
    private static class DoublyLinkedList {

        private final Node head, tail;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        /**
         * Adds a node at the front of the list (most recently used)
         *
         * @param node Node to be added
         */
        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        /**
         * Removes a node from the list
         *
         * @param node Node to be removed
         */
        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        /**
         * Removes and returns the last node (least recently used)
         *
         * @return Last node or null if list is empty
         */
        Node removeLast() {
            if (tail.prev == head) {
                return null;
            }
            Node last = tail.prev;
            remove(last);
            return last;
        }
    }

    /**
     * Initializes the LRU cache with specified capacity
     *
     * @param cap Maximum number of key-value pairs the cache can hold
     * @throws IllegalArgumentException if capacity is less than 1
     */
    public LRUCache(int cap) {
        if (cap < 1) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = cap;
        this.cache = new HashMap<>();
        this.dll = new DoublyLinkedList();
    }

    /**
     * Retrieves the value associated with the given key
     *
     * @param key Key to look up
     * @return Value associated with the key, or -1 if key doesn't exist
     */
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        dll.remove(node);
        dll.addFirst(node);
        return node.value;
    }

    /**
     * Inserts or updates a key-value pair in the cache
     *
     * @param key Key to insert/update
     * @param value Value to be associated with the key
     */
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            dll.remove(node);
            dll.addFirst(node);
        } else {
            if (cache.size() == capacity) {
                Node last = dll.removeLast();
                if (last != null) {
                    cache.remove(last.key);
                }
            }
            Node newNode = new Node(key, value);
            dll.addFirst(newNode);
            cache.put(key, newNode);
        }
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {
        // Test Case 1: Basic operations
        LRUCache cache1 = new LRUCache(2);
        cache1.put(1, 1);
        cache1.put(2, 2);
        assert cache1.get(1) == 1;
        cache1.put(3, 3);
        assert cache1.get(2) == -1;

        // Test Case 2: Update existing key
        LRUCache cache2 = new LRUCache(2);
        cache2.put(1, 1);
        cache2.put(1, 2);
        assert cache2.get(1) == 2;

        // Test Case 3: Capacity of 1
        LRUCache cache3 = new LRUCache(1);
        cache3.put(1, 1);
        cache3.put(2, 2);
        assert cache3.get(1) == -1;
        assert cache3.get(2) == 2;

        // Test Case 4: Get operation updates access order
        LRUCache cache4 = new LRUCache(2);
        cache4.put(1, 1);
        cache4.put(2, 2);
        cache4.get(1);
        cache4.put(3, 3);
        assert cache4.get(1) == 1;
        assert cache4.get(2) == -1;

        System.out.println("All test cases passed!");
    }
}
