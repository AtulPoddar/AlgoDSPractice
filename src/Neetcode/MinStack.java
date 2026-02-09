package Neetcode;

import java.util.Stack;

class MinStack {

    Stack<Integer> stk;
    Stack<Integer> minStk;

    public MinStack() {
        stk = new Stack<>();
        minStk = new Stack<>();
    }
    
    public void push(int val) {
        stk.push(val);
        if (minStk.isEmpty()) {
            minStk.push(val);
        }
        else {
            var min = Math.min(val, minStk.peek());
            minStk.push(min);
        }
    }
    
    public void pop() {
        stk.pop();
        minStk.pop();
    }
    
    public int top() {
        return stk.peek();
    }
    
    public int getMin() {
        return minStk.peek();
    }
}
