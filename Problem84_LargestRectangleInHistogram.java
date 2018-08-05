package LeetCode;

import java.util.Stack;

/**
 * @author Srunkyo
 * @Date: 2018/8/5
 * @Description:
 * Hard
 *
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 * Example:
 *
 * Input: [2,1,5,6,2,3]
 * Output: 10
 *
 * O_s = O(Stack) worst case: O(n)
 * O_t = O(2n) -> O(n)
 */

public class Problem84_LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> s = new Stack<Integer>(); // store indexes
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : heights[i]);
            if (s.isEmpty() || h >= heights[s.peek()]) { // 遇到上升阶梯，push入
                s.push(i);
            } else {
                // calculate the area
                int tp = s.pop();
                maxArea = Math.max(maxArea, heights[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                // 遇到下降阶梯，每次pop出所有高度小于当前的index，计算面积。
                // i - 1为此次计算最右边的histogram坐标，i - 1 - s.peek() 则可以计算出宽度
                i--; // why? To avoid moving the index we have add into stack next.
            }
        }
        return maxArea;
    }


}
