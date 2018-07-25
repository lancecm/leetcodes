package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/7/25
 * @Description:
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 *
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 *
 *
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 *
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 *
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 *
 *
 * Note:
 *
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 *
 * 思路：DP
 * 设置一个需求map，存储在每一格时骑士所需要的最小生命值（至少为1）
 * 假设我们现在到达了公主这边，由于这格是-5，因此在到达此格必须至少有6滴血
 * 走到这格的可能性有两种，一种是上面的格走下来，一种是左边的格子走向右侧
 * 从右下到左上生成need map, 则每次选择最小需求下一路径，并加上本格的需求。如果需求为负，则说明骑士在此格最小生命值为1。
 * 不断生成，直到推演到骑士出发的位置。
 *
 * 注意dummy nodes的使用 --> 非常有借鉴意义
 * 关注最后一个节点的计算
 */
public class Problem174_DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 0) return 0;
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] map = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) map[i][n] = Integer.MAX_VALUE; // right most col
        for (int i = 0; i < n; i++) map[m][i] = Integer.MAX_VALUE; // last row

        map[m][n - 1] = 1; // dummy node on the bottom
        map[m - 1][n] = 1; // dummy node on the right side
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j>=0; j--) {
                int need = Math.min(map[i + 1][j], map[i][j + 1]) - dungeon[i][j];
                map[i][j] = need <= 0 ? 1 : need;
            }
        }
        return map[0][0];
    }

    @Test
    public void test() {
        int[][] a = new int[][]{
                {-2,-3,3},
                {-5,-10,1},
                {10,30,-5}};
        System.out.println(calculateMinimumHP(a));
    }
}
