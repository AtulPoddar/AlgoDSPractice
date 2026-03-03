package Neetcode.Tries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class algos {
    public static void main(String[] args) {
        
    }

    public class TrieNode {
        Map<Character, TrieNode> children;
        boolean word;

        public TrieNode() {
            children = new HashMap<>();
        }

        public void addWord(String word) {
            TrieNode curr = this;
            for (int i = 0; i < word.length(); i++) {
                if (!curr.children.containsKey(word.charAt(i))) {
                    curr.children.put(word.charAt(i), new TrieNode());
                }

                curr = curr.children.get(word.charAt(i));
            }

            curr.word = true;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        int r = board.length;
        int c = board[0].length;
        HashSet<String> set = new HashSet<>();

        TrieNode root = new TrieNode();
        for (String word : words) {
            root.addWord(word);
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                findWordsHelper(i, j, board, set, root, res, "");
            }
        }

        return new ArrayList<>(res);
    }

    public void findWordsHelper(int r, int c, char[][] board, HashSet<String> set, TrieNode root, Set<String> res, String word) {
        String pos = r + "," + c;
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || set.contains(pos) || !root.children.containsKey(board[r][c])) {
            return;
        }

        root = root.children.get(board[r][c]);
        word += board[r][c];
        if (root.word) {
            res.add(word);
        }

        set.add(pos);

        findWordsHelper(r+1, c, board, set, root, res, word);
        findWordsHelper(r, c+1, board, set, root, res, word);
        findWordsHelper(r, c-1, board, set, root, res, word);
        findWordsHelper(r-1, c, board, set, root, res, word);

        set.remove(pos);
    }
}
