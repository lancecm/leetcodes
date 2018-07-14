package LeetCode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/14
 * @Description:
 * 56
 * Medium
 *
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considerred overlapping.
 *
 * 思路：先排序，后去重 O(nlogn) + O(n) -> O(nlogn)
 */
public class Problem56_MergeIntervals {
    public class Interval {
        int start;
        int end;
        Interval() {
            start = 0;
            end = 0;
        }
        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new LinkedList<>();
        if (intervals.size() <= 1) return intervals;
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
        for (int i = 0; i < intervals.size() - 1;) {
            Interval a = intervals.get(i);
            Interval b = intervals.get(i + 1);
            if (overlap(a, b)) {
                intervals.remove(i);
                intervals.set(i, new Interval(a.start, Math.max(a.end, b.end)));
            }
            else {
                result.add(a);
                i++;
            }
        }
        result.add(intervals.get(intervals.size() - 1)); // add last interval
        return result;
    }

    public boolean overlap(Interval a, Interval b) {
        if (a.end >= b.start) return true;
        else return false;
    }


    // ---------------------------------------------------
    public void print(List<Interval> list) {
        for (Interval i : list){
            System.out.println(i.start + "->" + i.end + " ");
        }
        System.out.println();
    }

    @Test
    public void test() {
        Interval i1 = new Interval(1,4);
        Interval i2 = new Interval(2,3);
//        Interval i3 = new Interval(8,10);
//        Interval i4 = new Interval(15,18);
        List<Interval> intervals = new LinkedList<>();
        intervals.add(i1);
        intervals.add(i2);
//        intervals.add(i3);
//        intervals.add(i4);
        List<Interval> result = merge(intervals);
        print(result);
    }

}
