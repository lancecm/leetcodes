package LeetCode;

import org.junit.Test;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/15
 * @Description:
 * 326
 * 易
 * 
 * Given an integer, write a function to determine if it is a power of three.
 *
 * Example 1:
 *
 * Input: 27
 * Output: true
 * Example 2:
 *
 * Input: 0
 * Output: false
 * Example 3:
 *
 * Input: 9
 * Output: true
 * Example 4:
 *
 * Input: 45
 * Output: false
 * Follow up:
 * Could you do it without using any loop / recursion?
 *
 * 思路：无它，注意判断特殊case
 * 不用循环写法：
 */
public class Problem326_PowerOfThree {
    public boolean isPowerOfThree(int n) {
        if(n <= 0) return false;
        if(n % 3 != 0) return false;
        int lastDigit = n % 10;
        if (lastDigit!=1 && lastDigit!=3 &&lastDigit!=7 &&lastDigit!=9) return false;
        while (n % 3 == 0) {
            n = n / 3;
        }
        return n == 1;
    }

    // without iteration and recursion
    public boolean isPowerOfThree2(int n) {
        // 3^(log_3_MAXINT)
        int maxPowerOfThree = (int) Math.pow(3, (int)(Math.log(Integer.MAX_VALUE) / Math.log(3)));
        return (n > 0 && maxPowerOfThree % n == 0);
        // 3^19 = 1162261467 3^20 > Integer.MAX_VALUE
        // return (n > 0 && 1162261467 % n == 0);
    }

    // recursive
    public boolean isPowerOfThree3(int n) {
        return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree(n / 3)));
    }

    @Test
    public void test() {
        System.out.println(isPowerOfThree(3));
        System.out.println(isPowerOfThree(0));
        System.out.println(isPowerOfThree(27));
        System.out.println(isPowerOfThree(1));
        System.out.println(isPowerOfThree(45));
    }

    @Test
    public void test2() {
        System.out.println(isPowerOfThree2(3));
        System.out.println(isPowerOfThree2(0));
        System.out.println(isPowerOfThree2(27));
        System.out.println(isPowerOfThree2(1));
        System.out.println(isPowerOfThree2(45));
    }
}
