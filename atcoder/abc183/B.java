import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int[] initPos = new int[2];
            initPos[0] = FR.nextInt();
            initPos[1] = FR.nextInt();

            int[] finalPos = new int[2];
            finalPos[0] = FR.nextInt();
            finalPos[1] = FR.nextInt();

            double collisionPoint = computeCollisionPoint(initPos, finalPos);
            solution.append(collisionPoint);
        }
        System.out.println(solution.toString());
    }

    // Computes and returns collision point with wall
    static double computeCollisionPoint(int[] initPos, int[] finalPos) {
        if (initPos[0] > finalPos[0]) {
            int[] temp = initPos.clone();
            initPos = finalPos.clone();
            finalPos = temp.clone();
        }
        int xDiff = finalPos[0] - initPos[0];
        double collisionPoint = initPos[0] + (double) initPos[1] * xDiff / (initPos[1] + finalPos[1]);
        return collisionPoint;
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