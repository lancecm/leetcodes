package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Srunkyo
 * @Date: 2018/8/16
 * @Description:
 *
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 *
 * 链表的深拷贝：
 * 先用hashmap保存好原先节点到新结点的对应关系   旧节点 - 新结点
 * 再遍历一遍，构建好新节点next的关系和random指针的关系
 */
public class Problem138_CopyListWithRandomPointer {
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) {
            this.label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        // copy all the nodes
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }

        // assign next and random pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }

}
