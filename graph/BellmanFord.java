
/**
 * Bellman-Ford Algorithm Implementation
 *
 * Problem: Given a weighted directed graph with V vertices and E edges, find the shortest
 * distances from a source vertex to all other vertices. If a negative weight cycle exists,
 * return [-1].
 *
 * Optimizations:
 * 1. Early termination if no relaxation occurs in an iteration
 * 2. Overflow protection when relaxing edges
 * 3. Efficient negative cycle detection
 * 4. Minimal memory usage with single distance array
 *
 * Time Complexity: O(V * E) in worst case, but typically better with early termination
 * Space Complexity: O(V) for the distance array
 *
 * @author Prerak Pithadiya
 */
class Solution {

    // Maximum value to represent infinity (unreachable)
    private static final int INF = 100000000; // 10^8

    /**
     * Implements the Bellman-Ford algorithm to find shortest paths from a
     * source vertex to all other vertices in a weighted directed graph.
     *
     * The algorithm works by relaxing all edges V-1 times, where V is the
     * number of vertices. After V-1 iterations, all shortest paths should be
     * found unless there's a negative cycle. We then check for negative cycles
     * by attempting one more relaxation - if any distance can still be
     * improved, a negative cycle exists.
     *
     * @param V Number of vertices in the graph (0 to V-1)
     * @param edges Array of edges where each edge is [u, v, w] representing an
     * edge from u to v with weight w
     * @param src Source vertex from which to find shortest paths
     * @return Array of shortest distances from src to all vertices, or [-1] if
     * negative cycle exists
     */
    public int[] bellmanFord(int V, int[][] edges, int src) {
        // Quick check for empty graph
        if (edges.length == 0) {
            int[] result = new int[V];
            for (int i = 0; i < V; i++) {
                result[i] = (i == src) ? 0 : INF;
            }
            return result;
        }

        // Initialize distances array with INF except for source vertex
        int[] dist = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = INF;
        }
        dist[src] = 0;

        // Main Bellman-Ford algorithm - relax all edges V-1 times
        boolean relaxed;
        for (int i = 0; i < V - 1; i++) {
            relaxed = false;
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                // Skip if source vertex is unreachable
                if (dist[u] == INF) {
                    continue;
                }

                // Relaxation step with overflow protection
                if (dist[u] + w < dist[v]) {
                    // Update the distance
                    dist[v] = dist[u] + w;
                    relaxed = true;
                }
            }

