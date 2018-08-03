package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/3
 * @Description:
 * Easy
 *
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * Example:
 *
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class Problem204_CountPrimes {
    public int countPrimes(int n) {
        int[] primes = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= n / i; j++) {
                primes[j * i] = 1; // not prime
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (primes[i] == 0) count++;
        }

        return count;
    }

    @Test
    public void test() {
        System.out.println(countPrimes(10));
    }
}
