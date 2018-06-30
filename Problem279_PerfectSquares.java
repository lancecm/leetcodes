package LeetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/30
 * @Description: Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * <p>
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class Problem279_PerfectSquares {

    // 超时了…… 我也不造我怎么写出来的code 思路是用DFS递归计算最小完全平方数数量
    public int numSquares(int n) {
        List<Integer> list = new ArrayList<>();
        int i = 1;
        while (n / (i * i) != 0) {
            list.add(i * i);
            i++;
        }
        return helper(list, list.size(), n);
    }

    private int helper(List<Integer> list, int len, int remains) {
        if (len <= 1) return remains; // 如果除数只剩下1
        int[] result = new int[len];
        for (int i = len; i > 0; i--) {
            int tmpRes = 0;
            int tmpRemains = remains;
            if (tmpRemains / list.get(i - 1) > 0) {
                tmpRes += tmpRemains / list.get(i-1);
                tmpRemains %= list.get(i - 1);
                if (tmpRemains != 0 ){
                    tmpRes += helper(list, i - 1, tmpRemains);
                }
                result[i - 1] = tmpRes;
            }
        }
        Arrays.sort(result);
        int i = 0;
        for (; i < result.length; i++) {
            if (result[i] != 0) {
                break;
            }
        }
        return result[i];
    }

    // 思路2： 采用动态规划去解
    // 依次计算0-n每个数字的numSquare数目；
    // n可由之前某个数字加上一个新的完全平方数组成，由于前面数字和新完全平方数都未知，我们只能一个一个地去计算和接近，
    // 最坏情况就是 n-1 加上 1（1为完全平方数），对应位置计数加一
    // dp[i] 表示组成i最少需要的完全平方数数量。
    // 时间复杂度：n*sqrt(n)
    //
    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i <= n; i++) {
            for(int j = 1; i + j*j <= n;j++) {
                dp[i + j*j] = Math.min(dp[i + j*j], dp[i] + 1);
            }
        }
        return dp[n];
    }

    // 及其低效；宽度优先思想？
    // TODO: 时间复杂度度是多少
    public int numSquares3(int n) {
        int result = n, num = 2;
        while (num * num <= n) {
            int temp1 = n/(num * num), temp2 = n % (num * num);
            result = Math.min(result, temp1 + numSquares3(temp2));
            num++;
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(numSquares(48));
    }
}
