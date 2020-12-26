import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            count = new long[10];
            h = FR.nextInt();
            w = FR.nextInt();
            count[0] = (long) (h-2) * (w-2);
            int blackCells = FR.nextInt();
            blackPoints = new HashMap<>();
            for (int c = 0; c < blackCells; ++c) {
                int row = FR.nextInt();
                int col = FR.nextInt();
                adjustCount(row, col);
            }
        }
        for (var element : count) {
            solution.append(element + "\n");
        }
        System.out.print(solution.toString());
    }
    static int h, w;
    static long[] count;
    static Map<Integer, Set<Integer>> blackPoints;

    // Places point on board and adjusts count
    static void adjustCount(int row, int col) {
        if (blackPoints.containsKey(row)) {
            Set<Integer> blackedCols = blackPoints.get(row);
            blackedCols.add(col);
            blackPoints.put(row, blackedCols);
        }
        else {
            Set<Integer> blackedCols = new HashSet<>();
            blackedCols.add(col);
            blackPoints.put(row, blackedCols);
        }

        int rEnd = Math.min(row, h-2);
        int cEnd = Math.min(col, w-2);
        Set<Integer> tempCols;
        for (int r = Math.max(1, row-2); r <= rEnd; ++r) {
            for (int c = Math.max(1, col-2); c <= cEnd; ++c) {
                int tempPoints = 0;
                for (int i = 0; i < 3; ++i) {
                    if (blackPoints.containsKey(r+i)) {
                        tempCols = blackPoints.get(r+i);
                        for (int j = 0; j < 3; j += 1) {
                            if (tempCols.contains(c+j))
                                tempPoints += 1;
                        }
                    }
                }
                count[tempPoints] += 1;
                count[tempPoints-1] = Math.max(0, count[tempPoints-1]-1);
            }
        }
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