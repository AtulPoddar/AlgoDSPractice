package LinkedList;

public class LL {
    
    public Node head;
    public int size;

    private class Node {
        int val;
        Node next;

        private Node() {}

        private Node(int val) {
            this.val = val;
        }

        private Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public void InsertFirst(int val) {
        Node newNode = new Node(val);
        
        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        newNode.next = head;
        head = newNode;
        size++;
    }

    public void InsertIndex(int val, int index) {
        if (index < 0) {
            System.out.println("Negative Index error");
            return;
        }

        Node newNode = new Node(val);
        int i = 0;
        Node temp = head;
        while (i < index-1) {
            temp = temp.next;
            i++;
        }

        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void Display() {
        Node temp = head;
        while (temp != null) {
            System.err.print(temp.val + " -> ");
            temp = temp.next;
        }

        System.out.print("END");
    }

    public void InsertIndexRec1(int val, int index, Node node) {
        if (index == 0) {
            Node newNode = new Node(val);
            newNode.next = node;
            head = newNode;
            return;
        }

        if (index == 1) {
            Node newNode = new Node(val);
            newNode.next = node.next;
            node.next = newNode;

            return;
        }

        InsertIndexRec1(val, index-1, node.next);
    }

    public void InsertIndexRec2(int val, int index) {
        InsertIndexRec2Helper(val, index, head);
    }

    private Node InsertIndexRec2Helper(int val, int index, Node node) {
        if (index == 0) {
            Node newNode = new Node(val);
            newNode.next = node;

            // In case when initial call is made with 0 index, i.e., to insert at beginning
            if (node == head) {
                head = newNode;
            }

            return newNode;
        }

        node.next = InsertIndexRec2Helper(val, index-1, node.next);
        return node;
    }

    public void RemoveDuplicates(Node head) {
        Node temp = head;
        Node curr = head; 

        while (temp != null) {
            while (curr != null && temp.val == curr.val) {
                curr = curr.next;
            }

            temp.next = curr;
            temp = curr;
        }
    }

    public Node MergeLL(Node head1, Node head2) {
        Node temp1 = head1;
        Node temp2 = head2;
        Node resultHead = null;
        Node temp3 = resultHead;

        while (temp1 != null && temp2 != null) {
            Node newNode = null;
            if (temp1.val <= temp2.val) {
                newNode = new Node(temp1.val);
                temp1 = temp1.next;
            }
            else {
                newNode = new Node(temp2.val);
                temp2 = temp2.next;
            }

            if (resultHead == null) {
                resultHead = newNode;
            }
            else {
                temp3.next = newNode;
            }

            temp3 = newNode;
        }

        while (temp1 != null) {
            temp3.next = new Node(temp1.val);
            temp3 = temp3.next;
            temp1 = temp1.next;
        }

        while (temp2 != null) {
            temp3.next = new Node(temp2.val);
            temp3 = temp3.next;
            temp2 = temp2.next;
        }

        return resultHead;
    }

    public Node CreateCyclicLL() {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(7);
        Node h = new Node(8);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = h;
        h.next = d;

        return a;
    }

    public boolean IsLLCyclic(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast.val == slow.val) {
                return true;
            }
        }

        return false;
    }

    public int GetCycleLength(Node head) {
        Node slow = head;
        Node fast = head;
        int count = 0;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            
            if (fast.val == slow.val) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return count;
        }

        do {
            slow = slow.next;
            count++;
        } while (slow.val != fast.val);

        return count;
    }

    public Node FindCycleStart(Node head) {
        int l = GetCycleLength(head);

        if (l == 0) {
            return null;
        }

        Node f = head;
        Node s = head;

        // move s by cycle length
        while (l > 0) {
            s = s.next;
            l--;
        }

        // move both, will meet at start of the cycle.
        while (f != s) {
            f = f.next;
            s = s.next;
        }

        return s;
    }

    public Node LLMiddle(Node head) {
        Node s = head;
        Node f = head;

        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }

        return s;
    }

    public Node LLMergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node midNode = GetMid(head);

        Node left = LLMergeSort(head);
        Node right = LLMergeSort(midNode);

        return merge(left, right);
    }

    private Node GetMid(Node head) {
        Node midPrev = null;
        while (head != null && head.next != null) {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }

        Node mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }

    private Node merge(Node left, Node right) {
        Node dummyHead = new Node();
        Node temp = dummyHead;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                temp.next = left;
                left = left.next;
                temp = temp.next;
            }
            else {
                temp.next = right;
                right = right.next;
                temp = temp.next;
            }
        }

        temp.next = left != null ? left : right;

        return dummyHead.next;
    }
}
