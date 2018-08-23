package LeetCode;


import org.junit.Test;

import java.util.HashMap;

/**
 * @author Srunkyo
 * @Date: 2018/7/30
 * @Description: Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * 思路：Use Hashtable for fast mapping and double linked list for fast manipulation.
 */
public class Problem146_LRUCache {
    private class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode next;
    }

    private HashMap<Integer, DLinkedNode> cache = new HashMap<>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public Problem146_LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        this.head = new DLinkedNode();
        this.tail = new DLinkedNode();
        this.head.next = tail;
        this.tail.pre = head;
    }

    public Problem146_LRUCache() {

    }

    // Add the node to the first position
    private void addNode(DLinkedNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    private void moveToHead(DLinkedNode node) {
        this.removeNode(node);
        this.addNode(node);
    }

    private DLinkedNode popTail() {
        DLinkedNode res = tail.pre;
        this.removeNode(res);
        return res;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1; // Exception
        }
        this.moveToHead(node);
        return node.value;
    }

    public void put(int key, int val) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = val;
            this.cache.put(key, newNode);
            this.addNode(newNode);
            ++ count;
            if (count > capacity) {
                DLinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
                --count;
            }
        }
        else {
            node.value = val;
            this.moveToHead(node);
        }
    }


    public static void main(String[] args)  {
        Problem146_LRUCache cache = new Problem146_LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }

    // 8.24 ver
    /*
        // 思路：HashTable-> 迅速找到key value; Double linked list -> 操作顺序、删除等。
    private class Node {
        Node pre;
        Node next;
        int key;
        int val;
    }
    int capacity;
    int count;
    Node head, tail;
    HashMap<Integer, Node> cache = new HashMap<>();


    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = tail;
        this.tail.next = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        // move to head
        moveToHead(node);
        // return value
        return node.val;
    }

    private void moveToHead(Node node) {
        // remove node from current position
        node.pre.next = node.next;
        node.next.pre = node.pre;

        // add to front
        addToFront(node);
    }

    private void addToFront(Node node) {
        node.next = head.next;
        head.next.pre = node;
        node.pre = head;
        head.next = node;
    }

    private void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        count--;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            // node doesn't exist.
            // Put the new node in the front
            Node tmp = new Node();
            tmp.key = key;
            tmp.val = value;
            cache.put(key, tmp);
            addToFront(tmp);
            // If capacity is full, delete the last node
            count += 1;
            if (count > capacity) {
                cache.remove(tail.pre.key);
                remove(tail.pre);
            }
        }
        else {
            // Update value
            node.val = value;
            // Put visited node to the front.
            moveToHead(node);
        }
    }
     */

}
