
import java.util.Arrays;

/**
 * Solution class containing method to count possible triangles from array of
 * integers
 */
class Solution {

    /**
     * Counts the number of possible triangles that can be formed using array
     * elements as sides
     *
     * Algorithm: 1. Sort the input array in ascending order 2. Fix the largest
     * element as one side and use two pointers to find other two sides 3. For
     * each triplet, check if it satisfies triangle inequality: sum of any two
     * sides > third side 4. Since array is sorted, we only need to check if
     * smallest + second > largest
     *
     * Time Complexity: O(n^2) where n is length of input array Space
     * Complexity: O(1) as we only use constant extra space
     *
     * @param arr Input array of positive integers representing possible side
     * lengths
     * @return Number of possible triangles that can be formed
     */
    static int countTriangles(int arr[]) {
        int n = arr.length;
        int count = 0;

        // Sort array in ascending order
        Arrays.sort(arr);

        // Fix the last element and find other two elements
        for (int i = n - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;

            // Use two pointers to find valid triangles
            while (left < right) {
                // Check triangle inequality
                if (arr[left] + arr[right] > arr[i]) {
                    // All elements between left and right will form valid triangles
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }

        return count;
    }

    /**
     * Test cases to verify the countTriangles method
     */
    public static void main(String[] args) {
        // Test Case 1: Basic case with valid triangles
        int[] arr1 = {4, 6, 3, 7};
        System.out.println("Test Case 1: " + countTriangles(arr1)); // Expected output: 3

        // Test Case 2: Array with no possible triangles
        int[] arr2 = {1, 2, 4, 8};
        System.out.println("Test Case 2: " + countTriangles(arr2)); // Expected output: 0

        // Test Case 3: Array with all equal elements
        int[] arr3 = {5, 5, 5, 5};
        System.out.println("Test Case 3: " + countTriangles(arr3)); // Expected output: 4

        // Test Case 4: Minimum length array
        int[] arr4 = {3, 4, 5};
        System.out.println("Test Case 4: " + countTriangles(arr4)); // Expected output: 1

        // Test Case 5: Larger array
        int[] arr5 = {10, 21, 22, 100, 101, 200, 300};
        System.out.println("Test Case 5: " + countTriangles(arr5)); // Expected output: 6
    }
}
