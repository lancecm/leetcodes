package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/7/28
 * @Description:
 * Medium
 *
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * Example 1:
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * 思路：
 * 首先计算所有数字总加和，不为偶数则不可能平分。
 * 不断增长可能的数字和的情况，并用一个长度为sum+1的数组表示，sum[i]=1表示sum=i是存在的
 * 判断是否存在sum/2
 * 举例：
 * {0}         --- {}
 * {0,1}       --- {1}
 * {0,1,5,6}   --- {1,5}
 * {0,1,5,6,11,12,16,17} --- {1,5,11}   由于出现了11
 * {0,1,5,6,11,12,16,17,21,22} --- {1,5,11,5}
 */
public class Problem416_PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        if (sum % 2 != 0) return false;
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = sum; i >= 0; i--) {
                if (dp[i] == 1) {
                    dp[i + num] = 1;
                }
                if (dp[sum/2] == 1) return true;
            }
        }
        return false;
    }
}
