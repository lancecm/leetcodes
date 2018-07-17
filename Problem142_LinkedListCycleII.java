package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/7
 * @Description:
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * Note: Do not modify the linked list.
 *
 * Follow up:
 * Can you solve it without using extra space?
 *
 * 1. 找到快慢指针相交点meet
 * 2. 从head和meet出发，直到二者相等，相等点为循环开始点。
 *
 * 证明：
 * 若有环, 设置head到环开始(start)距离为a, start到meet距离为b，meet到start距离为c
 * 则：快指针走了： a + b + c + b
 * 慢指针走了：    a + b
 * 由速度： 2(a + b) = a + b + c + b
 * 则：    a = c
 * 所以分别从meet和start同速度走直到指针相遇即可。
 */
public class Problem142_LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode meet = head;
        boolean hasCycle = false;
        // find meet point
        while(fast!= null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                meet = fast;
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) return null;
        while(head != meet) {
            head = head.next;
            meet = meet.next;
        }
        return head;
    }
}
