import java.io.*;
import java.util.*;
 
class Main {
    static final FastReader FR = new FastReader();
 
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = FR.nextInt();
            int[] skills = new int[1<<n];
            for (int i = 0; i < skills.length; i += 1) {
                skills[i] = FR.nextInt();
            }
            int secondPlaceIdx = getSecondPlaceIdx(skills);
            solution.append(secondPlaceIdx);
        }
      	PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
		writer.print(solution.toString());
        writer.close();
    }

    static int getSecondPlaceIdx(int[] arr) {
        int mid = arr.length>>1;
        // for first half
        int max1 = Integer.MIN_VALUE;
        int max1Idx = -1;
        for (int i = 0; i < mid; i += 1) {
            if (arr[i] > max1) {
                max1 = arr[i];
                max1Idx = i+1; // Converting to 1-indexing
            }
        }
        // for second half
        int max2 = Integer.MIN_VALUE;
        int max2Idx = -1;
        for (int i = mid; i < arr.length; i += 1) {
            if (arr[i] > max2) {
                max2 = arr[i];
                max2Idx = i+1; // Converting to 1-indexing
            }
        }
        return max1 < max2 ? max1Idx : max2Idx;
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