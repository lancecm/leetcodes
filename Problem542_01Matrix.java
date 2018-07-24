package LeetCode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Srunkyo
 * @Date: 2018/7/24
 * @Description:
 * 542
 * Medium
 *
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 * Example 1:
 * Input:
 *
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * Output:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * Example 2:
 * Input:
 *
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * Output:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * Note:
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 *
 * 思路：
 * BFS
 * TODO: DFS etc. faster options?
 */
public class Problem542_01Matrix {
    // BFS 25%
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<int[]> q = new LinkedList<> ();
        // add all 0s to queue and set all 1s to max value
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    q.add(new int[] {i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; // spread from 4 directions
        while (!q.isEmpty()) {
            int[] t = q.poll();
            for (int[] d : dir) {
                int x = t[0] + d[0];
                int y = t[1] + d[1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (matrix[t[0]][t[1]] + 1 < matrix[x][y]) {
                        matrix[x][y] = matrix[t[0]][t[1]] + 1;
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }
        return matrix;
    }

    @Test
    public void test() {
        int[][] matrix = {{0,0,0},{0,1,0},{1,1,1}};
        updateMatrix(matrix);
    }
}
