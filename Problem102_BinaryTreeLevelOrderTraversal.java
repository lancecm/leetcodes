package LeetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Srunkyo
 * @Date: 2018/7/19
 * @Description:
 * 102
 * 中
 *Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 思路：
 * BFS
 * 竟然也可以用DFS写！
 */
public class Problem102_BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> tmpList = new LinkedList<>();
            for(int i = 0; i < size; i++) {
                TreeNode tmp = q.peek();
                tmpList.add(tmp.val);
                if(tmp.left != null) q.add(tmp.left);
                if(tmp.right != null) q.add(tmp.right);
                q.poll();
            }
            result.add(tmpList);
        }
        return result;
    }


    // Very smart DFS solution!
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, root, 0);
        return res;
    }

    // 进行前序遍历的同时记录下深度，把对应深度的节点加入到对应位置的List当中去（这是一种memoization么？）
    private void helper(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) return;
        if (level == res.size()) res.add(new ArrayList<Integer>());
        res.get(level).add(root.val);
        helper(res, root.left, level + 1);
        helper(res, root.right, level + 1);
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        List<List<Integer>> result = levelOrder(root);
    }
}
