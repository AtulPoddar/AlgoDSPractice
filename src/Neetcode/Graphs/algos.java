package Neetcode.Graphs;

import java.util.*;

public class algos {
    public static void main(String[] args) {
        int[][] val = new int[4][4];
        val[0] = new int[] {1,2};
        val[1] = new int[] {1,3};
        val[2] = new int[] {3,4};
        val[3] = new int[] {2,4};
        //var temp = findRedundantConnection(val);
    }

    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int islands = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islandHelper(grid, r, c);
                    islands++;
                }
            }
        }

        return islands;
    }

    public void islandHelper(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';

        islandHelper(grid, r+1, c);
        islandHelper(grid, r-1, c);
        islandHelper(grid, r, c+1);
        islandHelper(grid, r, c-1);

        return;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int maxArea = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, islandAreaHelper(grid, r, c));
                }
            }
        }

        return maxArea;
    }

    public int islandAreaHelper(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        int count = 1;
        grid[r][c] = 0;

        count += islandAreaHelper(grid, r+1, c);
        count += islandAreaHelper(grid, r-1, c);
        count += islandAreaHelper(grid, r, c+1);
        count += islandAreaHelper(grid, r, c-1);

        return count;
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Queue<Node> q = new LinkedList<>();
        Map<Node,Node> oldToNew = new HashMap<>();
        oldToNew.put(node, new Node(node.val));
        q.offer(node);

        while (!q.isEmpty()) {
            var elem = q.poll();
            for (Node neighbour : elem.neighbors) {
                if (!oldToNew.containsKey(neighbour)) {
                    oldToNew.put(neighbour, new Node(neighbour.val));
                    q.offer(neighbour);
                }
                oldToNew.get(elem).neighbors.add(oldToNew.get(neighbour));
            }
        }

        return oldToNew.get(node);
    }

    /*
    public void islandsAndTreasure(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    islandTreasureHelper(grid, r, c, 0);
                }
            }
        }
    }

    public void islandTreasureHelper(int[][] grid, int r, int c, int count) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == -1 || grid[r][c] < count) {
            return;
        }

        grid[r][c] = Math.min(grid[r][c], count);

        islandTreasureHelper(grid, r+1, c, count+1);
        islandTreasureHelper(grid, r-1, c, count+1);
        islandTreasureHelper(grid, r, c+1, count+1);
        islandTreasureHelper(grid, r, c-1, count+1);
    }
        */

    public void islandsAndTreasure(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    q.offer(new int[] {r,c});
                }
            }
        }

        List<int[]> paths = new ArrayList<>();
        paths.add(new int[] {1,0});
        paths.add(new int[] {-1,0});
        paths.add(new int[] {0,1});
        paths.add(new int[] {0,-1});
        int length = 0;
        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                var elem = q.poll();
                int r = elem[0];
                int c = elem[1];
                for (int[] path : paths) {
                    int rp = r+path[0];
                    int cp = c+path[1];
                    if (rp >= 0 && cp >= 0 && rp < grid.length && cp < grid[0].length && grid[rp][cp] == 2147483647) {
                        q.offer(new int[] {rp,cp});
                        grid[rp][cp] = length+1;
                    }
                }
            }

            length++;
        }
    }

    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        int freshCount = 0;
        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    q.offer(new int[] {r,c});
                }
                else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }

        List<int[]> paths = new ArrayList<>();
        paths.add(new int[]{1,0});
        paths.add(new int[]{-1,0});
        paths.add(new int[]{0,1});
        paths.add(new int[]{0,-1});
        int time = 0;
        while (!q.isEmpty()) {
            int qSize = q.size();
            boolean rottenFound = false;
            for (int i = 0; i < qSize; i++) {
                var elem = q.poll();
                int r = elem[0];
                int c = elem[1];
                for (int[] path : paths) {
                    int rp = r+path[0];
                    int cp = c+path[1];
                    if (rp >=0 && cp >= 0 && rp < rows && cp < cols && grid[rp][cp] == 1) {
                        grid[rp][cp] = 2;
                        q.offer(new int[] {rp,cp});
                        rottenFound = true;
                        freshCount--;
                    }
                }
            }

            if (rottenFound) {
                time++;
            }
        }

        if (freshCount == 0) {
            return time;
        }

        return -1;
    }

