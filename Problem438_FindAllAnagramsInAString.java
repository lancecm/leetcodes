package LeetCode;

import java.util.*;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/20 7/16
 * @Description:
 * 438
 * Easy但是并不Easy
 *
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input:
 * s: "abab" p: "ab"
 *
 * Output:
 * [0, 1, 2]
 *
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 * TODO: 理解SLIDE WINDOW
 * 相似题目： 76 3 30
 */
public class Problem438_FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || s.length() < p.length()) return result;
        int[] hash = new int[26];
        for (char c: p.toCharArray()) hash[c - 'a']++; // construct hash map; 正数表示在anagram中还待使用的字符数量；负数表示该字符用多了或者不需要使用

        int start = 0, end = 0, len = p.length(), diff = len; // diff: 还需找到的长度
        char temp;
        // 先把窗口拉到p的大小
        for (end = 0; end < len; end ++) {
            temp = s.charAt(end);
            hash[temp - 'a']--; // 字符放到滑动窗口中
            if (hash[temp - 'a'] >= 0) diff--; // 找到满足条件的字符
        }
        if (diff == 0) result.add(0);

        while (end < s.length()) {
            temp = s.charAt(start);
            if (hash[temp - 'a'] >= 0) diff++; // 意味着该字符曾经属于anagram，现在要把其从滑动窗口放回，则未来还多需一个该字符，因此diff应当增加1。
            hash[temp - 'a']++; //相当于还原成原来的状态
            start++; // start向后移动

            temp = s.charAt(end);
            hash[temp - 'a']--; // 把字符移动放到滑动窗口中
            if (hash[temp - 'a'] >= 0) diff--;
            end++; // end向后移动

            if (diff == 0) result.add(start);
        }
        return result;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || s.length() < p.length()) return result;

        Map<Character, Integer> map = new HashMap<>();
        for (char c: p.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);

        int counter = map.size();
        int begin = 0, end = 0;

        while (end < s.length()) {
            // 先进行到满足counter==0，即找到全部anagram所需字符的地方
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1); // 纳入sliding window
                if (map.get(c) == 0) counter--; // ==0表示在滑动窗口内的字符数量满足anagram中要求
            }
            end++;

            // 再一个个把begin拉到长度位end-begin的位置
            while (counter == 0) {
                char tmp = s.charAt(begin);
                if (map.containsKey(tmp)) {
                    map.put(tmp, map.get(tmp) + 1); //从sliding window放回
                    if (map.get(tmp) > 0) counter++; // 当某个字符>0时，表示在滑动窗口内的字符有了缺失，counter对应加一，跳出循环
                }
                if (end - begin == p.length()) {
                    result.add(begin);
                }
                begin++;
            }
        }
        return result;
    }


    public void print (List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    public static void main(String[] args) {
        String s = "abab";
        String p = "ab";
        Problem438_FindAllAnagramsInAString prob = new Problem438_FindAllAnagramsInAString();
        List<Integer> l = prob.findAnagrams2(s,p);
        prob.print(l);
    }
}
