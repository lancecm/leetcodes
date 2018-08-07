package LeetCode;

import org.junit.Test;

import java.util.Stack;

/**
 * @author Srunkyo
 * @Date: 2018/8/7
 * @Description:
 * Hard
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * Example 1:
 *
 * Input: "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 *
 * 思路：
 * 1. 后缀表达式
 * 2. 使用一个栈，该栈保存(之前的结果和符号，直到)计算完毕，再从栈中取出先前结果进行加减运算。
 */
public class Problem224_BasicCalculator {
    public int calculate(String s) {
        int result = 0;
        int number = 0;
        int sign = 1;
        Stack<Integer> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            }
            else if (c == '+') {
                result += sign * number;
                sign = 1;
                number = 0;
            }
            else if (c == '-') {
                result += sign * number;
                sign = -1;
                number = 0;
            }
            else if (c == '(') {
                st.push(result);
                st.push(sign);
                result = 0;
                sign = 1;
            }
            else if (c == ')') {
                result += sign * number;
                number = 0;
                result *= st.pop();
                result += st.pop();
            }
        }
        if (number != 0) result += sign * number;
        return result;
    }


    @Test
    public void test() {
        System.out.println(calculate("1 + 1"));
    }
}
