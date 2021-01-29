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
      int studentCount = FR.nextInt();
      int[] rowOne = new int[studentCount];
      for (int i = 0; i < studentCount; i += 1) {
        rowOne[i] = FR.nextInt();
      }
      int[] rowTwo = new int[studentCount];
      for (int i = 0; i < studentCount; i += 1) {
        rowTwo[i] = FR.nextInt();
      }
      long maxHtSum = getMaxHtSum(rowOne, rowTwo);
      solution.append(maxHtSum + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static long getMaxHtSum(int[] rowOne, int[] rowTwo) {
    long lastFromOne, lastFromTwo;
    long newFromOne, newFromTwo;
    lastFromOne = lastFromTwo = 0;
    for (int i = 0; i < rowOne.length; i += 1) {
      newFromOne = Math.max(lastFromOne, lastFromTwo+rowOne[i]);
      newFromTwo = Math.max(lastFromTwo, lastFromOne+rowTwo[i]);
      lastFromOne = newFromOne;
      lastFromTwo = newFromTwo;
    }
    return Math.max(lastFromOne, lastFromTwo);    
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