import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int bill = FR.nextInt();
            int dislikeCount = FR.nextInt();
            boolean[] dislikes = new boolean[10];
            for (int i = 0; i < dislikeCount; i += 1) {
                int num = FR.nextInt();
                dislikes[num] = true;
            }
            int answer = getAns(bill, dislikes);
            solution.append(answer);
        }
        System.out.println(solution.toString());
    }

    // Computes and returns the answer
    static int getAns(int pay, boolean[] dislikes) {
        boolean payThis = true;
        int paidAmount = pay;
        while(paidAmount > 0) {
            int digit = paidAmount % 10;
            paidAmount /= 10;
            payThis &= !dislikes[digit];
        }
        if (payThis)
            return pay;
        else
            return getAns(pay+1, dislikes);
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