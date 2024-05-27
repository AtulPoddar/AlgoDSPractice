package KK.StacksAndQueues;

import java.util.Arrays;

// https://www.hackerrank.com/challenges/game-of-two-stacks/problem
public class TwoStacks {
    public static void main(String[] args) {
        int[] a = new int[] {1,1,4,6,1};
        int[] b = new int[] {2,1,1,5};
        System.out.println(MaxCount(a,b,10));
    }

    public static int MaxCount(int[] a, int[] b, int maxSize) {
        return MaxCountHelper(maxSize, a, b, 0, 0);
    }

    private static int MaxCountHelper(int maxSize, int[] a, int[] b, int currentSize, int currentCount) {
        if (currentSize > maxSize) {
            return currentCount-1;
        }

        if (a.length == 0 || b.length == 0) {
            return currentCount;
        }

        int popFromA = MaxCountHelper(maxSize, Arrays.copyOfRange(a, 1, a.length), b, currentSize + a[0], currentCount+1);
        int popFromB = MaxCountHelper(maxSize, a, Arrays.copyOfRange(b, 1, b.length), currentSize + b[0], currentCount+1);

        return Math.max(popFromA, popFromB);
    }
}
