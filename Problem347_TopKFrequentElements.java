package LeetCode;

import org.junit.Test;

import java.util.*;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/16  2018/7/03
 * @Description:
 * 难度： Medium
 * Given a non-empty array of integers, return the k most frequent elements.
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 * 思路：
 * 1. 使用hashmap统计字符出现次数，再使用长度为k的优先队列，优先取决于字符频次
 * 2. 使用桶排序
 */
public class Problem347_TopKFrequentElements {

    // 1. hashmap + priority queue
    // O(nlogn) 不合题意
    public List<Integer> tokKFrequent(int [] nums, int k) {
        // count digit frequency
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
           Integer count = map.get(nums[i]);
           if (count == null) {
               count = 0;
           }
           map.put(nums[i], count + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comp(map)); // 注意API
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
                continue;
            }
            if (map.get(pq.peek()) < map.get(key)) {
                pq.poll();
                pq.add(key);
            }
        }
        return new ArrayList<Integer>(pq);
    }

    // 优先队列需要传入一个comparator定义优先级别
    class Comp implements Comparator<Integer> {
        HashMap<Integer, Integer> map;

        public Comp(HashMap<Integer, Integer> map) {
            this.map = map;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            return map.get(o1) - map.get(o2);
        }
    }

    // 2. Bucket Sort
    // 频率为桶，统计完成后，从后往前倒出k个桶即可！ So easy!
    // Use an array to save numbers into different bucket whose index is the frequecy
    // Time = O(n) + O(k)
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<> ();
        for (int n : nums) // 用Java的getOrDefault避免exception
            frequency.put(n, frequency.getOrDefault(n, 0) + 1);

        List<Integer>[] bucket = new List[nums.length + 1];
        for (int key : frequency.keySet()) {
            int f = frequency.get(key);
            if (bucket[f] == null) bucket[f] = new ArrayList<>();
            bucket[f].add(key);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && result.size() < k; i--) {
            if (bucket[i] != null) result.addAll(bucket[i]);
        }
        return result; // 这里强调的是返回所有频率排序前k的数字；如果强调只返回k个数字 -> res.subList(0,k)
    }


    @Test
    public void testPQ(){
        int[] test = new int[] {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
        Problem347_TopKFrequentElements p = new Problem347_TopKFrequentElements();
        List<Integer> temp = p.tokKFrequent(test,   10);
        for (int i : temp) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        int[] test = new int[] {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
        Problem347_TopKFrequentElements p = new Problem347_TopKFrequentElements();
        List<Integer> temp = p.tokKFrequent(test,   10);
        for (int i : temp) {
            System.out.println(i);
        }
    }

}
