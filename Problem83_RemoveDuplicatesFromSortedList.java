package LeetCode;

/**
 * Author: Srunkyo
 * Date: 2018-10-16
 * Time: 1:01 AM
 * Description:
 * Easy
 *
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class Problem83_RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p2 != null) {
            if (p1.val == p2.val) {
                p1.next = p2.next;
                p2 = p1.next;
            }
            else {
                p1 = p2;
                p2 = p2.next;
            }
        }
        return head;
    }

    // recursive
    public ListNode deleteDuplicates2(ListNode head) {
         if (head == null || head.next == null) return head;
         head.next = deleteDuplicates2(head.next);
         return head.val == head.next.val ? head.next : head;
    }

    /**
     * 2019/06/24
     */
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null) return null;
        int pre = head.val;
        ListNode p = head;
        while(p.next != null) {
            if (pre == p.next.val) {
                // if equal, means duplicate, change next to next.next
                p.next = p.next.next;
            } else {
                // if not equal, just move the pointer to next
                p = p.next;
            }
            // update pre
            pre = p.val;
        }
        return head;
    }

    // 2019/07/15
    public ListNode deleteDuplicates4(ListNode head) {
        if (head == null) return head;
        ListNode c = head;
        while (c.next != null) {
            if (c.val == c.next.val) {
                c.next = c.next.next;
            } else {
                c = c.next;
            }
        }
        return head;
    }

}
