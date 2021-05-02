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
      int arrLen = FR.nextInt();
      
      int[] arr = new int[arrLen];
      for (int i = 0; i < arrLen; i++) {
        arr[i] = FR.nextInt();
      }

      String[] strings = getStrings(arr);
      for (var s : strings) {
        solution.append(s + "\n");
      }
    }
		PW.print(solution.toString());
    PW.close();
  }

  static String[] getStrings(int[] arr) {
    String[] strings = new String[arr.length+1];
    strings[0] = getString(0, "", arr[0]);
    for (int i = 1; i < strings.length-1; i++) {
      strings[i] = getString(arr[i-1], strings[i-1], arr[i]);
    }
    strings[strings.length-1] = getString(arr[arr.length-1], strings[arr.length-1], 0);
    return strings;
  }

  static String getString(int prevLen, String prev, int nextLen) {
    int len = Math.max(prevLen, nextLen);
    if (len == 0) {
      return prev.length() > 0 && prev.charAt(0) == 'a' ? "b" : "a";
    }

    StringBuilder s = new StringBuilder();
    for (int i = 0; i < prevLen; i++) {
      s.append(prev.charAt(i));
    }

    if (prev.length() > prevLen) {
      s.append(prev.charAt(prevLen) == 'a' ? 'b' : 'a');
    }
    for (int i = s.length(); i < nextLen; i++) {
      s.append('a');
    }
    return s.toString();
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