package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/1
 * @Description:
 * Medium
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 * Example 1:
 * Input:s1 = "ab" s2 = "eidbaooo"
 * Output:True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 * Note:
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 *
 * 思路：
 * permutations: 使用计数表，正数代表还需要多少个，负数表示多余多少个，0表示刚刚好满足。当计数表全部为0是表示子串为permutation
 * sliding window: 每次向右移动一位，更新计数表，并判断是否是permutation
 */
public class Problem567_PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int lenS1 = s1.length();
        int lenS2 = s2.length();
        if (lenS1 > lenS2) return false;
        int[] mask = new int[26];
        for (int i = 0; i < lenS1; i++) {
            mask[s1.charAt(i) - 'a']++;
            mask[s2.charAt(i) - 'a']--;
        }
        if(allZero(mask)) return true;
        for (int i = lenS1; i < lenS2; i++) {
            mask[s2.charAt(i) - 'a']--;
            mask[s2.charAt(i - lenS1) -'a']++;
            if (allZero(mask)) return true;
        }
        return false;
    }


    private boolean allZero(int[] mask) {
        for (int i : mask) {
            if (i != 0) return false;
        }
        return true;
    }

    @Test
    public void test() {
        checkInclusion("ab", "eibao");
    }
}
