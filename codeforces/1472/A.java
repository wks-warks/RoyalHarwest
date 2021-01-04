import java.io.*;
import java.util.*;

public class A {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int w = FR.nextInt();
            int h = FR.nextInt();
            int n = FR.nextInt();
            boolean canGet = checkIfPossible(w, h, n);
            solution.append(canGet ? "YES\n" : "NO\n");
        }
        System.out.print(solution.toString());
    }

    static boolean checkIfPossible(int w, int h, int n) {
        int twos = 0;
        twos = getTwos(w) + getTwos(h);
        return (twos > 30 || (1 << twos) >= n);       
    }

    static int getTwos(int n) {
        int twos = 0;
        while(n > 0 && (n&1) == 0) {
            n >>= 1;
            twos += 1;
        }
        return twos;
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