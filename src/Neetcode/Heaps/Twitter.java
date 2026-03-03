package Neetcode.Heaps;

import java.util.*;

public class Twitter {

    int count;
    Map<Integer, Set<Integer>> followMap;
    Map<Integer, List<int[]>> tweetMap;

    public Twitter() {
        count = 0;
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        tweetMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(new int[] {count++, tweetId});
    }
    
    public List<Integer> getNewsFeed(int userId) {
        followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);
        var followees = followMap.get(userId);
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> b[0] - a[0]);

        for (Integer followee : followees) {
            var tweets = tweetMap.get(followee);
            if (tweets != null) {
                var index = tweets.size() - 1;
                if (index >= 0) {
                    var lastTweet = tweets.get(index);
                    maxHeap.offer(new int[] {lastTweet[0], lastTweet[1], index, followee});
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!maxHeap.isEmpty() && res.size() < 10) {
            var tweet = maxHeap.poll();
            res.add(tweet[1]);
            var index = tweet[2] - 1;
            if (index >= 0) {
                var tweetToAdd = tweetMap.get(tweet[3]).get(index);
                maxHeap.offer(new int[] {tweetToAdd[0], tweetToAdd[1], index, tweet[3]});
            }
        }

        return res;
    }
    
    public void follow(int followerId, int followeeId) {
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        followMap.computeIfPresent(followerId, (k,v) -> {
            v.remove(followeeId);
            return v;
        });
    }
}
