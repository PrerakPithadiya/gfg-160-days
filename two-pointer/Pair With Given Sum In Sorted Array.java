
/**
 * Solution class to find pairs with given sum in a sorted array.
 * Time Complexity: O(n) where n is the length of input array
 * Space Complexity: O(1) as only constant extra space is used
 */
class Solution {

    /**
     * Counts the number of pairs in a sorted array that sum up to the target
     * value.
     *
     * @param arr The input sorted array of integers
     * @param target The target sum to find pairs for
     * @return The count of pairs that sum up to target
     *
     * Example cases: 1. arr = [1, 2, 3, 4, 5], target = 6 Result: 2 (pairs:
     * (1,5), (2,4))
     *
     * 2. arr = [1, 1, 1, 1], target = 2 Result: 6 (pairs: all possible
     * combinations of two 1's)
     *
     * 3. arr = [1, 2, 2, 3, 4, 4], target = 6 Result: 4 (pairs: (2,4), (2,4),
     * (2,4), (2,4))
     */
    int countPairs(int arr[], int target) {
        int left = 0;
        int right = arr.length - 1;
        int count = 0;

        while (left < right) {
            if (arr[left] + arr[right] == target) {
                // If we found elements that sum up to target
                if (arr[left] == arr[right]) {
                    // If both elements are same
                    int n = right - left + 1;
                    count += (n * (n - 1)) / 2;
                    break;
                } else {
                    int leftCount = 1;
                    int tempLeft = left;
                    while (tempLeft + 1 < right && arr[tempLeft] == arr[tempLeft + 1]) {
                        leftCount++;
                        tempLeft++;
                    }

                    int rightCount = 1;
                    int tempRight = right;
                    while (tempRight - 1 > tempLeft && arr[tempRight] == arr[tempRight - 1]) {
                        rightCount++;
                        tempRight--;
                    }

                    count += leftCount * rightCount;
                    left = tempLeft + 1;
                    right = tempRight - 1;
                }
            } else if (arr[left] + arr[right] < target) {
                left++;
            } else {
                right--;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Regular case with unique elements
        int[] arr1 = {1, 2, 3, 4, 5};
        assert solution.countPairs(arr1, 6) == 2 : "Test Case 1 Failed";

        // Test Case 2: Array with duplicate elements
        int[] arr2 = {1, 1, 1, 1};
        assert solution.countPairs(arr2, 2) == 6 : "Test Case 2 Failed";

        // Test Case 3: Array with some duplicate elements
        int[] arr3 = {1, 2, 2, 3, 4, 4};
        assert solution.countPairs(arr3, 6) == 4 : "Test Case 3 Failed";

        // Test Case 4: No pairs exist
        int[] arr4 = {1, 2, 3, 4, 5};
        assert solution.countPairs(arr4, 10) == 0 : "Test Case 4 Failed";

        // Test Case 5: Array with two elements
        int[] arr5 = {1, 2};
        assert solution.countPairs(arr5, 3) == 1 : "Test Case 5 Failed";

        System.out.println("All test cases passed successfully!");
    }
}
