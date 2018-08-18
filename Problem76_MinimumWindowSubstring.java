package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/8/18
 * @Description:
 * Hard
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 *
 * 在给定的字符串S中找到最小的窗口使其完全包含字符T中的所有字符，否则返回空串
 * ~ Longgest Substring Without Repeating Characters/ Substring with concatenation of all words
 * 使用l和r维护子串，用hash表记录T字符串中出现过的字符，用计数器cnt判断t中字符是否都出现过
 */
public class Problem76_MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++; // construct hashmap
        }
        int min = s.length() + 1, left = 0, lo = 0, count = 0;
        for (int hi = 0; hi < s.length(); hi++) {
            char c = s.charAt(hi);
            map[c]--; // 右边字符纳入window
            if (map[c] >= 0) count++; // 如果是t中的，则count增加
            while (count == t.length()) { // count等于t的长度，证明是substring
                if (hi - lo + 1 < min) { // 此时判断是否为更小的substring
                    min = hi - lo + 1; // 更新最小长度
                    left = lo; // 更新左边界
                }
                // 左边字符移出window
                char pre = s.charAt(lo);
                map[pre]++;
                if (map[pre] > 0) count--; // 如果是被需要的，则count减一
                lo++;
            }
        }
        return min == s.length() + 1 ? "" : s.substring(left,left + min);
    }
}
