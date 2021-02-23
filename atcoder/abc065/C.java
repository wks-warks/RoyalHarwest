import java.io.*;
import java.util.*;
 
public class Main {
    static final FastReader FR = new FastReader();
    static int[] fact, ifact;
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) {
        setFactorial();
        int dogCount = FR.nextInt();
        int monkeyCount = FR.nextInt();

        int ways = getWays(dogCount, monkeyCount);
        System.out.println(ways);
    }

    static int getWays(int dogCount, int monkeyCount) {
      if (Math.abs(dogCount - monkeyCount) > 1) {
        return 0;
      }

      int dogArrangements = fact[dogCount];
      int monkeyArrangements = fact[monkeyCount];

      int overallArrangements = (int) ((long) dogArrangements * monkeyArrangements % MOD);      
      if (dogCount == monkeyCount) {
        overallArrangements = (int) ((long) overallArrangements * 2 % MOD);
      }
      return overallArrangements;
    }

    static void setFactorial() {
      fact = new int[100_001];
        fact[0] = fact[1] = 1;
        for (int i = 2; i <= 100_000; ++i) {
            fact[i] = (int) ((long)fact[i-1] * i % MOD);
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