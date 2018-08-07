package LeetCode;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Srunkyo
 * @Date: 2018/8/6
 * @Description:
 * Hard 俄罗斯套娃
 *
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 *
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Example:
 * Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 *
 * For those who do not understand why Long Increasing Subsequence (LIS) can solve the problem, I will try to explain based on my understanding.
 *
 *
 *
 * This problem is asking for LIS in two dimensions, width and height.
 * Sorting the width reduces the problem by one dimension. If width is strictly increasing,
 * the problem is equivalent to finding LIS in only the height dimension.
 * However, when there is a tie in width, a strictly increasing sequence in height may not be a correct solution.
 * For example, [[3,3] cannot fit in [3,4]]. Sorting height in descending order when there is a tie prevents
 * such a sequence to be included in the solution.
 *
 * The same idea can be applied to problems of higher dimensions. For example, box fitting is three dimensions,
 * width, height, and length. Sorting width ascending and height descending reduces the problem by one dimension.
 * Finding the LIS by height further reduces the problem by another dimension. When find LIS based on only length,
 * it becomes a standard LIS problem.
 */
public class Problem354_RussianDollEnvelopes {
    // 将其中一个维度排序，确保从小到大的顺序，则需要在另一个维度中寻找最大递增子序列，最终转换为LIS问题
    // O(n^2)
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0 || envelopes[0].length != 2) return 0;
        Arrays.sort(envelopes, (a, b) -> (a[0] - b[0]));
        int max = 0;
        int dp[] = new int[envelopes.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    // 或者使用覆盖法
    // O(logN)
    // 本次采用Arrays.BinarySearch内置算法
    /**
     * @return index of the search key, if it is contained in the array
     *         within the specified range;
     *         otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element in the range greater than the key,
     *         or <tt>toIndex</tt> if all
     *         elements in the range are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     *         总结：binarySearch()方法的返回值为：
     *         1、如果找到关键字，则返回值为关键字在数组中的位置索引，且索引从0开始
     *         2、如果没有找到关键字，返回值为负的插入点值，所谓插入点值就是第一个比关键字大的元素在数组中的位置索引，而且这个位置索引从1开始。
     * */
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes.length == 0 || envelopes[0].length != 2) return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o2[1] - o1[1]; // 如果相等，把大的放前
                else return o1[0] - o2[0];
            }
        });
        int res[] = new int[envelopes.length];
        int len = 0;
        for (int[] envelope: envelopes) {
            int index = Arrays.binarySearch(res, 0, len, envelope[1]);
            if (index < 0) // doesn't find
                index = -(index + 1);
            res[index] = envelope[1];
            if (index == len) len++;
        }
        return len;
    }

    @Test
    public void test() {
        int res = maxEnvelopes2( new int[][] {{4,5},{4,6},{6,7},{2,3},{1,2}}  );
        System.out.println(res);
    }
}
