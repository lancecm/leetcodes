package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/7/27
 * @Description:
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Example 1:
 *
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 *              The third child gets 1 candy because it satisfies the above two conditions.
 *
 * 思路：
 * 设第一个人分1个糖果
 * 从前向后遍历，如果当前节点大于前一个节点，则分配糖果在前一个节点的基础上 + 1。 --》保证孩子比左侧评分更低的孩子多出糖果
 * 从后向前遍历，如果当前节点大于后一个节点，则分配糖果在后一个节点的基础上 + 1，并与原有分配糖果个数相比较取较大值。 -》保证孩子比右侧评分更低的孩子多出糖果
 */
public class Problem135_Candy {
    public int candy(int[] ratings) {
        int sum = 0;
        if (ratings.length == 0) return sum;
        int[] c = new int[ratings.length];
        c[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) c[i] = c[i - 1] + 1;
            else {
                c[i] = 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                c[i] = Math.max(c[i + 1] + 1, c[i]);
            }
        }
        for (int i = 0; i < c.length; i++) {
            sum += c[i];
        }
        return sum;
    }
}
