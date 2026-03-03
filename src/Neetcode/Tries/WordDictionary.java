package Neetcode.Tries;

import java.util.HashMap;
import java.util.Map;

public class WordDictionary {

    public class TrieNode {
        boolean word;
        Map<Character,TrieNode> children;

        public TrieNode() {
            word = false;
            children = new HashMap<>();
        }
    }

    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curr = root;
        for (Character ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                curr.children.put(ch, new TrieNode());
            }

            curr = curr.children.get(ch);
        }

        curr.word = true;
    }

    public boolean search(String word) {
        return dfs(root, word, 0);
    }

    public boolean dfs(TrieNode node, String word, int j) {
        TrieNode curr = node;

        for (int i = j; i < word.length(); i++) {
            Character ch = word.charAt(i);

            if (ch == '.') {
                for (var item : curr.children.entrySet()) {
                    if (dfs(item.getValue(), word, i+1)) {
                        return true;
                    }
                }

                return false;
            }
            else {
                if (!curr.children.containsKey(ch)) {
                    return false;
                }

                curr = curr.children.get(ch);
            }
        }

        return curr.word;
    }
}
