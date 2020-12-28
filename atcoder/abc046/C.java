import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int checks = FR.nextInt();
            long takahashi = FR.nextLong();
            long aoki = FR.nextLong();
            long total = takahashi + aoki;
            for (int i = 1; i < checks; i += 1) {
                long takaNew = FR.nextLong();
                long aoNew = FR.nextLong();
                long mul = Math.max(lig(aoki, aoNew), lig(takahashi, takaNew));
                takahashi = takaNew * mul;
                aoki = aoNew * mul;
            }
            solution.append(takahashi+aoki);
        }
        System.out.println(solution.toString());
    }

    static long lig(long num, long den) {
        return (num+den-1)/den;
    }
    static long gcd(long num1, long num2) {
        if (num1 == 0) return num2;
        else return gcd(num2 % num1, num1);
    }
    static long lcm(long num1, long num2) {
        return num1 / gcd(num1, num2) * num2;
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