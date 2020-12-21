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
            int[] arr = new int[arrLen];
            for (int i = 0; i < arr.length; i += 1) {
                arr[i] = FR.nextInt();
            }
            int operationsRequired = computeOperationsRequired(arr);
            solution.append(operationsRequired + "\n");
        }
        System.out.print(solution.toString());
    }

    // Computes and returns operations required to get desired result
    static int computeOperationsRequired(int[] arr) {
        Set <Integer> sumSet = new TreeSet <Integer> ();
        int sum = 0;
        for (var element : arr) {
            sum += element;
            sumSet.add(sum);
        }
        
        int average = sum / arr.length;

        int eachEquals = sum;
        for (var element : sumSet) {
            if (element < average || sum % element > 0)
                continue;
            
            boolean foundSolution = true;
            for (int i = 2; i < sum / element; i += 1) {
                if (!sumSet.contains(i * element)) {
                    foundSolution = false;
                    break;
                }
            }
            if (foundSolution) {
                eachEquals = element;
                break;
            }
        }

        return arr.length - (sum / eachEquals);        
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