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
      int changeCount = FR.nextInt();
      int maxInflation = FR.nextInt();
      int[] changes = new int[changeCount];
      for (int i = 0; i < changeCount; i += 1) {
        changes[i] = FR.nextInt();
      }
      long cheatingRequired = getCheatingRequired(changes, maxInflation);
      solution.append(cheatingRequired + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static long getCheatingRequired(int[] changes, int maxInflation) {
    long sumThusFar = changes[0];
    long cheatingRequired = 0;
    for (int i = 1; i < changes.length; ++i) {
      long inflation = LIG((100L * changes[i]), sumThusFar);
      if (inflation > maxInflation) {
        long cheating = LIG(100L*changes[i], maxInflation) - sumThusFar;
        cheatingRequired += cheating;
        sumThusFar += cheating; 
      }
      sumThusFar += changes[i];
    }
    return cheatingRequired;
  }

  static long LIG(long numerator, long denominator) {
    return (numerator+denominator-1) / denominator;
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