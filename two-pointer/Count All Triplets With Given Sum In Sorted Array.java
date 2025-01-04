
/**
 * Solution class to count triplets with given sum in a sorted array
 * Time Complexity: O(n^2) where n is the length of input array
 * Space Complexity: O(1) as only constant extra space is used
 */
class Solution {

    /**
     * Counts the number of triplets in a sorted array that sum up to the target
     * value
     *
     * @param arr The input sorted array of integers
     * @param target The target sum to find triplets for
     * @return The count of triplets that sum up to target
     *
     * Example 1: Input: arr = [1, 1, 2, 2, 2, 3], target = 5 Output: 4
     * Explanation: The triplets are: (1,1,3), (1,1,3), (1,2,2), (1,2,2)
     *
     * Example 2: Input: arr = [1, 2, 3, 4, 5], target = 9 Output: 2
     * Explanation: The triplets are: (2,3,4), (1,3,5)
     *
     * Example 3: Input: arr = [2, 2, 2, 2, 2], target = 6 Output: 10
     * Explanation: All possible combinations of three elements sum to 6
     *
     * Notes: - The array must be sorted in non-decreasing order - The solution
     * handles duplicate elements - For sum == target, we handle two cases: 1.
     * When left and right elements are same (using combination formula) 2. When
     * left and right elements are different (using product of frequencies)
     */
    public int countTriplets(int[] arr, int target) {
        int n = arr.length;
        int count = 0;

        // Fix the first element and use two pointers for the remaining two
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum == target) {
                    // When we find a match, we need to handle duplicates
                    if (arr[left] == arr[right]) {
                        // If left and right elements are same, calculate combinations
                        int range = right - left + 1;
                        count += (range * (range - 1)) / 2;
                        break;
                    } else {
                        // Count duplicates on both sides
                        int leftCount = 1;
                        int rightCount = 1;

                        // Count duplicates on left side
                        while (left + 1 < right && arr[left] == arr[left + 1]) {
                            leftCount++;
                            left++;
                        }

                        // Count duplicates on right side
                        while (right - 1 > left && arr[right] == arr[right - 1]) {
                            rightCount++;
                            right--;
                        }

                        count += leftCount * rightCount;
                        left++;
                        right--;
                    }
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }

    // Test cases
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Array with duplicates
        int[] arr1 = {1, 1, 2, 2, 2, 3};
        assert solution.countTriplets(arr1, 5) == 4 : "Test case 1 failed";

        // Test case 2: Array without duplicates
        int[] arr2 = {1, 2, 3, 4, 5};
        assert solution.countTriplets(arr2, 9) == 2 : "Test case 2 failed";

        // Test case 3: Array with all same elements
        int[] arr3 = {2, 2, 2, 2, 2};
        assert solution.countTriplets(arr3, 6) == 10 : "Test case 3 failed";

        // Test case 4: No triplets exist
        int[] arr4 = {1, 2, 3, 4, 5};
        assert solution.countTriplets(arr4, 15) == 0 : "Test case 4 failed";

        // Test case 5: Minimum size array
        int[] arr5 = {1, 2, 3};
        assert solution.countTriplets(arr5, 6) == 1 : "Test case 5 failed";

        System.out.println("All test cases passed!");
    }
}
