import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int a = FR.nextInt();
            int b = FR.nextInt();
            int c = FR.nextInt();
            double expectedOperationCount = estimateOperationCount(a, b, c);
            solution.append(expectedOperationCount);
        }
        System.out.println(solution.toString());
    }

    static TreeMap<Integer, Double> expectedValue = new TreeMap<>();
    // Returns expected number of times an operation is to be performed
    static double estimateOperationCount(int a, int b, int c) {
        int largest = Math.max(a, Math.max(b, c));
        int smallest = Math.min(a, Math.min(b, c));
        int middle = a + b + c - largest - smallest;
        if (largest == 100)
            return 0;
        else {
            int key = largest * 10_000 + middle * 100 + smallest;
            if (expectedValue.containsKey(key))
                return expectedValue.get(key);
            else {
                double pickSmallest = (double) smallest / (smallest + middle + largest);
                double pickMiddle = (double) middle / (smallest + middle + largest);
                double pickLargest = (double) largest / (smallest + middle + largest);
                double expectedOperations = 1.0 + pickSmallest * estimateOperationCount(smallest + 1, middle, largest)
                                                + pickMiddle * estimateOperationCount(smallest, middle + 1, largest)
                                                + pickLargest * estimateOperationCount(smallest, middle, largest + 1);
                expectedValue.put(key, expectedOperations);
                return expectedOperations;
            }
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