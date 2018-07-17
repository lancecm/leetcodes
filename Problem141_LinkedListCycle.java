package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/17
 * @Description:
 * Given a linked list, determine if it has a cycle in it.
 *
 * Follow up:
 * Can you solve it without using extra space?
 *
 * 思路：
 * 1. hashset
 * 2. fast and slow pointer
 */
public class Problem141_LinkedListCycle {
    // hashset
    public boolean hasCycle(ListNode head) {
        Set<ListNode> hash = new HashSet<>();
        boolean result = true;
        while(head != null) {
            if(hash.contains(head)) return false;
            hash.add(head);
            head = head.next;
        }
        return true;
    }

    // fast and slow pointer
    public boolean hasCycle2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(slow != null && fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
