package Neetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LinkedList {
    public static void main(String[] args) {
        /*ListNode head = new ListNode(2, new ListNode(4, new ListNode(6, new ListNode(8, null))));
        reorderList2(head); */

        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, null))))));
        reverseKGroup(head, 3);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode prev = null;
        ListNode pres = head;
        ListNode next = head.next;

        while (pres != null) {
            pres.next = prev;
            prev = pres;
            pres = next;
            if (next != null) {
                next = next.next;
            }
        }

        return prev;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        var newHead = reverseList2(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        var dummy = new ListNode(0);
        var curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            }
            else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        if (list1 != null) {
            curr.next = list1;
        }
        else {
            curr.next = list2;
        }

        return dummy.next;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode f = head;
        ListNode s = head;

        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;

            if (f == s) {
                return true;
            }
        }

        return false;
    }

    public void reorderList(ListNode head) {
        reorderRec(head, head.next);
    }

    public ListNode reorderRec(ListNode head, ListNode curr) {
        if (curr == null) {
            return head;
        }

        var root = reorderRec(head, curr.next);
        if (root == null) {
            return null;
        }

        ListNode temp = null;
        if (root == curr || root.next == curr) {
            curr.next = null;
        }
        else {
            temp = root.next;
            root.next = curr;
            curr.next = temp;
        }

        return temp;
    }

    public static void reorderList2(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode s = head;
        ListNode f = head;

        // To get mid of the LL
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }

        // To reverse 2nd half of LL
        ListNode prev = s;
        ListNode pres = s.next;
        ListNode next = null;
        if (pres == null || pres.next == null) {
            next = null;
        }
        else {
            next = pres.next;
        }
        
        // To reverse 2nd half of LL
        while (pres != null) {
            pres.next = prev;
            prev = pres;
            pres = next;
            if (next != null) {
                next = next.next;
            }
        }
        s.next = null;    // Mark next of last node to null

        s = head;
        while (s.next != null) {
            ListNode temp = s.next;
            s.next = prev;
            var prevNext = prev.next;
            if (prev != temp) {
                prev.next = temp;
            }
            s = temp;
            prev = prevNext;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }

        //find length of LL
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        if (n > len) {
            return head;
        }

        int removeAt = len - n;
        if (removeAt == 0) {
            return head.next;
        }

        curr = head;
        int count = 1;
        while (count < removeAt) {
            count++;
            curr = curr.next;
        }

        curr.next = curr.next.next;
        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }

        ListNode l = head;
        ListNode r = head;

        while (n > 0) {
            r = r.next;
            n--;

            if (r == null) {
                return head.next;
            }
        }

        while (r.next != null) {
            r = r.next;
            l = l.next;
        }

        l.next = l.next.next;
        return head;
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        HashMap<Node, Node> map = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            Node temp = new Node(curr.val);
            map.put(curr, temp);
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            Node temp = map.get(curr);
            temp.next = map.get(curr.next);
            temp.random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int val = v1+v2+carry;

            ListNode node = new ListNode(val % 10);
            curr.next = node;
            curr = curr.next;
            carry = val/10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        return dummy.next;
    }

    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int slow = 0;
        int fast = 0;

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }

        int slow2 = 0;
        while (true) {
            slow2 = nums[slow2];
            fast = nums[fast];
            if (slow2 == fast) {
                break;
            } 
        }

        return slow2;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }

        ListNode res = new ListNode();
        ListNode curr = res;

        while (true) {
            int minNode = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    continue;
                }

                if (minNode == -1 || lists[minNode].val > lists[i].val) {
                    minNode = i;
                }
            }

            if (minNode == -1) {
                break;
            }

            curr.next = lists[minNode];
            lists[minNode] = lists[minNode].next;
            curr = curr.next;
        }

        return res.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        while (lists.length > 1) {
            List<ListNode> mergedLists = new ArrayList<>();
            for (int i = 0; i < lists.length; i+=2) {
                ListNode l1 = lists[i];
                ListNode l2 = i == lists.length-1 ? null : lists[i+1];
                mergedLists.add(mergeLists(l1, l2));
            }

            lists = mergedLists.toArray(new ListNode[0]);
        }

        return lists[0];
    }

    public ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode curr = res;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            }
            else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if (l1 != null) {
            curr.next = l1;
        }
        else {
            curr.next = l2;
        }

        return res.next;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        int count = 0;
        ListNode curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        ListNode newHead = head;
        ListNode tail = head;

        ListNode prev = null;
        ListNode pres = head;
        ListNode next = head.next;
        ListNode secHead = prev;
        for (int i = 0; i < count/k; i++) {
            for (int j = 0; j < k; j++) {
                pres.next = prev;
                prev = pres;
                pres = next;
                if (next != null) {
                    next = next.next;
                }
            }

            if (i == 0) {
                newHead = prev;
            }
            if (secHead != null) {
                secHead.next = prev;
            }

            tail.next = pres;
            prev = tail;
            tail = pres;
            if (pres != null) {
                next = pres.next;
            }
            
            secHead = prev;
        }

        return newHead;
    }
}
