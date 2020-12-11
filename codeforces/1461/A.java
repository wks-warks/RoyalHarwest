import java.io.*;
import java.util.*;

public class A {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        int tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = FR.nextInt();
            int k = FR.nextInt();
            String solution = getSolution(n, k);
            System.out.println(solution);
        }
    }

    static String getSolution(int n, int k) {
        StringBuilder solution = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            char ch = (char) ('a' + i % 3);
            solution.append(ch);
        }
        return solution.toString();
    }

    // Fast Input - GFG
    static class FastReader 
    { 
        BufferedReader br; 
        StringTokenizer st; 
  
        public FastReader() 
        { 
            br = new BufferedReader(new
                     InputStreamReader(System.in)); 
        } 
  
        String next() 
        { 
            while (st == null || !st.hasMoreElements()) 
            { 
                try
                { 
                    st = new StringTokenizer(br.readLine()); 
                } 
                catch (IOException  e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 
  
        int nextInt() 
        { 
            return Integer.parseInt(next()); 
        } 
  
        long nextLong() 
        { 
            return Long.parseLong(next()); 
        } 
  
        double nextDouble() 
        { 
            return Double.parseDouble(next()); 
        } 
  
        String nextLine() 
        { 
            String str = ""; 
            try
            { 
                str = br.readLine(); 
            } 
            catch (IOException e) 
            { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    }
}