package LeetCode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/3
 * @Description:
 * 8
 * Medium
 *
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 *
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * Example 1:
 *
 * Input: "42"
 * Output: 42
 * Example 2:
 *
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 *              Then take as many numerical digits as possible, which gets 42.
 * Example 3:
 *
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * Example 4:
 *
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 *              digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 *
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 *              Thefore INT_MIN (−231) is returned.
 *
 * 思路:
 * 普通循环，注意special case
 * 1. deal with ' '
 * 2. deal with sign
 * 3. deal with invalid input
 * 4. deal with overflow (need trick)
 *
 * Mind: +-1 ------> invalid input!!!!!
 *
 * [面试]
 * I was actually asked this question during an interview at one of the hot companies and received positive feedback.
 * When I heard the word "atoi" I felt defeated since I too attempted this problem on LeetCode and could not pass all
 * cases but thought whatever let's give it a shot.
 * Assuming you get a good interviewer, he/she really only cares that you acknowledge the special cases
 * (random symbols, 1e23, overflow/underflow, string that is not a number, etc..).
 * The meat of the problem is just simply converting into a number (e.g. 0, 2, 1000, 200000, -123456)
 * which I'm sure most of us can and have already done.
 * If the interviewer expects you do cover cases like LeetCode does,
 * then yes you are probably ****ed or you have to think on your feet.
 * I can understand the frustration here. Rest assured, if you got most of the basic cases,
 * then you are likely to perform well in the real interview.
 * Don't evaluate yourself solely on the number of test cases you passed,
 * consider everything else LeetCode can't evaluate (communication, thought-process, etc).
 *
 * TODO: 还有更快的？
 */
public class Problem8_StringToInteger_atoi {
    // 30%
    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int i = 0, result = 0, sign = 1;
        // ignore leading ' '
        while (i < str.length() && chars[i] == ' ')
            i++;
        if (i == chars.length) return 0;
        // deal with sign
        if (chars[i] == '+') i++;
        else if (chars[i] == '-'){
            sign = 0;
            i++;
        }

        for (; i < str.length(); i++) {
            char c = chars[i];
            if (!Character.isDigit(c)) break; // deal with invalid input
            int tmp = result;
            result = result * 10 + (c - '0');
            if (!(result % 10 + '0' == c && (result - (c - '0')) / 10 == tmp))  {
                result = sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE; // deal with overflow
                break;
            }
        }
        return sign == 1 ? result : -result;
    }

//        for (; i < chars.length; i++) {
//            if (chars[i] != '+' && chars[i] != '-') break;
//            sign = chars[i] == '+' ? 1 : 0;
//        }

    @Test
    public void test() {
        System.out.println(myAtoi("   "));
        System.out.println(myAtoi("   42"));
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("    -42"));
        System.out.println(myAtoi("42   "));
        System.out.println(myAtoi("4232 with words"));
        System.out.println(myAtoi("word and 987"));
        System.out.println(myAtoi("+-1"));
        Assert.assertEquals(0, myAtoi("0+-1"));
        System.out.println(myAtoi("0+-1"));
        System.out.println(myAtoi("-9128347232")); // count case overflow!
    }
}
