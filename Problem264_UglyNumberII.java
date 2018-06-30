package LeetCode;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/29
 * @Description: 264
 * medium
 * <p>
 * Write a program to find the n-th ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * <p>
 * Example:
 * <p>
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note:
 * <p>
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 *
 * 时间复杂度：~ O(n)
 * 思路1：动态规划
 * 新生成的丑陋数都是上一个丑陋数乘上2，3，5
 * 因此用三个指针标记没有被2，3，5乘过的丑陋数
 * 每次比较这三个指针所表示的丑陋数分别乘上2，3，5的结果，取最小的那一个，放入动态规划数组中，相应的指针向后移动一个；
 * 直到动态规划数组填满为止，返回最后一个结果。
 *
 * 思路2：使用priority queue
 */
public class Problem264_UglyNumberII {
    public int nthUglyNumber(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        int i = 0, j = 0, k = 0;
        for (int m = 1; dp[n - 1] == 0;) {
            int tempt = Math.min(dp[i] * 2, Math.min(dp[j] * 3, dp[k] * 5));
            if (tempt == dp[i] * 2) i++;
            if (tempt == dp[j] * 3) j++;
            if (tempt == dp[k] * 5) k++;
            dp[m++] = tempt;
        }
        return dp[n - 1];
    }

    // use priority queue
    // 优先队列每次只弹出最小元素
    // poll之后调整堆结构的时间复杂度为？
    // 该做法实质上就是省略了排序，让数据结构自己用堆进行排序和去重，用最小的生成完新数据后把最小的扔掉
    // 然而有一点不好：多生成一些很大的数据，占用额外的空间。需要用long; 效率极低。！！！ 10%
    public int nthUglyNumber2(int n) {
        if (n == 0) return 0;
        PriorityQueue<Long> q = new PriorityQueue<>();
        q.add(1l);
        for (int i = 1; i < n; i++) {
            long tmp = q.poll(); // the top one
            while (!q.isEmpty() && q.peek() == tmp) tmp = q.poll(); // 一些一样的节点
            q.add(tmp * 2);
            q.add(tmp * 3);
            q.add(tmp * 5);
        }
        return q.poll().intValue();
    }

    @Test
    public void test() {
        System.out.println(nthUglyNumber(0));
        System.out.println(nthUglyNumber(1));
        System.out.println(nthUglyNumber(10));
        System.out.println(nthUglyNumber(11));
        System.out.println(nthUglyNumber(12));
    }

    @Test
    public void test2() {
        System.out.println(nthUglyNumber2(0));
        System.out.println(nthUglyNumber2(1));
        System.out.println(nthUglyNumber2(10));
        System.out.println(nthUglyNumber2(11));
        System.out.println(nthUglyNumber2(12));
        System.out.println(nthUglyNumber2(1407));
    }


}
