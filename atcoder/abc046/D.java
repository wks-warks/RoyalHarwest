import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            String topCoder = FR.next();
            int atCoderScore = computeAtCoderScore(topCoder);
            solution.append(atCoderScore);
        }
        System.out.println(solution.toString());
    }

    // Computes and returns score of AtCoder under optimal playing conditions
    static int computeAtCoderScore(String topCoder) {
        int acScore = 0;
        int acRocks = 0;
        int acPapers = 0;
        for (var element : topCoder.toCharArray()) {
            if (element == 'g') { //Rock
                if (acRocks > acPapers) {
                    acPapers += 1;
                    acScore += 1;
                }
                else {
                    acRocks += 1;
                }
            } else { // Paper
                if (acRocks > acPapers) {
                    acPapers += 1;
                } else {
                    acRocks += 1;
                    acScore -= 1;
                }
            }
        }
        return acScore;
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