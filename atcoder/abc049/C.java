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
            String[] comp = new String[] {"dream", "dreamer", "erase", "eraser"}; // Components
            boolean madeOfComp = checkIfMadeOfComp(inp, comp);
            solution.append(madeOfComp? "YES" : "NO");
        }
        System.out.println(solution.toString());
    }

    static boolean checkIfMadeOfComp(String inp, String[] comp) {
        int inpLen = inp.length();
        int compTill = inpLen;
        while (true) {
            boolean foundAtLeastOne = false;
            for (var element : comp) {
                int compLen = element.length();
                if (compTill - compLen < 0)
                    continue;
                if (inp.substring(compTill-compLen, compTill).equals(element)) {
                    compTill -= compLen;
                    foundAtLeastOne = true;
                }
            }
            if (!foundAtLeastOne)
                break;
        }
        return compTill == 0;
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