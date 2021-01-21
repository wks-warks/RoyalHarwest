import java.io.*;
import java.util.*;

public class E {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int friends = FR.nextInt();
            int[][] original = new int[friends][2];
            Integer[][] heightFirst = new Integer[friends][3];
            Integer[][] widthFirst = new Integer[friends][3];
            for (int i = 0; i < friends; i += 1) {
                original[i][0] = FR.nextInt();
                original[i][1] = FR.nextInt();
                heightFirst[i][0] = original[i][0];
                heightFirst[i][1] = original[i][1];
                widthFirst[i][0] = heightFirst[i][1];
                widthFirst[i][1] = heightFirst[i][0];
                heightFirst[i][2] = widthFirst[i][2] = i+1;
            }
            Arrays.sort(heightFirst, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            Arrays.sort(widthFirst, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            int[][] heightFirstWidthMin = getMinArray(heightFirst);
            int[][] widthFirstHeightMin = getMinArray(widthFirst);
            for (int i = 0; i < friends; i += 1) {
                int friendIdx = getFriendIdx(original[i][0], original[i][1], heightFirst, widthFirst, heightFirstWidthMin, widthFirstHeightMin);
                solution.append(friendIdx + " ");
            }
            solution.append("\n");
        }
        System.out.print(solution.toString());
    }

    static int getFriendIdx(int height, int width, Integer[][] heightFirst, Integer[][] widthFirst, int[][] heightFirstWidthMin, int[][] widthFirstHeightMin) {
        int friendIdx = -1;
        int hIdx = Integer.MAX_VALUE;
        int left = 0;
        int right = heightFirst.length-1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (heightFirst[mid][0] < height) {
                hIdx = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        if (hIdx != Integer.MAX_VALUE && heightFirstWidthMin[hIdx][0] < width) friendIdx = heightFirstWidthMin[hIdx][1];
        if (friendIdx == -1) {
            int wIdx = Integer.MAX_VALUE;
            left = 0;
            right = widthFirst.length-1;
            while (left <= right) {
                int mid = (left+right) >> 1;
                if (widthFirst[mid][0] < height) {
                    wIdx = mid;
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            }
            if (wIdx != Integer.MAX_VALUE && widthFirstHeightMin[wIdx][0] < width) friendIdx = widthFirstHeightMin[wIdx][1];
        }
        return friendIdx;
    }

    static int[][] getMinArray(Integer[][] twoDArray) {
        int[][] minArray = new int[twoDArray.length][2];
        minArray[0][0] = twoDArray[0][1];
        minArray[0][1] = twoDArray[0][2];
        for (int i = 1; i < minArray.length; i += 1) {
            if (twoDArray[i][1] < minArray[i-1][0]) {
                minArray[i][0] = twoDArray[i][1];
                minArray[i][1] = twoDArray[i][2];
            } else {
                minArray[i][0] = minArray[i-1][0];
                minArray[i][1] = minArray[i-1][1];
            }
        }
        return minArray;
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