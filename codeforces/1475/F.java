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
      int matSize = FR.nextInt();
      char[][] given = new char[matSize][];
      for (int i = 0; i < matSize; i += 1) {
        given[i] = FR.next().toCharArray();
      }
      char[][] target = new char[matSize][];
      for (int i = 0; i < matSize; i += 1) {
        target[i] = FR.next().toCharArray();
      }
      
      boolean canTransform = checkTransformation(given, target);
      solution.append(canTransform ? "YES\n" : "NO\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static boolean checkTransformation(char[][] given, char[][] target) {
    boolean[] swappedCol = new boolean[given.length];
    boolean[] swappedRow = new boolean[given.length];
    int maxSwappedCol = -1;
    for (int i = 0; i < given.length; i += 1) {
      for (int j = 0; j < given.length; j += 1) {
        boolean original = true;
        if (swappedRow[i]) original = !original;
        if (swappedCol[j]) original = !original;
        if (original == (given[i][j] == target[i][j])) continue;
        if (swappedRow[i] && swappedCol[j]) return false;
        if (j == 0) {
          swappedRow[i] = true;
        } else if (i == 0) {
          swappedCol[j] = true;
        } else {
          return false;
        }
      }
    }
    return true;
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