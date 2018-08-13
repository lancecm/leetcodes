package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/8/13
 * @Description:
 * Easy
 *
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 *
 * Example 1:
 *
 * Input: [3,0,1]
 * Output: 2
 * Example 2:
 *
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
public class Problem268_MissingNumber {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int i : nums) sum = sum + i;
        int nSum = 0;
        for (int i = 1; i <= nums.length; i++) {
            nSum = nSum + i;
        }
        return nSum - sum;
    }

    // 方法一：和的相减
    public int missingNumber3(int[] nums) {
        int sum = (1 + nums.length) * nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
        }
        return sum;
    }

    // 方法二：异或操作
    public int missingNumber2(int[] nums) {
        int xor = nums.length;
        for (int i = 0; i < nums.length; i++) {
            xor = xor ^ i ^ nums[i];
        }
        return xor;
    }
}
