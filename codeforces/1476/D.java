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
      int cityCount = FR.nextInt()+1;
      char[] roadDirections = FR.next().toCharArray();
      int[] visitableCount = getVisitableCount(cityCount, roadDirections);
      for (var element : visitableCount) {
        solution.append(element + " ");
      }
      solution.append("\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int[] getVisitableCount(int cityCount, char[] roadDirections) {
    int[] leftVisitable = new int[cityCount];
    int[] flippedLeftVisitable = new int[cityCount];
    int[] rightVisitable = new int[cityCount];
    int[] flippedRightVisitable = new int[cityCount];
    for (int i = 1; i < cityCount; i += 1) {
      if (roadDirections[i-1] == 'L') {
        leftVisitable[i] = flippedLeftVisitable[i-1] + 1;
        flippedLeftVisitable[i] = 0;
      } else {
        flippedLeftVisitable[i] = leftVisitable[i-1] + 1;
        leftVisitable[i] = 0;
      }
    }

    for (int i = cityCount-2; i >= 0; i -= 1) {
      if (roadDirections[i] == 'L') {
        flippedRightVisitable[i] = rightVisitable[i+1] + 1;
        rightVisitable[i] = 0;
      } else {
        rightVisitable[i] = flippedRightVisitable[i+1] + 1;
        flippedRightVisitable[i] = 0;
      }
    }

    int[] visitableCount = new int[cityCount];
    for (int i = 0; i < cityCount; i += 1) {
      visitableCount[i] = leftVisitable[i] + rightVisitable[i] + 1;
    }
    return visitableCount;
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