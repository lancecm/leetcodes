package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/1
 * @Description:
 * Medium
 *
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 *
 * Example:
 *
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 *              received 3, 0, 6, 1, 5 citations respectively.
 *              Since the researcher has 3 papers with at least 3 citations each and the remaining
 *              two with no more than 3 citations each, her h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 *
 * 思路：
 * 计数排序 counting sort
 * 计算各论文因用量出现的次数，超过论文数量的引用频次看作是引用n次，从后向前计算论文数量
 * 若论文数量超过或者等于index，则此时的index为H index。表示至少有h篇论文的引用数量超过了h
 *
 * https://leetcode.com/problems/h-index/discuss/70768/Java-bucket-sort-O(n)-solution-with-detail-explanation
 * "the h-index is defined as the number of papers with reference greater than the number."
 * "The reason to scan from the end of the array is that we are looking for the greatest h-index."
 */
public class Problem274_HIndex {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1];
        for (int i : citations) {
            int index = i > n ? n : i;
            count[index] += 1;
        }
        int num = 0;
        for (int i = n; i >= 0; i--) {
            num += count[i];
            if (num >= i) {
                return i;
            }
        }
        return 0;
    }

    @Test
    public void test() {
        System.out.println(hIndex(new int[] {0,0,6,1,5}));
    }
}
