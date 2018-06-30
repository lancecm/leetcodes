package LeetCode;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/29
 * @Description: 119
 * Easy
 * <p>
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 * Note that the row index starts from 0.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * <p>
 * Follow up:
 * use only O(k) extra space
 *
 * 注意：从0开始
 */
public class Problem119_PascalTriangleII {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex > 33) return null;
        List<Integer> pre = new ArrayList<>();
        pre.add(1);
        if (rowIndex == 0) return pre;
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) cur.add(1);
                else {
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = cur;
        }
        return pre;
    }

    // 只使用O(K)额外空间，思路：从后往前
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        int[] res = new int[rowIndex + 1];
        res[0] = 1;
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i; j >= 1; j--) {
                res[j] = res[j] + res[j-1];
            }
        }
        for (int i = 0; i <= rowIndex; i++) {
            result.add(res[i]);
        }
        return result;
     }

     // 只使用O(K)额外空间，利用ArrayList的内置方法，不转换数组
    public List<Integer> getRow2ArrayList(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            result.add(1);
            for (int j = i - 1; j >= 1; j--) {
                result.set(j, result.get(j) + result.get(j-1));
            }
        }
        return result;
    }

    public void print(List<Integer> list) {
        for (int i : list) System.out.print(i + " ");
        System.out.println();
    }

    @Test
    public void test() {
        print(getRow(0));
        print(getRow(1));
        print(getRow(2));
        print(getRow(3));
        print(getRow(4));
        print(getRow(5));
        print(getRow(6));
        print(getRow(7));
        print(getRow(8));
        print(getRow(9));
        print(getRow(10));
    }

    @Test
    public void test2(){
        print(getRow2(0));
        print(getRow2(1));
        print(getRow2(2));
        print(getRow2(3));
        print(getRow2(4));
        print(getRow2(5));
    }

    @Test
    public void test3(){
        print(getRow2ArrayList(0));
        print(getRow2ArrayList(1));
        print(getRow2ArrayList(2));
        print(getRow2ArrayList(3));
        print(getRow2ArrayList(4));
        print(getRow2ArrayList(5));

    }
}
