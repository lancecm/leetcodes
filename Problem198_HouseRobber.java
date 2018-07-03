package LeetCode;

import org.junit.Test;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/2
 * @Description:
 * 198
 * Easy
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
 *
 *
 */
public class Problem198_HouseRobber {
    // 尝试1： Brute force 超时
    public int rob(int[] nums) {
        return helper(nums, nums.length - 1, -2, 0);
    }

    public int helper(int[] nums, int n, int index, int sum) {
        if(index == n || index + 1 == n) return sum;
        if(index + 2 == n) {
            sum += nums[index + 2];
            return sum;
        }
        int skip1 = helper(nums, n, index + 2, sum + nums[index + 2]);
        int skip2 = helper(nums, n, index + 3, sum + nums[index + 3]);
        return Math.max(skip1, skip2);
    }

    // 尝试2： 动态规划
    public int rob2(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = Math.max(dp[1], nums[2] + dp[0]);
        for (int i = 3; i < nums.length; i++) {
            dp[i] = Math.max(dp[i], nums[i] + dp[i-2]);
            dp[i] = Math.max(dp[i], nums[i] + dp[i-3]);
        }
        return Math.max(dp[nums.length-1], dp[nums.length-2]);
    }

    // 解法1：别人的写法，用的2D数组
    public int rob3(int[] nums) {
        int[][] dp = new int[nums.length + 1][2];
        for(int i = 1; i <= nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i-1][0] + nums[i-1];
        }
        return Math.max(dp[nums.length][0], dp[nums.length][1]);
    }

    // 解法2：观察解法1可知，每次只需判断2个数就好了，进一步转换。
    public int rob4(int[] nums) {
        int preRob = 0; // Rob previous house's precious amount.
        int preNot = 0; // Un-robbed amount.
        for(int n : nums) {
            int tmp = preNot;
            preNot = Math.max(preRob, preNot); // Compare skip 1 and skip 2
            preRob = tmp + n; // Previous must be un-robbed, just add up new precious.
        }
        return Math.max(preNot, preRob);
    }

    // 解法3：
    public int rob5(int[] nums) {
        int prev = 0, cur = 0;
        for (int n : nums) {
            int tmp = cur;
            cur = Math.max(prev + n, cur);
            prev = tmp;
        }
        return cur;
    }

    @Test
    public void test() {
        int[] nums = new int[] {1,2,3,1,2,4,33};
        System.out.println(rob(nums));
        System.out.println(rob2(nums));
        System.out.println(rob3(nums));
        System.out.println(rob4(nums));
        System.out.println(rob5(nums));
    }
}
