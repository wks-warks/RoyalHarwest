import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = FR.nextInt();
            int[] arr = new int[arrLen];
            for (int i = 0; i < arrLen; i += 1) {
                arr[i] = FR.nextInt();
            }
            int minCost = calculateMinCost(arr);
            solution.append(minCost);
        }
        System.out.println(solution.toString());
    }

    // Computes and returns minimum cost required to get all N elements equal
    static int calculateMinCost(int[] arr) {
        Arrays.sort(arr);
        int min = arr[0];
        int max = arr[arr.length-1];
        int minTotalCost = Integer.MAX_VALUE;
        for (int target = min; target <= max; ++target) {
            int cost = 0;
            for (var element : arr) {
                cost += (element-target)*(element-target);
            }
            minTotalCost = Math.min(minTotalCost, cost);
        }
        return minTotalCost;
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