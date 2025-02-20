import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Solution class to find the median of a stream of integers.
 */
class Solution {
    /**
     * Method to get the median of a stream of integers.
     * 
     * @param arr the input array of integers
     * @return an ArrayList of Double containing the medians after each insertion
     */
    public ArrayList<Double> getMedian(int[] arr) {
        ArrayList<Double> medians = new ArrayList<>();
        PriorityQueue<Integer> lowerHalf = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> upperHalf = new PriorityQueue<>();
        
        for (int num : arr) {
            if (lowerHalf.isEmpty() || num <= lowerHalf.peek()) {
                lowerHalf.offer(num);
            } else {
                upperHalf.offer(num);
            }
            
            if (lowerHalf.size() > upperHalf.size() + 1) {
                upperHalf.offer(lowerHalf.poll());
            } else if (upperHalf.size() > lowerHalf.size()) {
                lowerHalf.offer(upperHalf.poll());
            }
            
            if (lowerHalf.size() == upperHalf.size()) {
                medians.add((lowerHalf.peek() + upperHalf.peek()) / 2.0);
            } else {
                medians.add((double) lowerHalf.peek());
            }
        }
        
        return medians;
    }

    /**
     * Main method to run test cases.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println("Test case 1: " + solution.getMedian(arr1)); // Expected: [1.0, 1.5, 2.0, 2.5, 3.0]
        
        // Test case 2
        int[] arr2 = {5, 15, 1, 3};
        System.out.println("Test case 2: " + solution.getMedian(arr2)); // Expected: [5.0, 10.0, 5.0, 4.0]
        
        // Test case 3
        int[] arr3 = {2, 4, 6, 8, 10, 12};
        System.out.println("Test case 3: " + solution.getMedian(arr3)); // Expected: [2.0, 3.0, 4.0, 5.0, 6.0, 7.0]
        
        // Test case 4
        int[] arr4 = {10, 20, 30, 40, 50};
        System.out.println("Test case 4: " + solution.getMedian(arr4)); // Expected: [10.0, 15.0, 20.0, 25.0, 30.0]
    }
}
