package LeetCode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/28
 * @Description: 10
 * Hard
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * <p>
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 * <p>
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not isMatch the entire string "aa".
 * Example 2:
 * <p>
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * <p>
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 * <p>
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 * Example 5:
 * <p>
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 * <p>
 * 思路：
 * 1. 如果p中第二个字符不是*：
 * 1.1. 如果第一个字符，s符合p，s和p各自前进一步
 * 1.2. 如果p字符为. 代表任意一个字符，s和p各自前进一步
 * 2. 如果模式中第二个字符是*时，可以有两种不同的匹配方式:
 * 2.1 在模式上向后移动两个字符，相当于*和其前面的字符被忽略，表示匹配0个前面的字符
 * 2.2 如果s p两个字符串第一个字符相匹配，则在字符串上向后移动一个字符：
 * 2.2.1 在p上向后移动两个字符，表示匹配1个字符
 * 2.2.2 保持不变，表示匹配n个字符
 */
public class Problem10_RegularExpressionMatching {
    public boolean isMatch(String str, String pattern) {
        if (str == null || pattern == null) return false;
        return matchCore2(str, pattern);
    }

    private boolean matchCore(String s, String p) {
        if (p.isEmpty()) return s.isEmpty(); // 如果p为空，s需要为空，才能达成匹配
        // 如果p的长度等于1或者p的长度大于1但是第二个字符不是*
        if (p.length() == 1 || p.charAt(1) != '*') { // p的第二个字符不是* 或者只剩下一个字符了
            if (s.isEmpty() || p.charAt(0) != '.' && p.charAt(0) != s.charAt(0)) return false;
            else return matchCore(s.substring(1), p.substring(1));
        }
        // 如果p的长度大于1且第二个字符是*;
        // 如果第一个字符相匹配
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (matchCore(s, p.substring(2))) return true; //无视p中两个字符，匹配1次
            // 或者匹配n次
            s = s.substring(1); //s向后移动一位；
        }
        // 如果第一个字符不匹配
        return matchCore(s, p.substring(2)); // 无视p中两个字符
    }

    // 使用剑指offer上的答案（测试显示会超时，放弃）
    private boolean matchCore2(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        // 如果剩下2个以上待匹配
        if (p.length() >= 2) {
            // 如果第二个字符是*
            if (p.charAt(1) == '*') {
                // 如果第一个字符匹配
                if (s.length() != 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')) {
                    boolean res1 = matchCore2(s.substring(1), p.substring(2));
                    boolean res2 = matchCore2(s.substring(1), p);
                    boolean res3 = matchCore2(s, p.substring(2));
                    return res1 // 匹配一个字符
                            || res2  // 匹配n个字符
                            || res3; // 忽略0个字符，忽略*和前一个模式字符
                }
                // 否则忽略
                else return matchCore2(s, p.substring(2));
            }
            // 如果第二个字符不是*
            if (s.length() != 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'))
                return matchCore2(s.substring(1), p.substring(1));
        }
        // 如果只剩下1个待匹配
        else {
            if (s.length() != 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'))
                return matchCore2(s.substring(1), p.substring(1));
        }
        return false;
    }

    /*
    动态规划：
     */
    public boolean isMatchDP(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true; // '' ''
        // '' '*****'
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) { // *表示前面元素的一个或者0个，所以不会越界
                dp[0][i + 1] = true;
            }
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = 1; j < p.length(); j++) {
                if (p.charAt(j - 1) == '.') dp[i][j] = dp[i - 1][j - 1];
                if (p.charAt(j - 1) == s.charAt(i - 1)) dp[i][j] = dp[i - 1][j - 1];
                if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];  // 第一个字符不匹配，忽略pattern中*及其前面字符
                    } else dp[i][j] = (dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2]);
                    // 第一个字符匹配，有三种可能选择：1. 匹配多个；2.匹配1个；3.忽略
                }
            }
        }
        return dp[s.length()][p.length()];
    }


    @Test
    public void test1() {
        Assert.assertFalse(isMatch("aa", "a"));
        Assert.assertTrue(isMatch("aa", "a*"));
        Assert.assertTrue(isMatch("ab", ".*"));
        Assert.assertTrue(isMatch("aab", "c*a*b"));
        Assert.assertFalse(isMatch("mississippi", "mis*is*p*."));
    }

    @Test
    public void test2() {
        Assert.assertTrue(isMatchDP("aa", "a*"));
    }
}
