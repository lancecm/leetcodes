package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/23
 * @Description:
 *
 * 136
 * easy
 *
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: [4,1,2,1,2]
 * Output: 4
 *
 * 要求：不适用额外空间
 * 思路: 不能使用map, 也不能使用排序，用异或来解决
 * 两个相同异或 -> 等于0
 * 0与某个数异或 -> 该数
 * 因此如果single number为所有数异或的结果
 */
public class Problem136_SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {4,1,2,1,2};
        Problem136_SingleNumber p = new Problem136_SingleNumber();
        System.out.println(p.singleNumber(nums));
    }
}
