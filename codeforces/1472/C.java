import java.io.*;
import java.util.*;

public class C {
    static final FastReader FR = new FastReader();

    static int arrLen, maxAnswer;
    static int[] arr;
    static int[] answers;
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            arrLen = FR.nextInt();
            arr = new int[arrLen];
            answers = new int[arrLen];
            for (int i = 0; i < arrLen; i += 1) {
                arr[i] = FR.nextInt();
            }
            for (int i = 0; i < arrLen; i += 1) {
                answers[i] = Integer.MIN_VALUE;
            }
            setMaxAnswer();
            solution.append(maxAnswer);
            solution.append("\n");
        }
        System.out.print(solution.toString());
    }

    static void setMaxAnswer() {
        maxAnswer = Integer.MIN_VALUE;
        for (int i = arrLen-1; i >= 0; i -= 1) {
            answers[i] = arr[i];
            if (i+answers[i] < arrLen) answers[i] += answers[i+answers[i]];
            maxAnswer = Math.max(maxAnswer, answers[i]);
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