package LeetCode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Srunkyo
 * @Date: 2018/7/24
 * @Description:
 * 17
 * 中
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 *
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 *
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 *
 * 思路：
 * BFS
 * 每一次添加一个可能的字母，直到结果内的所有字符串长度等于目标长度
 * 重点：像这种有规律可寻的字母-》字母映射，本题直接使用字符串数组来表示映射，very smart
 */
public class Problem17_LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digtis) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digtis.isEmpty()) return ans;
        String[] mapping = new String[] {"", "", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        ans.add("");
        while (ans.peek().length() != digtis.length()) {
            String remove = ans.remove(); // head of the queue
            String map = mapping[digtis.charAt(remove.length()) - '0'];
            for (char c : map.toCharArray()) {
                ans.addLast(remove + c);
            }
        }
        return ans;
    }

    // 7.29 ver 24%
    // 差距：result 是一个linkedlist完全可以当做queue来用，无需再重建一个queue
    public List<String> letterCombinations2(String digits) {
        LinkedList<String> result = new LinkedList<>();
        if(digits.equals("")) return result;
        String[] map = new String[] {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        Queue<String> q = new LinkedList<>();
        q.offer("");
        while(q.size() != 0) {
            int size = q.size();
            // for each item, we append possible character to them
            for (int i = 0; i < size; i++) {
                String t = q.poll();
                if (t.length() == digits.length()) {
                    result.add(t);
                }
                else {
                    int index = t.length();
                    String m = map[digits.charAt(index) - '0'];
                    for (char c : m.toCharArray()) {
                        q.offer(t + c);
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        letterCombinations2("23");
    }
}
