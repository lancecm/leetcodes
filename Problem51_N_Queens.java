package LeetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/13
 * @Description:
 * 51
 * N-Queens
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 *
 * 思路：
 * DFS + BackTracing
 * 关键： 如何判断invalid还是valid （由于Queen不能在同一行上，每次固定住行，能减少很多判断）
 */
public class Problem51_N_Queens {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> result = new ArrayList<>();
        dfs(board, 0, result); // 按列递归，
        return result;
    }

    private void dfs(char[][] board, int colIndex, List<List<String>> res) {
        if (colIndex == board.length) {
            res.add(construct(board));
            return;
        }
        for(int i = 0; i < board.length; i++) {
            if (validate(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(board, colIndex + 1, res);
                board[i][colIndex] = '.';
            }
        }

    }

    private boolean validate(char[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < col; j++) {
                // row - col == i - j (左上右下) 增减幅度相同
                // row + col == i + j (右上左下) 加一减一恰好抵消
                if (board[i][j] == 'Q' && (row == i || row - col == i - j|| row + col == i + j)) {
                    return false;
                }
            }
        }
        return true;
    }

    // construct solution
    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            res.add(String.valueOf(board[i]));
        }
        return res;
    }

    public void print(List<List<String>> res) {
        for (List l : res){
            for (int i = 0; i < l.size(); i++) {
                System.out.println(l.get(i));
            }
            System.out.println();
        }
    }

    @Test
    public void test() {
        List<List<String>> result = solveNQueens(5);
        print(result);
    }
}