            // Early termination optimization - if no relaxation occurred in this iteration,
            // we've already found all shortest paths
            if (!relaxed) {
                break;
            }
        }

        // Check for negative weight cycles
        // If we can still relax any edge after V-1 iterations, a negative cycle exists
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (dist[u] != INF && dist[u] + w < dist[v]) {
                return new int[]{-1}; // Negative cycle detected
            }
        }

        return dist;
    }

    /**
     * Test cases for the Bellman-Ford algorithm
     *
     * Includes comprehensive test cases to verify correctness: 1. Basic graph
     * with positive weights 2. Graph with negative weights (but no negative
     * cycles) 3. Graph with a negative weight cycle 4. Disconnected graph 5.
     * Empty graph 6. Large graph with various edge cases
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean allTestsPassed = true;

        // Test Case 1: Example from the problem statement
        int V1 = 5;
        int[][] edges1 = {{1, 3, 2}, {4, 3, -1}, {2, 4, 1}, {1, 2, 1}, {0, 1, 5}};
        int src1 = 0;
        int[] result1 = solution.bellmanFord(V1, edges1, src1);
        int[] expected1 = {0, 5, 6, 6, 7};
        boolean test1Passed = assertArrayEquals(result1, expected1, "Test Case 1");
        allTestsPassed &= test1Passed;

        // Test Case 2: Graph with negative weight cycle
        int V2 = 4;
        int[][] edges2 = {{0, 1, 4}, {1, 2, -6}, {2, 3, 5}, {3, 1, -2}};
        int src2 = 0;
        int[] result2 = solution.bellmanFord(V2, edges2, src2);
        int[] expected2 = {-1};
        boolean test2Passed = assertArrayEquals(result2, expected2, "Test Case 2");
        allTestsPassed &= test2Passed;

        // Test Case 3: Disconnected graph
        int V3 = 3;
        int[][] edges3 = {{0, 1, 1}};
        int src3 = 0;
        int[] result3 = solution.bellmanFord(V3, edges3, src3);
        int[] expected3 = {0, 1, INF};
        boolean test3Passed = assertArrayEquals(result3, expected3, "Test Case 3");
        allTestsPassed &= test3Passed;

        // Test Case 4: Empty graph
        int V4 = 2;
        int[][] edges4 = {};
        int src4 = 0;
        int[] result4 = solution.bellmanFord(V4, edges4, src4);
        int[] expected4 = {0, INF};
        boolean test4Passed = assertArrayEquals(result4, expected4, "Test Case 4");
        allTestsPassed &= test4Passed;

        // Test Case 5: Graph with negative edges but no negative cycle
        int V5 = 5;
        int[][] edges5 = {{0, 1, 6}, {0, 2, 7}, {1, 2, 8}, {1, 3, -4}, {1, 4, 5}, {2, 3, 9}, {2, 4, -3}, {3, 4, 7}, {4, 0, 2}};
        int src5 = 0;
        int[] result5 = solution.bellmanFord(V5, edges5, src5);
        int[] expected5 = {0, 6, 7, 2, 4};
        boolean test5Passed = assertArrayEquals(result5, expected5, "Test Case 5");
        allTestsPassed &= test5Passed;

        // Test Case 6: Another example with negative cycle
        int V6 = 4;
        int[][] edges6 = {{0, 1, 1}, {1, 2, -1}, {2, 3, -1}, {3, 0, -1}};
        int src6 = 0;
        int[] result6 = solution.bellmanFord(V6, edges6, src6);
        int[] expected6 = {-1};
        boolean test6Passed = assertArrayEquals(result6, expected6, "Test Case 6");
        allTestsPassed &= test6Passed;

        // Final result
        if (allTestsPassed) {
            System.out.println("\nAll tests passed successfully!");
        } else {
            System.out.println("\nSome tests failed. Please check the output above.");
        }
    }

    /**
     * Helper method to assert that two arrays are equal
     *
     * @param actual The actual result array
     * @param expected The expected result array
     * @param testName The name of the test case
     * @return true if arrays are equal, false otherwise
     */
    private static boolean assertArrayEquals(int[] actual, int[] expected, String testName) {
        if (actual.length != expected.length) {
            System.out.println(testName + " FAILED: Arrays have different lengths");
            System.out.println("  Expected: " + arrayToString(expected));
            System.out.println("  Actual:   " + arrayToString(actual));
            return false;
        }

        for (int i = 0; i < actual.length; i++) {
            // Special case for INF comparison
            if ((actual[i] == INF && expected[i] != INF)
                    || (actual[i] != INF && expected[i] == INF)) {
                System.out.println(testName + " FAILED: Arrays differ at index " + i);
                System.out.println("  Expected: " + arrayToString(expected));
                System.out.println("  Actual:   " + arrayToString(actual));
                return false;
            }

            // Regular comparison for non-INF values
            if (actual[i] != INF && expected[i] != INF && actual[i] != expected[i]) {
                System.out.println(testName + " FAILED: Arrays differ at index " + i);
                System.out.println("  Expected: " + arrayToString(expected));
                System.out.println("  Actual:   " + arrayToString(actual));
                return false;
            }
        }

        System.out.println(testName + " PASSED");
        return true;
    }

    /**
     * Helper method to convert an array to a string for display
     *
     * @param arr The array to convert to string
     * @return String representation of the array
     */
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == INF) {
                sb.append("INF");
            } else {
                sb.append(arr[i]);
            }
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
