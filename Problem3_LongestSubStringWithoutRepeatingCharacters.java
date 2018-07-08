package LeetCode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/8
 * @Description:
 * 3
 * Medium
 * 看似简单但是容易想错。。
 *
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Examples:
 *
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 *
 * Given "bbbbb", the answer is "b", with the length of 1.
 *
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * 注意是substring而不是subsequence
 * 思路：
 * 在没有遇到重复字符，将当前字符赋值为下标；
 * 当遇到重复字符时（通过下标判断），更新新子串的起点为上一次重复过字符的下一位，更新当前最大子串长度值
 * O(N)
 */
public class Problem3_LongestSubStringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s){
        Map<Character, Integer> map = new HashMap<>();
        char[] sc = s.toCharArray();
        int max = 0;
        for (int i = 0, j = 0; i < sc.length; i++) {
            if (map.containsKey(sc[i])) {
                j = Math.max(j, map.get(sc[i]) + 1); // next start
            }
            map.put(sc[i], i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring("dvdfved"));
        System.out.println(lengthOfLongestSubstring("aab"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("adfaefew"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("abcdefg"));
    }
}
