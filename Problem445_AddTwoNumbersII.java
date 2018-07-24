package LeetCode;

import org.junit.Test;

import java.util.Stack;

/**
 * @author Srunkyo
 * @Date: 2018/7/24
 * @Description:
 *
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

 */
public class Problem445_AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> st1 = new Stack<Integer>();
        Stack<Integer> st2 = new Stack<Integer>();
        while(l1 != null) {
            st1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) {
            st2.push(l2.val);
            l2 = l2.next;
        }
        ListNode pre = null;
        int carry = 0;
        while(st1.size() != 0 && st2.size() != 0) {
            int sum = st1.pop() + st2.pop() + carry;
            ListNode node = new ListNode(sum % 10);
            carry = sum / 10;
            node.next = pre;
            pre = node;
        }
        while (st1.size() != 0) {
            int sum = st1.pop() + carry;
            ListNode node = new ListNode(sum % 10);
            carry = sum / 10;
            node.next = pre;
            pre = node;
        }
        while (st2.size() != 0) {
            int sum = st1.pop() + carry;
            ListNode node = new ListNode(sum % 10);
            carry = sum / 10;
            node.next = pre;
            pre = node;
        }
        if (carry!=0){
            ListNode node = new ListNode(carry);
            node.next = pre;
            pre = node;
        }
        return pre;
    }


    // simplified version
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> st1 = new Stack<Integer>();
        Stack<Integer> st2 = new Stack<Integer>();
        while(l1 != null) {
            st1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) {
            st2.push(l2.val);
            l2 = l2.next;
        }
        int sum = 0;
        ListNode list = new ListNode(0);
        while (!st1.isEmpty() || !st2.isEmpty()) {
            if (!st1.isEmpty()) sum+= st1.pop();
            if (!st2.isEmpty()) sum+= st2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }
        return list.val == 0 ? list.next : list;
    }
    @Test
    public void test() {
        ListNode listNode1 = new ListNode(7);
//        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(4);
//        ListNode listNode4 = new ListNode(3);
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//        ListNode listNode5 = new ListNode(5);
//        ListNode listNode6 = new ListNode(6);
//        ListNode listNode7 = new ListNode(4);
//        listNode5.next = listNode6;
//        listNode6.next = listNode7;
        addTwoNumbers(listNode1, null);
    }
}
