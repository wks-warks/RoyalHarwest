import java.io.*;
import java.util.*;

public class C {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        int tests = FR.nextInt();
        for (int t = 0;  t < tests; ++t) {
            int permLen = FR.nextInt();
            int experiments = FR.nextInt();
            int[] permutation = new int[permLen];
            for (int p = 0; p < permLen; ++p)
                permutation[p] = FR.nextInt();
            int smallestUnsortedIdx = findSmallestUnsortedIdx(permutation);
            double notAnswer = 1.0;
            for (int e = 0; e < experiments; ++e) {
                int prefLen = FR.nextInt();
                double probability = FR.nextDouble();
                if (prefLen >= smallestUnsortedIdx) {
                    notAnswer *= (1.0 - probability);
                }
            }
            System.out.println(smallestUnsortedIdx != 0 ? 1.0 - notAnswer : 1.0);
        }
    }

    // Finds and returns smallest unsorted idx
    static int findSmallestUnsortedIdx(int[] permutation) {
        int idx = permutation.length;
        while (idx > 0 && permutation[idx-1] == idx) {
            idx -= 1;
        }
        return idx;
    }

    // Fast Input - GFG
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