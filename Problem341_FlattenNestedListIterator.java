package LeetCode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author Srunkyo
 * @Date: 2018/7/24
 * @Description:
 * 341
 * 中
 *
 * Given a nested list of integers, implement an iterator to flatten it.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 * Given the list [[1,1],2,[1,1]],
 *
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 *
 * Example 2:
 * Given the list [1,[4,[6]]],
 *
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 *
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 *
 * 思路1： 把nestedList转换为arrayList
 * 思路2： 使用栈
 */

public class Problem341_FlattenNestedListIterator implements Iterator<Integer> {

    public interface NestedInteger{
        public boolean isInteger();
        public Integer getInteger();
        public List<NestedInteger> getList();
    }

    /*
    //思路1：
    List<Integer> list = new ArrayList<>();
    Iterator<Integer> iter;

    public Problem341_FlattenNestedListIterator(List<NestedInteger> nestedList) {
        helper(nestedList);
        iter = list.iterator();
    }

    public void helper(List<NestedInteger> nestedList) {
        for(NestedInteger n: nestedList) {
            if (n.isInteger()) list.add(n.getInteger());
            else {
                helper(n.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return iter.next();
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }
    */

    /*
    Stack<NestedInteger> stack = new Stack<>();
    public Problem341_FlattenNestedListIterator(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        } else {
            return stack.pop().getInteger();
        }
    }

    @Override
    public boolean hasNext() {
        // keep iterating until top element is an integer
        while (!stack.isEmpty()) {
            NestedInteger curr = stack.peek();
            if (curr.isInteger()) return true;
            stack.pop();
            // if it's not integer, push them into the stack reversely
            for (int i = curr.getList().size() - 1; i >= 0; i--) {
                stack.push(curr.getList().get(i));
            }
        }
        return false;
    }
    */

    List<Integer> list = new ArrayList<>();
    int index = 0;

    public Problem341_FlattenNestedListIterator(List<NestedInteger> nestedList) {
        helper(nestedList);
    }

    public void helper(List<NestedInteger> nestedList) {
        for(NestedInteger n: nestedList) {
            if (n.isInteger()) list.add(n.getInteger());
            else {
                helper(n.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return list.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }
}
