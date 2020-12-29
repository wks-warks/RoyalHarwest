import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int width = FR.nextInt();
            int height = FR.nextInt();
            int points = FR.nextInt();
            int xLeft = 0;
            int xRight = width;
            int yDown = 0;
            int yUp = height;
            for (int i = 0; i < points; i += 1) {
                int x = FR.nextInt();
                int y = FR.nextInt();
                int a = FR.nextInt();
                switch(a) {
                    case 1: xLeft = Math.max(x, xLeft);
                        break;
                    case 2: xRight = Math.min(x, xRight);
                        break;
                    case 3: yDown = Math.max(y, yDown);
                        break;
                    case 4: yUp = Math.min(y, yUp);
                }
            }
            int whiteArea = getWhiteArea(xLeft, xRight, yDown, yUp);
            solution.append(whiteArea);
        }
        System.out.println(solution.toString());
    }

    // Computes and returns area of white region
    static int getWhiteArea(int left, int right, int down, int up) {
        if (left >= right || down >= up)
            return 0;
        else
            return (right-left) * (up-down);
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