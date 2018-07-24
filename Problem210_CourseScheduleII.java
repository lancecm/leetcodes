package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Srunkyo
 * @Date: 2018/7/24
 * @Description:
 * 210
 * 中
 *
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 * Example 2:
 *
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 *
 * 思路：
 * BFS拓扑排序 O（n + m）
 * 入度为0的队列访问顺序为可行解
 */
public class Problem210_CourseScheduleII {
    public int[] findOrder(int numCourse, int[][] prerequisites) {
        int[] in_degree = new int[numCourse];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourse; i++) graph.add(new ArrayList<> ());
        for (int i = 0; i < prerequisites.length; i++) {
            int pre = prerequisites[i][1];
            int cur = prerequisites[i][0];
            graph.get(pre).add(cur);
            in_degree[cur]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourse; i++) if (in_degree[i] == 0) q.offer(i);
        int[] result = new int[numCourse];
        int visited = 0; // control the poniter of result array and count the visited node
        while (!q.isEmpty()) {
            int from = q.poll();
            result[visited++] = from;
            List<Integer> items = graph.get(from);
            for (int item: items) {
                if (--in_degree[item] == 0) {
                    q.offer(item);
                } else if (in_degree[item] < 0) {
                    return new int[0];
                }
            }
        }
        return visited == numCourse ? result : new int[0];
    }
}
