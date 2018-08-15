package LeetCode;

import java.util.*;

/**
 * @author Srunkyo
 * @Date: 2018/8/14
 * @Description:
 * Hard
 *
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1:
 *
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * Example 2:
 *
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 *
 * 思路1：
 * 暴力解法，两两组合k*n^2
 *
 * 思路2：
 * HashMap, 对于每个字符串，将其一分为2，若前一半为回文串，后一半的逆序字符串也存在，则为解 REV(回文部分)REV； 对于后半部分同理。
 * 复杂度：n*k^2
 *
 * 思路3：
 * Trie
 * 对于每一个字符串，逆序存放在Trie中，标记位置存放的是该字符串在数组中的位置
 * 每次搜索时，若搜索到有标记的位置，则判断剩下的是否是回文串
 * http://www.allenlipeng47.com/blog/index.php/2016/03/15/palindrome-pairs/
 */
public class Problem336_PalindromePairs {

    // 暴力：TLE
    public List<List<Integer>> palindromePairs1(String[] words) {
        int len = words.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                String s = words[i] + words[j];
                if (isPalindrome(s)) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.add(j);
                    result.add(tmp);
                }
            }
        }
        return result;
    }

    // hashmap
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) return res;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) map.put(words[i], i);
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                String str1 = words[i].substring(0, j); // 前半段回文，后半段逆序在map中存在
                String str2 = words[i].substring(j); // 后半段回文，前半段逆序在map中存在
                if (isPalindrome(str1)) {
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        List<Integer> list = new ArrayList<>();
                        list.add(map.get(str2rvs));
                        list.add(i);
                        res.add(list);
                    }
                }
                if (isPalindrome(str2)) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length() != 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(map.get(str1rvs));
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }

    // 方法二：Trie
    class TrieNode{
        public TrieNode[] children;
        public int index;
        public List<Integer> list;
        public TrieNode() {
            this.children = new TrieNode[26];
            this.index = -1;
            this.list = new ArrayList<>();
        }
    }

    public void insertWord(TrieNode root, String word, int idx) {
        TrieNode node = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a'; // index
            if (node.children[j] == null) {
                node.children[j] = new TrieNode();
            }
            if (isPalindrome(word, 0, i)) { // 查看剩下的部分是不是回文串。如果是，则标记上字符串在数组中的位置
                node.list.add(idx);
            }
            node = node.children[j];
        }
        node.index = idx; // 结尾处做标记
        node.list.add(idx);
    }

    public boolean isPalindrome(String word, int s, int e) {
        while (s < e) {
            if (word.charAt(s++) != word.charAt(e--)) return false;
        }
        return true;
    }

    public void searchWord(TrieNode root, String word, int idx, List<List<Integer>> res) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            // node为实际节点，不能和自己组成回文，剩下的需要是回文串
            if (node.index >= 0 && node.index != idx && isPalindrome(word, i, word.length() -1)) {
                res.add(Arrays.asList(idx, node.index));
            }
            node = node.children[word.charAt(i) - 'a'];
            if (node == null) return;
        }
        for (int j : node.list) {
            if (idx != j) {
                res.add(Arrays.asList(idx, j));
            }
        }
    }

    public List<List<Integer>> palindromePairs3(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            insertWord(root, words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            searchWord(root, words[i], i, res);
        }
        return res;
    }


    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) != str.charAt(right--)) return false;
        }
        return true;
    }
}
