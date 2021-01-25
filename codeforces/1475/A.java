import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
 
  static Set<Long> negations = new HashSet<Long>();
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    setNegations();
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      long num = FR.nextLong();
      solution.append(negations.contains(num) ? "NO\n" : "YES\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static void setNegations() {
    long negate = 1;
    for (int i = 0; i < 50; i += 1) {
      negate <<= 1;
      negations.add(negate);
    }
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