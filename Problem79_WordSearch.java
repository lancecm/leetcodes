package LeetCode;

import org.junit.Test;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/20
 * @Description: 79
 * 难度：Medium
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 * 初步想法：使用贪心算法，看四周是否有符合条件的。是，则进一步，否则退一步，改变方向;
 * 即向上下左右四个方向进行DFS
 *
 * 7月9日重写：
 * 1. 注意出界判断
 * 2. 注意防止返回原路的方式
 * 3. 有一条路径即可，不用算出所有路径的可能，否则会超时。
 */
public class Problem79_WordSearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        if (m * n < word.length()) return false;
        char[] w = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (exist(board, w, 0, i, j)) return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, char[] word, int index, int i, int j) {
        // base cases
        if (index == word.length) return true; // till the end of string
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word[index])
            return false; // out of bound
        // recursive logic:
        char tmp = board[i][j]; // remember original value
        board[i][j] = '*'; // already visited
        boolean result = exist(board, word, index + 1, i, j - 1)||
                exist(board, word, index + 1, i, j + 1) ||
                exist(board, word, index + 1, i - 1, j) ||
                exist(board, word, index + 1, i + 1, j);
        board[i][j] = tmp; // reset back to origin value
        return result;
    }


    @Test
    public void test2() {
        char[][] b = new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};
        String word = "ABCCE";
        boolean result = exist(b, word);
        System.out.println(result);
    }
}
