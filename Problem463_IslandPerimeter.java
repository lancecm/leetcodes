package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/14
 * @Description:
 * 463
 * Easy
 *
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 * Example:
 *
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * Answer: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 *
 * 这是一道找规律题
 */
public class Problem463_IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int line = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int tmp = 0;
                    if (i == 0 || grid[i - 1][j] == 0) tmp++; // up
                    if (j == 0 || grid[i][j - 1] == 0) tmp++; // left
                    if (i == grid.length - 1 || grid[i + 1][j] == 0) tmp++; // right
                    if (j == grid[0].length - 1 || grid[i][j + 1] == 0) tmp++; // down
                    if (tmp == 3) line += 2;
                    else if(tmp == 4) line += 4;
                    grid[i][j] = 0;
                }
            }
        }
        return line;
    }
}
