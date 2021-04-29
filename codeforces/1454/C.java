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
      int n = FR.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = FR.nextInt();
      }
      
      int minOperations = getMinOperations(arr);
      solution.append(minOperations + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getMinOperations(int[] arr) {
    Map<Integer, Integer> prevOccurrence = new HashMap<Integer, Integer>();
    Map<Integer, Integer> movesRequired = new HashMap<Integer, Integer>();

    for (int i = 0; i < arr.length; i++) {
      if (!prevOccurrence.containsKey(arr[i])) {
        prevOccurrence.put(arr[i], -1);
        movesRequired.put(arr[i], 1);
      }
    }
    movesRequired.put(arr[arr.length-1], movesRequired.get(arr[arr.length-1]) - 1); // As this won't need the additional move to remove elements to the right

    for (int i = 0; i < arr.length; i++) {
      if (prevOccurrence.get(arr[i]) != i-1) {
        movesRequired.put(arr[i], movesRequired.get(arr[i]) + 1);
      }
      prevOccurrence.put(arr[i], i);
    }

    int answer = Integer.MAX_VALUE;
    for (var entry : movesRequired.entrySet()) {
      answer = Math.min(answer, entry.getValue());
    }
    return answer;
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