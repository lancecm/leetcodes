package LeetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/2
 * @Description:
 * 22
 * 中
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 时间复杂度：卡特兰数 （n+1）C(n,2n)
 */
public class Problem22_GenerateParentheses {
    // Try1: 20%
    // Brute force + cut (剪的还不够干净) 25%
    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        helper(result, n * 2, 0, 0, "");
        return result;
    }

    private void helper(List<String> result, int n, int left, int right, String cur) {
        if (right > left || left > n/2 || right > n/2) return;
        if (left + right == n) {
            result.add(cur);
            return;
        }
        helper(result, n, left + 1, right, cur + '(');
        helper(result, n, left, right + 1, cur + ')');
    }

    // Solve1:
    // BackTracing 快一些 84%
    // 优化： use char[] rather than string.
    public List<String> generateParenthesis2(int n) {
        List<String> result = new LinkedList<>();
        helper(result, n, 0, 0, "");
        return result;
    }

    private void helper2(List<String> result, int n, int left, int right, String cur) {
        if (left + right == n * 2) {
            result.add(cur);
            return;
        }
        if (left < n)  helper2(result, n, left + 1, right, cur + '(');
        if (right < left) helper2(result, n, left, right + 1, cur + ')');
    }

    // Sovle2:
    // Iterative method 52%
    /* 思路：
    f(0): ""

    f(1): "("f(0)")"

    f(2): "("f(0)")"f(1), "("f(1)")"

    f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"

    So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(i)")"f(n-1-i) ... "(f(n-1)")"
     */
    public List<String> generateParenthesis3(int n) {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList("")); // 生成只读的单一元素
        for (int i = 1; i<= n; i++) {
            final List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (final String first: lists.get(j)) {
                    for (final String second: lists.get(i - 1 - j)) {
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            lists.add(list);
        }
        return lists.get(lists.size() - 1);
    }

    @Test
    public void test() {
        generateParenthesis3(3);
    }

}
