package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/3
 * @Description:
 * 121
 * Easy
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * 思路：
 * Store the min buy price and calculate the max profit.
 * O(n)
 */
public class Problem121_BestTimeToBuyAndSellStock {
    // try1：
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 1) return 0;
        int maxAmount = 0;
        int index = 0;
        for(int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[index] > maxAmount) maxAmount = prices[i] - prices[index];
            if (prices[i] < prices[index]) index = i;
        }
        return maxAmount;
    }

    // Better solution:
    // 和上面基本是一样的，少了个判断而已
    public int maxProfit2(int[] prices){
        int max = 0;
        if (prices.length == 0) return 0;
        int minbuyprice = prices[0];
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minbuyprice) minbuyprice = prices[i];
            else max = max > prices[i] - minbuyprice ? max : prices[i] - minbuyprice;
        }
        return max;
    }

    // Use Kadane's algorithms.
    // 该算法可以应用于寻找最大子序列、最大乘积等等 详见 max subarray problem（53）
    public int maxProfit3(int[] prices) {
        int maxCur = 0;
        int maxSoFar = 0;
        for (int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);  // calculate the difference
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }
}
