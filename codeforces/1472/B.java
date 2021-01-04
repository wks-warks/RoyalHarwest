import java.io.*;
import java.util.*;

public class B {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int candies = FR.nextInt();
            int ones = 0;
            int twos = 0;
            while(candies-->0) {
                if (FR.nextInt() == 1) ones += 1;
                else twos += 1;
            }
            boolean evenlyDistributable = checkDistribution(ones, twos);
            solution.append(evenlyDistributable ? "Yes\n" : "No\n");
        }
        System.out.print(solution.toString());
    }

    static boolean checkDistribution(int ones, int twos) {
        if ((ones&1) == 1) return false;
        else return (ones + twos > 1) && !(ones == 0 && (twos&1)==1);
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