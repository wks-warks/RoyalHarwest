import java.io.*;
import java.util.*;

public class A {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = FR.nextInt();
            int[] arr = new int[arrLen];
            for (int i = 0; i < arrLen; i += 1) {
                arr[i] = FR.nextInt();
            }
            int[] originalArr = getOriginalArr(arr);
            for (var element : originalArr) {
                solution.append(element);
                solution.append(' ');
            }
            solution.append('\n');
        }
        System.out.print(solution.toString());
    }

    // Computes and returns original array
    static int[] getOriginalArr(int[] arr) {
        int[] originalArr = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            if (i % 2 == 0) {
                originalArr[i] = arr[i/2];
            }
            else {
                originalArr[i] = arr[arr.length-1 -(i-1)/2];
            }
        }
        return originalArr;
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