
/**
 * Solution class containing method to calculate product array puzzle
 * Given an array of integers, return an array where each element at index i
 * is the product of all elements in the original array except the element at index i.
 */
class Solution {

    /**
     * Calculates a product array where each element is the product of all array
     * elements except self
     *
     * @param arr Input array of integers
     * @return Array where each element is product of all elements except self
     *
     * Time Complexity: O(n) where n is the length of input array Space
     * Complexity: O(1) excluding the output array
     *
     * Example 1: Input: arr = [1, 2, 3, 4] Output: [24, 12, 8, 6] Explanation:
     * For index 0: 2 * 3 * 4 = 24 For index 1: 1 * 3 * 4 = 12 For index 2: 1 *
     * 2 * 4 = 8 For index 3: 1 * 2 * 3 = 6
     *
     * Example 2: Input: arr = [10, 3, 5, 6, 2] Output: [180, 600, 360, 300,
     * 900]
     */
    public static int[] productExceptSelf(int arr[]) {
        int n = arr.length;
        int[] result = new int[n];

        // Initialize result array with 1
        result[0] = 1;

        // Calculate left products and store in result array
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * arr[i - 1];
        }

        // Calculate right products and multiply with existing values
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] = result[i] * rightProduct;
            rightProduct *= arr[i];
        }

        return result;
    }

    /**
     * Test cases to verify the functionality
     */
    public static void main(String[] args) {
        // Test Case 1
        int[] arr1 = {1, 2, 3, 4};
        int[] result1 = productExceptSelf(arr1);
        assert java.util.Arrays.equals(result1, new int[]{24, 12, 8, 6}) : "Test Case 1 Failed";

        // Test Case 2
        int[] arr2 = {10, 3, 5, 6, 2};
        int[] result2 = productExceptSelf(arr2);
        assert java.util.Arrays.equals(result2, new int[]{180, 600, 360, 300, 900}) : "Test Case 2 Failed";

        // Test Case 3: Array with zeros
        int[] arr3 = {1, 0, 3, 4};
        int[] result3 = productExceptSelf(arr3);
        assert java.util.Arrays.equals(result3, new int[]{0, 12, 0, 0}) : "Test Case 3 Failed";

        // Test Case 4: Single element array
        int[] arr4 = {5};
        int[] result4 = productExceptSelf(arr4);
        assert java.util.Arrays.equals(result4, new int[]{1}) : "Test Case 4 Failed";

        System.out.println("All test cases passed successfully!");
    }
}
