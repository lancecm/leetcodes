package LeetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/16
 * @Description:
 * 406
 * Medium
 *
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 *
 * Example
 *
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 * 题意：
 * (h,k)分别表示此人身高，和排在他前面身高大于等于他的人数
 *
 * 思路：
 * 1. 找出身高最高的人按照k进行排列
 * 2. 每次找到次高的人按照k插入到排列中
 * --> 先按照身高降序排列，相同身高的按照人数升序排列
 */
public class Problem406_QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1]: b[0] - a[0]);
        List<int[]> result = new LinkedList();
        for(int i = 0; i < people.length; i++) {
            result.add(people[i][1], people[i]);
        }
        return result.toArray(new int[result.size()][]);
    }
}
