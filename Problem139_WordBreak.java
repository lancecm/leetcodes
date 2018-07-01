package LeetCode;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/1
 * @Description:
 * 139
 * Medium
 *Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 *
 * 时间复杂度：如果使用List，k为WordList的长度，contains操作为O(k)复杂度，总复杂度为 O(n^2)O(k) 13ms
 *           如果将List转换为HashSet，contains操作为O(1)复杂度，总复杂度为O(n^2) 9ms
 * 空间复杂度：O(n)
 */
public class Problem139_WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.equals("")) return false;
        boolean [] dp = new boolean[s.length() + 1];
        Set<String> hashset = new HashSet<>(wordDict);
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i-1; j >= 0; j--) { // !! 内循环从后往前搜索，遇到可行的立刻跳出循环，会比从前往后搜索快一些
                if (dp[j] && hashset.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // Stop searching other methods.
                }
            }
        }
        return dp[s.length()];
    }

    // TODO: BFS DFS 写法！

    @Test
    public void test() {
        System.out.println("leetcode".substring(0,4));
        List<String> l = new LinkedList<>();
        l.add("cats");
        l.add("dog");
        l.add("sand");
        l.add("and");
        l.add("cat");
        System.out.println(wordBreak("catsandog", l));
    }
}
