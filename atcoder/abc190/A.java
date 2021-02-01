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
      int[] candyCount = new int[2];
      for (int i = 0; i < 2; i += 1) candyCount[i] = FR.nextInt();
      int first = FR.nextInt();
      String winner = getWinner(candyCount, first);
      solution.append(winner + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static String getWinner(int[] candyCount, int first) {
    return candyCount[0] == candyCount[1] ? first == 0 ? "Aoki" : "Takahashi" : candyCount[0] > candyCount[1] ? "Takahashi" : "Aoki";
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