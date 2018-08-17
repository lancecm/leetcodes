package LeetCode;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Srunkyo
 * @Date: 2018/8/17
 * @Description:
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example 1:
 *
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 *
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class Problem143_ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        // 找到中间节点
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 翻转中间到末尾部分的节点 1-2-3-4-5-6  1-2-3-6-5-4
        ListNode start = slow;
        ListNode pre = slow.next;
        while (pre.next != null) {
            ListNode cur = pre.next;
            pre.next = cur.next;
            cur.next = start.next;
            start.next = cur;
        }

        // 1-2-3-6-5-4  1-6-2-3-5-4
        ListNode p1 = head;
        ListNode p2 = slow.next;
        slow.next = null;
        while (p1 != null && p2 != null) {
            ListNode tmp1 = p1.next;
            ListNode tmp2 = p2.next;
            p2.next = p1.next;
            p1.next = p2;
            p1 = tmp1;
            p2 = tmp2;
        }
    }



    @Test
    public void test() {
        ListNode head = new ListNode(1);
        ListNode h2 = new ListNode(2);
        ListNode h3 = new ListNode(3);
        ListNode h4 = new ListNode(4);
        ListNode h5 = new ListNode(5);
        ListNode h6 = new ListNode(6);

        head.next = h2;
        h2.next = h3;
        h3.next = h4;
        h4.next = h5;
        h5.next = h6;
        reorderList(head);
    }
}
