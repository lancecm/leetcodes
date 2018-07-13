package LeetCode;

import org.junit.Test;

import java.util.*;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/12
 * @Description:
 * 127
 * Medium
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 * 思路：
 * 无权图求最短路径 --> BFS  （每一次更新一个字母都看做进一步）
 * 1. 采用诸位替换字母方法来替换一一比较。
 * 2. 优化方案：两边一起搜索
 *
 * TODO: 重新理解
 */
public class Problem127_WordLadder {

    // 双向Set
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dic = new HashSet<>(wordList);
        if (!dic.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);
        int len = 1;
        boolean found = false;
        while (!found && !begin.isEmpty() && !end.isEmpty()) {
            Set<String> next = new HashSet<>();
            if (begin.size() > end.size()) {
                Set<String> tmp = begin;
                begin = end;
                end = tmp;
            }
            found = helper(begin, end, dic, visited, next); // If visited, the word must in begin or end list!
            begin = next;
            len ++;
        }
        return found?len:0;
    }

    private boolean helper(Set<String> begin, Set<String> end, Set<String> dic, Set<String> visited, Set<String> res) {
        for (String w : begin) {
            for (int i = 0; i < w.length(); i++) {
                char[] chs = w.toCharArray();
                for (int j = 0; j < 26; j++) {
                    chs[i] = (char) (j + 'a');
                    String s = new String(chs);

                    if (dic.contains(s)) {
                        if (end.contains(s)) return true;
                        if (visited.contains(s)) continue;
                        res.add(s);
                        visited.add(s);
                    }
                }
            }
        }
        return false;
    }

    // BFS
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> reached = new HashSet<>();
        wordSet.remove(beginWord);
        reached.add(beginWord);
        int level = 1;
        while (!reached.isEmpty()) {
            Set<String> reachedNext = new HashSet<>();
            for (String s : reached) {
                for (int i = 0; i < s.length(); i++) {
                    char[] c = s.toCharArray();
                    for (char j = 'a'; j <= 'z'; j++) {
                        c[i] = j;
                        String newString = new String(c);
                        if (wordSet.remove(newString)) {
                            reached.add(newString);
                            if (endWord.equals(newString)) return level + 1;
                        }
                    }
                }
            }
            reached = reachedNext;
            level++;
        }
        return 0;
    }



    @Test
    public void test() {
        String begin = "hit";
        String end = "cog";
        List<String> l = new LinkedList<>();
        l.add("hot");
        l.add("dot");
        l.add("dog");
        l.add("lot");
        l.add("log");
        l.add("cog");
        System.out.println(ladderLength(begin, end, l));
    }
}
