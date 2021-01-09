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
            long m = FR.nextLong();
            long modM = expMod(10, n, m);
            long modM2 = expMod(10, n, m*m);
            long answer = (modM2 - modM) / m;
            solution.append(answer);
        }
      	PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
		writer.print(solution.toString());
        writer.close();
    }

    static long expMod(long base, long pow, long mod) {
        if (pow == 0)
            return 1 % mod;
        if (pow == 1)
            return base % mod;
        long tmp = expMod(base, pow/2, mod);
        tmp *= tmp;
        tmp %= mod;
        if ((pow&1) == 0) return tmp;
        else return ((base %mod) * tmp) % mod;        
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