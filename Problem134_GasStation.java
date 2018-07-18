package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/18 7/18
 * @Description: 134
 * 难度：medium
 * 类型：Array
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 *
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 *
 * Note:
 *
 * If there exists a solution, it is guaranteed to be unique.
 * Both input arrays are non-empty and have the same length.
 * Each element in the input arrays is a non-negative integer.
 * Example 1:
 *
 * Input:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * Output: 3
 *
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 * Example 2:
 *
 * Input:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * Output: -1
 *
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 * 思路：
 * 如果一个数组的总和非负，那么一定可以找到一个起始位置，从他开始绕数组一圈，累加和一直都是非负的
 * 注意到这样一个现象：
 * <p>
 * 1. 假如从位置i开始，i+1，i+2...，一路开过来一路油箱都没有空。说明什么？说明从i到i+1，i+2，...肯定是正积累。
 * 2. 现在突然发现开往位置j时油箱空了。这说明什么？
 * 说明从位置i开始没法走完全程(废话)。
 * 那么，我们要从位置i+1开始重新尝试吗？不需要！为什么？
 * 因为前面已经知道，位置i肯定是正积累，那么，如果从位置i+1开始走更加没法走完全程了，
 * 因为没有位置i的正积累了。同理，也不用从i+2，i+3，...开始尝试。
 * 所以我们可以放心地从位置j+1开始尝试。
 */
public class Problem134_GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int total = 0;
        int diff = 0;
        for(int i = 0; i < gas.length; i++) {
            diff += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if(diff < 0) {
                // counting the diff start at new position again
                diff = 0;
                start = i + 1;
            }
        }
        return total >= 0 ? start : -1;
    }
}
