package LeetCode;

import org.junit.Test;

import java.util.Stack;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/30
 * @Description:
 * 20
 * Easy
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 *
 */
public class Problem20_ValidParentheses {
    // 用栈来存右括号，从右往左遍历字符串，有三种情况
    public boolean isValid2(String s) {
        if (s.equals("")) return true;
        if (s.length() %2 != 0) return false;
        char[] chars = s.toCharArray();
        Stack<Character> right = new Stack<>();
        for (int i = chars.length - 1; i >= 0; i--) {
            char tmp = chars[i];
            if (tmp == '}' || tmp == ']' || tmp == ')') right.push(tmp); // 右括号
            else if(right.size() == 0) return false; // 左括号，但右括号栈为空
            else { // 左括号，右括号栈不为空
                char r = right.pop();
                if (tmp == '{' && r != '}') return false;
                if (tmp == '[' && r != ']') return false;
                if (tmp == '(' && r != ')') return false;
            }
        }
        return true;
    }

    // 同样是用栈，从左往右遍历字符串
    // 如果是左括号，则往栈中推入一个同类型右括号
    // 如果是右括号，则看栈顶是否不为空，且为相同的右括号
    public boolean isValid(String s) {
        if (s.equals("")) return true;
        if (s.length() % 2 != 0) return false;
        Stack<Character> st = new Stack<> ();
        for (char c : s.toCharArray()) {
            if (c == '(') st.push(')');
            else if (c == '[') st.push(']');
            else if (c == '{') st.push('}');
            else if (st.isEmpty() || st.pop() != c) return false;
        }
        return st.isEmpty();
    }

    // 8.28 version
    public boolean isValid3(String s) {
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') st.push(')');
            else if (c == '[') st.push(']');
            else if (c == '{') st.push('}');
            else {
                if (st.isEmpty()) return false;
                char tmp = st.pop();
                if (tmp != c) return false;
            }
        }
        return st.isEmpty();
    }

    @Test
    public void test() {
        System.out.println(isValid3("(}"));
        System.out.println(isValid3("(("));
        System.out.println(isValid3(""));
        System.out.println(isValid3("([{}])"));
        System.out.println(isValid3("{}[]"));
        System.out.println(isValid3("{"));
    }
}
