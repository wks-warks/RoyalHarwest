import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int height = FR.nextInt();
            int width = FR.nextInt();
            char[][] grid = new char[height][];
            for (int h = 0; h < height; ++h) {
                grid[h] = FR.next().toCharArray();
            }
            int timeRequired = computeMinimumTimeRequired(height, width, grid);
            solution.append(timeRequired);
        }
        System.out.println(solution.toString());
    }

    // Computes and returns minimum time required to reach destination, -1 if impossible
    static int computeMinimumTimeRequired(int height, int width, char[][] grid) {
        boolean[][] visited = new boolean[height][width];
        PosPair initPos = new PosPair();
        PosPair finalPos = new PosPair();
        ArrayList<PosPair>[] warps = new ArrayList[26];
        for (int i = 0; i < 26; ++i)
            warps[i] = new ArrayList<>();
        for (int h = 0; h < height; ++h) {
            for (int w = 0; w < width; ++w) {
                if (grid[h][w] == 'S') {
                    initPos.h = h;
                    initPos.w = w;
                }
                else if (grid[h][w] == 'G') {
                    finalPos.h = h;
                    finalPos.w = w;
                }
                else if (Character.isLowerCase(grid[h][w])) {
                    warps[grid[h][w] - 'a'].add(new PosPair(h, w));
                }
            }
        }

        boolean[] visitedWarps = new boolean[26];
        Deque<BFSPair> visitPoints = new LinkedList<BFSPair>();
        visitPoints.add(new BFSPair(0, initPos));
        visited[initPos.h][initPos.w] = true;
        while (visitPoints.size() > 0) {
            BFSPair considered = visitPoints.poll();
            PosPair currPos = considered.position;
            if (currPos.h + 1 < height && !visited[currPos.h+1][currPos.w] && grid[currPos.h+1][currPos.w] != '#') {
                if (finalPos.h == currPos.h+1 && finalPos.w == currPos.w)
                    return considered.timeRequired + 1;
                visitPoints.add(new BFSPair(considered.timeRequired + 1, new PosPair(currPos.h+1, currPos.w)));
                visited[currPos.h+1][currPos.w] = true;
            }
            if (currPos.h > 0 && !visited[currPos.h-1][currPos.w] && grid[currPos.h-1][currPos.w] != '#') {
                if (finalPos.h == currPos.h-1 && finalPos.w == currPos.w)
                    return considered.timeRequired + 1;
                visitPoints.add(new BFSPair(considered.timeRequired + 1, new PosPair(currPos.h-1, currPos.w)));
                visited[currPos.h-1][currPos.w] = true;
            }
            if (currPos.w + 1 < width && !visited[currPos.h][currPos.w+1] && grid[currPos.h][currPos.w+1] != '#') {
                if (finalPos.h == currPos.h && finalPos.w == currPos.w+1)
                    return considered.timeRequired + 1;
                visitPoints.add(new BFSPair(considered.timeRequired + 1, new PosPair(currPos.h, currPos.w+1)));
                visited[currPos.h][currPos.w+1] = true;
            }
            if (currPos.w > 0 && !visited[currPos.h][currPos.w-1] && grid[currPos.h][currPos.w-1] != '#') {
                if (finalPos.h == currPos.h && finalPos.w == currPos.w-1)
                    return considered.timeRequired + 1;
                visitPoints.add(new BFSPair(considered.timeRequired + 1, new PosPair(currPos.h, currPos.w-1)));
                visited[currPos.h][currPos.w-1] = true;
            }
            if (Character.isLowerCase(grid[currPos.h][currPos.w]) && !visitedWarps[grid[currPos.h][currPos.w] - 'a']) {
                int idx = grid[currPos.h][currPos.w] - 'a';
                visitedWarps[idx] = true;
                for (PosPair warpgates : warps[idx]) {
                    if (!visited[warpgates.h][warpgates.w]) {
                        if (finalPos.h == warpgates.h && finalPos.w == warpgates.w)
                            return considered.timeRequired + 1;
                        visitPoints.add(new BFSPair(considered.timeRequired + 1, warpgates));
                        visited[warpgates.h][warpgates.w] = true;
                    }
                }
            }
        }
        return -1;
    }

    // Fast Input - GFG
    static class FastReader { 
        BufferedReader br; 
        StringTokenizer st; 
  
        public FastReader() { 
            br = new BufferedReader(new
                     InputStreamReader(System.in)); 
        } 
  
        String next() { 
            while (st == null || !st.hasMoreElements()) { 
                try { 
                    st = new StringTokenizer(br.readLine()); 
                } catch (IOException  e) { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 
  
        int nextInt() { 
            return Integer.parseInt(next()); 
        } 
  
        long nextLong() { 
            return Long.parseLong(next()); 
        } 
  
        double nextDouble() { 
            return Double.parseDouble(next()); 
        } 

        String nextLine() { 
            String str = ""; 
            try { 
                str = br.readLine(); 
            } catch (IOException e)  { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    }
}

class PosPair {
    public PosPair() {

    }
    public PosPair(int h, int w) {
        this.h = h;
        this.w = w;
    }
    int h;
    int w;
}

class BFSPair {
    public BFSPair() {
        
    }
    public BFSPair(int timeRequired, PosPair position) {
        this.timeRequired = timeRequired;
        this.position = position;
    }
    int timeRequired;
    PosPair position;
}