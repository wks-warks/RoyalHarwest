// Author : RegalBeast
 
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
      int listSize = FR.nextInt();
 
      List<Integer> list = new ArrayList<Integer>(listSize);
      for (int i = 0; i < listSize; i++) {
        list.add(FR.nextInt());
      }
 
      boolean equalizable = isEqualizable(list);
      solution.append(equalizable ? "YES\n" : "NO\n");
    }
		PW.print(solution.toString());
    PW.close();
  }
 
  static boolean isEqualizable(List<Integer> list) {
    List<Integer> xorList = new ArrayList<Integer>(list.size());
    xorList.add(list.get(0));
 
    for (int i = 1; i < list.size(); i++) {
      xorList.add(list.get(i) ^ xorList.get(i-1));
    }
 
    int equatedXor = xorList.get(xorList.size() - 1);
    int found = 0;
    for (int i = 0; i < xorList.size(); i++) {
      int effectiveXOR = xorList.get(i) ^ ((found & 1) == 1 ? equatedXor : 0);
      if (effectiveXOR == equatedXor) {
        found += 1;
      }
    }
 
    return equatedXor == 0 || found > 1;
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