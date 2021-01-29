import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
 
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int chainCount = FR.nextInt();
      int[] chainVertices = new int[chainCount];
      for (int i = 0; i < chainCount; i += 1) {
        chainVertices[i] = FR.nextInt();
      }
      
      int[] topToPrev = new int[chainCount];
      for (int i = 0; i < chainCount; i += 1) {
        topToPrev[i] = FR.nextInt();
      }
      
      int[] bottomToPrev = new int[chainCount];
      for (int i = 0; i < chainCount; i += 1) {
        bottomToPrev[i] = FR.nextInt();
      }

      long maxCycleSize = getMaxCycleSize(chainCount, chainVertices, topToPrev, bottomToPrev);
      solution.append(maxCycleSize + "\n");      
    }
		PW.print(solution.toString());
    PW.close();
  }

  static long getMaxCycleSize(int chainCount, int[] chainVertices, int[] topToPrev, int[] bottomToPrev) {
    long[] cycleBefore = new long[chainCount];
    int upperBack = Math.min(topToPrev[1], bottomToPrev[1]);
    int lowerBack = Math.max(topToPrev[1], bottomToPrev[1]);
    cycleBefore[1] = lowerBack - upperBack + 1;
    
    // System.out.println(cycleBefore[1] + "  i  " + 1);
    long maxCycleSize = cycleBefore[1] + chainVertices[1];
    for (int i = 2; i < chainCount; i += 1) {
      upperBack = Math.min(topToPrev[i], bottomToPrev[i]);
      lowerBack = Math.max(topToPrev[i], bottomToPrev[i]);
      if (upperBack == lowerBack) {
        cycleBefore[i] = 1;
      } else {
        cycleBefore[i] = Math.max(cycleBefore[i-1] + upperBack + chainVertices[i-1] - lowerBack + 1, lowerBack - upperBack + 1);
      }
      maxCycleSize = Math.max(maxCycleSize, cycleBefore[i] + chainVertices[i]);
      // System.out.println(cycleBefore[i] + "  i  " + i);
    }

    return maxCycleSize;
  }
 
  static class FastReader {
    BufferedReader br;
    StringTokenizer st;
  
    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
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