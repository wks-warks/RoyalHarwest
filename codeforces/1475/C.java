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
      int boyCount = FR.nextInt();
      int girlCount = FR.nextInt();
      int pairsPossible = FR.nextInt();
      int[] boyOccurrences = new int[boyCount];
      int[] girlOccurrences = new int[girlCount];
      int[][] pairs = new int[pairsPossible][2];
      for (int i = 0; i < pairsPossible; i += 1) {
        pairs[i][0] = FR.nextInt()-1;
        boyOccurrences[pairs[i][0]]++;
      }
      for (int i = 0; i < pairsPossible; i += 1) {
        pairs[i][1] = FR.nextInt()-1;
        girlOccurrences[pairs[i][1]]++;
      }
      long waysToPair = getWays(pairsPossible, boyOccurrences, girlOccurrences, pairs);
      solution.append(waysToPair+"\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static long getWays(int pairsPossible, int[] boyOccurrences, int[] girlOccurrences, int[][] pairs) {
    long answer = 0;
    for (var element : pairs) {
      int boy = element[0];
      int girl = element[1];
      int otherGirl = pairsPossible - girlOccurrences[girl];
      int withOtherBoy = otherGirl - boyOccurrences[boy] + 1;
      answer += withOtherBoy;
    }
    return answer>>1;
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