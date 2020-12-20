import java.io.*;
import java.util.*;

public class B {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int strlen = FR.nextInt();
            String inp = FR.next();
            boolean happyPolycarp = checkFor2020(inp);
            solution.append(happyPolycarp ? "YES" : "NO");
            solution.append('\n');
        }
        System.out.print(solution.toString());
    }

    // Checks and returns whether 2020 can be obtained in upto 1 operation
    static boolean checkFor2020(String s) {
        for (int i = 0; i <= 4; i += 1) {
            if ((s.substring(0, i) + s.substring(s.length()-4+i)).equals("2020"))
                return true;
        }

        return false;
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