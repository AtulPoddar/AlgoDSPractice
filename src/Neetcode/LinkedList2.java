package Neetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LinkedList2 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        //ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, 
            //new ListNode(5, new ListNode(6, null))))));
        
        //reverseKGroup(head,3);
        var test = validPalindrome("acdccba");
    }

    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;
        if (head != null) {
            next = head.next;
        }

        while (curr != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            if (next != null) {
                next = next.next;
            }
        }

        return prev;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode curr = head;
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

        return head.next;
    }

    public boolean hasCycle(ListNode head) {
        ListNode s = head;
        ListNode f = head;

        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;

            if (s == f) {
                return true;
            }
        }

        return false;
    }

    public static void reorderList(ListNode head) {
        rec(head, head.next);
    }

    private static ListNode rec(ListNode root, ListNode cur) {
        if (cur == null) {
            return root;
        }

        root = rec(root, cur.next);
        if (root == null) {
            return null;
        }

        ListNode tmp = null;
        if (root == cur || root.next == cur) {
            cur.next = null;
        } else {
            tmp = root.next;
            root.next = cur;
            cur.next = tmp;
        }

        return tmp;
    }

    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }

        ListNode s = head;
        ListNode f = head;

        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
        }

        ListNode prev = s;
        ListNode curr = s.next;
        ListNode next = null;
        if (curr != null) {
            next = curr.next;
        }

        while (curr != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            if (next != null) {
                next = next.next;
            }
        }
        s.next = null;

        ListNode h = head;
        while (h.next != null) {
            ListNode hNext = h.next;
            ListNode prevNext = prev.next;
            h.next = prev;
            if (prev.next != null) {
                prev.next = hNext;
            }
            
            h = hNext;
            prev = prevNext;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode s = head;
        while (s != null) {
            len++;
            s = s.next;
        }

        int removeAt = len - n + 1;
        if (removeAt == 1) {
            head = head.next;
            return head;
        }
        
        int count = 1;
        s = head;
        while (count < removeAt - 1) {
            s = s.next;
            count++;
        }

        s.next = s.next.next;
        return head;
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        HashMap<Node,Node> map = new HashMap<>();
        Node s = head;
        while (s != null) {
            Node node = new Node(s.val);
            map.put(s, node);
            s = s.next;
        }

        s = head;
        while (s != null) {
            var newNode = map.get(s);
            newNode.next = map.get(s.next);
            newNode.random = map.get(s.random);
            s = s.next;
        }

        return map.get(head);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode head = res;

        int carry = 0;
        while (l1 != null || l2 != null) {
            var sum = ((l1 == null ? 0 : l1.val) + (l2 == null ? 0 :l2.val)) + carry;
            var nodeVal = sum % 10;
            carry = sum / 10;

            res.next = new ListNode(nodeVal);
            res = res.next;
            l1 = l1 != null ? l1.next : l1;
            l2 = l2 != null ? l2.next : l2;
        }

        if (carry != 0) {
            res.next = new ListNode(carry);
        }

        return head.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        while (lists.length > 1) {
            List<ListNode> arrayList = new ArrayList<>();
            for (int i = 0; i < lists.length; i+=2) {
                ListNode l1 = lists[i];
                ListNode l2 = i == lists.length-1 ? null : lists[i+1];
                arrayList.add(mergeLists(l1, l2));
            }

            lists = arrayList.toArray(new ListNode[0]);
        }

        return lists[0];
    }

    public ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode ans = res;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                res.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            else {
                res.next = new ListNode(l2.val);
                l2 = l2.next;
            }

            res = res.next;
        }

        if (l1 != null) {
            res.next = l1;
        }
        else {
            res.next = l2;
        }

        return ans.next;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 0 || k == 1) {
            return head;
        }
        
        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            curr = curr.next;
            len++;
        }

        int grp = len / k;
        ListNode prev = null;
        ListNode pres = head;
        ListNode next = pres.next;
        ListNode pres2 = pres;

        for (int i = 1; i <= grp; i++) {
            ListNode pres3 = pres;
            prev = null;
            for (int j = 1; j <= k; j++) {
                pres.next = prev;
                prev = pres;
                pres = next;
                if (next != null) {
                    next = next.next;
                }
            }

            if (i == 1) {
                head = prev;
            }

            if (i >= 2) {
                pres2.next = prev;
                pres2 = pres3;
            }
        }

        pres2.next = pres;
        return head;
    }

    public String mergeAlternately(String word1, String word2) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        Character w1 = word1 == null ? null : word1.charAt(i);
        Character w2 = word2 == null ? null : word2.charAt(i);
        int len1 = word1 != null ? word1.length() : 0;
        int len2 = word2 != null ? word2.length() : 0;

        while (len1 > 0 && len2 > 0) {
            sb.append(w1).append(w2);
            i++;
            len1--;
            len2--;
            w1 = len1 != 0 ? word1.charAt(i) : null;
            w2 = len2 != 0 ? word2.charAt(i) : null;
        }

        if (w1 != null) {
            sb.append(word1.substring(i));
        }
        else if (w2 != null) {
            sb.append(word2.substring(i));
        }

        return sb.toString();
    }

    public static boolean validPalindrome(String s) {
        if (s == null) {
            return false;
        }

        int len = s.length();
        int l = 0;
        int r = len-1;
        
        while (l <= r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            }
            else {
                return isPalindrome(s, l+1, r) || isPalindrome(s, l, r-1);
            }
        }

        return true;
    }

    public static boolean isPalindrome(String s, int l, int r) {
        while (l <= r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            }
            else {
                return false;
            }
        }

        return true;
    }

    public int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0) {
            return 0;
        }

        int max = Arrays.stream(people).max().getAsInt();
        int[] count = new int[max+1];
        for (int p : people) {
            count[p]++;
        }

        int i = 0;
        for (int j = 0; j < count.length; j++) {
            while (count[j] != 0) {
                people[i++] = j;
                count[j]--;
            }
        }

        int l = 0;
        int r = people.length-1;
        int res = 0;

        while (l <= r) {
            if (l == r) {
                res++;
                break;
            }
            else {
                if (people[r] == limit || people[l] + people[r] > limit) {
                    res++;
                    r--;
                    continue;
                }
                
                l++;
                r--;
                res++;
            }
        }

        return res;
    }
}
