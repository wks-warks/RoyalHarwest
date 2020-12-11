import java.io.*;
import java.util.*;

public class B {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        int tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = FR.nextInt();
            int m = FR.nextInt();
            char[][] matrix = new char[n][];
            for (int i = 0; i < n; ++i) {
                matrix[i] = FR.next().toCharArray();
            }
            int spruces = countSpruces(matrix);
            System.out.println(spruces);
        }
    }

    // Counts and returns number of spruces in the matrix
    static int countSpruces(char[][] matrix) {
        int[][] spruceOrder = new int[matrix.length][matrix[0].length];
        int maxOrder = 0;
        int count = 0;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] == '*') {
                    spruceOrder[i][j] = 1;
                    maxOrder = 1;
                    count += 1;
                }
            }
        }
        if (maxOrder == 0)
            return 0;
        int treeLengthPossible = Math.min(matrix.length, matrix[0].length);
        for (int i = 1; i < treeLengthPossible; ++i) {
            for (int j = i; j < matrix.length; ++j) {
                for (int k = i; k < matrix[0].length - i; ++k) {
                    if (spruceOrder[j][k] == i) {
                        if (spruceOrder[j-1][k] >= i && (matrix[j][k-i] == '*') && (matrix[j][k+i] == '*')) {
                            spruceOrder[j][k] += 1;
                            count += 1;
                            maxOrder = i + 1;
                        }
                    }
                }
            }
            if (i == maxOrder)
                break;
        }
        return count;
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