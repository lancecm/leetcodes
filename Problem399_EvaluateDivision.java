package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Srunkyo
 * @Date: 2018/8/13
 * @Description:
 * Medium
 *
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 *
 * 思路：
 * https://www.youtube.com/watch?v=UwpvInpgFmo
 * Graph题
 * Graph + DFS/
 * Union-Find
 *
 * JAVA8: computeIfAbsent： 如果map里没有这个key，那么就按照后面的这个function添加对应的key和value
 * 如果要这个key，那么就不添加
 *
 */
public class Problem399_EvaluateDivision {
    // 做法1： 图的深度有限搜索。构建邻接图，并在图为每隔queries找路径，不存在则返回-1.0
    // O_s : O(e + q * e)
    // O_t : O(e)
    Map<String, HashMap<String, Double>> g =  new HashMap<>();
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        for (int i = 0; i < equations.length; ++i) {
            String x = equations[i][0];
            String y = equations[i][1];
            double k = values[i];
            g.computeIfAbsent(x, l -> new HashMap<String, Double>()).put(y, k);
            g.computeIfAbsent(y, l -> new HashMap<String, Double>()).put(x, 1.0 / k);
        }

        double[] ans = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String x = queries[i][0];
            String y = queries[i][1];
            if (!g.containsKey(x) || !g.containsKey(y)) ans[i] = - 1.0;
            else ans[i] = divide(x, y, new HashSet<String>());
        }
        return ans;
    }

    private double divide(String x, String y, Set<String> visited) {
        if (x.equals(y)) return 1.0;
        visited.add(x);
        for (String n : g.get(x).keySet()) { // 通过中间节点，找到一个到y的路径
            if (visited.contains(n)) continue;
            visited.add(n);
            double d = divide(n, y, visited);
            if (d > 0) return d * g.get(x).get(n); // x/n * n/y
        }
        return -1.0;
    }

    // 做法2： 并查集
    // O_s : O(e + q)
    // O_t : O(e)
    class Node {
        public String parent;
        public double ratio;
        public Node(String parent, double ratio) {
            this.parent = parent;
            this.ratio = ratio; // 与祖先的比值关系
        }
    }

    class UnionFindSet {
        private Map<String, Node> parents = new HashMap<>(); // 节点-> parent和parent的关系 A -> B 2.0  ---> A = 2.0 * B

        public Node find(String s) { // 寻找根节点
            if (!parents.containsKey(s)) return null;
            Node n = parents.get(s);
            if (!n.parent.equals(s)) { // 每次查找都保证s的parent指向的是最原始的根节点（路径压缩）
                Node p = find(n.parent);
                n.parent = p.parent;
                n.ratio *= p.ratio;
            }
            return n;
        }

        public void union(String s, String p, double ratio) {
            boolean hasS = parents.containsKey(s);
            boolean hasP = parents.containsKey(p);
            if (!hasS && !hasP) { // 两节点都不在森林里
                parents.put(s, new Node(p, ratio)); // A -> B, A/B
                parents.put(p, new Node(p, 1.0)); // B -> B, B/B
            } else if (!hasP) { // 如果某一个节点在森林里，添加另一个节点
                parents.put(p, new Node(s, 1.0 / ratio));
            } else if (!hasS) {
                parents.put(s, new Node(p, ratio));
            } else { // 两个节点都在森林里，查它们的祖先，如果祖先不相同做merge，A的祖先指向B的祖先
                Node rs = find(s);
                Node rp = find(p);
                rs.parent = rp.parent;
                rs.ratio = ratio / rs.ratio * rp.ratio; // 需要自己推算一下
            }
        }
    }

    public double[] calcEquation2(String[][] equations, double[] values, String[][] queries){
        UnionFindSet u = new UnionFindSet();
        for (int i = 0; i < equations.length; i++) {
            u.union(equations[i][0], equations[i][1], values[i]);
        }
        double[] ans = new double[queries.length];

        for (int i = 0; i < queries.length; i++) {
            Node rx = u.find(queries[i][0]);
            Node ry = u.find(queries[i][1]);
            if (rx == null || ry == null || !rx.parent.equals(ry.parent)) // 如果rx和ry其中一个不存在，或者rx和ry之间不存在路径的话，则无解
                ans[i] = -1.0;
            else
                ans[i] = rx.ratio / ry.ratio;
        }
        return ans;
    }
}
