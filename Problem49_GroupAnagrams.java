package LeetCode;

import org.junit.Test;

import java.util.*;

/**
 * @author Srunkyo
 * @Date: 2018/7/21
 * @Description:
 * 49
 * Medium
 *
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 * 思路：本题考察的是哈希
 */
public class Problem49_GroupAnagrams {
    // 解法1：Categorize by Sorted String
    // ans: {String -> List}
    // Two strings are anagrams if and only if their sorted string are equal
    // O_t = O(Nklogk)
    // O_s = O(NK)
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if(strs.length == 0) return result;
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strs) {
            char[] chs = s.toCharArray();
            Arrays.sort(chs);
            String t = new String(chs);
            List<String> l = map.get(t);
            if (l == null) {
                l = new ArrayList<>();
                result.add(l);
                map.put(t, l);
            }
            l.add(s);
        }
        return result;
    }

    // 解法2：Categorize by Count(避免排序)
    // 使用一个字符串 "#0#1#3#2#0..." 记录26个字母出现的数量
    // 同解法1思路大致一样，但是慢不少
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs.length == 0) return result;
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strs) {
            int[] mask = new int[26];
            for (char c : s.toCharArray()) mask[c - 'a']++;
            String key = "";
            for (int i = 0; i < 26; i++) key = key + "#" + mask[i];
            List<String> l = map.get(key);
            if (l == null) {
                l = new ArrayList<>();
                map.put(key, l);
                result.add(l);
            }
            l.add(s);
        }
        return result;
    }


    @Test
    public void test() {
        String[] s = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams2(s);
        for (List<String> l : result) {
            for (String str: l) {
                System.out.println(str);
            }
        }
    }
}
