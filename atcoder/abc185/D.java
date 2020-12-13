import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int squares = FR.nextInt();
            int blueSquares = FR.nextInt();
            int[] bluePositions = new int[blueSquares];
            for (int b = 0; b < blueSquares; ++b)
                bluePositions[b] = FR.nextInt();
            int stampingCount = blueSquares > 0 ? getMinimumStampingCount(squares, bluePositions) : 1;
            solution.append(stampingCount);
        }
        System.out.println(solution.toString());
    }

    // Computes and returns minimum number of stampings required
    static int getMinimumStampingCount(int squares, int[] bluePositions) {
        int prevBlue = 0;
        Arrays.sort(bluePositions);
        ArrayList<Integer> whiteBlocks = new ArrayList<>();
        for (int pos : bluePositions) {
            int width = pos - prevBlue - 1;
            if (width > 0)
                whiteBlocks.add(width);
            prevBlue = pos;
        }
        int lastWidth = squares - bluePositions[bluePositions.length-1];
        if (lastWidth > 0)
            whiteBlocks.add(lastWidth);
        int stampSize = getStampSize(whiteBlocks);
        int stampingCount = 0;
        for (int block : whiteBlocks) {
            stampingCount += (block+stampSize-1) / stampSize;
        }
        return stampingCount;
    }

    // Computes and returns Stampsize
    static int getStampSize(ArrayList<Integer> whiteBlocks) {
        int minimum = Integer.MAX_VALUE;
        for (int block : whiteBlocks)
            minimum = Math.min(block, minimum);
        return minimum;
    }

    // Returns GCD of two numbers
    static int GCD(int num1, int num2) {
        if (num1 == 0)
            return num2;
        return GCD(num2 % num1, num1);
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