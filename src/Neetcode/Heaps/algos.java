package Neetcode.Heaps;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class algos {
    public static void main(String[] args) {
        
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (Integer stone : stones) {
            maxHeap.offer(stone);
        }

        while (!maxHeap.isEmpty()) {
            var stone1 = maxHeap.poll();
            if (maxHeap.size() >= 1) {
                var stone2 = maxHeap.poll();
                var diff = stone1 - stone2;
                if (diff > 0) {
                    maxHeap.offer(diff);
                }
            }
            else {
                return stone1;
            }
        }

        return 0;
    }

    public class Pair2<K,V> {
        public K val1;
        public V val2;

        public Pair2(K val1, V val2) {
            this.val1 = val1;
            this.val2 = val2;
        }

        public K getVal1() {
            return val1;
        }

        public V getVal2() {
            return val2;
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Pair2<Double, int[]>> maxHeap = new PriorityQueue<>((a,b) -> Double.compare(b.getVal1(), a.getVal1()));
        for (int i = 0; i < points.length; i++) {
            var val = points[i];
            var x = val[0];
            var y = val[1];

            var dist = Math.sqrt((x*x) + (y*y));
            maxHeap.offer(new Pair2<>(dist, val));
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] res = new int[k][2];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            var val = maxHeap.poll().val2;
            res[i++] = new int[] {val[0], val[1]};
        }

        return res;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (Integer num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }

    // Task Scheduler
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char ch : tasks) {
            count[ch - 'A']++;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int c : count) {
            if (c > 0) {
                maxHeap.offer(c);
            }
        }

        int time = 0;
        Queue<int[]> q = new LinkedList<>();
        while (!q.isEmpty() || !maxHeap.isEmpty()) {
            time++;

            if (!maxHeap.isEmpty()) {
                var val = maxHeap.poll() - 1;
                if (val > 0) {
                    q.offer(new int[] {val, time + n});
                }
            }
            else {
                // This is to reduce the number of loops if heap is empty and q items are not ready to be moved to heap
                time = q.peek()[1];
            }

            if (!q.isEmpty() && q.peek()[1] == time) {
                maxHeap.offer(q.poll()[0]);
            }
        }

        return time;
    }
}
