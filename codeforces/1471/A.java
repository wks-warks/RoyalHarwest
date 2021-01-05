import java.io.*;
import java.util.*;
 
public class A {
    static final FastReader FR = new FastReader();
 
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = FR.nextInt();
            int x = FR.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i += 1) {
                arr[i] = FR.nextInt();
            }
            long[] minMax = getMinMax(arr, x);
            solution.append(minMax[0] + " " + minMax[1] + "\n");
        }
      	PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
		writer.print(solution.toString());
        writer.close();
    }

    static long[] getMinMax(int[] arr, int x) {
        long max = 0;
        long sum = 0;
        for (var element : arr) {
            max += LIG(element, x);
            sum += element;
        }
        long min = LIG(sum, x);
        return new long[] {min, max};
    }
 
    static int LIG(int num1, int num2) {
        return (num1+num2-1) / num2;
    }
    static long LIG(long num1, long num2) {
        return (num1+num2-1) / num2;
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