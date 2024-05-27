package KK.StacksAndQueues;

import java.util.Stack;

public class ValidParanMinAdd {
    public static void main(String[] args) {
        String s = "(())((((";
        System.out.println(minAddCount(s));
    }

    private static int minAddCount(String str) {
        Stack<Character> stack = new Stack<>();

        for (Character ch : str.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            }
            else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
                else {
                    stack.push(ch);
                }
            }
        }

        return stack.size();
    }
}
