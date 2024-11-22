package GFG;

/**
 * Solution class to find maximum profit from buying and selling a stock with
 * one transaction allowed
 */
class Solution {

    /**
     * Calculates the maximum profit that can be obtained by buying and selling
     * a stock once
     *
     * @param prices Array of stock prices where prices[i] is the price on day i
     * @return Maximum profit possible from one buy-sell transaction, 0 if no
     * profit possible
     */
    public int maximumProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            // Update the minimum price
            if (price < minPrice) {
                minPrice = price;
            }
            // Calculate the profit if selling at the current price
            int potentialProfit = price - minPrice;
            // Update the maximum profit
            if (potentialProfit > maxProfit) {
                maxProfit = potentialProfit;
            }
        }

        return maxProfit;
    }

    /**
     * Test cases to verify the maximumProfit method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Regular case with profit
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test 1: " + (solution.maximumProfit(prices1) == 5 ? "PASS" : "FAIL"));

        // Test case 2: Decreasing prices, no profit
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Test 2: " + (solution.maximumProfit(prices2) == 0 ? "PASS" : "FAIL"));

        // Test case 3: Empty array
        int[] prices3 = {};
        System.out.println("Test 3: " + (solution.maximumProfit(prices3) == 0 ? "PASS" : "FAIL"));

        // Test case 4: Single element
        int[] prices4 = {1};
        System.out.println("Test 4: " + (solution.maximumProfit(prices4) == 0 ? "PASS" : "FAIL"));

        // Test case 5: All same prices
        int[] prices5 = {1, 1, 1, 1};
        System.out.println("Test 5: " + (solution.maximumProfit(prices5) == 0 ? "PASS" : "FAIL"));
    }
}
