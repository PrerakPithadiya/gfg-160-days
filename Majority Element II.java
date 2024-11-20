package GFG;

import java.util.*;

/**
 * Solution class to find elements that appear more than n/3 times in an array
 * using the Boyer-Moore Majority Vote algorithm extended for two candidates.
 */
class Solution {

    /**
     * Finds all elements that appear more than n/3 times in the given array.
     *
     * @param nums the input array of integers
     * @return a list containing elements that appear more than n/3 times, in
     * sorted order
     *
     * Time Complexity: O(n) where n is the length of input array Space
     * Complexity: O(1) as we only use constant extra space
     */
    public List<Integer> findMajority(int[] nums) {
        // Initialize two potential candidates and their respective counts
        int candidate1 = 0, candidate2 = 0, count1 = 0, count2 = 0;
        int n = nums.length;

        // Phase 1: Find the two potential candidates
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Phase 2: Verify the candidates
        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }

        // Prepare the result
        List<Integer> result = new ArrayList<>();
        if (count1 > n / 3) {
            result.add(candidate1);
        }
        if (count2 > n / 3) {
            result.add(candidate2);
        }

        // Return the result in sorted order
        Collections.sort(result);
        return result;
    }

    /**
     * Test cases to verify the functionality of findMajority method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Array with two majority elements
        int[] test1 = {1, 1, 1, 2, 2, 2, 3};
        System.out.println("Test 1: " + solution.findMajority(test1)); // Expected: [1, 2]

        // Test Case 2: Array with one majority element
        int[] test2 = {1, 1, 1, 1, 2, 2, 3};
        System.out.println("Test 2: " + solution.findMajority(test2)); // Expected: [1]

        // Test Case 3: Array with no majority element
        int[] test3 = {1, 2, 3, 4, 5};
        System.out.println("Test 3: " + solution.findMajority(test3)); // Expected: []

        // Test Case 4: Array with all same elements
        int[] test4 = {1, 1, 1, 1};
        System.out.println("Test 4: " + solution.findMajority(test4)); // Expected: [1]

        // Test Case 5: Empty array
        int[] test5 = {};
        System.out.println("Test 5: " + solution.findMajority(test5)); // Expected: []
    }
}
