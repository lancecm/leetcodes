package LeetCode;

import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/30
 * @Description:
 * 50
 * Medium
 *
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 *
 * Example 1:
 *
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 * Note:
 *
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 *
 * 思路：
 * 注意！不涉及到大数问题！要求并不是来仿写加法乘法平方程序！！！！
 * 1. 用循环解决
 * 2. 为了缩短耗时，观察到 a^b = (a * a) ^ b/2 || (a * a) ^ b /2 * a
 * **. 当n % 2 == 0时，无需乘x或1/x
 *     当n < 0 且n % 2 != 0时，需要在末尾乘上一个 1/x
 *     当n > 0 且n % 2 != 0时，在末尾乘上一个x
 */
public class Problem50_Powxn {

    // double x 版本
    public double myPow(double x, int n) {
        if (n==0) return 1;
        return myPow(x*x,n/2)*(n%2==0?1:n>0?x:1/x);
    }

    // double myPow 版本
    public double myPow2(double x, int n) {
        if (n == 0) return 1;
        double result = myPow2(x, n/2);
        if (n % 2 == 0) return result * result;
        else return n < 0 ? result * result * 1/x : result * result * x;
    }

    // 自下而上:
    // 注意：这种写法有问题，test case 2.00000 -2147483648 时会溢出 WHY?
    public double myPow3(double x, int n) {
        if(n==0) return 1;
        if(n<0) {
            n = -n;
            x = 1/x;
        }
        double ans = 1;
        while(n>0){
            if((n&1) == 1) ans *= x;
            x *= x;
            n >>= 1;
        }
        return ans;
    }

    @Test
    public void test() {
        System.out.println(myPow3(2,4));
    }
}
