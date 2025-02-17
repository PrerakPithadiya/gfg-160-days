package heap;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Solution class to find k largest elements in an array Time Complexity: O(n
 * log k) where n is the array length Space Complexity: O(k) for storing k
 * elements in heap
 */
class Solution {

    /**
     * Finds the k largest elements in the given array
     *
     * @param arr Input array of integers
     * @param k Number of largest elements to find
     * @return ArrayList containing k largest elements in descending order
     * @throws IllegalArgumentException if k is greater than array length or
     * negative
     */
    public ArrayList<Integer> kLargest(int[] arr, int k) {
        if (k > arr.length || k <= 0) {
            throw new IllegalArgumentException("k must be positive and less than or equal to array length");
        }

        // Create a min heap to store k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Process each element in the array
        for (int num : arr) {
            // Add current element to the heap
            minHeap.offer(num);

            // If heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Create result list and add elements in descending order
        ArrayList<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(0, minHeap.poll());
        }

        return result;
    }

    /**
     * Test cases to verify the functionality
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Normal case
        int[] arr1 = {1, 23, 12, 9, 30, 2, 50};
        int k1 = 3;
        System.out.println("Test Case 1: " + solution.kLargest(arr1, k1)); // Expected: [50, 30, 23]

        // Test Case 2: k equals array length
        int[] arr2 = {1, 2, 3, 4, 5};
        int k2 = 5;
        System.out.println("Test Case 2: " + solution.kLargest(arr2, k2)); // Expected: [5, 4, 3, 2, 1]

        // Test Case 3: Array with duplicates
        int[] arr3 = {5, 5, 5, 5, 5};
        int k3 = 2;
        System.out.println("Test Case 3: " + solution.kLargest(arr3, k3)); // Expected: [5, 5]

        // Test Case 4: Single element
        int[] arr4 = {42};
        int k4 = 1;
        System.out.println("Test Case 4: " + solution.kLargest(arr4, k4)); // Expected: [42]

        try {
            // Test Case 5: Invalid k
            int[] arr5 = {1, 2, 3};
            int k5 = 4;
            solution.kLargest(arr5, k5); // Should throw IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 5: Exception caught as expected");
        }
    }

}
