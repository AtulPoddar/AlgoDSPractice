package Neetcode;

import java.util.Map;
import java.util.HashMap;

public class LRUCache {
    private Map<Integer, DLLNode> cache;
    DLLNode head = new DLLNode(0,0);
    DLLNode tail = new DLLNode(0,0);
    int capacity;
    public LRUCache(int capacity) {
        cache = new HashMap<>();
        this.capacity = capacity;
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
    }
    
    public int get(int key) {
        var node = cache.containsKey(key) ? cache.get(key) : null;
        if (node == null) {
            return -1;
        }

        removeNode(node);
        insertNode(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        DLLNode node = null;
        if (cache.containsKey(key)) {
            node = cache.get(key);
            node.value = value;
            removeNode(node);
        }
        else {
            node = new DLLNode(key, value);
            cache.put(key, node);
        }
        
        insertNode(node);
        if (cache.size() > capacity) {
            var toRemove = tail.prev;
            removeNode(toRemove);
            cache.remove(toRemove.key);
        }
    }

    public void removeNode(DLLNode node) {
        DLLNode prev = node.prev;
        DLLNode next = node.next;

        prev.next = next;
        next.prev = prev;
        node.prev = null;
        node.next = null;
    }

    public void insertNode(DLLNode node) {
        var nextNode = head.next;
        head.next = node;
        node.prev = head;
        node.next = nextNode;
        nextNode.prev = node;
    }

    public class DLLNode {
        public int key;
        public int value;
        public DLLNode prev;
        public DLLNode next;

        public DLLNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}
