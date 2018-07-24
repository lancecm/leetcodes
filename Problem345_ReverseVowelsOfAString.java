package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/7/24
 * @Description:
 * 345
 * Easy
 *
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".

 思路：2 pointers
 */
public class Problem345_ReverseVowelsOfAString {
    // PS: 可以直接用 String.contains简洁代码
    public String reverseVowels(String s) {
        if (s.equals("")) return "";
        int l = 0, r = s.length() - 1;
        char[] chs = s.toCharArray();
        while (l < r) {

            while (l < r && chs[l] != 'a' && chs[l] != 'e' && chs[l] != 'i' && chs[l] != 'o'&& chs[l] != 'u'
                    && chs[l] != 'A' && chs[l] != 'E' && chs[l] != 'I' && chs[l] != 'O'&& chs[l] != 'U') {
                l++;
            }
            while (l < r && chs[r] != 'a' && chs[r] != 'e' && chs[r] != 'i' && chs[r] != 'o'&& chs[r] != 'u'
                    && chs[r] != 'A' && chs[r] != 'E' && chs[r] != 'I' && chs[r] != 'O'&& chs[r] != 'U') {
                r--;
            }
            char tmp = chs[l];
            chs[l] = chs[r];
            chs[r] = tmp;
            l++;
            r--;
        }
        return new String(chs);
    }


    @Test
    public void test() {
        System.out.println(reverseVowels("aA"));
    }
}
