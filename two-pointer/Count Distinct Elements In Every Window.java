
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Solution class containing method to count distinct elements in every window
 * of size k in an array.
 */
class Solution {

    /**
     * Counts the number of distinct elements in every window of size k in the
     * given array.
     *
     * Time Complexity: O(n), where n is the length of input array Space
     * Complexity: O(k), where k is the window size
     *
     * @param arr Input array of integers
     * @param k Size of the sliding window
     * @return ArrayList containing count of distinct elements in each window
     */
    ArrayList<Integer> countDistinct(int arr[], int k) {
        ArrayList<Integer> result = new ArrayList<>();

        // If array length is less than window size
        if (arr.length < k) {
            return result;
        }

        // Initialize frequency map for first window
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < k; i++) {
            frequencyMap.put(arr[i], frequencyMap.getOrDefault(arr[i], 0) + 1);
        }

        // Add count of distinct elements in first window
        result.add(frequencyMap.size());

        // Process remaining windows
        for (int i = k; i < arr.length; i++) {
            // Remove first element of previous window
            int removeElement = arr[i - k];
            frequencyMap.put(removeElement, frequencyMap.get(removeElement) - 1);
            if (frequencyMap.get(removeElement) == 0) {
                frequencyMap.remove(removeElement);
            }

            // Add new element
            frequencyMap.put(arr[i], frequencyMap.getOrDefault(arr[i], 0) + 1);

            // Add count of distinct elements in current window
            result.add(frequencyMap.size());
        }

        return result;
    }

    /**
     * Main method containing test cases
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Normal case
        int[] arr1 = {1, 2, 1, 3, 4, 2, 3};
        int k1 = 4;
        System.out.println("Test Case 1:");
        System.out.println("Input: arr = [1, 2, 1, 3, 4, 2, 3], k = 4");
        System.out.println("Output: " + solution.countDistinct(arr1, k1));  // Expected: [3, 4, 4, 3]

        // Test Case 2: Array with all distinct elements
        int[] arr2 = {1, 2, 3, 4, 5};
        int k2 = 3;
        System.out.println("\nTest Case 2:");
        System.out.println("Input: arr = [1, 2, 3, 4, 5], k = 3");
        System.out.println("Output: " + solution.countDistinct(arr2, k2));  // Expected: [3, 3, 3]

        // Test Case 3: Array with all same elements
        int[] arr3 = {1, 1, 1, 1, 1};
        int k3 = 2;
        System.out.println("\nTest Case 3:");
        System.out.println("Input: arr = [1, 1, 1, 1, 1], k = 2");
        System.out.println("Output: " + solution.countDistinct(arr3, k3));  // Expected: [1, 1, 1, 1]

        // Test Case 4: Window size equal to array length
        int[] arr4 = {1, 2, 3};
        int k4 = 3;
        System.out.println("\nTest Case 4:");
        System.out.println("Input: arr = [1, 2, 3], k = 3");
        System.out.println("Output: " + solution.countDistinct(arr4, k4));  // Expected: [3]

        // Test Case 5: Window size greater than array length
        int[] arr5 = {1, 2};
        int k5 = 3;
        System.out.println("\nTest Case 5:");
        System.out.println("Input: arr = [1, 2], k = 3");
        System.out.println("Output: " + solution.countDistinct(arr5, k5));  // Expected: []
    }
}
