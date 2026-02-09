package Neetcode;

public class MyHashSet {

    private static class ListNode {
        int key;
        ListNode next;

        public ListNode(int key) {
            this.key = key;
        }
    }

    private final ListNode[] set;

    public MyHashSet() {
        set = new ListNode[10000];
    }
    
    public void add(int key) {
        int index = key % set.length;
        ListNode curr = set[index];
        if (curr == null) {
            set[index] = new ListNode(key);
        }
        else if (curr.key != key) {
            while (curr.next != null && curr.next.key != key) {
                curr = curr.next;
            }

            if (curr.next == null) {
                curr.next = new ListNode(key);
            }
        }
    }
    
    public void remove(int key) {
        int index = key % set.length;
        ListNode curr = set[index];
        if (curr != null) {
            if (curr.key == key) {
                set[index] = curr.next;
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
    
    public boolean contains(int key) {
        int index = key % set.length;
        ListNode curr = set[index];
        
        if (curr != null) {
            if (curr.key == key) {
                return true;
            }
            else {
                while (curr.next != null && curr.next.key != key) {
                    curr = curr.next;
                }

                return curr.next != null;
            }
        }

        return false;
    }
}
