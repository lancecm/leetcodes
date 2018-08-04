package LeetCode;

import java.util.Arrays;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/17
 * @Description:
 * 16
 * 中
 *
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * Example:
 *
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * 思路：同3sum一样，夹逼法。
 */
public class Problem16_3SumCloest {
    public int threeSumCloest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) { // avoid duplicate
                int lo = i + 1, hi = nums.length - 1;
                while (lo < hi) {
                    int tmpSum = nums[lo] + nums[hi] + nums[i];
                    int tmpDiff = Math.abs(target - tmpSum);
                    if (tmpSum == target) return target;
                    if (tmpDiff < diff) {
                        diff = tmpDiff;
                        sum = tmpSum;
                    }
                    if (tmpSum < target) {
                        lo++;
                    }
                    if (tmpSum > target) {
                        hi--;
                    }
                }
            }
        }
        return sum;
    }

    // 别人的简化写法：
    public int threeSumCloest2(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                }
                else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }
}
