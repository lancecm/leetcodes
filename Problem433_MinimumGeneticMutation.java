package LeetCode;

import org.junit.Test;

import java.util.*;

/**
 * @author Srunkyo
 * @Date: 2018/8/25
 * @Description:
 * Medium
 * 类似讨厌的Word Ladder..
 *
 * A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
 *
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.
 *
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 *
 * Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.
 *
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.
 *
 * Note:
 *
 * Starting point is assumed to be valid, so it might not be included in the bank.
 * If multiple mutations are needed, all mutations during in the sequence must be valid.
 * You may assume start and end string is not the same.
 *
 *
 * Example 1:
 *
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 *
 * return: 1
 *
 *
 * Example 2:
 *
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 *
 * return: 2
 *
 *
 * Example 3:
 *
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 *
 * return: 3
 */
public class Problem433_MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        if (bank == null || bank.length == 0) return -1;
        if (start.equals(end)) return 0;
        HashSet<String> set = new HashSet<>();
        set.add(start);
        for (String s : bank) {
            set.add(s);
        }
        int dist = 0;
        HashSet<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList();
        q.offer(start);
        visited.add(start);
        char[] dict = new char[] {'A','C','G','T'};
        while (!q.isEmpty()) {
            int size = q.size();
            dist++;
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                for (String next : findNext(cur, dict, set)) {
                    if (visited.contains(next)) {
                        continue;
                    }
                    if (next.equals(end)) return dist;
                    visited.add(next);
                    q.offer(next);
                }
            }
        }
        return -1;
    }

    // 找到只差一个字符的
    private List<String> findNext(String cur, char[] dict, HashSet<String> set) {
        List<String> res = new ArrayList<>();
        // traverse all the possible sequence
        // 对于每个字符，改成一种，看看bank中是否存在
        for (int i = 0; i < cur.length(); i++) {
            for (char ch : dict) {
                if (ch == cur.charAt(i)) continue;
                char[] tmp = cur.toCharArray();
                tmp[i] = ch;
                String next = new String(tmp);
                if (set.contains(next)) res.add(next);
            }
        }
        return res;
    }

    // 第二次写
    public int minMutation2(String start, String end, String[] bank) {
        if (bank == null || bank.length == 0) return -1;
        if (end == start) return 0;
        HashSet<String> set = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        for (String s : bank) set.add(s);
        Queue<String> q = new LinkedList<>();
        set.add(start);
        q.add(start);
        visited.add(start);
        int step = 0;
        char[] chs = new char[] {'A','T','G','C'};
        while (!q.isEmpty()) {
            int size = q.size();
            step += 1;
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                List<String> nexts = findNext(set, cur, chs);
                for (String next : nexts) {
                    if (visited.contains(next)) continue;
                    if (next.equals(end)) return step;
                    visited.add(next);
                    q.add(next);
                }
            }
        }
        return -1; // not found
    }

    private List<String> findNext(HashSet<String> set, String cur, char[] chs) {
        List<String> result = new LinkedList<>();
        for (int i = 0; i < cur.length(); i++) {
            for (char ch : chs) {
                if (ch == cur.charAt(i)) continue;
                char[] tmp = cur.toCharArray();
                tmp[i] = ch;
                String next = new String(tmp);
                if (set.contains(next)) result.add(next);
            }
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(minMutation2("AACCGGTT", "AACCGGTA", new String[] {"AACCGGTA"}));
    }
}
