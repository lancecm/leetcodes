package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/7/29
 * @Description:
 * Easy
 *
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * 思路：
 * 斐波那契数列
 */
public class Problem70_ClimbingStairs {
    public int climbStairs(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        int[] nums = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        return fib(n, nums);
    }

    public int fib(int n, int[] nums) {
        if (n == 0 || n == 1) return 1;
        int step1 = 0;
        int step2 = 0;
        if (nums[n - 1] != 0) step1 = nums[n - 1];
        else {
            nums[n - 1] = fib(n - 1, nums);
            step1 = nums[n - 1];
        }
        if (nums[n - 2] != 0) step2 = nums[n - 2];
        else {
            nums[n - 2] = fib(n - 2, nums);
            step2 = nums[n - 2];
        }
        nums[n] = step1 + step2;
        return step1 + step2;
    }

    public int climbStairs2(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }
        return nums[n];
    }
}
