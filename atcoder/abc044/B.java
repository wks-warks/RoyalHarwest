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
            boolean isBeautiful = checkIfBeautiful(inp);
            solution.append(isBeautiful ? "Yes" : "No");
        }
        System.out.println(solution.toString());
    }

    // Checks and returns whether or not the given string is beautiful
    static boolean checkIfBeautiful(String inp) {
        Map<Character, Integer> charCount = new TreeMap<>();
        for (var element : inp.toCharArray()) {
            if (charCount.containsKey(element)) {
                charCount.put(element, charCount.get(element) + 1);
            }
            else {
                charCount.put(element, 1);
            }
        }
        
        for (var element : charCount.entrySet()) {
            if (element.getValue() % 2 != 0)
                return false;
        }
        return true;
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