import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
 
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    // tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int numLimit = FR.nextInt();
      int seqSize = FR.nextInt();
      int goodCount = getGoodCount(numLimit, seqSize);
      solution.append(goodCount + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getGoodCount(int numLimit, int seqSize) {
    int[][] counts = new int[seqSize][numLimit];

    for (int i = 0; i < numLimit; i += 1) {
      counts[0][i] = 1;
    }

    for (int i = 0; i < seqSize-1; i += 1) {
      for (int j = 0; j < numLimit; j += 1) {
        int jump = j+1;
        for (int k = j; k < numLimit; k += jump) {
          counts[i+1][k] += counts[i][j];
          counts[i+1][k] %= 1_000_000_007;          
        }
      }
    }

    int totalCount = 0;
    for (var count : counts[seqSize-1]) {
      totalCount += count;
      totalCount %= 1_000_000_007;
    }
    return totalCount;
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