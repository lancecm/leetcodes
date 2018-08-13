package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/13
 * @Description:
 * Medium
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 *
 *
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 *
 *
 *
 * Example:
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */
public class Problem11_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int len = right - left;
            int lh = height[left];
            int rh = height[right];
            max = Math.max(max, len * Math.min(lh, rh));
            if (lh > rh) { // leftH > rightH
                right--;
            }
            else if (lh < rh) {
                left++;
            }
            else {
                right--;
                left++;
            }
        }
        return max;
    }

    @Test
    public void test() {
        System.out.println(maxArea(new int[] {1,2,4,3}));
        System.out.println(maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
    }
}
