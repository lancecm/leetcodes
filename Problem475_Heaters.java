package LeetCode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Srunkyo
 * @Date: 2018/7/30
 * @Description:
 * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
 *
 * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.
 *
 * So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.
 *
 * Note:
 * Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
 * Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
 * As long as a house is in the heaters' warm radius range, it can be warmed.
 * All the heaters follow your radius standard and the warm radius will the same.
 * Example 1:
 * Input: [1,2,3],[2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 * Example 2:
 * Input: [1,2,3,4],[1,4]
 * Output: 1
 * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 *
 * 思路：
 * 二分搜索
 * 或者将houses和heaters全部排序，对于每个house在heaters中确认最近radius，全局取最大radius
 *
 * TODO: 时间复杂度分析
 */
public class Problem475_Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;
        for (int house: houses) {
            // for each house, binary search its position in heaters, amd calculate its min radius inside two heaters
            int index = Arrays.binarySearch(heaters, house); // this API returns - (low + 1)
            if (index < 0) {
                index = - (index + 1); // find the index that the heater is on the right side of house
                int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE; // heater - house
                int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE; // house - heater
                result = Math.max(result, Math.min(dist1, dist2));
            }
            else {
                result = Math.max(result, 0);
            }
        }
        return result;
    }

    public int findRadius2(int[] houses, int[] heaters) {
        if (houses == null || houses.length == 0 || heaters == null || heaters.length == 0) return 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int res = 0;
        int index = 0;
        for (int house : houses) {
            while (index < heaters.length - 1 && heaters[index] + heaters[index + 1] < 2 * house) {
                index++;
            }
            res = Math.max(res, Math.abs(heaters[index] - house));
        }
        return res;
    }

    @Test
    public void test() {
        findRadius(new int[] {1,2,3}, new int[] {2});
    }
}
