package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/7/27
 * @Description:
 * 400
 * Easy
 *
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 *
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 *
 * Example 1:
 *
 * Input:
 * 3
 *
 * Output:
 * 3
 * Example 2:
 *
 * Input:
 * 11
 *
 * Output:
 * 0
 *
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 *
 * 思路：
 * 1. 先找到目标在是几位数
 * 2. 计算目标在哪个数字
 * 3. 计算Nth Digit
 */
public class Problem400_NthDigit {
    public int findNthDigit(int n) {
        int digitNum = 1;
        long level = 9;  // remember to use long because level might overflow
        while (n - digitNum * level > 0) {
            n -= digitNum * level;
            digitNum++;
            level *= 10; // 9, 90, 900
        }
        int base = (int) Math.pow(10, digitNum - 1); // 1, 10, 100, 100
        int number = (n - 1) / digitNum + base; // find the number where nth digit locate
        int mod = (n - 1) % digitNum; // find the digit where nth digit locate at the number
        return String.valueOf(number).charAt(mod) - '0';
    }
}
