package LeetCode;

/**
 * Author: Srunkyo
 * Date: 2018-11-12
 * Time: 11:44 PM
 * Description:
 */
public class Problem237_DeleteAListNodeInALinkedList {
    public void deleteNode(ListNode node) {
        // delete the node itself, just by replace itself with the next node.
        node.val = node.next.val;
        node.next = node.next.next;
    }
    // 06/30/2019 -》 same code.
}
