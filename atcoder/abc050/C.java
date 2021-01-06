import java.io.*;
import java.util.*;
 
class Main {
    static final FastReader FR = new FastReader();
 
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int persons = FR.nextInt();
            int[] claims = new int[persons];
            Map<Integer, Integer> claimCount = new HashMap<Integer, Integer>();
            for (int i = 0; i < persons; i += 1) {
                claims[i] = FR.nextInt();
                if (claimCount.containsKey(claims[i])) claimCount.put(claims[i], claimCount.get(claims[i])+1);
                else claimCount.put(claims[i], 1);
            }
            boolean isConsistent = true;
            for (var element : claimCount.entrySet()) {
                int key = element.getKey();
                int value = element.getValue();
                if (key == 0) {
                    isConsistent &= (persons&1) == 1;
                } else {
                    if ((persons&1) == 0) isConsistent &= (key&1) == 1;
                    else isConsistent &= (key&1) == 0;
                    isConsistent &= value == 2;
                }
            }
            if (isConsistent) {
                long ans = getTwoPowMod(persons/2, 1_000_000_007);
                solution.append(ans + "\n");
            } else {
                solution.append(0 + "\n");
            }
        }
      	PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
		writer.print(solution.toString());
        writer.close();
    }

    static long getTwoPowMod(long pow, long mod) {
        long digit = 1;
        long mul = 2;
        long ans = 1;
        while (digit <= pow) {
            if ((digit & pow) > 0) {
                ans *= mul;
                ans %= mod;
            }
            mul *= mul;
            mul %= mod;
            digit <<= 1;
        }
        return ans;
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