package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Srunkyo
 * @Date: 2018/7/26
 * @Description:
 * 753
 * Hard
 *
 * There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.
 *
 * You can keep inputting the password, the password will automatically be matched against the last n digits entered.
 *
 * For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.
 *
 * Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.
 *
 * Example 1:
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 * Example 2:
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 * Note:
 * n will be in the range [1, 4].
 * k will be in the range [1, 10].
 * k^n will be at most 4096.
 *
 *
 * 思路：
 * 欧拉图
 * 详见题解
 */
public class Problem753_CrackingTheSafe {
    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int total = (int) (Math.pow(k, n));
        for (int i = 0; i < n; i++) sb.append('0');

        Set<String> visited = new HashSet<>();
        visited.add(sb.toString());

        dfs(sb, total, visited, n, k);
        return sb.toString();
    }

    /*
     * 思路：
     * 总共共有k^n种Permutation，生成一个hashset，用来判断最终结果是否能包含全部permutation
     * 画图，每个vertext表示一种permutation，若一种permutaion A和另一种permutation B仅相差一个字符，则在图上存在一条A到B的边；每个节点存在到自身的环
     * 根据图论证明，一定存在一条路径，可以从A点出发经过所有不同路径回到A点。（咋证明？??）
     * 采用DFS搜索该条路径（Back Tracing）
     */
    private boolean dfs(StringBuilder sb, int goal, Set<String> visited, int n, int k) {
        if (visited.size() == goal) return true;
        String prev = sb.substring(sb.length() - n + 1, sb.length());
        for (int i = 0; i < k; i++) {
            String next = prev + i;
            if (!visited.contains(next)) {
                visited.add(next);
                sb.append(i);
                if (dfs(sb, goal, visited, n, k)) return true;
                else {
                    visited.remove(next);
                    sb.delete(sb.length() - 1, sb.length());
                }
            }
        }
        return false;
    }

    /*
    https://leetcode.com/articles/cracking-the-safe/
     */
    Set<String> seen;
    StringBuilder ans;
    public String crackSafe2(int n, int k) {
        if (n == 1 && k == 1) return "0";
        seen = new HashSet<>();
        ans = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) sb.append("0");
        String start = sb.toString();

        helper(start, k); // every time we pass the prefix
        ans.append(start);
        return new String(ans);
    }

    private void helper(String pre, int k) {
        for (int x = 0; x < k; x++) {
            String newS = pre + x;
            if (!seen.contains(newS)) {
                seen.add(newS);
                helper(newS.substring(1), k);
                ans.append(x);
            }
        }
    }



}
