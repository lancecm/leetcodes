package LeetCode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Srunkyo
 * @Date: 2018/8/4
 * @Description:
 * Medium
 *
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
 *
 * Example 1:
 *
 * Input: "2-1-1"
 * Output: [0, 2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 *
 * Input: "2*3-4*5"
 * Output: [-34, -14, -10, -10, 10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 * 分治
 */
public class Problem241_DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '-' || c == '+' || c == '*') {
                List<Integer> resLeft = diffWaysToCompute(input.substring(0, i));
                List<Integer> resRight = diffWaysToCompute(input.substring(i + 1));
                for (int p1 : resLeft) {
                    for (int p2 : resRight) {
                        int tmp = 0;
                        if (c == '-') {
                            tmp = p1 - p2;
                        }
                        if (c == '+') {
                            tmp = p1 + p2;
                        }
                        if (c == '*') {
                            tmp = p1 * p2;
                        }
                        res.add(tmp);
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        return res;
    }
}
