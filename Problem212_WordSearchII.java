package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/10
 * @Description:
 * 212
 * Hard
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Example:
 *
 * Input:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 *
 * Output: ["eat","oath"]
 *
 * 思路：
 *    搭建Trie(前缀树，字典树) 使用回溯法
 *    基本类似wordbreak1，但是要自己搭
 * TODO: 重抄几遍 深入了解Trie！习惯题目中的搭建方式
 */
public class Problem212_WordSearchII {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w: words) {
            TrieNode p = root;
            for (char c: w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w; // append word to the lastnode
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] word) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(word);
        // start from each char and search possible word in the tire.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return; // visited or does not exists
        p = p.next[c - 'a']; // check next tire node
        if (p.word != null) { // find one
            res.add(p.word);
            p.word = null; // de-duplicated
        }
        board[i][j] = '#'; // temporary visited
        if (i > 0) dfs(board, i - 1, j, p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        board[i][j] = c;
    }
}
