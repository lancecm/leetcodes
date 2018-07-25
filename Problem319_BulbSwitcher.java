package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/7/24
 * @Description:
 * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
 *
 * Example:
 *
 * Input: 3
 * Output: 1
 * Explanation:
 * At first, the three bulbs are [off, off, off].
 * After first round, the three bulbs are [on, on, on].
 * After second round, the three bulbs are [on, off, on].
 * After third round, the three bulbs are [on, off, off].
 *
 * So you should return 1, because there is only one bulb is on.
 *
 * 思路：
 * Math
 * 每个灯泡开关被按的次数等于编号的约数次数，最终如果灯泡亮起，证明有奇数个约数
 * 一个数有奇数个约数，等价于为它有平方数
 * 设共有n个灯泡，则从1到n共有根号n个平方数
 */
public class Problem319_BulbSwitcher {
    // TLE
    public int bulbSwitch2(int n) {
        int[] mask = new int[n];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n; j++) {
                if ((j + 1) % i == 0) {
                    if (mask[j] == 0) mask[j] = 1;
                    else mask[j] = 0;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += mask[i];
        }
        return sum;
    }

    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }

    @Test
    public void test() {
        System.out.println(bulbSwitch(121));
    }

}
