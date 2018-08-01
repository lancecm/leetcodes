package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/8/1
 * @Description:
 * Medium
 *
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 * Credits:
 * Special thanks to @dietpepsi for adding this problem and creating all test cases.
 *
 * DFS - 动态规划：
 * 对于一个高度为2的数，如果抢根节点，则不能抢叶节点；如果抢叶节点（分别取以叶节点为根时抢或者不抢的最大值），则不能抢根节点。
 * TODO：时间复杂度 空间复杂度？
 */
public class Problem337_HouseRobberIII {
    public int rob(TreeNode root) {
        int[] res = getMoney(root);
        return Math.max(res[0], res[1]);
    }

    private int[] getMoney(TreeNode node) {
        int[] res = new int[2]; // res[0]->rob res[1]->notrob
        if (node == null) return res;
        int[] lres = getMoney(node.left);
        int[] rres = getMoney(node.right);
        res[0] = lres[1] + rres[1] + node.val;
        res[1] = Math.max(lres[0], lres[1]) + Math.max(rres[0], rres[1]);
        return res;
    }
}
