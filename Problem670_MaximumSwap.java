package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/14
 * @Description:
 * Medium
 *
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
 *
 * Example 1:
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * Example 2:
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 * Note:
 * The given number is in the range [0, 108]
 *
 */
public class Problem670_MaximumSwap {

    // 自己的写法:
    // 从头右到左保存digit右侧最大的digit和其对应的位置
    public int maximumSwap(int num) {
        char[] chs = (num + "").toCharArray();
        int[][] tmp = new int[chs.length][2]; // val, pos

        int pos = chs.length - 1;
        char val = chs[pos];
        for (int i = chs.length - 1; i >= 0; i--) {
            if (chs[i] > val) {
                val = chs[i];
                pos = i;
            }
            tmp[i][0] = val - '0';
            tmp[i][1] = pos;
        }

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i][0] > chs[i] - '0') {
                int p = tmp[i][1];
                char t = chs[i];
                chs[i] = chs[p];
                chs[p] = t;
                break;
            }
        }

        return Integer.parseInt(String.valueOf(chs));
    }


    // 使用桶记录下数字0-9在Num中最后一次出现的位置
    // 从数字的左侧遍历到右侧，看是否存在比当前数字大的数字在右侧
    public int maximumSwap2(int num) {
        char[] A = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < A.length; i++) {
            last[A[i] - '0'] = i;
        }

        for (int i = 0; i < A.length; i++) {
            for (int d = 9; d > A[i] - '0'; d--) {
                if (last[d] > i) {
                    char tmp = A[i];
                    A[i] = A[last[d]];
                    A[last[d]] = tmp;
                    return Integer.valueOf(new String(A));
                }
            }
        }
        return num;
    }
    @Test
    public void test() {
        System.out.println(maximumSwap(2736));
        System.out.println(maximumSwap(9973));
    }

}
