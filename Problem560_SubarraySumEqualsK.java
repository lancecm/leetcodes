package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/13
 * @Description:
 * 560
 * 中
 *
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class Problem560_SubarraySumEqualsK {

    // Brute force
    // 求从i到j的和
    public int subarraySum1(int[] nums, int k) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) result++;
            }
        }
        return result;
    }

    // Use HashMap O(N)
    // SUM[i,j] = SUM[0, j] - SUM[0, i - 1]
    public int subarraySum2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // <pre sum, kinds of this pre sum>
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            res += map.getOrDefault(sum - k, 0); // if pre sum == sum - k, this means it has a valid substring
            map.put(sum, map.getOrDefault(sum, 0) + 1); // update presum
        }
        return res;
    }
}
