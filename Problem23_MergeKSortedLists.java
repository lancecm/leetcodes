package LeetCode;

import java.util.PriorityQueue;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/4
 * @Description: Merge k sorted linked lists and return it as one sorted list.
 * Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 * <p>
 * 思路：
 * 1. 使用分治法（两两合并）
 * 2. PriorityQueue （推荐 通用解法，例如MergeKSortedArray）
 */
public class Problem23_MergeKSortedLists {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    // Solution 1;
    // 分治法，两两合并直至变为一个linkedlist; merge 函数是21为MergeTwoSortedLists中的答案
    // 时间复杂度： O(nlogk), k为链表的数量 （画图分析即可）
    // 空间复杂度： O(n)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }

    public ListNode sort(ListNode[]lists, int lo, int hi) {
        if (lo >= hi) return lists[lo];
        int mid = (hi - lo) / 2 + lo;
        ListNode l1 = sort(lists, lo, mid);
        ListNode l2 = sort(lists, mid + 1, hi);
        return merge(l1, l2);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        }
        else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    // Priority Queue
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        ListNode dummy = new ListNode(0); // head
        ListNode cur = dummy;
        for (ListNode list : lists)
            if (list != null)
                queue.add(list); // add each list's first node
        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
            if (cur.next != null)
                queue.add(cur.next);
        }
        return dummy.next;
    }

}
