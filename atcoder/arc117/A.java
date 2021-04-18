// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();

    int positiveIntegers = FR.nextInt();
    int negativeIntegers = FR.nextInt();

    List<Integer> godSequence = getGodSequnce(positiveIntegers, negativeIntegers);
    
    for (var num : godSequence) {
      solution.append(num + " ");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static List<Integer> getGodSequnce(int positiveIntegers, int negativeIntegers) {
    List<Integer> godSequence = new ArrayList<Integer>(positiveIntegers + negativeIntegers);

    int larger = Math.max(positiveIntegers, negativeIntegers);
    int smaller = Math.min(positiveIntegers, negativeIntegers);
    int multiplier = positiveIntegers <= negativeIntegers ? -1 : 1;
  
    for (int i = 0; i < larger; i++) {
      godSequence.add((1001+i) * multiplier);
    }

    for (int i = 1; i < smaller; i++) {
      godSequence.add((-i) * multiplier);
    }

    int remaining = -multiplier * (sum(1000+larger) - sum(1000) - sum(smaller-1));
    godSequence.add(remaining);
    return godSequence;
  }

  static int sum(int num) {
    return num * (num + 1) >> 1;
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