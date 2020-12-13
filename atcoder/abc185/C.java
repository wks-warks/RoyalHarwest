import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int length = FR.nextInt();
            long cuts = countWaysToCut(length);
            solution.append(cuts);
        }
        System.out.println(solution.toString());
    }


    // Returns number of ways to cut the rod successfully
    static long countWaysToCut(int length) {
        long[][] dp = new long[length+1][12];
        for (int i = 0; i < dp.length; ++i)
            dp[i][0] = 1;
        
        for (int i = 0; i < length; ++i) {
            for (int j = 1; j < 12; ++j) {
                dp[i+1][j] = dp[i][j] + dp[i][j-1];
            }
        }
        return dp[length-1][11];
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