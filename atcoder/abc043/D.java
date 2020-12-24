import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            String inp = FR.next();
            int[] answer = getAnswer(inp);
            solution.append(answer[0] + " " + answer[1]);
        }
        System.out.println(solution.toString());
    }

    // Computes and returns answer for given question
    static int[] getAnswer(String inp) {
        int[] answer = new int[] {-1, -1};
        for (int i = 1; i < inp.length(); ++i) {
            if (i > 1 && inp.charAt(i-2) == inp.charAt(i)) {
                answer[0] = i-1;
                answer[1] = i+1;
                break;
            }
            else if (inp.charAt(i-1) == inp.charAt(i)) {
                answer[0] = i;
                answer[1] = i+1;
                break;
            }
        }
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