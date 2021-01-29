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
      int modelCount = FR.nextInt();
      int[] modelSizes = new int[modelCount];
      for (int i = 0; i < modelCount; i += 1) {
        modelSizes[i] = FR.nextInt();
      }
      int maxBeautiful = getMaxBeautiful(modelSizes);
      solution.append(maxBeautiful + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getMaxBeautiful(int[] modelSizes) {
    int[] beautifulCount = new int[modelSizes.length];
    for (int i = 0; i < beautifulCount.length; i += 1) {
      beautifulCount[i] = 1;
    }
    
    for (int i = 0; i < modelSizes.length; i += 1) {
      int jump = i+1;
      for (int j = i+jump; j < beautifulCount.length; j += jump) {
        if (modelSizes[j] > modelSizes[i]) {
          beautifulCount[j] = Math.max(beautifulCount[i]+1, beautifulCount[j]);
        }
      }
    }
    
    int maxBeautiful = 0;
    for (var count : beautifulCount) {
      maxBeautiful = Math.max(count, maxBeautiful);
    }
    return maxBeautiful;
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