
/**
 * Solution class for the Book Allocation Problem
 * This class provides methods to find the minimum maximum number of pages
 * that must be allocated to students when distributing books
 */
class Solution {

    /**
     * Finds the minimum possible maximum number of pages that can be allocated
     * to students
     *
     * @param arr Array containing number of pages in each book
     * @param k Number of students to distribute books to
     * @return Minimum possible maximum pages that any student must read, or -1
     * if allocation is impossible
     */
    public static int findPages(int[] arr, int k) {
        int n = arr.length;
        if (k > n) {
            return -1; // Impossible to allocate
        }

        int low = getMax(arr); // Minimum possible max pages
        int high = getSum(arr); // Maximum possible max pages
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isFeasible(arr, n, k, mid)) {
                result = mid; // Valid allocation found
                high = mid - 1; // Try for a smaller max
            } else {
                low = mid + 1; // Increase max to make allocation feasible
            }
        }
        return result;
    }

    /**
     * Finds the maximum number of pages among all books
     *
     * @param arr Array containing number of pages in each book
     * @return Maximum number of pages in any book
     */
    private static int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int pages : arr) {
            max = Math.max(max, pages);
        }
        return max;
    }

    /**
     * Calculates the total number of pages in all books
     *
     * @param arr Array containing number of pages in each book
     * @return Sum of pages in all books
     */
    private static int getSum(int[] arr) {
        int sum = 0;
        for (int pages : arr) {
            sum += pages;
        }
        return sum;
    }

    /**
     * Checks if it's possible to allocate books such that no student gets more
     * than maxPages
     *
     * @param arr Array containing number of pages in each book
     * @param n Total number of books
     * @param k Number of students
     * @param maxPages Maximum pages that can be allocated to any student
     * @return true if allocation is possible, false otherwise
     */
    private static boolean isFeasible(int[] arr, int n, int k, int maxPages) {
        int studentsRequired = 1;
        int currentPages = 0;

        for (int pages : arr) {
            if (currentPages + pages > maxPages) {
                studentsRequired++;
                currentPages = pages;
                if (studentsRequired > k) {
                    return false;
                }
            } else {
                currentPages += pages;
            }
        }
        return true;
    }

    /**
     * Test cases to verify the functionality of the book allocation algorithm
     */
    public static void main(String[] args) {
        // Test Case 1: Normal case
        int[] books1 = {12, 34, 67, 90};
        int k1 = 2;
        System.out.println("Test Case 1: " + findPages(books1, k1)); // Expected: 113

        // Test Case 2: More students than books
        int[] books2 = {10, 20, 30};
        int k2 = 4;
        System.out.println("Test Case 2: " + findPages(books2, k2)); // Expected: -1

        // Test Case 3: Single student
        int[] books3 = {10, 20, 30, 40};
        int k3 = 1;
        System.out.println("Test Case 3: " + findPages(books3, k3)); // Expected: 100

        // Test Case 4: Equal number of books and students
        int[] books4 = {10, 20, 30, 40};
        int k4 = 4;
        System.out.println("Test Case 4: " + findPages(books4, k4)); // Expected: 40

        // Test Case 5: Large numbers
        int[] books5 = {100, 200, 300, 400, 500};
        int k5 = 3;
        System.out.println("Test Case 5: " + findPages(books5, k5)); // Expected: 600
    }
}
