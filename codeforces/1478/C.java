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
      int arrLen = n<<1;
      long[] differences = new long[arrLen];
      for (int i = 0; i < arrLen; i += 1) {
        differences[i] = FR.nextLong();
      }
      solution.append(symmetryAttainable(differences) ? "YES\n" : "NO\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static boolean symmetryAttainable(long[] differences) {
    Map<Long, Integer> freq = new TreeMap<Long, Integer>(Collections.reverseOrder());
    for (var element : differences) {
      if ((element&1) == 1L || element == 0L) return false;
      int value = freq.containsKey(element) ? freq.get(element)+1 : 1;
      if (value > 2) return false;
      freq.put(element, value);
    }
    if ((freq.size()<<1) != differences.length) return false;

    int behindThis = 0;
    long num, prevKey, prevNum;
    num = prevNum = 0;
    prevKey = 0;
    for (var key : freq.keySet()) {
      int aheadOfThis = differences.length-behindThis-1;
      int modulo = aheadOfThis-behindThis+1;
      // debug(behindThis, modulo, aheadOfThis);
      if ((prevKey-key) % modulo != 0L) return false;
      num = prevNum - (prevKey-key) / modulo;
      if (num <= 0) return false;
      prevNum = num;
      prevKey = key;
      behindThis += 1;
    }
    return true;
  }

  static void debug(int behindThis, int modulo, int aheadOfThis) {
      System.out.println("Behind this: " + behindThis);
      System.out.println("Modulo: " + modulo);
      System.out.println("Ahead of this: " + aheadOfThis);
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

/*
6
240 240
210 210
186 186
174 174
162 162
154 154
*/