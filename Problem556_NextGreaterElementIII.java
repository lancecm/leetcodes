package LeetCode;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author Srunkyo
 * @Date: 2018/8/11
 * @Description:
 * Medium
 *
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.
 *
 * Example 1:
 *
 * Input: 12
 * Output: 21
 *
 *
 * Example 2:
 *
 * Input: 21
 * Output: -1
 *
 * 思路 ~ next permutation?? 似乎以前做过类似的
 */
public class Problem556_NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        char[] number = (n + "").toCharArray();
        int i, j;
        // 1 find first digit that is bigger than the previous one
        for (i = number.length - 1; i > 0; i--) {
            if (number[i - 1] < number[i])
                break;
        }
        if (i == 0) return -1;
        // 2 find the first digit that is bigger than the "previous digit found in step 1" and also smaller than "digit found in step 1"
        int x = number[i - 1], smallest = i;
        for (j = i + 1; j < number.length; j++) {
            if (number[j] > x && number[j] <= number[smallest]) {
                smallest = j;
            }
        }
        swap(number, i - 1, smallest);
        // 3 sort digits after i - 1 in ascending order
        Arrays.sort(number, i, number.length);
        // 4 test validility
        long val = Long.parseLong(new String(number));
        return (val <= Integer.MAX_VALUE) ? (int) val : -1;
    }

    private void swap(char[] chs, int l, int r) {
        char tmp = chs[l];
        chs[l] = chs[r];
        chs[r] = tmp;
    }
    @Test
    public void test() {
//        System.out.println(nextGreaterElement(21));
//        System.out.println(nextGreaterElement(11));
//        System.out.println(nextGreaterElement(123));
//        System.out.println(nextGreaterElement(234922321));
// Integer在32位的编译环境下，存储长度为32位。即-2^31~2^31-1; 即-2,147,483,648~2,147,483,647
//        System.out.println(nextGreaterElement(2147483647));
        System.out.println(nextGreaterElement(1999999999));
        System.out.println(nextGreaterElement(241));
    }
}
