package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Srunkyo
 * Date: 2018-10-07
 * Time: 3:27 PM
 * Description:
 * Hard 其实没有Hard难度。
 *
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 */
public class Problem57_InsertInterval {
    // 三步走战略。
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        if (intervals.size() == 0 && newInterval == null) return result;
        if (intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }
        int i = 0;
        // add previous intervals that won't be affected by newInterval
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            result.add(intervals.get(i));
            i++;
        }
        while (i < intervals.size() && newInterval.end >= intervals.get(i).start) {
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end   = Math.max(intervals.get(i).end,   newInterval.end);
            i++;
        }
        result.add(newInterval);
        while (i < intervals.size()) {
            result.add(intervals.get(i));
            i++;
        }
        return result;
    }
}
