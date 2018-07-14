package LeetCode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/10
 * @Description:
 * 155
 * Easy
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 *
 * 思路：
 * 1. 使用2个栈，一个栈为正常，一个最小栈专门用来处理最小值。
 * 当且仅当插入数据小于等于min的时候这个栈push数据进来，取最小的时候操作最小栈。
 *
 * 2. 用全局min来保存当前最小值。
 *    观察到，栈顶如果是最小值的时候，每次pop出它，就会面临不知道下一个最小值是什么的问题。
 *    因此，每一次放入一个最小值前，先压入上一个最小值。每次pop时pop两次，对Min值进行更新。
 */
public class Problem155_MinStack {
//    Stack<Integer> st = new Stack<Integer>();
//    Stack<Integer> stmin =  new Stack<Integer>();
//
//    /** initialize your data structure here. */
//    public Problem155_MinStack() {
//    }
//
//    public void push(int x) {
//        st.push(x);
//        if (stmin.isEmpty() || stmin.peek() >= x) {
//            stmin.push(x);
//        }
//    }
//
//    public void pop() {
//        if (st.pop().equals(stmin.peek())) {
//            stmin.pop();
//        }
//    }
//
//    public int top() {
//        return st.peek();
//    }
//
//    public int getMin() {
//        return stmin.peek();
//    }



    Stack<Integer> st;
    int min = Integer.MAX_VALUE;

    /** initialize your data structure here. */
    public Problem155_MinStack() {
        st = new Stack<Integer>();
    }

    public void push(int x) {
        if (x <= min) {
            st.push(min);
            min = x;
        }
        st.push(x);
    }

    public void pop() {
        if (min == st.pop())
            min = st.pop();
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        return min;
    }

    @Test
    public void test() {
        Problem155_MinStack p = new Problem155_MinStack();
        p.push(512);
        p.push(-1024);
        p.push(-1024);
        p.push(512);
        p.pop();
        System.out.println(p.getMin());
        p.pop();
        System.out.println(p.getMin());
        p.pop();
        p.pop();
    }
}
