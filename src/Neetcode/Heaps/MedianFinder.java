package Neetcode.Heaps;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {

    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        maxHeap.offer(num);
        if (maxHeap.size() - minHeap.size() > 1 || (!minHeap.isEmpty() && minHeap.peek() < maxHeap.peek())) {
            minHeap.offer(maxHeap.poll());
        }
        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (double)(maxHeap.peek() + minHeap.peek()) / 2;
        }
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        else {
            return minHeap.peek();
        }
    }
}
