package Neetcode;

public class MyHashMap2 {

    class ListNode {
        int key;
        int val;
        ListNode next;

        public ListNode(int key, int val, ListNode next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public ListNode() {
            this(-1, -1, null);
        }
    }

    ListNode[] map;

    public MyHashMap2() {
        map = new ListNode[1000];
        for (int i = 0; i < 1000; i++) {
            map[i] = new ListNode();
        }
    }
    
    public void put(int key, int value) {
        int index = key % 1000;
        ListNode node = map[index];
        while (node.next != null && node.next.key != key) {
            node = node.next;
        }
        if (node.next == null) {
            node.next = new ListNode(key, value, null);
        }
        else {
            node.next.val = value;
        }
    }
    
    public int get(int key) {
        int index = key % 1000;
        ListNode node = map[index];
        while (node.next != null && node.next.key != key) {
            node = node.next;
        }
        if (node.next == null) {
            return -1;
        }
        else {
            return node.next.val;
        }
    }
    
    public void remove(int key) {
        int index = key % 1000;
        ListNode node = map[index];
        while (node.next != null && node.next.key != key) {
            node = node.next;
        }
        if (node.next != null) {
            node.next = node.next.next;
        }
    }
}
