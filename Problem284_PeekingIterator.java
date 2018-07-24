package LeetCode;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Srunkyo
 * @Date: 2018/7/24
 * @Description:
 * 284
 * Medium
 *
 * 增加函数peek() 返回下一个元素，但是不影响iterator
Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator
that support the peek() operation -- it essentially peek() at the element that will be returned by the next
call to next().

Example:

Assume that the iterator is initialized to the beginning of the list: [1,2,3].

Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
You call next() the final time and it returns 3, the last element.
Calling hasNext() after that should return false.
Follow up: How would you extend your design to be generic and work with all types, not just integer?

思路：
 cache next element
 */
public class Problem284_PeekingIterator implements Iterator<Integer> {
    private Integer next;
    private Iterator<Integer> iter;
    boolean noSushElement;

    public Problem284_PeekingIterator(Iterator<Integer> iterator) {
        iter = iterator;
        if (iter.hasNext()) next = iter.next();
        else noSushElement = true;
    }

    @Override
    public boolean hasNext() {
        return !noSushElement;
    }

    @Override
    public Integer next() {
        if (noSushElement) throw new NoSuchElementException(); // should behave the same way as Iterator interface
        Integer res = next;
        if (iter.hasNext()) next = iter.next();
        else noSushElement = true;
        return res;
    }
}
