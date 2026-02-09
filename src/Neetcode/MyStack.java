package Neetcode;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {

    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    public void push(int x) {
        if (!q1.isEmpty()) {
            q1.offer(x);
        }
        else {
            q2.offer(x);
        }
    }
    
    public int pop() {
        if (!q1.isEmpty()) {
            while (q1.size() > 1) {
                q2.offer(q1.poll());
            }

            return q1.poll();
        }
        else {
            while (q2.size() > 1) {
                q1.offer(q2.poll());
            }

            return q2.poll();
        }
    }
    
    public int top() {
        int res = 0;
        if (!q1.isEmpty()) {
            while (q1.size() > 1) {
                q2.offer(q1.poll());
            }

            res = q1.peek();
            q2.offer(q1.poll());
        }
        else {
            while (q2.size() > 1) {
                q1.offer(q2.poll());
            }

            res = q2.peek();
            q1.offer(q2.poll());
        }

        return res;
    }
    
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}
