/**
 * LeetCode #121: Best Time to Buy and Sell Stock
 * 
 * Problem Statement:
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing 
 * a different day in the future to sell that stock.
 * 
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve 
 * any profit, return 0.
 * 
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * 
 * Example 2:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 */
public class BestTimeToBuyStock121 {
    
    // Method 1: Brute Force Approach
    // Time Complexity: O(n²), Space Complexity: O(1)
    public int maxProfitBruteForce(int[] prices) {
        int maxProfit = 0;
        
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        
        return maxProfit;
    }
    
    // Method 2: One Pass Approach (Optimal)
    // Time Complexity: O(n), Space Complexity: O(1)
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            // Update minimum price seen so far
            minPrice = Math.min(minPrice, prices[i]);
            
            // Calculate profit if we sell at current price
            int currentProfit = prices[i] - minPrice;
            
            // Update maximum profit
            maxProfit = Math.max(maxProfit, currentProfit);
        }
        
        return maxProfit;
    }
    
    public static void main(String[] args) {
        BestTimeToBuyStock121 solution = new BestTimeToBuyStock121();
        
        // Test Case 1
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int result1 = solution.maxProfit(prices1);
        System.out.println("Test Case 1: " + result1); // Expected: 5
        
        // Test Case 2
        int[] prices2 = {7, 6, 4, 3, 1};
        int result2 = solution.maxProfit(prices2);
        System.out.println("Test Case 2: " + result2); // Expected: 0
        
        // Test Case 3
        int[] prices3 = {1, 2, 3, 4, 5};
        int result3 = solution.maxProfit(prices3);
        System.out.println("Test Case 3: " + result3); // Expected: 4
        
        // Compare with brute force
        int bruteResult = solution.maxProfitBruteForce(prices1);
        System.out.println("Brute Force Result: " + bruteResult);
    }
}
