package LeetCode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Srunkyo
 * Date: 2018-10-07
 * Time: 1:27 PM
 * Description:
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 */
public class Problem253_MeetingRoomsII {
    // 排序法 类似记录栈所需最大空间，如果是start进栈。如果是end就出栈。
    public int minMeetingRooms(Interval[] intervals) {
        List<int[]> ins = new ArrayList<>();
        for (Interval i : intervals) {
            ins.add(new int[]{i.start, 0});
            ins.add(new int[]{i.end, 1});
        }
        Collections.sort(ins, (i1, i2) -> {
            if (i1[0] == i2[0]) return i2[1] - i1[1]; // exit come first
            else return i1[0] - i2[0];
        });
        int room = 0;
        int maxRoom = 0;
        for (int[] in : ins) {
            if (in[1] == 0) room++;
            else room--;
            maxRoom = Math.max(room, maxRoom);
        }
        return maxRoom;
    }

    // O(n)解法
    public int minMeetingRooms2(Interval[] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        int room = 0;
        int endIndex = 0;
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        for (int i = 0; i < intervals.length; i++) {
            if (start[i] < end[endIndex]) {
                // have intervals, extend room
                room++;
            }
            // can be placed into previous rooms.
            else endIndex++;
        }
        return room;
    }
}
