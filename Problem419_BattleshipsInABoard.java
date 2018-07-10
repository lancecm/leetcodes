package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/9
 * @Description:
 * 419
 * Medium
 *
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 * At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 * Example:
 * X..X
 * ...X
 * ...X
 * In the above board there are 2 battleships.
 * Invalid Example:
 * ...X
 * XXXX
 * ...X
 * This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
 * Follow up:
 * Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
 *
 * 思路1 （最优）：
 * 这是一道需要智慧的题目。
 * 数军舰即数军舰舰头。其左边上边都是海，即是舰头。
 *
 * 思路2:
 * DFS
 * Flood Fill algorithm （=island number?）
 *
 * 注意：输入不会出现例2的情况。
 */
public class Problem419_BattleshipsInABoard {
    // one pass
    public int countBattleships(char[][] board) {
        if (board == null) return 0;
        int m = board.length;
        int n = board[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 'X') continue;
                if(j > 0 && board[i][j-1] == 'X') continue;
                if(i > 0 && board[i-1][j] == 'X') continue;
                count++;
                /*
                if(board[i][j] =='X' && (i == 0 || board[i-1][j] == '.') && (j==0 || board[i][j-1] == '.')) count++;
                 */
            }
        }
        return count;
    }

    // dfs
    private boolean visited[][];
    int go[][] = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int m;
    private int n;
    void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == '.'||visited[i][j]) return;
        visited[i][j] = true;
        for (int l = 0; l < 4; l++) {
            dfs(board, i + go[l][0], j + go[l][1]); // 向四个方向前进
        }
    }

    public int countBattleships2(char[][] board) {
        if (board == null) return 0;
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X' && !visited[i][j]) {
                    count++;
                    dfs(board, i, j);
                }
            }
        }
        return count;
    }
}
