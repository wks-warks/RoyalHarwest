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
      int queryCount = FR.nextInt();
      int luckyDigit = FR.nextInt();
      achievable = new HashMap<Integer, Boolean>();
      for (int i = 0; i < queryCount; i += 1) {
        int number = FR.nextInt();
        solution.append(canGet(number, luckyDigit) ? "YES\n" : "NO\n");
      }
    }
		PW.print(solution.toString());
    PW.close();
  }

  static Map<Integer, Boolean> achievable;
  static boolean canGet(int number, int luckyDigit) {
    if (number >= 10*luckyDigit) return true;
    if (number < luckyDigit) return false;
    if (achievable.containsKey(number)) return achievable.get(number);
    if (number % 10 == luckyDigit) return true;
    boolean achievability = canGet(number-luckyDigit, luckyDigit) || canGet(number-10, luckyDigit);
    achievable.put(number, achievability);
    return achievability;
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