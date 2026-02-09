package Neetcode;

import java.util.HashMap;

public class LRUCache2 {
    int capacity;
    HashMap<Integer,DLLNode> map;
    DLLNode head;
    DLLNode tail;
    int currCount;

    public LRUCache2(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new DLLNode();
        tail = new DLLNode();
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
        currCount = 0;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        var valueNode = map.get(key);
        removeNode(map.get(key));
        inserNode(map.get(key));
        return valueNode.val;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).val = value;
            removeNode(map.get(key));
            inserNode(map.get(key));
        }
        else {
            var newNode = new DLLNode();
            newNode.val = value;
            newNode.key = key;
            map.put(key, newNode);
            inserNode(newNode);
        }
    }

    public void inserNode(DLLNode node) {
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
        currCount++;

        if (currCount > capacity) {
            removeNode(tail.prev);
            map.remove(tail.prev.key);
        }
    }

    public void removeNode(DLLNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
        currCount--;
    }

    public static class DLLNode {
        int val;
        int key;
        DLLNode prev;
        DLLNode next;

        public DLLNode() {}

        public DLLNode(int val, int key, DLLNode prev, DLLNode next) {
            this.val = val;
            this.key = key;
            this.prev = prev;
            this.next = next;
        }
    }
}
