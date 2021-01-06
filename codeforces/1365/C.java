//Codeforces 1365C 
import java.util.*;
import java.io.*;

public class CF1365C {
    static final FastReader FR = new FastReader();
    public static void main(String[] args) {
        int n = FR.nextInt();
        int[] perm1 = new int[n];
        for (int i = 0; i < n; i += 1) {
            perm1[i] = FR.nextInt();
        }
        int[] perm2 = new int[n];
        for (int i = 0; i < n; i += 1) {
            perm2[i] = FR.nextInt();
        }
        int maxCommon = getMaxCommon(perm1, perm2);

        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
		writer.println(maxCommon);
        writer.close();
    }

    static int getMaxCommon(int[] perm1, int[] perm2) {
        int n = perm1.length;
        Map<Integer, Integer> idxVal1 = new HashMap<>();
        for (int i = 0; i < n; i += 1) {
            idxVal1.put(perm1[i], i+1);
        }
        int maxCommon = 1;
        Map<Integer, Integer> commonVals = new HashMap<>();
        for (int i = 0; i < n; i += 1) {
            int val = perm2[i];
            int idx2 = i+1;
            int idx1 = idxVal1.get(val);
            int leftShift = idx1 - idx2;
            if (leftShift < 0) leftShift += n;
            if (commonVals.containsKey(leftShift)) {
                int pres = commonVals.get(leftShift);
                int newAns = pres+1;
                commonVals.put(leftShift, newAns);
                maxCommon = Math.max(maxCommon, newAns);
            } else {
                commonVals.put(leftShift, 1);
            }
        }

        return maxCommon;
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
