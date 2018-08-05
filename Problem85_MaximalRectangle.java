package LeetCode;

import org.junit.Test;

import java.util.Stack;

/**
 * @author Srunkyo
 * @Date: 2018/8/5
 * @Description:
 * Hard
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 * Example:
 *
 * Input:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * Output: 6
 *
 * 思路：参考p84 LargestRectangleHistogram
 * O_s: O(Col)
 * O_t: O(2 * Col * Row)
 */
public class Problem85_MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int[] his = new int[n];
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    his[j] = his[j] + 1;
                }
                else {
                    his[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, maxRectangleHistogram(his, n));
        }
        return maxArea;
    }

    // calculate the maximum histogram area for each row
    private int maxRectangleHistogram(int[] heights, int n) {
        Stack<Integer> st = new Stack<>();
        int max = 0;
        for (int i = 0; i <= n; i++) {
            int h = i == n ? 0 : heights[i];
            if (st.isEmpty() || heights[st.peek()] <= h) {
                st.push(i);
            }
            else {
                // calculate each rectangle area
                int curIndex = st.pop();
                if (st.isEmpty()) {
                    max = Math.max(max, heights[curIndex] * i);
                }
                else {
                    max = Math.max(max, heights[curIndex] * (i - 1 - st.peek()));
                }
                i--; // Don't forget to maintain the origin i
            }
        }
        return max;
    }
}
