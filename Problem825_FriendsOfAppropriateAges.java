package LeetCode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Srunkyo
 * @Date: 2018/8/24
 * @Description:
 * Medium
 *
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
 *
 * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
 *
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * Otherwise, A will friend request B.
 *
 * Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
 *
 * How many total friend requests are made?
 *
 * Example 1:
 *
 * Input: [16,16]
 * Output: 2
 * Explanation: 2 people friend request each other.
 * Example 2:
 *
 * Input: [16,17,18]
 * Output: 2
 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 * Example 3:
 *
 * Input: [20,30,100,110,120]
 * Output:
 * Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 *
 * 排列组合
 */
public class Problem825_FriendsOfAppropriateAges {
    public int numFriendRequests(int[] ages) {
        int res = 0;
        int[] num = new int[121];
        for (int i : ages) {
            num[i]++;
        }
        int[] sum = new int[121];
        for (int i =1 ; i < 121; i++) {
            sum[i] += sum[i - 1] + num[i]; // num of ages so far （ less or equal than)
        }
        for (int i = 15; i < 121; i++) {
            int min =  i /2 + 7;
            if (num[i] == 0) continue;
            int count = sum[i] - sum[min];
            res += count * num[i] - num[i];
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(numFriendRequests(new int[] {20,30,100,110,120}));
    }

    // 暴力
    public int numFriendRequests2(int[] ages) {
        int request = 0;
        Arrays.sort(ages);
        for (int i = 1; i < ages.length; i++) {
            int min = (int) (ages[i] * 0.5 + 7);
            for (int j = i - 1; j >= 0; j--) {
                if (ages[j] > 100 && ages[i] < 100) continue;
                if (ages[j] <= min) {
                    break;
                }
                else if (ages[j] < ages[i]) {
                    request += 1;
                }
                else if (ages[j] == ages[i]) {
                    request += 2; // mutal
                }
            }
        }
        return request;
    }
}
