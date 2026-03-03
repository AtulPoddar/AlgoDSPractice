package Neetcode.Tries;

import java.util.HashMap;
import java.util.Map;

public class PrefixTree {

    public class TrieNode {
        boolean word;
        Map<Character, TrieNode> children;

        public TrieNode() {
            word = false;
            children = new HashMap<>();
        }
    }

    TrieNode root;
    public PrefixTree() {
         root = new TrieNode();
    }

    public void insert(String word) {
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
        TrieNode curr = root;
        for (Character ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                return false;
            }
            curr = curr.children.get(ch);
        }

        return curr.word;
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (Character ch : prefix.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                return false;
            }
            curr = curr.children.get(ch);
        }

        return true;
    }
}
