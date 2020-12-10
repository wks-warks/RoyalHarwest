//Codeforces 433B 
import java.util.*;
import java.io.*;

public class CF433B {
    static final FastReader FR = new FastReader();
    
    static long[] originalPrefixSum, sortedCostPrefixSum;
    public static void main(String[] args) {
        int stones = FR.nextInt();
        Integer[] costs = new Integer[stones];
        for (int s = 0; s < stones; ++s)
            costs[s] = FR.nextInt();
        setPrefixSums(costs);

        StringBuilder solution = new StringBuilder();
        int questions = FR.nextInt();
        for (int q = 0; q < questions; ++q) {
            int type = FR.nextInt();
            int left = FR.nextInt();
            int right = FR.nextInt();
            long answer = getAns(type, left, right);
            solution.append(answer + "\n");
        }
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
        writer.print(solution.toString());
        writer.close();
    }

    // Computes and returns answer to the given question
    static long getAns(int type, int left, int right) {
        if (type == 1)
            return originalPrefixSum[right] - originalPrefixSum[left-1];
        else
            return sortedCostPrefixSum[right] - sortedCostPrefixSum[left-1];
    }

    // Sets prefixSums as per costs
    static void setPrefixSums(Integer[] costs) {
        originalPrefixSum = getPrefixSum(costs);
        Arrays.sort(costs);
        sortedCostPrefixSum = getPrefixSum(costs);
    }
    
    // Computes and returns prefix sum for given array
    static long[] getPrefixSum(Integer[] array) {
        long[] prefixSum = new long[array.length+1];
        for (int i = 1; i < prefixSum.length; ++i)
            prefixSum[i] = prefixSum[i-1] + array[i-1];
        return prefixSum;
    }

    static class FastReader 
    { 
        BufferedReader br; 
        StringTokenizer st; 
  
        public FastReader() 
        { 
            br = new BufferedReader(new
                     InputStreamReader(System.in)); 
        } 
  
        String next() 
        { 
            while (st == null || !st.hasMoreElements()) 
            { 
                try
                { 
                    st = new StringTokenizer(br.readLine()); 
                } 
                catch (IOException  e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 
  
        int nextInt() 
        { 
            return Integer.parseInt(next()); 
        } 
  
        long nextLong() 
        { 
            return Long.parseLong(next()); 
        } 
  
        double nextDouble() 
        { 
            return Double.parseDouble(next()); 
        } 
  
        String nextLine() 
        { 
            String str = ""; 
            try
            { 
                str = br.readLine(); 
            } 
            catch (IOException e) 
            { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    }
}
