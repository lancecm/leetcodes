package LeetCode;

import org.junit.Test;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/5
 * @Description: 123
 * Hard
 * <p>
 * 122进化：最多只能买两次；仍然必须在买入之前卖出全部
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * <p>
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * <p>
 * Example 1:
 * <p>
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 * <p>
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 * engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * 思路：动态规划
 * 局部最优值是比较前一天并少交易一次的全局最优加上大于0的差值，和前一天的局部最优加上差值中取较大值
 * 全局最优比较局部最优和前一天的全局最优
 */
public class Problem123_BestTimeToBuyAndSellStockIII {
    // DP
    // O(n*k), k=2, O(n); O(k), k=2, O(1)
    // 每轮检查：在少一次交易的基础上新增一次交易 VS 在目前交易k次的基础上拓展；哪个更大取哪个
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[] local = new int[3];
        int[] global = new int[3];
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i]; // 卖出价格
            for (int j = 2; j >= 1; j--) {
                local[j] = Math.max(global[j - 1] + (diff > 0 ? diff : 0), local[j] + diff);
                global[j] = Math.max(local[j], global[j]);
            }
        }
        return global[2];
    }

    // 上面的逻辑拉平
    // O(n) O(1)
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int local1 = 0, local2 = 0, global1 = 0, global2=0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            // 交易2次当前最大 = max(上一轮交易1次最大 + 新增交易（需正数，否则设0）, 交易两次当前值的拓展）
            local2 = Math.max(global1 + Math.max(diff, 0), local2 + diff);
            // 交易2次全局最大 = max(交易2次当前最大，上一次交易2次全局最大)
            global2 = Math.max(local2, global2);
            // 以下算法为kadane's algorithms, 同系列2
            local1 = Math.max(0, local1 += diff);
            global1 = Math.max(local1, global1);
        }
        return global2;
    }

    // 另一种DP思路：
    //
    // dp[k, i] = max(dp[k-1, i-1])

    @Test
    public void test() {
        int[] nums = new int[] {3,3,5,0,0,3,1,4};
        maxProfit(nums); // 3-0 + 4 -1
        System.out.println(maxProfit2(nums));
        int[] nums2 = new int[] {2,1,4,5,2,9,7};
        System.out.println(maxProfit2(nums2));
    }
}
