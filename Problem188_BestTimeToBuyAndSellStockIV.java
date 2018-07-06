package LeetCode;

import org.junit.Test;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/6
 * @Description:
 * 188
 * Hard
 * 系列4：可交易最多4次
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 *
 * Input: [3,2,6,5,0,3], k = 2
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 *              Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 */
public class Problem188_BestTimeToBuyAndSellStockIV {
    // DP解法，由系列3 generalize得出; 详见上一题
    // 超出最大空间限制 ---> 由于local 和 global是按照k生成的, 当k取很大时，就不可取。
    // 最多只能买卖n/2次，k超出这个界限，就可以直接用II来解
    // Memory Limit Exceeded  TODO: WHY???
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        if (k > prices.length / 2) {
            int result = 0;
            for (int i = 1; i < prices.length; i++)
                result += Math.max(prices[i] - prices[i-1], 0);
            return result;
        }
        int[] local = new int[k + 1];
        int[] global = new int[k + 1];
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            for (int j = k; j >= 1; j--) {
                local[j] = Math.max(global[j - 1] + (diff > 0 ? diff : 0), local[j] + diff);
                global[j] = Math.max(local[j], global[j]);
            }
        }
        return global[k];
    }

    // 思路2：
    // hold[i][j] = Math.max(unhold[i-1][j] - prices[i], hold[i-1][j]);  买入，手中持股 hold[i][j] 在第i天最多完成了j次交易的利润
    // unhold[i][j] = Math.max(hold[i-1][j-1] + prices[i], unhold[i-1][j]);  卖出，手中未持股
    // 买入不看做完成了交易；
    public int maxProfit2(int k, int[] prices) {
        if (prices == null || prices.length == 1) return 0;
        if (k > prices.length / 2) return maxP(prices); // 说明买入卖出次数可以按照最大的利润来
        int[][] hold = new int[prices.length][k+1];
        int[][] unhold = new int[prices.length][k+1];
        hold[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            hold[i][0] = Math.max(hold[i-1][0], -prices[i]); // 最小进货价格（取在该天之前出现过的最小值）
        }
        for (int j = 1; j <= k; j++) {
            hold[0][j] = -prices[0]; // 第0天已经进行了k次交易：原价买入卖出算一次
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                hold[i][j] = Math.max(unhold[i-1][j] - prices[i], hold[i-1][j]); // 前一天未买入今日买入 vs 前一天已经买入
                unhold[i][j] = Math.max(hold[i-1][j-1] + prices[i], unhold[i-1][j]);         // 今天卖出 vs 昨天已卖出
            }
        }
        return Math.max(hold[prices.length - 1][k], unhold[prices.length - 1][k]);
    }

    // Same as BestTimeToBuyAndSellStock II
    public int maxP(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i > 0 && prices[i] > prices[i-1]) {
                res += prices[i] - prices[i-1];
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = new int[] {3,2,6,5,0,3};
        System.out.println(maxProfit2(2, nums));
    }

    @Test
    public void test2() {
        int k = 1000000000;
        int [] nums = new int[] {106,373,495,46,359,919,906,440,783,583,784,73,238,701,972,308,165};
         System.out.println(maxProfit(k, nums));
    }

}
