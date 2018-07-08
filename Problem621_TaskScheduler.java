package LeetCode;

import java.util.Arrays;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/8
 * @Description:
 * 621
 * Medium
 *
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * Note:
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 *
 * 关键：模拟CPU任务分配，A 到 Z表示不同的任务，任务可以以不同顺序进行。
 * 每个任务可以在一个时间间隔中完成。对于一个时间间隔，CPU可以执行一个任务或者是闲置。
 * 但是，两个同样的任务之间需要有 n 个冷却时间，也就是说假如A执行后，那么未来n个时间间隔内，A是不允许再执行的。
 *
 * TODO: 其他算法
 */
public class Problem621_TaskScheduler {
    // 1. 插数法
    // 先统计出频率最大的task的频率k，以它计算所需区间（k - 1）(n + 1) 最后还差最大频率的task们放在任务结尾即可
    public int leastInterval(char[] task, int n) {
        int[] c = new int[26];
        for (char t : task) {
            c[t - 'A'] ++; // Frequency Table
        }
        Arrays.sort(c); // Sort tasks by frequency
        // tasks that are not with maximum frequency!
        int k = 25;
        while (k >= 0 && c[k] == c[25]) k--;
        // if all the idle intervals are filled -> the length is size of char list
        // 25 - k: number of tasks with maximum frequency
        return Math.max(task.length, (c[25] - 1) * (n + 1) + 25 - k);
    }
}
