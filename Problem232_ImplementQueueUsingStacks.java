package LeetCode;

import org.junit.Test;

import java.util.Stack;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/12
 * @Description:
 * 232
 * Easy
 *
 * Implement the following operations of a queue using stacks.
 *
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * Example:
 *
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // returns 1
 * queue.pop();   // returns 1
 * queue.empty(); // returns false
 * Notes:
 *
 * You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */
public class Problem232_ImplementQueueUsingStacks {
    Stack<Integer> st1;
    Stack<Integer> st2;

    /** Initialize your data structure here. */
    public Problem232_ImplementQueueUsingStacks() {
        st1 = new Stack<Integer>();
        st2 = new Stack<Integer>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        st1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (st2.isEmpty())  while (!st1.isEmpty()) st2.push(st1.pop());
        return st2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (!st2.isEmpty()) return st2.peek();
        else{
            while (!st1.isEmpty()) st2.push(st1.pop());
            return st2.peek();
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return st1.isEmpty() && st2.isEmpty();
    }

    @Test
    public void test(){
        Problem232_ImplementQueueUsingStacks obj = new Problem232_ImplementQueueUsingStacks();
        obj.push(1);
        obj.push(2);
        obj.peek();
        obj.pop();
        obj.empty();
    }
}
