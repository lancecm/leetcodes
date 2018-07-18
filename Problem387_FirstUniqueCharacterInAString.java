package LeetCode;

import org.junit.Test;

import java.util.*;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/17
 * @Description:
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 *
 */
public class Problem387_FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        int[][] map = new int[26][2];
        char[] sc = s.toCharArray();
        int minIndex = Integer.MAX_VALUE;
        boolean has = false;
        for(int i = 0; i < sc.length; i++) {
            int tmp = sc[i] - 'a';
            map[tmp][0] += 1;
            map[tmp][1] = map[tmp][1] == 0 ? i : map[tmp][1];
        }
        for(int i = 0; i < 26; i++) {
            if (map[i][0] == 1) {
                minIndex = Math.min(minIndex, map[i][1]);
                has = true;
            }
        }
        return has == true ? minIndex : -1;
    }

    //别人的简洁写法
    public int firstUniqChar2(String s) {
        int freq[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }

    @Test
    public void test() {
        System.out.println(firstUniqChar("llee"));
    }
}
