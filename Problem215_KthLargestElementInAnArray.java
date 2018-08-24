package LeetCode;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Srunkyo
 * @Date: 2018/7/18
 * @Description:
 * 215
 * 中
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class Problem215_KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        if (k > nums.length) return -1;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
        }
        while (k > 1) {
            pq.remove();
            k--;
        }
        return pq.peek();
    }

    // 8.24
    // 优先队列 O(Nlogk) O(K)
    public int findKthLargest2(int[] nums, int k) {
        if (k > nums.length) return -1;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i : nums) {
            if (q.size() < k) q.add(i);
            else {
                q.add(i);
                q.poll(); // remove top
            }
        }
        return q.poll();
    }

    // 排序法 O(NlogN) O(1)
    public int findKthLargest3(int[] nums, int k) {
        int N = nums.length;
        Arrays.sort(nums);
        return nums[k];
    }
    


    @Test
    public void test() {
        System.out.println(findKthLargest(new int[] {3,2,1,5,6,4}, 2));
        System.out.println(findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4));
    }
}
