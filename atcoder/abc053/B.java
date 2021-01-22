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
      String input = FR.next();
      int maxAZ = getLenAZ(input);
      solution.append(maxAZ + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getLenAZ(String input) {
    int idxA = -1;
    int idxZ = -1;
    for (int i = 0; i < input.length(); i += 1) {
      if (input.charAt(i) == 'A') {
        if (idxA == -1) idxA = i;
      } else if (input.charAt(i) == 'Z') {
        idxZ = i;
      }
    }
    return idxZ - idxA + 1;
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