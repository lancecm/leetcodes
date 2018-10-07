package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/18
 * @Description:
 * Easy
 *
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 *
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 *
 * Note:
 * Assume the length of given string will not exceed 1,010.
 *
 * Example:
 *
 * Input:
 * "abccccdd"
 *
 * Output:
 * 7
 *
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class Problem409_LongestPalindrome {
    public int longestPalindrome(String s) {
        char[] chs = s.toCharArray();
        int[] mask = new int[128];
        for(char c : chs) {
            mask[c] +=1;
        }
        boolean single = false;
        int sum = 0;
        for(int i = 0; i < 128; i++) {
            if (mask[i] % 2 == 0) { // double
                sum += mask[i];
            }
            else if (single == true && mask[i] % 2 != 0) {
                sum += mask[i] - 1;
            }
            else if (single == false && mask[i] % 2 != 0) {
                sum += mask[i];
                single = true;
            }
        }
        return sum;
    }

    @Test
    public void test() {
        System.out.println(longestPalindrome("abccccdd"));
    }
}
