package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/21 7/31
 * @Description: 96
 * 难度：medium
 * <p>
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 思路：以某一个序号为根，其唯一二叉树个数位左右子种类个数相乘
 *      F(i, n) = G(i - 1) * G(n - i)
 *      必须以动态规划的方法解，否则会超时
 * Time：
 * Space：
 */
public class Problem96_UniqueBinarySearch {
    // 注意：这种写法太过naive, 很容易超出时间范围
    public int numTrees(int n) {
        if (n < 1) return 1; // base case
        int num = 0;
        for (int i = 1; i <= n; ++i) {
            num += numTrees(i - 1) * numTrees(n - i);
        }
        return num;
    }

    // 改写成动态规划 DP -- 自顶向下version
    public int numTreesDP(int n) {
        int[] dp = new int[n + 1]; // 多一个节点无用
        dp[0] = 1;
        dp[1] = 1;
        return numTreesDP(n, dp);
    }

    //自顶向下
    private int numTreesDP(int n, int[] dp) {
        if (n < 1) return dp[1];
        int num = 0;
        for (int i = 1; i <= n; i++) {
            int left = dp[i - 1];
            int right = dp[n - i];
            if (left == 0) {
                dp[i - 1] = numTreesDP(i - 1, dp);
                left = dp[i - 1];
            }
            if (right == 0) {
                dp[n - i] = numTreesDP(n - i, dp);
                right = dp[n - i];
            }
            num += left * right;
        }
        return num;
    }

    // 自底向上版本
    public int numTreesDPBottomUp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) { // calculate each node
            for (int j = 1; j <= i; j++) { // for each node, calculate its tree number
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    // Catalan Number  f(n) = 1/(n+1) * C(n, 2n)
    public int numTreesCatalan(int n) {
        long result = 1;
        for (int i = 2 * n; i > n; i--) {
            result *= i;
        }
        for (int i = n; i > 1; i--) {
            result /= i;
        }
        return (int) result/(n+1);
    }

    public static void main(String[] args) {
        Problem96_UniqueBinarySearch p = new Problem96_UniqueBinarySearch();
        System.out.println(p.numTreesDP(5));
        System.out.println(p.numTrees(5));
        System.out.println(p.numTreesDPBottomUp(5));
        System.out.println(p.numTreesCatalan(5));
    }

}
