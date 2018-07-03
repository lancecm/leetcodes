package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/3
 * @Description: 73
 * Medium
 * <p>
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * [1,1,1],
 * [1,0,1],
 * [1,1,1]
 * ]
 * Output:
 * [
 * [1,0,1],
 * [0,0,0],
 * [1,0,1]
 * ]
 * Example 2:
 * <p>
 * Input:
 * [
 * [0,1,2,0],
 * [3,4,5,2],
 * [1,3,1,5]
 * ]
 * Output:
 * [
 * [0,0,0,0],
 * [0,4,5,0],
 * [0,3,1,0]
 * ]
 * Follow up:
 * <p>
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class Problem73_SetMatrixZeroes {
    // Try 1:
    // Straight Forward Solution： 用一个m*n的矩阵作为mask
    // O(mn)
    public void setZeroes(int[][] matrix) {
        if (matrix == null) return;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++)      // mask all the positions that should be placed with 0
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 0) {
                    for (int r = 0; r < m; r++) map[r][j] = 1;
                    for (int c = 0; c < n; c++) map[i][c] = 1;
                }
        for (int i = 0; i < m; i++)     // set 0 according to the map
            for (int j = 0; j < n; j++)
                if (map[i][j] == 1) matrix[i][j] = 0;
    }

    // Try2:
    // Straight forward O(m + n) solution
    // 把更新信息存在两个代表行、列的数组中
    public void setZeroes2(int[][] matrix) {
        if (matrix == null) return;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] rowMask = new int[m];
        int[] colMask = new int[n];
        for(int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 0) {
                    rowMask[i] = 1;
                    colMask[j] = 1;
                }
        // update all rows according to rowMask
        for (int i = 0; i < m; i++)
            if (rowMask[i] == 1)
                for (int j = 0; j < n; j++)
                    matrix[i][j] = 0;
        // update all cols according to colMask
        for (int j = 0; j < n; j++)
            if (colMask[j] == 1)
                for (int i = 0; i < m; i++)
                    matrix[i][j] = 0;
    }

    // Try3:
    // O(1)
    // Using the first row and column as a memory to keep track of all the 0's in the entire matrix.
    public void setZeroes3(int[][] matrix) {
        if (matrix == null) return;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRow = false;
        boolean firstCol = false;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 0) {
                    if (i == 0) firstRow = true;
                    if (j == 0) firstCol = true;
                    matrix[0][j] = 0; // cols that should be set to 0s
                    matrix[i][0] = 0; // rows that should be set to 0s
                }
        for (int i = 1; i < m; i++)   // update rows
            if (matrix[i][0] == 0)
                for (int j = 1; j < n; j++)
                    matrix[i][j] = 0;
        for (int j = 1; j < n; j++)   // update cols
            if (matrix[0][j] == 0)
                for (int i = 1; i < m; i++)
                    matrix[i][j] = 0;
        if (firstRow)                // update firstRow
            for (int j = 0; j < n; j++)
                matrix[0][j] = 0;
        if (firstCol)                // update firstCol
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
    }

}
