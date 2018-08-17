package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/17
 * @Description:
 * Easy
 *
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 *
 */
public class Problem125_ValidPalindrome {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        char[] chs = s.toCharArray();
        while (left <= right) {
            if (valid(chs[left]) == false) {
                left++;
                continue;
            }
            if (valid(chs[right]) == false) {
                right--;
                continue;
            }
            if (Character.toLowerCase(chs[left]) != Character.toLowerCase(chs[right])) return false;
            left++;
            right--;
        }
        return true;
    }

    public boolean valid(char c) {
        if ((c >='A' && c <='Z') || (c >= 'a' && c <= 'z') || (c >='0' && c <= '9')) return true;
        else return false;
    }

    @Test
    public void test() {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

}
