package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/7/31 8/17
 * @Description:
 * Medium
 *
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Note:
 *
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 *
 * 思路：
 * 1. 考察二分搜索
 * 2. 对于溢出的处理 先转换成long再转换回int
 * 3. 符号的处理
 */
public class Problem29_DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) sign = -1;
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        if (divisor == 0) return Integer.MAX_VALUE;
        if ((ldividend == 0) || (ldividend < ldivisor)) return 0;

        long lans = ldivide(ldividend, ldivisor);
        int ans;
        if (lans > Integer.MAX_VALUE) ans = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        else ans = (int) (sign * lans);
        return ans;
    }

    private long ldivide(long ldividend, long ldivisor) {
        if (ldividend < ldivisor) return 0;
        long sum = ldivisor;
        long multiple = 1;
        // use binary search; 1, 2, 4, 8, 16 ... 2^n
        while ((sum + sum) <= ldividend) {
            sum += sum;
            multiple += multiple;
        }
        // look for additional value for the multiple from the reminder recursively
        return multiple + ldivide(ldividend - sum, ldivisor);
    }

    // 8.17
    public int divide2(int dividend, int divisor) {
        int sign = 0;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) sign = 1;
        long di = Math.abs((long) dividend);
        long dv = Math.abs((long) divisor);
        long ans  = helper(di, dv);
        if (ans > Integer.MAX_VALUE) return sign == 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        return sign == 0 ? (int) ans : (int) -ans;
    }

    // binary search
    public long helper(long di, long dv) {
        if (di < dv) return 0;
        long sum = dv;
        long ans = 1;
        while (sum + sum <= di) {
            sum += sum;
            ans += ans;
        }
        return ans + helper(di - sum, dv);
    }

    @Test
    public void test() {
        System.out.println(divide2(2147483647, -1));
    }


}
