package LeetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Srunkyo
 * @Date: 2018/8/24
 * @Description:
 * Medium
 * 以邻接表形式给定无向图graph，判断是否为二分图
 * Given an undirected graph, return true if and only if it is bipartite.
 *
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 *
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation:
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 *
 * 思路： 染色 DFS/BFS
 * 0: 未被染色
 * 1: A色
 * -1: B色
 *
 * 二分图满足存在子集A和子集B，使得从A出发的边只能连接到B，B出发的边只能连接到A。
 * 换句话说，A出发的边不能连到A中的元素。B同理，采用染色法，因为相连的边一定是互异的，
 * 如果在不断染色的过程当中，出现冲突的情况，即返回false，全部染色完毕未发现冲突则返回true。
 */
public class Problem785_IsGraphBipartite {
    // DFS
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == 0) {
                if (!validColor(graph, colors, 1, i)) return false;
            }
        }
        return true;
    }

    public boolean validColor(int[][] graph, int[] colors, int color, int node) {
        if (colors[node] != 0) {
            return colors[node] == color;
        }
        colors[node] = color; // paint
        for (int adj : graph[node]) {
            if (!validColor(graph, colors, -color, adj)) return false;
        }
        return true;
    }

    // BFS
    public boolean isBipartitle(int[][] g) {
        int[] colors = new int[g.length];
        for (int i = 0; i < g.length; i++) {
            if (colors[i] == 0) {
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                colors[i] = 1; // 涂上其中一种颜色
                while (!q.isEmpty()) {
                    Integer node = q.poll();
                    for (int adj : g[node]) {
                        if (colors[adj] == colors[node]) {
                            return false;
                        }
                        else if (colors[adj] == 0) { // haven't been colored
                            q.add(adj);
                            colors[adj] = -colors[node];
                        }
                    }
                }
            }
        }
        return true;
    }
}
