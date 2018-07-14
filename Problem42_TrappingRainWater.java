package LeetCode;

import org.junit.Test;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/14
 * @Description:
 * 42
 * Hard
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * 思路：维护左右两个指针，代表左右最高柱子的高度，根据短的那一端向高的那一端靠拢
 * 过程中更新左右的柱子，并计算中间的蓄水量（max-当前高度）
 * 类似题目：Largest Rectangle in Histogram
 * Keep track of the maximum height from both forward directions backward directions, call them left max and right max;
 * O_t: O(n)
 * O_s: O(1)
 *
 */
public class Problem42_TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int result = 0;
        int l = 0;
        int r = height.length - 1;
        int maxL = height[l];
        int maxR = height[r];
        while(l < r) {
            maxL = Math.max(maxL, height[l]);
            maxR = Math.max(maxR, height[r]);
            if (maxL < maxR) { // left is smaller; amount based on left
                result += maxL - height[l];
                l++;
            }
            else{
                result += maxR - height[r];
                r--;
            }
        }
        return result;
    }

    @Test
    public void test() {
        int[] nums = new int[] {0,0,1,0,1};
        System.out.println(trap(nums));
    }
}
