package Neetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeMap {

    private HashMap<String, List<Pair<Integer,String>>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>())
            .add(new Pair<>(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        var pairs = map.getOrDefault(key, new ArrayList<>());
        int l = 0;
        int r = pairs.size() - 1;

        String res = "";
        while (l <= r) {
            int m = l + (r-l)/2;
            if (timestamp >= pairs.get(m).getKey()) {
                res = pairs.get(m).getValue();
                l = m+1;
            }
            else {
                r = m-1;
            }
        }

        return res;
    }

    private static class Pair<K,V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}