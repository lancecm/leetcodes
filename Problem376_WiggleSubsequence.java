package LeetCode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/14
 * @Description: A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * The entire sequence is a wiggle sequence.
 * <p>
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 * <p>
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * 思路： 动态规划； 贪心算法
 * 复杂度要求： O(n)
 */
public class Problem376_WiggleSubsequence {

    // bottom-up dp
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = dp[0][1] = 1;
        int diff = 0;
        for (int i = 1; i < n; i++) {
            diff = nums[i] - nums[i-1];
            if (diff > 0) {
                dp[i][0] = dp[i - 1][1] + 1;
                dp[i][1] = dp[i - 1][1]; // as previous, no change
            }
            else if (diff < 0){
                dp[i][1] = dp[i - 1][0] + 1;
                dp[i][0] = dp[i - 1][0]; // as previous, no change
            }
            else {
                dp[i][1] = dp[i - 1][1];
                dp[i][0] = dp[i - 1][0];
            }
        }
        return Math.max(dp[n-1][0], dp[n-1][1]);
    }

    // 贪心算法
    // 当序列连续递增递减时，只保留首位末尾元素，才更有可能使得下一个元素成为wiggle subsequence
    public int wiggleMaxLength2(int[] nums) {
        if(nums.length < 2) return nums.length;
        int begin = 0;
        int state = begin;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            switch(state) {
                case 0:
                    if (nums[i - 1] < nums[i]) {
                        state = 1;
                        max++;
                    }
                    else if (nums[i - 1] > nums[i]) {
                        state = 2;
                        max++;
                    }
                    break;
                case 1:
                    if (nums[i - 1] > nums[i]) {
                        state = 2;
                        max++;
                    }
                    break;
                case 2:
                    if (nums[i - 1] < nums[i]) {
                        state = 1;
                        max++;
                    }
                    break;
            }
        }
        return max;
    }

    @Test
    public void test() {
        Problem376_WiggleSubsequence p = new Problem376_WiggleSubsequence();
        int[] nums = new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        int[] nums2 = new int[]{1,8,7,5,6,4,5,6,9};
        Assert.assertEquals(p.wiggleMaxLength(nums2),6);
        Assert.assertEquals(p.wiggleMaxLength(nums),7);
    }
}
