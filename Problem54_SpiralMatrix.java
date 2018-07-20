package LeetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Srunkyo
 * @Date: 2018/7/20
 * @Description:
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * Example 1:
 *
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 *
 * Input:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 思路：看似简单但是一点也不好写！很容易错啊
 * 每访问完一行或者一列，则缩短下次所需要访问的范围
 */
public class Problem54_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return result;
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (true) {
            for (int i = left; i <= right; i++) result.add(matrix[top][i]);
            top++;
            if (left > right || top > bottom) break;
            for (int i = top; i <= bottom; i++) result.add(matrix[i][right]);
            right--;
            if (left > right || top > bottom) break;
            for (int i = right; i >= left; i--) result.add(matrix[bottom][i]);
            bottom--;
            if (left > right || top > bottom) break;
            for (int i = bottom; i >= top; i--) result.add(matrix[i][left]);
            left++;
            if (left > right || top > bottom) break;
        }
        return result;
    }

    @Test
    public void test() {
//        int[][] nums = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
        int[][] nums = new int[][] {{1,2,3,4}, {5,6,7,8},{9,10,11,12},{13,14,15,16}};
//        int[][] nums = new int[][] {{1,2,3}};
        List<Integer> result = spiralOrder(nums);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
