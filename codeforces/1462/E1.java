import java.io.*;
import java.util.*;

public class E1 {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = FR.nextInt();
            Integer[] arr = new Integer[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arr[i] = FR.nextInt();
            Arrays.sort(arr);
            long answer = getAnswer(arr);
            solution.append(answer);
            solution.append('\n');
        }
        System.out.print(solution.toString());
    }

    // Gets answer
    static long getAnswer(Integer[] arr) {
        long answer = 0;
        int left, right, mid, lIdx, rIdx;
        for (int i = 0; i < arr.length-1; ++i) {
            left = i+1;
            right = arr.length-1;
            rIdx = -1;
            while (left <= right) {
                mid = (left + right) / 2;
                if (arr[mid] - arr[i] <= 2) {
                    rIdx = mid;
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
            if (rIdx != -1) {
                lIdx = i+1;
                answer += (long) (rIdx-lIdx+1) * (rIdx-lIdx) / 2;
            }
        }
        return answer;
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