package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/7/24
 * @Description:
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 *
 * Example:
 *
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 * Note:
 *
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 *
 * 思路：
 * bit operation
 * 首先对数字进行异或求和，得到sum
 * 找到sum中为1的一位，可知两个single number的该位一定是一个为0，一个为1
 * 则将此位为0的分为一组，为1的分为一组，各自异或，则可得到两个数字
 */
public class Problem260_SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int[] result = new int[2];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum ^= nums[i];
        int mask = 1;
        while ((sum & mask) == 0)
            mask = mask << 1; // find a position that equals to 1
        int left = 0, right = 0;
        for(int i = 0; i < nums.length; i++) {
            if ((nums[i] & mask) == 0) left ^= nums[i];
            else right ^= nums[i];
        }
        result[0] = left;
        result[1] = right;
        return result;
    }

    @Test
    public void test() {
        int[] nums = new int[] {1,2,1,3,2,5};
        singleNumber(nums);
    }
}
