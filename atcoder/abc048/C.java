import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int boxes = FR.nextInt();
            int adjacentSumLimit = FR.nextInt();
            int[] candies = new int[boxes];
            for (int i = 0; i < boxes; i += 1) {
                candies[i] = FR.nextInt();
            }
            long toRemove = getRemoveCount(candies, adjacentSumLimit);
            solution.append(toRemove);
        }
        System.out.println(solution.toString());
    }

    // Computes and returns minimum number of candies to be removed
    static long getRemoveCount(int[] candies, int adjacentSumLimit) {
        long totalRemoval = 0;
        int prev = 0;
        for (var element : candies) {
            int remove = Math.max(0, element+prev-adjacentSumLimit);
            prev = element - remove;
            totalRemoval += remove;
        }
        return totalRemoval;
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