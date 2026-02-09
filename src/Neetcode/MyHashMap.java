package Neetcode;

public class MyHashMap {

    private static class ListNode {
        int key;
        int value;
        ListNode next;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private ListNode[] map;

    public MyHashMap() {
        map = new ListNode[10000];
    }
    
    public void put(int key, int value) {
        int index = key % map.length;
        ListNode curr = map[index];
        if (curr == null) {
            map[index] = new ListNode(key, value);
        }
        else {
            if (curr.key == key) {
                curr.value = value;
            }
            else {
                while (curr.next != null && curr.next.key != key) {
                    curr = curr.next;
                }

                if (curr.next == null) {
                    curr.next = new ListNode(key, value);
                }
                else {
                    curr.next.value = value;
                }
            }
        }
    }
    
    public int get(int key) {
        int index = key % map.length;
        ListNode curr = map[index];
        if (curr == null) {
            return -1;
        }
        else {
            if (curr.key == key) {
                return curr.value;
            }
            else {
                while (curr.next != null && curr.next.key != key) {
                    curr = curr.next;
                }

                if (curr.next == null) {
                    return -1;
                }
                else {
                    return curr.next.value;
                }
            }
        }
    }
    
    public void remove(int key) {
        int index = key % map.length;
        ListNode curr = map[index];
        if (curr != null) {
            if (curr.key == key) {
                map[index] = curr.next;
            }
            else {
                while (curr.next != null && curr.next.key != key) {
                    curr = curr.next;
                }

                if (curr.next != null) {
                    curr.next = curr.next.next;
                }
            }
        }
    }
}
