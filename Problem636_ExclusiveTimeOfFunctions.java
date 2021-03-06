package LeetCode;

import java.util.List;
import java.util.Stack;

/**
 * @author Srunkyo
 * @Date: 2018/8/14
 * @Description:
 * Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.
 *
 * Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.
 *
 * A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.
 *
 * Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.
 *
 * Example 1:
 * Input:
 * n = 2
 * logs =
 * ["0:start:0",
 *  "1:start:2",
 *  "1:end:5",
 *  "0:end:6"]
 * Output:[3, 4]
 * Explanation:
 * Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1.
 * Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
 * Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time.
 * So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
 * Note:
 * Input logs will be sorted by timestamp, NOT log id.
 * Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
 * Two functions won't start or end at the same time.
 * Functions could be called recursively, and will always end.
 * 1 <= n <= 100
 */
public class Problem636_ExclusiveTimeOfFunctions {
    // 方法1：
    // 栈中存放元素为[idx，start time]。遇到start事件入栈，遇到end事件计算到上一个start时间的相距时间，并放入答案中。如果这个事件是递归的，则外部呼叫它的事件需要减少相应的等待时间长度。
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<int[]> st = new Stack<>();
        for (String log : logs) {
            String[] s = log.split(":");
            int idx = Integer.parseInt(s[0]);
            int time = Integer.parseInt(s[2]);
            if (s[1].equals("start")) {
                st.push(new int[] {idx, time});
            }
            else {
                int val = time - st.pop()[1] + 1;
                res[idx] += val;
                if (!st.isEmpty()) res[st.peek()[0]] -= val;
            }
        }
        return res;
    }

    // 方法2：
    // 采用pre标记每一个时间段的起点
    public int[] exclusiveTime2(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        int pre = 0;
        for (String log : logs) {
            String[] a = log.split(":");
            int idx = Integer.valueOf(a[0]);
            int t = Integer.valueOf(a[2]);
            if (a[1].equals("start")) {
                if(!st.isEmpty()) res[st.peek()] += t - pre;
                st.push(idx);
                pre = t;
            }
            else {
                res[st.pop()] += t - pre + 1;
                pre = t + 1;
            }
        }
        return res;
    }
}
