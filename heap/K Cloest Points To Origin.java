import java.util.PriorityQueue;

/**
 * Solution class to find the K closest points to the origin.
 */
class Solution {
    /**
     * Method to find the K closest points to the origin.
     * 
     * @param points the array of points where each point is represented as [x, y]
     * @param k the number of closest points to find
     * @return a 2D array containing the K closest points
     */
    public int[][] kClosest(int[][] points, int k) {
        // Create a max heap using PriorityQueue
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> 
            (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
        );
        
        // Add points to the heap, maintaining only k elements
        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        
        // Create result array and fill it with the k closest points
        int[][] result = new int[k][2];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = maxHeap.poll();
        }
        
        return result;
    }

    /**
     * Main method to run test cases.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1
        int[][] points1 = {{1, 3}, {-2, 2}};
        int k1 = 1;
        int[][] result1 = solution.kClosest(points1, k1);
        printPoints(result1); // Expected: [[-2, 2]]
        
        // Test case 2
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int k2 = 2;
        int[][] result2 = solution.kClosest(points2, k2);
        printPoints(result2); // Expected: [[3, 3], [-2, 4]]
        
        // Test case 3
        int[][] points3 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        int k3 = 3;
        int[][] result3 = solution.kClosest(points3, k3);
        printPoints(result3); // Expected: [[1, 2], [2, 3], [3, 4]]
    }

    /**
     * Helper method to print the points array.
     * 
     * @param points the 2D array of points to print
     */
    public static void printPoints(int[][] points) {
        for (int[] point : points) {
            System.out.print("[" + point[0] + ", " + point[1] + "]");
            System.out.print(" ");
        }
        System.out.println();
    }
}
