package LeetCode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/8
 * @Description:
 * 242
 * Easy
 *
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 * Note:
 * You may assume the string contains only lowercase alphabets.
 *
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class Problem242_ValidAnagram {
    // 纯字母
    public boolean isAnagram(String s, String t) {
        if(s == null || t == null || (s.length() != t.length())) return false;
        int [] al = new int[26];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for(int i = 0; i < sc.length; i++) {
            al[sc[i] - 'a'] += 1;
        }
        for(int i = 0; i < tc.length; i++) {
            int index = tc[i] - 'a';
            al[index] --;
            if(al[index] < 0) {
                return false;
            }
        }
        return true;
    }

    // 适用于unicode
    public boolean isAnagram2(String s, String t) {
        if(s == null || t == null || (s.length() != t.length())) return false;
        Map<Character, Integer> map = new HashMap<>();
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for(char c1 : sc) {
            map.put(c1, map.getOrDefault(c1, 0) + 1);
        }
        for(char c2 : tc) {
            if (map.getOrDefault(c2, 0) - 1 < 0) {
                return false;
            }
            map.put(c2, map.getOrDefault(c2, 0) - 1);
        }
        return true;
    }


    @Test
    public void test() {
        isAnagram("anagram","nagaram");
        isAnagram2("aacc","ccac");
    }
}
