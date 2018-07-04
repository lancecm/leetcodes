package LeetCode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/4
 * @Description:
 * 200
 * 中
 * 
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * Output: 1
 * Example 2:
 * <p>
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * Output: 3
 * <p>
 *
 * 思路: 回溯法
 * 1. DFS
 * 2. BFS
 * 【回溯法】
 * 回溯法可以被认为是一个有过剪枝的DFS过程
 */
public class Problem200_NumberOfIslands {

    // Try 1:
    // 回溯法 30%
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == '1')
                    count += helper(grid, i, j, i, j);
        return count;
    }

    private int helper(char[][] grid, int i, int j, int si, int sj) {
        if (grid[i][j] == '0' || grid[i][j] == '*') return 0;
        if (grid[i][j] == '1' && j < grid[0].length - 1 && grid[i][j + 1] == '1')
            helper(grid, i, j + 1, si, sj); // move right
        grid[i][j] = '2';
        if (grid[i][j] == '2' && i < grid.length - 1 && grid[i + 1][j] == '1')
            helper(grid, i + 1, j, si, sj);    // move down
        grid[i][j] = '3';
        if (grid[i][j] == '3' && j > 0 && grid[i][j - 1] == '1')
            helper(grid, i, j - 1, si, sj);    // move left
        grid[i][j] = '4';
        if (grid[i][j] == '4' && i > 0 && grid[i - 1][j] == '1')
            helper(grid, i - 1, j, si, sj);
        grid[i][j] = '*';                     // move up
        if (i == si && j == sj) {
            return 1;
        } else return 0;
    }

    // Solution 1: 别人的简洁代码, 效率也上去了。。
    // TIME：O(m*n)
    private int m;
    private int n;

    public int numIslands2(char[][] grid) {
        int count = 0;
        m = grid.length;
        if (m == 0) return 0;
        n = grid[0].length;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '1') {
                    DFS(grid, i, j);
                    count++;
                }
        return count;
    }

    private void DFS(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFS(grid, i, j + 1); // right
        DFS(grid, i + 1, j); // down
        DFS(grid, i, j - 1); // left
        DFS(grid, i - 1, j); // up
    }

    // Solution 2: BFS写法！
    // 效率有点低
    public int numIslands3(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == '1') {
                    count++;
                    BFS(grid, i, j);
                }
        return count;
    }

    private void BFS(char[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        grid[x][y] = '0';
        Queue<Integer> queue = new LinkedList<>();
        int index = x * n + y;
        queue.offer(index);
        while (!queue.isEmpty()) {
            index = queue.poll();
            int i = index / n;
            int j = index % n;
            if (i > 0 && grid[i - 1][j] == '1') { // search upward
                queue.offer((i - 1) * n + j);
                grid[i - 1][j] = '0';
            }
            if (i < m - 1 && grid[i + 1][j] == '1') { // search down
                queue.offer((i + 1) * n + j);
                grid[i + 1][j] = '0';
            }
            if (j > 0 && grid[i][j - 1] == '1') { // search left
                queue.offer(i * n + j - 1);
                grid[i][j - 1] = '0';
            }
            if (j < n - 1 && grid[i][j + 1] == '1') { // search right
                queue.offer(i * n + j + 1);
                grid[i][j + 1] = '0';
            }
        }
    }

    /*
    ---------------TEST---------------------
     */
    char[][] map = new char[][]{
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}};
    char[][] map2 = new char[][]{};
    char[][] map3 = new char[][]{
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}};
    char[][] map4 = new char[][]{
            {'1'},
            {'0'},
            {'1'},
            {'0'},
            {'1'},
            {'1'}};

    @Test
    public void test() {
        System.out.println(numIslands(map));
        System.out.println(numIslands(map2));
        System.out.println(numIslands(map3));
    }

    @Test
    public void test2() {
        System.out.println(numIslands2(map));
        System.out.println(numIslands2(map2));
        System.out.println(numIslands2(map3));
    }

    @Test
    public void test3() {
        System.out.println(numIslands3(map));
        System.out.println(numIslands3(map2));
        System.out.println(numIslands3(map3));
        System.out.println(numIslands3(map4));
    }

}
