package LeetCode;

import java.util.Arrays;

/**
 * Easy
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: true
 */


public class Problem252_MeetingRooms {
    //  排序后，观察是否存在interval
    public boolean canAttendMeetings(Interval[] intervals) {
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        Arrays.sort(intervals, (i1, i2)-> {
            return i1.start == i2.start ? i1.end - i2.end : i1.start - i2.start;
        });
        int i = 0;
        for (Interval in : intervals) {
            start[i] = in.start;
            end[i] = in.end;
            i++;
        }
        for (int j = 0; j < n; j++) {
            if (j == n - 1 || end[j] <= start[j + 1]) continue;
            else return false;
        }
        return true;
    }

    // 上述简写
    public boolean canAttendMeetings2(Interval[] intervals) {
        Arrays.sort(intervals, (i1, i2)-> {
            return i1.start == i2.start ? i1.end - i2.end : i1.start - i2.start;
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start) return false;
        }
        return true;
    }

    // O(n)写法
    public boolean canAttendMeetings3(Interval[] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        for (int i = 0; i < intervals.length - 1; i++) {
            if (end[i] > start[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
