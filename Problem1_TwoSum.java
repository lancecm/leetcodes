package LeetCode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/13
 * @Description:
 * 1
 * Easy
 */
public class Problem1_TwoSum {
    // 普通做法
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return  result;
    }

    // 使用HashSet
    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>(); // value and its index
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    @Test
    public void test() {
        int[] num = new int[] {1,2,4,5,7};
        int target = 8;
        twoSum2(num, target);
    }
}
