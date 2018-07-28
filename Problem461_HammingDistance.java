package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/7/28
 * @Description:
 * easy
 * hamming distance为两个数字之间二进制表示不同的比特数
 */
public class Problem461_HammingDistance {
    public int hammingDistance(int x, int y) {
        int tmp = x ^ y;
        int count = 0;
        while (tmp != 0) {
            count += tmp & 1;
            tmp = tmp >> 1;
        }
        return count;
    }
}
