package LeetCode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Srunkyo
 * @Date: 2018/8/13
 * @Description:
 * Medium
 *
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Note:
 *
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 * Example 1:
 *
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: ["4", "13", "5", "/", "+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * Output: 22
 * Explanation:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 * 思路：后缀波兰表达式，栈
 * 注意Stack没有Deque（用LinkedList）实现的栈快，后者用push pop操作
 */
public class Problem150_EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        if (tokens.length == 0) return 0;
        Stack<Integer> nums = new Stack<>();
        for (String s: tokens) {
            if (!s.equals("*") && !s.equals("/") && !s.equals("+") && !s.equals("-")) {
                nums.add(Integer.parseInt(s));
            }
            else {
                int num2 = nums.pop();
                int num1 = nums.pop();
                int tmp = 0;
                if (s.equals("*")) {
                    tmp = num1 * num2;
                }
                else if (s.equals("+")) {
                    tmp = num1 + num2;
                }
                else if (s.equals("-")) {
                    tmp = num1 - num2;
                }
                else if (s.equals("/")) {
                    tmp = num1 / num2;
                }
                nums.add(tmp);
            }
        }
        return nums.pop();
    }

    public int evalRPN2(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for(String s: tokens) {
            if (!s.equals("*") && !s.equals("/") && !s.equals("+") && !s.equals("-")) {
                stack.push(Integer.parseInt(s)); // 头部第一个节点插入
            }
            else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (s) {
                    case "+" :
                        stack.push(num1 + num2); // 取出头部第一个节点
                        break;
                    case "-" :
                        stack.push(num1 - num2);
                        break;
                    case "*" :
                        stack.push(num1 * num2);
                        break;
                    case "/" :
                        stack.push(num1 / num2);
                        break;
                }
            }
        }
        return stack.pop();
    }

    @Test
    public void test() {
        System.out.println(evalRPN2(new String[] {"0","3","/"}));
    }
}
