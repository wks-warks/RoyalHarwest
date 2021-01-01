import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int height = FR.nextInt();
            int width = FR.nextInt();
            String imageRow;
            for (int i = 0; i < height; i += 1) {
                imageRow = FR.next();
                solution.append(imageRow + "\n");
                solution.append(imageRow + "\n");
            }
            
        }
        System.out.print(solution.toString());
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