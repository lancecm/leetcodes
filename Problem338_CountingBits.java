package LeetCode;

import org.junit.Test;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/1
 * @Description:
 * 338
 * Medium
 *
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 *
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 *
 * 思路：
 * 方法1：
 * 观察每个数字的二进制，发现有这种规律 0, 1, 2-3(2)个, 4-7（4个）, 8-15（8个）, 16-31（16个）...每后一组数的二进制表示是前面所有数的二进制表示前面多加了个1
 * 0000
 *
 * 0001
 *
 * 0010
 * 0011
 *
 * 0100
 * 0101
 * 0110
 * 0111
 *
 * 1000
 * 1001
 * 1010
 * 1011
 * 1100
 * 1101
 * 1110
 * 1111
 *
 * 方法2：
 * 发现存在公式： f[i] = f[i/2] + i % 2
 *
 * 时间复杂度： O(N)
 * 空间复杂度： O(N)
 */
public class Problem338_CountingBits {
    // 方法一：找规律 动态规划
    public int[] countBits(int num) {
        if (num == 0) return new int[]{0};
        int dp[] = new int[num + 1];
        for (int i = 1, count = 1, tmp = 0; i <= num; i++) {
            dp[i] = 1 + dp[i - count];
            tmp += 1;
            if (tmp == count) {
                count *= 2;
                tmp = 0;
            }
        }
        return dp;
    }

    // 另一种牛逼的动态规划解法！！！
    // f[i] = f[i/2] + i % 2;
    // 某数的1bit量 = 截去其最后一位形成数字的1bit量 + 最后一位1bit量
    // 举例： 10011001 -》 1001100  1
    public int[] countBits2(int num) {
        int[] f = new int[num + 1];
        for (int i = 1; i <= num; i++)
            f[i] = f[i>>1] + (i & 1);
        return f;
    }

    public void print(int [] dp) {
        for (int i : dp){
            System.out.print(i);
        }
        System.out.println();
    }

    @Test
    public void test() {
        print(countBits(0));
        print(countBits(1));
        print(countBits(2));
        print(countBits(5));
    }

    @Test
    public void test2() {
        print(countBits2(0));
        print(countBits2(1));
        print(countBits2(2));
        print(countBits2(5));
    }

}
