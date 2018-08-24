package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/24
 * @Description:
 * Easy
 *
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 *
 * Note:
 *
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class Problem415_AddStrings {
    public String addStrings(String num1, String num2) {
        if (num1.length() <  num2.length()) return addStrings(num2, num1);
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        char[] chs1 = num1.toCharArray();
        char[] chs2 = num2.toCharArray();
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int d1 = 0, d2 = 0;
            if (i >= 0) d1 = chs1[i] - '0';
            if (j >= 0) d2 = chs2[j] - '0';
            int sum = d1 + d2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            i--;
            j--;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    @Test
    public void test() {
        System.out.println(addStrings("0","0"));
    }
}
