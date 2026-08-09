package Neetcode.practice2;

import java.util.*;

public class Twitter {

    Map<Integer, Set<Integer>> followMap;
    Map<Integer, List<int[]>> tweets;
    int time;

    public Twitter() {
        followMap = new HashMap<>();
        tweets = new HashMap<>();
        time = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId, n -> new ArrayList<>()).add(new int[] { time++, tweetId });
    }
    
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> b[0] - a[0]);
        if (tweets.containsKey(userId)) {
            var selfTweets = tweets.get(userId);
            selfTweets.forEach(n -> maxHeap.add(n));
        }
        if (followMap.containsKey(userId)) {
            var following = followMap.get(userId);
            for (Integer f : following) {
                if (f == userId) {
                    continue;
                }
                var tweet = tweets.get(f);
                if (tweet != null) {
                    tweet.forEach(n -> maxHeap.add(n));
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (maxHeap.size() == 0) {
                break;
            }
            
            var pt = maxHeap.poll();
            res.add(pt[1]);
        }

        return res;
    }
    
    public void follow(int followerId, int followeeId) {
        followMap.computeIfAbsent(followerId, n -> new HashSet<>()).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        followMap.computeIfPresent(followerId, (k,v) -> {
            v.remove(followeeId);
            return v;
        });
    }
}
