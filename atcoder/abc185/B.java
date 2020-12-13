import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int capacity = FR.nextInt();
            int cafeVisits = FR.nextInt();
            int returnTime = FR.nextInt();
            int[][] visitTimes = new int[cafeVisits][2];
            for (int v = 0; v < cafeVisits; ++v) {
                visitTimes[v][0] = FR.nextInt();
                visitTimes[v][1] = FR.nextInt();
            }
            boolean safeReturn = checkReturnSafety(capacity, visitTimes, returnTime);
            solution.append(safeReturn ? "Yes" : "No");
        }
        System.out.println(solution.toString());
    }

    // Checks and returns whether or not Takahashi can return home without his battery ever discharging
    static boolean checkReturnSafety(int capacity, int[][] visitTimes, int returnTime) {
        int charge = capacity;
        int lastExit = 0;
        for (int[] entryAndExit : visitTimes) {
            int entry = entryAndExit[0];
            int exit = entryAndExit[1];
            int discharge = entry - lastExit;
            charge = Math.max(0, charge - discharge);
            if (charge == 0)
                return false;
            int recharge = exit - entry;
            charge = Math.min(capacity, charge + recharge);
            lastExit = exit;
        }
        int discharge = returnTime - lastExit;
        charge = Math.max(0, charge - discharge);
        return charge != 0;
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