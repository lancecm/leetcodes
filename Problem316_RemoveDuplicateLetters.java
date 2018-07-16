package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/16
 * @Description:
 * 316
 * Hard
 *
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example 1:
 *
 * Input: "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: "cbacdcbc"
 * Output: "acdb"
 *
 * 思路：
 * 贪婪
 *
 * 1. 建立hashmap，把字母按照最晚出现位置加入到hashmap中
 * 2. 找到hashmap中最小的字母位置index
 * 3. 所求结果的开头字母为在0-index之间出现的最小字母
 * 4. 类似地重复1-2步骤
 *
 * TODO：Greedy+Recursion?
 */
public class Problem316_RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        char[] sc = s.toCharArray();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (!map.containsKey(sc[i])) {
                map.put(sc[i], i);
                pq.add(i);
            }
        }
        int startIdx = 0;
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int endIdx = pq.peek(); //最近结束位
            char curr_char = 'z' + 1;
            for (int k = startIdx; k <= endIdx; k++) { //找当前最小字母序字母
                char char2 = s.charAt(k);
                if (curr_char > char2 && map.containsKey(char2)) {
                    curr_char = char2;
                    startIdx = k + 1;
                }
            }

            // 排除已加入结果的字母影响
            int idx = map.get(curr_char);
            pq.remove(idx);
            map.remove(curr_char);
            sb.append(curr_char);
        }
        return sb.toString();
    }
}
