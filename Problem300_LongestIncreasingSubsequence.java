package LeetCode;

import org.junit.Test;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/7
 * @Description:
 * 300
 * Medium
 *
 * 最大递增序列的长度
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 *
 */
public class Problem300_LongestIncreasingSubsequence {

    // O(n^2) TODO: O(nlogn)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0 ) return 0;
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        for(int i = 1; i < len; i++) {
            int tmp = nums[i];
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (tmp > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 1;
        for (int i = 0; i < len; i++) max = Math.max(max, dp[i]);
        return max;
    }

    @Test
    public void test() {
        int[] nums = new int[] {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }
}
