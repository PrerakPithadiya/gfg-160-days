
/**
 * This file contains three different solutions for finding the intersection of two arrays with duplicate elements.
 * Each solution offers different time and space complexity trade-offs.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Solution 1: HashSet-based approach This solution uses HashSet to efficiently
 * find intersection while handling duplicates. Best for: General purpose use
 * when space complexity is not a major concern.
 */
class Solution {

    /**
     * Finds the intersection of two arrays using HashSet
     *
     * @param a first input array
     * @param b second input array
     * @return ArrayList containing intersection elements without duplicates
     */
    public ArrayList<Integer> intersectionWithDuplicates(int[] a, int[] b) {
        // Create HashSet to store unique elements from first array
        HashSet<Integer> set = new HashSet<>();
        for (int num : a) {
            set.add(num);
        }

        // Create HashSet for result to avoid duplicates
        HashSet<Integer> intersection = new HashSet<>();
        for (int num : b) {
            if (set.contains(num)) {
                intersection.add(num);
            }
        }

        // Convert to ArrayList
        return new ArrayList<>(intersection);
    }

    /**
     * Test cases for Solution 1
     */
    public static void testSolution1() {
        Solution sol = new Solution();

        // Test Case 1: Basic intersection
        assert sol.intersectionWithDuplicates(new int[]{1, 2, 2, 1}, new int[]{2, 2}).equals(new ArrayList<>(Arrays.asList(2)));

        // Test Case 2: No intersection
        assert sol.intersectionWithDuplicates(new int[]{1, 2}, new int[]{3, 4}).isEmpty();

        // Test Case 3: All elements intersect
        assert sol.intersectionWithDuplicates(new int[]{1, 1}, new int[]{1, 1}).equals(new ArrayList<>(Arrays.asList(1)));

        System.out.println("All test cases passed for Solution 1");
    }
}

/**
 * Solution 2: Sorting and Two Pointers approach This solution sorts both arrays
 * and uses two pointers to find intersection. Best for: When space complexity
 * needs to be minimized and arrays are of similar size.
 */
class Solution2 {

    /**
     * Finds the intersection of two arrays using sorting and two pointers
     *
     * @param a first input array
     * @param b second input array
     * @return ArrayList containing intersection elements without duplicates
     */
    public ArrayList<Integer> intersectionWithDuplicates(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.length && j < b.length) {
            // Skip duplicates in first array
            while (i > 0 && i < a.length && a[i] == a[i - 1]) {
                i++;
            }
            // Skip duplicates in second array
            while (j > 0 && j < b.length && b[j] == b[j - 1]) {
                j++;
            }

            if (i < a.length && j < b.length) {
                if (a[i] < b[j]) {
                    i++;
                } else if (a[i] > b[j]) {
                    j++;
                } else {
                    result.add(a[i]);
                    i++;
                    j++;
                }
            }
        }

        return result;
    }

    /**
     * Test cases for Solution 2
     */
    public static void testSolution2() {
        Solution2 sol = new Solution2();

        // Test Case 1: Arrays with duplicates
        assert sol.intersectionWithDuplicates(new int[]{1, 2, 2, 1}, new int[]{2, 2}).equals(new ArrayList<>(Arrays.asList(2)));

        // Test Case 2: Empty arrays
        assert sol.intersectionWithDuplicates(new int[]{}, new int[]{}).isEmpty();

        // Test Case 3: Multiple intersections
        assert sol.intersectionWithDuplicates(new int[]{1, 2, 3, 4}, new int[]{2, 4, 6, 8}).equals(new ArrayList<>(Arrays.asList(2, 4)));

        System.out.println("All test cases passed for Solution 2");
    }
}

/**
 * Solution 3: Binary Search approach This solution sorts the larger array and
 * performs binary search for elements of smaller array. Best for: When arrays
 * have very different sizes (n << m).
 */
class Solution3 {

    /**
     * Finds the intersection of two arrays using binary search
     *
     * @param a first input array (unsorted)
     * @param b second input array (will be sorted)
     * @return ArrayList containing intersection elements without duplicates
     */
    public ArrayList<Integer> intersectionWithDuplicates(int[] a, int[] b) {
        // Sort the larger array for binary search
        Arrays.sort(b);

        HashSet<Integer> intersection = new HashSet<>();
        HashSet<Integer> seen = new HashSet<>();

        for (int num : a) {
            // Skip if we've already processed this number
            if (!seen.add(num)) {
                continue;
            }

            // Binary search in array b
            if (binarySearch(b, num)) {
                intersection.add(num);
            }
        }

        return new ArrayList<>(intersection);
    }

    /**
     * Performs binary search on a sorted array
     *
     * @param arr sorted array to search in
     * @param target value to search for
     * @return true if target is found, false otherwise
     */
    private boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return true;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    /**
     * Test cases for Solution 3
     */
    public static void testSolution3() {
        Solution3 sol = new Solution3();

        // Test Case 1: Large difference in array sizes
        assert sol.intersectionWithDuplicates(new int[]{1, 2}, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .equals(new ArrayList<>(Arrays.asList(1, 2)));

        // Test Case 2: Duplicates in first array
        assert sol.intersectionWithDuplicates(new int[]{1, 1, 1}, new int[]{1}).equals(new ArrayList<>(Arrays.asList(1)));

        // Test Case 3: No intersection with large second array
        assert sol.intersectionWithDuplicates(new int[]{1, 2}, new int[]{3, 4, 5, 6, 7, 8, 9, 10}).isEmpty();

        System.out.println("All test cases passed for Solution 3");
    }
}

/**
 * Main class to run all test cases
 */
class Main {

    public static void main(String[] args) {
        Solution.testSolution1();
        Solution2.testSolution2();
        Solution3.testSolution3();
        System.out.println("All solutions passed their test cases successfully!");
    }
}
