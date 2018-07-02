package LeetCode;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/1
 * @Description: 494
 * 中
 * <p>
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * <p>
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * <p>
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * Note:
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 * <p>
 * 这道题包罗万象……
 */
public class Problem494_TargetSum {
    // 尝试0：
    // 暴力BFS 超时了
    // 时间复杂度： O(2^n)
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        Queue<Integer> queue = new LinkedList<>(); // BFS Structure
        queue.offer(nums[0]);
        queue.offer(-nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int tmp = nums[i];
                int top = queue.poll();
                queue.offer(tmp + top);
                queue.offer(-tmp + top);
            }
        }
        int result = 0;
        while (!queue.isEmpty()) {
            result += (queue.poll() == S ? 1 : 0);
        }
        return result;
    }

    // 解法1：
    // Brute force recursion
    int count = 0;

    public int findTargetSumWays2(int[] nums, int S) {
        helper(nums, 0, 0, S);
        return count;
    }

    public void helper(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (sum == S) count++;
        } else {
            helper(nums, i + 1, sum + nums[i], S);
            helper(nums, i + 1, sum - nums[i], S);
        }
    }

    // 解法2：重点关注
    // Recursion with memoization DFS
    // For each recursion level, we found there are lots of identical current sums,
    // which means identical outcome as the former one. Use memoization to store results.
    // ** since the range of sum is [-1000, 1000], for each recursion level, use int [][2001]
    // eg:
    //      -1(-1)        1(1)
    //  -1(-2) +1(0)  -1(0) + 1 (2)
    // ......
    // Time: O(l*n) l: range of sum
    // Explanation: The memo array of size l*n have been filled only once.
    public int findTargetSumWays3(int[] nums, int S) {
        int[][] memo = new int[nums.length][2001];
        for (int[] row : memo) Arrays.fill(row, Integer.MIN_VALUE);
        return calculate(nums, 0, 0, S, memo);
    }

    public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
        if (i == nums.length) {
            if (sum == S) return 1;
            else return 0;
        } else {
            if (memo[i][sum + 1000] != Integer.MIN_VALUE) {
                return memo[i][sum + 1000]; // if already calculated
            }
            int tmp1 = calculate(nums, i + 1, sum + nums[i], S, memo);
            int tmp2 = calculate(nums, i + 1, sum - nums[i], S, memo);
            memo[i][sum + 1000] = tmp1 + tmp2; // all possible kinds in this stage
            return memo[i][sum + 1000];
        }
    }

    // 解法3 2D DP
    // dp[i][j] i: index of nums, j: sum;
    // d[i][j]: how many ways of the sum j calculated by numbers nums[0] -> nums[i]
    // Time: O(n)*2001
    public int findTargetSumWays4(int[] nums, int S) {
        if (S > 1000) return 0; // 题意要求不超过1000
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1; // if number is 0
        for (int i = 1; i < nums.length; i++) {
            for (int j = -1000; j <= 1000; j++) {
                int pre = dp[i - 1][j + 1000];
                if (pre > 0) { // if have way to that sum
                    dp[i][j + nums[i] + 1000] += pre;
                    dp[i][j - nums[i] + 1000] += pre;
                }
            }
        }
        return dp[nums.length - 1][S + 1000];
    }


    // 解法4 1D dp （也可以写成2D）
    // 改变防止溢出的策略
    public int findTargetSumWays5(int[] nums, int S) {
        int sum = 0;
        for(int i : nums) sum += i;
        if (S > sum || S < -sum) return 0;
        int[] dp = new int[2 * sum +1];
        dp[sum] = 1;
        for(int i = 0; i < nums.length; i++) {
            int[] tmp = new int[2 * sum +1];
            for (int j = 0; j < 2 * sum +1; j++) {
                int pre = dp[j];
                if (pre > 0) { // if have way to that sum
                    tmp[j + nums[i]] += pre;
                    tmp[j - nums[i]] += pre;
                }
            }
            dp = tmp;
        }
        return dp[S+sum]; // dp以sum作为中点
    }

    // 解法5： Change it to a subset sum problem!!!
    // P - N = T
    // P + N + p - N = T + P + N
    // 2P = T + P + N
    // P = (T + SUM) / 2
    // -----> target = (target + sum) / 2, find how many ways of subset sum of nums[] equals target
    public int findTargetSumWays6(int[] nums, int S){
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum < S || (sum + S) % 2 > 0) return 0;
        return subsetSum(nums, (sum + S) / 2);
    }

    public int subsetSum(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int n : nums) {
            for (int i = target; i >= n; i--) {
                dp[i] = dp[i] + dp[i - n]; // core code...
            }
        }
        return dp[target];
    }

    @Test
    public void test() {
        int[] nums = {2, 20, 24, 38, 44, 21, 45, 48, 30, 48, 14, 9, 21, 10, 46, 46, 12, 48, 12, 38};
        System.out.println(findTargetSumWays(nums, 48)); // 5401
        System.out.println(findTargetSumWays2(nums, 48));
        System.out.println(findTargetSumWays3(nums, 48));
        System.out.println(findTargetSumWays4(nums, 48));
        System.out.println(findTargetSumWays5(nums, 48));
        System.out.println(findTargetSumWays6(nums, 48));
    }

}
