package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/7
 * @Description:
 * Medium
 *
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 * Example 1:
 * Input:
 * asteroids = [5, 10, -5]
 * Output: [5, 10]
 * Explanation:
 * The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 * Example 2:
 * Input:
 * asteroids = [8, -8]
 * Output: []
 * Explanation:
 * The 8 and -8 collide exploding each other.
 * Example 3:
 * Input:
 * asteroids = [10, 2, -5]
 * Output: [10]
 * Explanation:
 * The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
 * Example 4:
 * Input:
 * asteroids = [-2, -1, 1, 2]
 * Output: [-2, -1, 1, 2]
 * Explanation:
 * The -2 and -1 are moving left, while the 1 and 2 are moving right.
 * Asteroids moving the same direction never meet, so no asteroids will meet each other.
 * Note:
 *
 * The length of asteroids will be at most 10000.
 * Each asteroid will be a non-zero integer in the range [-1000, 1000]..
 */
public class Problem753_AsterooidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        int len = 0;
        int n = asteroids.length;
        int[] st = new int[n];
        for (int i = 0; i < n; i++) {
            int in = asteroids[i];
            if (in > 0) { // positive -> to right
                st[len++] = asteroids[i];
            }
            else if (in < 0) { // negative -> to left
                if (len == 0 || st[len - 1] < 0) {
                    st[len++] = asteroids[i];
                }
                else if (st[len - 1] > 0) {
                    // 2 3 4 -5
                    if (Math.abs(in) > st[len - 1]) { // to left is bigger than to right -> previous asteroid will explode
                        st[len--] = 0;
                        i--; // to left asteroid will not explode
                    }
                    // 2 3 5 -5
                    else if(Math.abs(in) == st[len - 1]) { // if two asteroid have same quality, both will explode
                        st[len--] = 0;
                    }
                    // 2 3 5 -1
                    else if (Math.abs(in) > st[len - 1]) {
                        continue;
                    }
                }
            }
        }
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = st[i];
        }
        return res;
    }

    @Test
    public void test() {
        int[] res = asteroidCollision(new int[] {10, 2, -5});
        for (int i : res) {
            System.out.println(i + " ");
        }
    }
}
