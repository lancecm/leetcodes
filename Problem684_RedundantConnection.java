package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/7/25
 * @Description:
 * 684
 * Medium
 *
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 *   1
 *  / \
 * 2 - 3
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 *
 * Update (2017-09-26):
 * We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph. For the directed graph follow up please see Redundant Connection II). We apologize for any inconvenience caused.
 *
 * 思路：
 * 典型union find题目，在union find过程中如果存在一条edge的两个vertext存在于同一棵树中，则证明形成了环，返回此边即可
 */
public class Problem684_RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[2001];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i; // For each union set, set parent as itself.
        }
        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            if (find(parent, v1) == find(parent, v2)) return edge; // if we find two vertext is in the same set, it means that there's a loop, so the current edge is the redundant edge
            else {
                parent[find(parent, v1)] = find(parent, v2); // set root of v1's group is the same with v2's group
            }
        }
        return new int[2];
    }

    // find the root of a vertext
    private int find(int[] parent, int v) {
        if (parent[v] == v) return v;
        else return find(parent, parent[v]);
    }
}
