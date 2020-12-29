import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int towns = FR.nextInt();
            int applesExchanged = FR.nextInt(); // Redundant
            int[] costInTown = new int[towns];
            for (int i = 0; i < towns; i += 1) {
                costInTown[i] = FR.nextInt();
            }
            int costToDecreaseProfit = computeCost(costInTown);
            solution.append(costToDecreaseProfit);
        }
        System.out.println(solution.toString());
    }

    // Returns answer to our problem
    static int computeCost(int[] costInTown) {
        int[] minCosts = new int[costInTown.length];
        minCosts[0] = costInTown[0];
        for (int i = 1; i < minCosts.length; ++i)
            minCosts[i] = Math.min(minCosts[i-1], costInTown[i]);
        
        int profit = 0;
        int profitCount = 0;
        for (int i = 0; i < costInTown.length; i += 1) {
            int townProfit = costInTown[i] - minCosts[i];
            if (townProfit == profit)
                profitCount += 1;
            else if (townProfit > profit) {
                profit = townProfit;
                profitCount = 1;
            }
        }
        return profitCount;
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