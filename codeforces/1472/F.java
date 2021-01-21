import java.io.*;
import java.util.*;

public class F {
    static final FastReader FR = new FastReader();

    static Map<Integer, Integer> strip;
    static int stripSize;
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            stripSize = FR.nextInt();
            strip = new TreeMap<Integer, Integer>();
            strip.put(stripSize+1, 3);
            int m = FR.nextInt();
            for (int i = 0; i < m; i += 1) {
                int row = FR.nextInt();
                int col = FR.nextInt();
                if (strip.containsKey(col)) {
                    strip.put(col, 3);
                } else {
                    strip.put(col, row);
                }
            }
            boolean canGet = checkPossibility();
            solution.append(canGet ? "Yes\n" : "No\n");
        }
        System.out.print(solution.toString());
    }

    static boolean checkPossibility() {
/*        boolean prevUp, prevDown, prevBoth, nextUp, nextDown, nextBoth;
        prevUp = prevDown = nextBoth = nextDown = nextUp = false;
        nextBoth = true;
        for (int i = 0; i < strip.length; i += 1) {
            prevBoth = nextBoth;
            prevUp = nextUp;
            prevDown = nextDown;
            // System.out.println(i + " " + prevBoth + " " + prevDown + " " + prevUp);

            nextBoth = nextUp = nextDown = false;
            if (strip[i][0] == 0 && strip[i][1] == 0) {
                if (prevUp) nextDown = true;
                if (prevDown) nextUp = true;
                if (prevBoth) nextBoth = true;
            }
            else if (strip[i][0] == 0 && strip[i][1] == 1) {
                if (!prevDown && !prevBoth) return false;
                if (prevDown) nextBoth = true;
                if (prevBoth) nextDown = true;
            }
            else if (strip[i][0] == 1 && strip[i][1] == 0) {
                if (!prevUp && !prevBoth) return false;
                if (prevUp) nextBoth = true;
                if (prevBoth) nextUp = true;
            }
            else {
                if (!prevBoth) return false;
                nextBoth = true;
            }
        }
        return nextBoth;
*/
        int prevCol = 0;
        int prevFilled = 3; // Both
        for (var element : strip.entrySet()) {
            int col = element.getKey();
            int filled = element.getValue();
            int colDiff = col - prevCol;
            prevCol = col;
            if (filled == 3 && prevFilled != 3) return false;
            if (prevFilled == 3) {
                prevFilled = filled;
                continue;
            }
            if ((colDiff & 1) == 0) {
                if (filled != prevFilled) {
                    prevFilled = 3;
                } else {
                    return false;
                }
            } else {
                if (filled == prevFilled) {
                    prevFilled = 3;
                } else {
                    return false;
                }
            }
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