package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/8/23
 * @Description:
 * Easy
 *
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth term of the count-and-say sequence.
 *
 * Note: Each term of the sequence of integers will be represented as a string.
 *
 * Example 1:
 *
 * Input: 1
 * Output: "1"
 * Example 2:
 *
 * Input: 4
 * Output: "1211"
 *
 * 教训：使用StringBuilder明显要比String快。
 */
public class Problem38_CountAndSay {
    public String countAndSay(int n) {
        String origin = "1";
        while (n - 1 > 0) {
            StringBuilder s = new StringBuilder();
            int count = 1;
            for (int i = 0; i < origin.length(); i++) {
                if (i == origin.length() - 1 || origin.charAt(i) != origin.charAt(i + 1)) {
                    s = s.append(count).append(origin.charAt(i));
                    count = 1;
                }
                else {
                    count = count + 1;
                }
            }
            origin = s.toString();
            n--;
        }
        return origin;
    }
}
