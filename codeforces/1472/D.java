import java.io.*;
import java.util.*;

public class D {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
           int arrLen = FR.nextInt();
           Integer[] arr = new Integer[arrLen];
           for (int i = 0; i < arrLen; i += 1) {
               arr[i] = FR.nextInt();
           }
           long[] scores = getScores(arr);
           solution.append(scores[0]>scores[1] ? "Alice\n" : scores[0] == scores[1] ? "Tie\n" : "Bob\n");
        }
        System.out.print(solution.toString());
    }

    static long[] getScores(Integer[] arr) {
        Arrays.sort(arr);
        int len = arr.length-1;
        long alice = 0;
        long bob = 0;
        int fromEnd = 0;
        while(fromEnd <= len) {
            if ((fromEnd&1) == 0) { // Alice's turn
                if ((arr[len-fromEnd]&1) == 0) alice += arr[len-fromEnd];
            } else {
                if ((arr[len-fromEnd]&1) == 1) bob += arr[len-fromEnd];
            }
            fromEnd += 1;
        }
        return new long[]{alice, bob};
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