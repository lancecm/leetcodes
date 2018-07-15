package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/7  7/15
 * @Description:
 * 92
 * 中
 *
 * Reverse a linked list from position m to n. Do it in one-pass.
 *
 * Note: 1 ≤ m ≤ n ≤ length of list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 *
 * 思路:
 * 先找到待交换的第一个节点，逆序n-m+1个节点
 * 逆序后，链表断成了三个部分。
 * 要明确四个位置，分别是逆序链表前一个节点pre， 逆序列表第一个节点newHead， 逆序列表最后一个节点modifiedListTail, 逆序列表下一个节点next
 * 链接后两个：modifiedListTail.next = next;
 * 链接前两个：使pre.next = newHead, 返回result; 如果pre为空，返回newHead
 */
public class Problem92_ReverseLinkedListII {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        int change_len = n - m + 1; // numbers of nodes that should be reverse
        ListNode preHead = null;
        ListNode result = head;
        // find Node m
        while (head != null && --m > 0) {
            preHead = head;
            head = head.next;
        }
        ListNode modifiedListTail = head;
        ListNode newHead = null;

        // reverse linked list I
        while (head != null && change_len > 0) {
            ListNode next = head.next; // remember next node
            head.next = newHead;
            newHead = head;
            head = next;
            change_len--;
        }

        // connect three part of linkedList
        modifiedListTail.next = head;
        if (preHead != null) {
            preHead.next = newHead;
            return result;
        } else {
            return newHead;
        }
    }
}
