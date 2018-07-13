package LeetCode;

import org.junit.Test;

import java.util.*;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/13
 * @Description: 15
 * Medium
 * <p>
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class Problem15_3Sum {

    // 两重枚举
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        Set<Integer> hash = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            hash.clear();
            visited.clear();
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (hash.contains(-(nums[i] + nums[j]))) {
                        result.add(Arrays.asList(nums[i], nums[j], -(nums[i] + nums[j])));
                        visited.add(-(nums[i] + nums[j]));
                    } else {
                        hash.add(nums[j]);
                    }
                    if (visited.contains(-(nums[i] + nums[j]))) {
                        hash.remove(-(nums[i] + nums[j]));
                    }
                }
            }
        }
        return result;
    }

    /*
    尝试不使用集合操作来判重。
    同样是一重循环st无重复枚举第一个数字，然后接下来采用两头向里寻找的方式无重复的构造剩下的两个数字，具体在循环中：
    1. 初始化l为st+1，r为n-1。
    2. 当l<r时，如果当前nums[l] + nums[r] == target，则增加答案并同时向后无重复移动l，向前无重复移动r；否则根据nums[l] + nums[r]与target的关系判断移动l还是移动r。
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            while (i != 0 && i < nums.length && nums[i] == nums[i - 1]) i++; // avoid duplicated target
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                if (nums[lo] + nums[hi] + nums[i] == 0) {
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    do {
                        lo++;
                    } while (lo < hi && nums[lo - 1] == nums[lo]);
                    do {
                        hi--;
                    } while (lo < hi && nums[hi] == nums[hi + 1]);
                } else if (nums[lo] + nums[hi] + nums[i] < 0) { // to small
                    do {
                        lo++;
                    } while (lo < hi && nums[lo - 1] == nums[lo]);
                } else {
                    do {
                        hi--;
                    } while (lo < hi && nums[hi] == nums[hi + 1]);
                }
            }
        }
        return res;
    }

    // 写法2
    public List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) { // avoid duplicate
                int lo = i + 1, hi = nums.length - 1;
                while (lo < hi) {
                    // 1. equal to 0
                    if (nums[lo] + nums[hi] + nums[i] == 0) {
                        result.add(Arrays.asList(nums[lo], nums[hi], nums[i]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++; // avoid dulicate
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    }
                    // 2. too small
                    else if (nums[lo] + nums[hi] + nums[i] < 0) lo++;
                        // 3. too big
                    else hi--;
                }
            }
        }
        return result;
    }


    public void print(List<List<Integer>> res) {
        for (List l : res) {
            for (int i = 0; i < l.size(); i++) {
                System.out.print(l.get(i) + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void main() {
        print(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println();
        print(threeSum(new int[]{0,2,2,3,0,1,2,3,-1,-4,2}));
    }
}
