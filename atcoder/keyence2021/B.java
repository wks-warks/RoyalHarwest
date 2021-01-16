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
      int ballCount = FR.nextInt();
      int boxCount = FR.nextInt();
      Map<Integer, Integer> valCount = new TreeMap<Integer, Integer>();
      for (int i = 0; i < ballCount; i += 1) {
        valCount.put(i, 0);
      }
      for (int i = 0; i < ballCount; i += 1) {
        int val = FR.nextInt();
        valCount.put(val, valCount.get(val)+1);
      }
      long maxSum = getMaxSum(valCount, boxCount);
      solution.append(maxSum+"\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static long getMaxSum(Map<Integer, Integer> valCount, int boxCount) {
    int remainingBoxes = boxCount;
    long sum = 0;
    for (var element : valCount.entrySet()) {
      int key = element.getKey();
      int val = element.getValue();
      if (val < remainingBoxes) {
        sum += (long)key * (remainingBoxes-val);
        remainingBoxes = val;
      }
    }
    if (remainingBoxes > 0) sum += (long) boxCount * remainingBoxes;
    return sum;
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