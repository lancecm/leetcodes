package LeetCode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/22 7/31
 * @Description: 95
 * 难度：medium
 * <p>
 * * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 * * Example:
 * * Input: 3
 * * Output: 5
 * * Explanation:
 * * Given n = 3, there are a total of 5 unique BST's:
 * *
 * *    1         3     3      2      1
 * *     \       /     /      / \      \
 * *      3     2     1      1   3      2
 * *     /     /       \                 \
 * *    2     1         2                 3
 * <p>
 *
 * 这题构造结果的方法比较特别：
 *   对于在left, right范围内的每一个节点作为根，分别构建出它们的左子树和右子树，再与根链接，最终返回所有的可能性
 * 思路：
 * 以i为根节点建立树，其左子树由[1, i-1]构成，右子树由[i + 1, r]
 * Each time we chose a node as root, then get all results of left trees and right trees recursively.
 * then connect them, return as an unique tree.
 * 时间复杂度： O(catalan number), exponential.
 * 空间复杂度： O(n) 开了n层栈
 */
public class Problem95_UniqueBinarySearchII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        return generateTreesHelper(1, n);
    }

    private List<TreeNode> generateTreesHelper(int left, int right) {
        List<TreeNode> result = new LinkedList<>();
        if (left > right) {
            result.add(null);
            return result;
        }

        for (int i = left; i <= right; i++) {
            List<TreeNode> lefts = generateTreesHelper(left, i - 1);
            List<TreeNode> rights = generateTreesHelper(i + 1, right);
            for (int j = 0; j < lefts.size(); j++) {
                for (int k = 0; k < rights.size(); k++) {
                    TreeNode root = new TreeNode(i); // 根节点
                    root.left = lefts.get(j); // 左子树 （可能有多种）
                    root.right = rights.get(k); // 右子树 （可能有多种）
                    result.add(root);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Problem95_UniqueBinarySearchII p = new Problem95_UniqueBinarySearchII();
        p.generateTrees(3);
    }

}
