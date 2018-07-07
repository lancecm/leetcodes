package LeetCode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/7
 * @Description:
 * 654
 * Medium
 *
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 *
 * Example 1:
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 * Note:
 * The size of the given array will be in the range [1,1000].
 *
 * 思路1:
 * 分治法
 * Time：O(n^2) TODO： 这个对么？
 * Space: O(n)
 * 思路2：
 * 遍历法，用了一个栈的结构；栈顶是之前一个节点
 */
public class Problem654_MaximumBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    // Solution1： 灰常直观的分治法 O（N^2）
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return helper (nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int l, int r) {
        if (l > r) return null;
        int maxIndex = findMaxIndex(nums, l, r);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = helper(nums, l, maxIndex - 1);
        root.right = helper(nums, maxIndex + 1, r);
        return root;
    }

    private int findMaxIndex(int[] nums, int l, int r) {
        int index = l;
        for (int i = l + 1; i <= r; i++)
            if (nums[i] > nums[index]) index = i;
        return index;
    }

    // Solution2： 别人的O(N)解法； 总体上没有上一个快。
    // 第一次想到的一次遍历法跟这个差不多。。
    // 新插入的节点cur和上一个节点pre进行比较
    // 1. cur < pre, pre.right = cur
    // 2. cur > pre, 找出之前最大点，作为其左节点
    // 整体效果是保持栈元素从底到顶部是递减的
    // 时间复杂度：最坏O(N^N) (找到次大节点同要需要循环比较)  TODO: 对么。。？
    //            Amortized => O(N)
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        Deque<TreeNode> stack = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            TreeNode cur = new TreeNode(nums[i]);
            while (!stack.isEmpty() && stack.peek().val < nums[i]) {
                cur.left = stack.poll();
            }
            if (!stack.isEmpty()) stack.peek().right = cur;
            stack.push(cur);
        }
        return stack.isEmpty() ? null : stack.removeLast();
    }

    @Test
    public void test() {
        int[] nums = {3,2,1,6,0,5};
        constructMaximumBinaryTree(nums);
        constructMaximumBinaryTree2(nums);
    }
}
