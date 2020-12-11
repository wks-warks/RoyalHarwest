import java.io.*;
import java.util.*;

public class D {
    static final FastReader FR = new FastReader();

    static TreeSet<Long> solutionSet;
    static long[] prefixSum;
    static int[] arr;
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = FR.nextInt();
            int queries = FR.nextInt();
            arr = new int[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arr[i] = FR.nextInt();
            Arrays.sort(arr);
            setPrefixSum();
            solutionSet = new TreeSet<>();
            fillSolutionSet(0, arrLen-1);
            for (int i = 0; i < queries; ++i) {
                long sumRequired = FR.nextLong();
                solution.append(solutionSet.contains(sumRequired)? "YES\n" : "NO\n");
            }
        }
        System.out.print(solution.toString());
    }

    // Fills solution set
    static void fillSolutionSet(int lIdx, int rIdx) {
        solutionSet.add(prefixSum[rIdx+1] - prefixSum[lIdx]);
        if (lIdx < rIdx) {
            int midIdx = computeMidIdx(lIdx, rIdx);
            if (midIdx < rIdx) {
                fillSolutionSet(lIdx, midIdx);
                fillSolutionSet(midIdx + 1, rIdx);
            }
        }
    }

    // Computes and returns midIdx
    static int computeMidIdx(int lIdx, int rIdx) {
        int mean = (arr[lIdx] + arr[rIdx]) / 2;
        int left = lIdx;
        int right = rIdx;
        int midIdx = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= mean) {
                midIdx = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return midIdx;
    }

    // Sets prefixSum array
    static void setPrefixSum() {
        prefixSum = new long[arr.length + 1];
        for (int i = 1; i <= arr.length; ++i) {
            prefixSum[i] = prefixSum[i-1] + arr[i-1];
        }
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