import java.io.*;
import java.util.*;
 
public class C {
    static final FastReader FR = new FastReader();
 
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = FR.nextInt();
            int m = FR.nextInt();
            Integer[] friendIdx = new Integer[n];
            for (int i = 0; i < n; i += 1) {
                friendIdx[i] = FR.nextInt()-1;
            }
            int[] presCost = new int[m];
            for (int i = 0; i < m; i += 1) {
                presCost[i] = FR.nextInt();
            }
            long totalPrice = getTotalPrice(presCost, friendIdx);
            solution.append(totalPrice+"\n");
        }
      	PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
		writer.print(solution.toString());
        writer.close();
    }

    static long getTotalPrice(int[] presCost, Integer[] friendIdx) {
        long price = 0;
        Arrays.sort(friendIdx);
        int giftIdx = 0;
        for (int i = friendIdx.length-1; i >= 0; i -= 1) {
            int element = friendIdx[i];
            if (element > giftIdx) price += presCost[giftIdx++];
            else if (element == giftIdx) price += presCost[giftIdx];
            else price += presCost[element];
        }
        return price;        
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