import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            long n = FR.nextLong();
            long s = FR.nextLong();
            long base = getBase(n, s);
            solution.append(base);
        }
        System.out.println(solution.toString());
    }

    // Computes and returns base for given n, s
    static long getBase(long n, long s) {
        if (n == s) {
            return n+1;
        }

        int sqrt = (int) Math.sqrt(n);
        for (int consider = 2; consider <= sqrt; consider++) {
            if (s == getDigitSum(consider, n)) {
                return consider;
            }
        }
        for (int consider = sqrt; consider > 0; consider--) {
            long b = (n - s) / consider + 1;
            if (b > sqrt && s == getDigitSum(b, n)) {
                return b;
            }
        }
        return -1;
    }

    static long getDigitSum(long base, long num) {
        if (num < base) {
            return num;
        } else {
            return getDigitSum(base, num / base) + num % base;
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