package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/13
 * @Description:
 * Medium
 *
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:
 *
 * The number at the ith position is divisible by i.
 * i is divisible by the number at the ith position.
 * Now given N, how many beautiful arrangements can you construct?
 *
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation:
 *
 * The first beautiful arrangement is [1, 2]:
 *
 * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 *
 * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 *
 * The second beautiful arrangement is [2, 1]:
 *
 * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 *
 * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 * Note:
 * N is a positive integer and will not exceed 15.
 *
 * 思路：
 * Back Tracing
 * 每次从1~N中选择一个数字，并且需要满足条件：该数字除以位置或者位置除以该数字余0 ： i % pos == 0 || pos % i == 0
 * 使用回溯法，若数字能选全证明是一个beautiful arrangement
 */
public class Problem526_BeautifulArrangement {
    int count = 0;

    public int countArrangement(int N) {
        if (N == 0) return 0;
        helper(N, 1, new int[N + 1]);
        return count;
    }

    private void helper(int N, int pos, int[] used) {
        if (pos > N) {
            count++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (used[i] == 0 && (i % pos == 0 || pos % i == 0)) {
                used[i] = 1;
                helper(N, pos + 1, used);
                used[i] = 0;
            }
        }
    }

    @Test
    public void test() {
        // 1 2 3
        // 2 1 3
        // 3 2 1
        System.out.println(countArrangement(3));
    }
}