/* 
    // Ineffecient - O((m*n)^2)
    boolean pacific = false;
    boolean atlantic = false;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        List<List<Integer>> res = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                pacific = false;
                atlantic = false;
                pacificAtlanticHelperDfs(heights, r, c, Integer.MAX_VALUE);
                if (pacific && atlantic) {
                    res.add(new ArrayList<>(Arrays.asList(r,c)));
                }
            }
        }

        return res;
    }

    public void pacificAtlanticHelperDfs(int[][] heights, int r, int c, int parentValue) {
        if (r < 0 || c < 0) {
            pacific = true;
            return;
        }
        if (r == heights.length || c == heights[0].length) {
            atlantic = true;
            return;
        }
        if (heights[r][c] > parentValue) {
            return;
        }

        List<int[]> paths = new ArrayList<>(Arrays.asList(new int[] {1,0}, new int[] {-1,0}, new int[] {0,1}, new int[] {0,-1}));
        var temp = heights[r][c];
        heights[r][c] = Integer.MAX_VALUE;
        for (int[] path : paths) {
            pacificAtlanticHelperDfs(heights, r+path[0], c+path[1], temp);
        }
        heights[r][c] = temp;
    }
*/

    // O(m*n)
    List<int[]> paths = new ArrayList<>(Arrays.asList(new int[] {1,0}, new int[] {-1,0}, new int[] {0,1}, new int[] {0,-1}));
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] pac = new boolean[rows][cols];
        boolean[][] atl = new boolean[rows][cols];

        for (int c = 0; c < cols; c++) {
            pacAtlDfs(heights, pac, 0, c);
            pacAtlDfs(heights, atl, rows-1, c);
        }

        for (int r = 0; r < rows; r++) {
            pacAtlDfs(heights, pac, r, 0);
            pacAtlDfs(heights, atl, r, cols-1);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pac[r][c] && atl[r][c]) {
                    res.add(new ArrayList<>(Arrays.asList(r,c)));
                }
            }
        }

        return res;
    }

    public void pacAtlDfs(int[][] heights, boolean[][] ocean, int r, int c) {
        ocean[r][c] = true;
        for (int[] path : paths) {
            int rp = r+path[0];
            int cp = c+path[1];
            if (rp >= 0 && cp >= 0 && rp < heights.length && cp < heights[0].length && !ocean[rp][cp] && heights[rp][cp] >= heights[r][c]) {
                pacAtlDfs(heights, ocean, rp, cp);
            }
        }
    }

    // Surrounded Regions
    public void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            surroundDfsHelper(board, r, 0);
            surroundDfsHelper(board, r, cols-1);
        }
        for (int c = 0; c < cols; c++) {
            surroundDfsHelper(board, 0, c);
            surroundDfsHelper(board, rows-1, c);
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                }
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'T') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    public void surroundDfsHelper(char[][] board, int r, int c) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != 'O') {
            return;
        }

        board[r][c] = 'T';

        surroundDfsHelper(board, r+1, c);
        surroundDfsHelper(board, r-1, c);
        surroundDfsHelper(board, r, c+1);
        surroundDfsHelper(board, r, c-1);
    }

    // Course Schedule
    // DFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            return true;
        }

        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for (int[] pre : prerequisites) {
            map.computeIfAbsent(pre[0], k -> new ArrayList<>()).add(pre[1]);
        }

        var keySet = map.keySet();
        // This loop is for the case when not all nodes are connected to each other
        for (int course : keySet) {
            if (!canFinishHelper(map, new HashSet<>(), course)) {
                return false;
            }
        }
        return true;
    }

    public boolean canFinishHelper(HashMap<Integer,List<Integer>> preMap, HashSet<Integer> visited, int currCourse) {
        if (visited.contains(currCourse)) {
            return false;
        }
        if (preMap.get(currCourse) == null || preMap.get(currCourse).isEmpty()) {
            return true;
        }

        visited.add(currCourse);
        var deps = preMap.get(currCourse);
        for (Integer dep : deps) {
            if (!canFinishHelper(preMap, visited, dep)) {
                return false;
            }
        }

        // This will reduce the number of times result is computed for a node to just 1.
        preMap.get(currCourse).clear();

        visited.remove(currCourse);
        return true;
    }

    // Course Schedule
    // Topological Sort (Kahn's algorithm)
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            return true;
        }

        int[] indegree = new int[numCourses];
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int[] pre : prerequisites) {
            indegree[pre[0]]++;
            map.computeIfAbsent(pre[1], k -> new ArrayList<>()).add(pre[0]);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            var elem = q.poll();
            res.add(elem);
            if (map.containsKey(elem)) {
                var dep = map.get(elem);
                for (Integer d : dep) {
                    indegree[d]--;
                    if (indegree[d] == 0) {
                        q.offer(d);
                    }
                }
            }
        }

        return res.size() == numCourses;
    }

    // Course Schedule 2
    // DFS
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }
        
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for (int[] pre : prerequisites) {
            map.computeIfAbsent(pre[0], k -> new ArrayList<>()).add(pre[1]);
        }

        int[] res = new int[numCourses];
        LinkedHashSet<Integer> resSet = new LinkedHashSet<>();
        var keySet = map.keySet();
        for (int key : keySet) {
            if (!findOrderHelper(map, new HashSet<>(), resSet, key)) {
                return new int[0];
            }
        }

        for (int j = 0; j < numCourses; j++) {
            if (!resSet.contains(j)) {
                resSet.add(j);
            }
        }

        int i = 0;
        for (Integer r : resSet) {
            res[i++] = r;
        }
        
        return res;
    }

    public boolean findOrderHelper(HashMap<Integer,List<Integer>> preMap, HashSet<Integer> visited, LinkedHashSet<Integer> resSet, int currCourse) {
        if (visited.contains(currCourse)) {
            return false;
        }
        if (preMap.get(currCourse) == null || preMap.get(currCourse).isEmpty()) {
            if (!resSet.contains(currCourse)) {
                resSet.add(currCourse);
            }
            return true;
        }

        visited.add(currCourse);
        var deps = preMap.get(currCourse);
        for (int dep : deps) {
            if (!findOrderHelper(preMap, visited, resSet, dep)) {
                return false;
            }
        }

        if (!resSet.contains(currCourse)) {
            resSet.add(currCourse);
        }
        
        preMap.get(currCourse).clear();
        visited.remove(currCourse);
        return true;
    }

    // Course Schedule 2
    // Topological Sort Kahn
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }

        int[] indegree = new int[numCourses];
        Map<Integer,List<Integer>> preMap = new HashMap<>();
        for (int[] pre : prerequisites) {
            indegree[pre[0]]++;
            preMap.computeIfAbsent(pre[1], k -> new ArrayList<>()).add(pre[0]);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] res = new int[numCourses];
        int index = 0;
        while (!q.isEmpty()) {
            var elem = q.poll();
            res[index++] = elem;
            var deps = preMap.get(elem);
            if (deps != null) {
                for (Integer dep : deps) {
                    indegree[dep]--;
                    if (indegree[dep] == 0) {
                        q.offer(dep);
                    }
                }
            }            
        }

        return index == numCourses ? res : new int[0];
    }

    public boolean validTree(int n, int[][] edges) {
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        HashSet<Integer> visited = new HashSet<>();
        if (!validTreeHelper(0, -1, visited, map)) {
            return false;
        }

        return visited.size() == n;
    }

    public boolean validTreeHelper(int node, int parent, HashSet<Integer> visited, HashMap<Integer,List<Integer>> map) {
        if (visited.contains(node)) {
            return false;
        }
        
        visited.add(node);
        var children = map.get(node);
        if (children == null) {
            return true;
        }
        for (Integer child : children) {
            if (child == parent) {
                continue;
            }
            if (!validTreeHelper(child, node, visited, map)) {
                return false;
            }
        }

        return true;
    }

    public class DSU {
        Map<Integer,Integer> parent;
        Map<Integer,Integer> rank;

        public DSU(int n) {
            parent = new HashMap<>();
            rank = new HashMap<>();
            for (int i = 0; i < n; i++) {
                parent.put(i, i);
                rank.put(i, 0);
            }
        }

        public int find(int x) {
            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }

            return parent.get(x);
        }

        public boolean union(int p, int q) {
            int r1 = find(p);
            int r2 = find(q);

            if (r1 == r2) {
                return false;
            }
            if (rank.get(r1) > rank.get(r2)) {
                parent.put(r2, r1);
            }
            else if (rank.get(r1) < rank.get(r2)) {
                parent.put(r1, r2);
            }
            else {
                parent.put(r2, r1);
                rank.put(r1, rank.get(r1)+1);
            }

            return true;
        }
    }

    // Union-Find
    public boolean validTree2(int n, int[][] edges) {
        var dsu = new DSU(n);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) {
                return false;
            }
        }

        var val = dsu.find(0);
        for (int i = 1; i < n; i++) {
            var par = dsu.find(i);
            if (val != par) {
                return false;
            }
        }

        return true;
    }

    public int countComponents(int n, int[][] edges) {
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        HashSet<Integer> visited = new HashSet<>();
        int count = 0;
        var keySet = map.keySet();
        for (Integer key : keySet) {
            if (!visited.contains(key)) {
                countComponentsHelper(key, -1, visited, map);
                count++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                count++;
            }
        }

        return count;
    }

    public void countComponentsHelper(int node, int parent, HashSet<Integer> visited, HashMap<Integer,List<Integer>> map) {
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        var children = map.get(node);
        if (children == null) {
            return;
        }
        for (Integer child : children) {
            if (parent == child) {
                continue;
            }
            countComponentsHelper(child, node, visited, map);
        }
    }

    public class DSU2 {
        Map<Integer,Integer> parent;
        Map<Integer,Integer> rank;
        int components;

        public DSU2(int n) {
            parent = new HashMap<>();
            rank = new HashMap<>();
            components = n;
            for (int i = 0; i < n; i++) {
                parent.put(i, i);
                rank.put(i, 0);
            }
        }

        public int find(int x) {
            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }

            return parent.get(x);
        }

        public void union(int p, int q) {
            int f1 = find(p);
            int f2 = find(q);
            if (f1 == f2) {
                return;
            }
            else if (rank.get(f1) > rank.get(f2)) {
                parent.put(f2, f1);
            }
            else if (rank.get(f1) < rank.get(f2)) {
                parent.put(f1, f2);
            }
            else {
                parent.put(f2, f1);
                rank.put(f1, rank.get(f1) + 1);
            }
            components--;
        }
    }

    // Union-Find
    public int countComponents2(int n, int[][] edges) {
        DSU2 dsu = new DSU2(n);
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }

        return dsu.components;
    }

    public int cycleStart = -1;
    List<Integer> cycle = new ArrayList<>();
    public int[] findRedundantConnection(int[][] edges) {
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        
        redConnectionHelper(1, -1, new HashSet<>(), map);
        for (int i = edges.length-1; i >= 0; i--) {
            int[] edge = edges[i];
            if (cycle.contains(edge[0]) && cycle.contains(edge[1])) {
                return new int[] {edge[0], edge[1]};
            }
        }

        return new int[0];
    }

    public boolean redConnectionHelper(int node, int parent, HashSet<Integer> visited, HashMap<Integer,List<Integer>> map) {
        if (visited.contains(node)) {
            cycleStart = node;
            return true;
        }

        visited.add(node);
        var children = map.get(node);
        if (children == null) {
            return false;
        }
        for (Integer child : children) {
            if (child == parent) {
                continue;
            }
            if (redConnectionHelper(child, node, visited, map)) {
                if (cycleStart != -1) {
                    cycle.add(node);
                }
                if (cycleStart == node) {
                    cycleStart = -1;
                }
                return true;
            }
        }
        return false;
    }

    public class DSU3 {
        Map<Integer,Integer> parent;
        Map<Integer,Integer> rank;

        public DSU3(int n) {
            parent = new HashMap<>();
            rank = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                parent.put(i, i);
                rank.put(i, 0);
            }
        }

        public int find(int x) {
            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }

            return parent.get(x);
        }

        public int[] union(int p, int q) {
            int f1 = find(p);
            int f2 = find(q);

            if (f1 == f2) {
                return new int[] {p,q};
            }
            else if (rank.get(f1) > rank.get(f2)) {
                parent.put(f2, f1);
            }
            else if (rank.get(f2) > rank.get(f1)) {
                parent.put(f1, f2);
            }
            else {
                parent.put(f2, f1);
                rank.put(f1, rank.get(f1)+1);
            }

            return null;
        }
    }

    // Union-Find
    public int[] findRedundantConnection2(int[][] edges) {
        DSU3 dsu = new DSU3(edges.length);
        for (int[] edge : edges) {
            var union = dsu.union(edge[0], edge[1]);
            if (union != null) {
                return union;
            }
        }

        return new int[0];
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Map<String,List<String>> map = new HashMap<>();
        wordList.add(beginWord);
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String pattern = word.substring(0, i) + "*" + word.substring(i+1);
                map.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
            }
        }

        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        visited.add(beginWord);
        int len = 1;
        while (!q.isEmpty()) {
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var elem = q.poll();
                if (elem.equals(endWord)) {
                    return len;
                }
                for (int j = 0; j < elem.length(); j++) {
                    String pattern = elem.substring(0, j) + "*" + elem.substring(j+1);
                    if (map.containsKey(pattern)) {
                        var ls = map.get(pattern);
                        for (String l : ls) {
                            if (!visited.contains(l)) {
                                q.offer(l);
                                visited.add(l);
                            }
                        }
                    }
                }
            }
            len++;
        }

        return 0;
    }
}
