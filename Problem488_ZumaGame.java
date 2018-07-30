package LeetCode;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * @author Srunkyo
 * @Date: 2018/7/30
 * @Description:
 * Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.
 *
 * Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.
 *
 * Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.
 *
 * Examples:
 *
 * Input: "WRRBBW", "RB"
 * Output: -1
 * Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
 *
 * Input: "WWRRBBWW", "WRBRW"
 * Output: 2
 * Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
 *
 * Input:"G", "GGGGG"
 * Output: 2
 * Explanation: G -> G[G] -> GG[G] -> empty
 *
 * Input: "RBYYBBRRB", "YRBGB"
 * Output: 3
 * Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty
 *
 * Note:
 * You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
 * The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
 * The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
 * Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
 *
 * 思路：
 * DFS + 剪枝
 * TODO： 时间复杂度 空间复杂度
 */
public class Problem488_ZumaGame {
    public int findMinStep(String board, String hand) {
        int[] c = new int[128]; // count the number of appearance of each character in hand
        for (char x: hand.toCharArray()) {
            c[x]++;
        }
        return aux(board, c);
    }

    private int aux(String s, int[] c) {
        if ("".equals(s)) return 0;
        // how many balls should be used
        int res = 2 * s.length() + 1; // if s is constructed by single balls, the maximum balls used should be 2 * len(s); 2 * len(s) means impossible
        for (int i = 0; i < s.length();) {
            int j = i;
            while (j < s.length() && s.charAt(i) == s.charAt(j)) j++;
            int inc = 3 - (j - i); // how many balls should be shot
            if (c[s.charAt(i)] >= inc) { //
                int used = inc <= 0 ? 0 : inc;
                c[s.charAt(i)] -= used;
                int tmp = aux(s.substring(0, j) + s.substring(i), c);
                if (tmp >= 0) res = Math.min(res, used + tmp);
                c[s.charAt(i)] += used; // back trace
            }
            i = j;
        }
        return res == 2 * s.length() + 1 ? -1 : res;
    }

    // 上述解法优化: 直接消除因为连锁反应可以消除的那部分球
    public int findMinStep2(String board, String hand) {
        int[] c = new int[128];
        for (char h : hand.toCharArray()) c[h]++;
        return dfs(board, c);
    }

    // Return the min of balls needed in hand to clear the board.
    // Returns -1 if not possible.
    private int dfs(String board, int[] c) {
        if (board.isEmpty()) return 0;

        int ans = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        while (i < board.length()) {
            while (j < board.length() && board.charAt(i) == board.charAt(j)) j++;
            char color = board.charAt(i);
            int b = 3 - (j - i); // balls needed
            if (c[color] >= b) {
                // remove board[i] ~ board[j-1] and update the board (if has chain action)
                String nb = update(board.substring(0, i) + board.substring(j)); // new string
                c[color] -= b; // update hand
                int r = dfs(nb, c); // steps
                if (r >= 0) ans = Math.min(ans, r + b);
                c[color] += b;
            }
            i = j;
        }
        return ans == Integer.MAX_VALUE ? - 1 : ans;
    }

    // remove all the balls with explode
    private String update(String board) {
        int i = 0;
        while (i < board.length()) {
            int j = i;
            while (j < board.length() && board.charAt(i) == board.charAt(j)) ++j;
            if (j - i >= 3) {
                board = board.substring(0, i) + board.substring(j);
                i = 0;
            } else {
                ++i;
            }
        }
        return board;
    }

}
