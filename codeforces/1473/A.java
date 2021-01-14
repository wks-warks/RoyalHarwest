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
      int n = FR.nextInt();
      int d = FR.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i += 1) {
        arr[i] = FR.nextInt();
      }
      solution.append(resultAttainable(arr, d) ? "Yes\n" : "No\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static boolean resultAttainable(int[] arr, int d) {
    int min1 = Integer.MAX_VALUE;
    int min2 = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i += 1) {
      max = Math.max(max, arr[i]);
      if (arr[i] < min1) {
        min2 = min1;
        min1 = arr[i];
      } else if (arr[i] < min2) {
        min2 = arr[i];
      }
    }
    return max <= d || (min1+min2) <= d;
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