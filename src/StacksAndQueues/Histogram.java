package StacksAndQueues;

import java.util.Stack;

public class Histogram {
    public static void main(String[] args) {
        int[] arr = new int[] {2,1,5,6,2,3};
        System.out.println(maxRect(arr));
    }

    private static int maxRect(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int max = 0;

        for (int i=1; i<arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                max = getMax(arr, stack, max, i);
            }
            stack.push(i);
        }

        int i = arr.length;
        while (!stack.isEmpty()) {
            max = getMax(arr, stack, max, i);
        }

        return max;
    }

    private static int getMax(int[] arr, Stack<Integer> stack, int max, int i) {
        int popped = stack.pop();
        int area = 0;

        if (stack.isEmpty()) {
            area = arr[popped] * i;
        }
        else {
            area = arr[popped] * (i - 1 - stack.peek());
        }

        return Math.max(max, area);
    }
}
