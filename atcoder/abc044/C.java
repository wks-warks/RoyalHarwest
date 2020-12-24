import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int cards = FR.nextInt();
            int average = FR.nextInt();
            int[] deviations = new int[cards];
            for (int i = 0; i < cards; i += 1) {
                deviations[i] = FR.nextInt() - average;
            }
            long ways = countWays(deviations);
            solution.append(ways);
        }
        System.out.println(solution.toString());
    }

    // Computes and returns number of ways to get zero deviation sum
    static long countWays(int[] deviations) {
        Map<Integer, Long> ways = new HashMap<>();
        ways.put(0, 1L);
        for (var element : deviations) {
            Map<Integer, Long> temp = new HashMap<>();
            for (var sums : ways.entrySet()) {
                int newSum = sums.getKey() + element;
                if (temp.containsKey(newSum)) {
                    temp.put(newSum, temp.get(newSum)*2L);
                }
                else {
                    temp.put(newSum, sums.getValue());
                }
            }
            for (var sums : temp.entrySet()) {
                int key = sums.getKey();
                long value = sums.getValue();
                if (ways.containsKey(key)) {
                    ways.put(key, ways.get(key) + value);
                }
                else {
                    ways.put(key, value);
                }
            }
        }
        return ways.get(0) - 1; // -1 as we must pick at least 1 card and 0 cards adds to total 0 as well
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