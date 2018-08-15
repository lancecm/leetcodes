package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/14
 * @Description:
 * Easy
 *
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class Problem680_ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int lo = 0;
        int hi = s.length() - 1;
        while (lo <= hi) {
            if (s.charAt(lo) != s.charAt(hi)) {
                return helper(s.substring(lo + 1, hi + 1)) || helper(s.substring(lo, hi - 1 + 1));
            }
            lo++;
            hi--;
        }
        return true;
    }

    public boolean helper(String s) {
        int lo = 0;
        int hi = s.length() - 1;
        while (lo <= hi) {
            if (s.charAt(lo) != s.charAt(hi)) return false;
            lo++;
            hi--;
        }
        return true;
    }

    public boolean subvalidPalindrome(char[] stringChar, int start, int end) {
        while (start < end) {
            if (stringChar[start++] != stringChar[end--]) return false;
        }
        return true;
    }

    public boolean validPalindrome2(String s) {
        int start = 0, end = s.length() - 1;
        char[] stringChar = s.toCharArray();
        while (start < end) {
            if (stringChar[start] != stringChar[end]) {
                return subvalidPalindrome(stringChar, start + 1 ,end) ||
                        subvalidPalindrome(stringChar, start, end - 1);
            }
            start++;
            end--;
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println(validPalindrome("abab"));
    }
}
