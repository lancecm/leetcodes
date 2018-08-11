package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/11
 * @Description:
 * 中
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 *
 * Example:
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output:  [1,2,4,7,5,3,6,8,9]
 * Explanation:
 *
 * Note:
 * The total number of elements of the given matrix will not exceed 10,000.
 *
 * 思路：关键是算出下一步的位置：
 * 方向一共变换m+n-1次（m+n-1次链接线），每个方向都可以根据一个方位坐标确认另一个方位坐标
 * i = k + 1 - j; 右上方向：下一次改左下方向，j加一或者不变，i随着j变
 * j = k + 1 - i; 左下方向：下一次改右上方向，i加一或者不变，j随着i变
 */
public class Problem498_DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m * n];
        int i = 0,  j= 0, index = 0, di = 0; // 0 : start from up right ; 1 : start form down left
        for (int k = 0; k < m + n; k++) {
            if (di == 0) {
                while (i >= 0 && j < n) {
                    res[index++] = matrix[i--][j++];
                }
                if (j == n) j = n - 1;
                i = k + 1 - j;
                di = 1;
            }
            else {
                while (i < m && j >= 0) {
                    res[index++] = matrix[i++][j--];
                }
                if (i == m) i = m - 1;
                j = k + 1 - i; // Note this usage!
                di = 0;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] res = findDiagonalOrder( new int[][] {{1,2,3},{4,5,6},{7,8,9}}
        );
        for (int i : res) System.out.println(i);
    }
}
