package LeetCode;

import java.math.BigInteger;

/**
 * @author Srunkyo
 * @Date: 2018/8/1
 * @Description:
 * Additive number is a string whose digits can form additive sequence.
 *
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 *
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 *
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 * Example 1:
 *
 * Input: "112358"
 * Output: true
 * Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 *              1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * Example 2:
 *
 * Input: "199100199"
 * Output: true
 * Explanation: The additive sequence is: 1, 99, 100, 199.
 *              1 + 99 = 100, 99 + 100 = 199
 * Follow up:
 * How would you handle overflow for very large input integers?
 *
 * The idea is quite straight forward. Generate the first and second of the sequence,
 * check if the rest of the string match the sum recursively.
 * i and j are length of the first and second number. i should in the range of [0, n/2].
 * The length of their sum should >= max(i,j)
 *
 * 思路：
 * 确认两个数字的长度，看这两个数字是否能够生成整个字符串。
 * API：.startsWith() 判断字符串是否以prefix开头
 */
public class Problem306_AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; i++) { // i: length of num1
            for (int j = 1; Math.max(j, i) <= n - i - j; j++) { // j: length of num2
                if (isValid(i, j, num)) return true;
            }
        }
        return false;
    }

    private boolean isValid(int i, int j, String num) {
        if (num.charAt(0) == '0' && i > 1) return false; //first number can't start with 0
        if (num.charAt(i) == '0' && j > 1) return false; //second number can't start with 0
        String sum = "";
        Long x1 = Long.parseLong(num.substring(0, i));
        Long x2 = Long.parseLong(num.substring(i, i + j));
        for (int start = i + j; start != num.length(); start += sum.length()) {
            long tmp = x2;
            x2 = x1 + x2;
            x1 = tmp;
            sum = x2.toString();
            /*    public boolean startsWith(String prefix, int toffset) */
            if (!num.startsWith(sum, start)) return false;
        }
        return true;
    }
}
