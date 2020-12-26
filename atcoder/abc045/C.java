import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            String num = FR.next();
            pairMap = new HashMap<Integer, Pair>();
            pairMap.put(num.length(), new Pair(0L, 1L));
            long totalSum = getPair(num, 0).sum;
            solution.append(totalSum);
        }
        System.out.println(solution.toString());
    }

    static Map<Integer, Pair> pairMap;

    // Computes and returns total sum
    static Pair getPair(String num, int start) {
        if (pairMap.containsKey(start))
            return pairMap.get(start);

        Pair answer = new Pair();
        Pair nextSum = new Pair();

        for(int i = start; i < num.length(); ++i) {
            nextSum = getPair(num, i+1);
            answer.ways += nextSum.ways;
            answer.sum += nextSum.sum + nextSum.ways*Long.parseLong(num.substring(start, i+1));
        }
        pairMap.put(start, answer);
        return answer;
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

class Pair {
    public Pair() {
        this.sum = 0L;
        this.ways = 0L;
    }
    public Pair(long sum, long ways) {
        this.sum = sum;
        this.ways = ways;
    }
    long sum;
    long ways;
}