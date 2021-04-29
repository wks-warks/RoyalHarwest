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
      int k = FR.nextInt();

      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = FR.nextInt();
      }

      long movesRequired = getMovesRequired(arr, k);
      solution.append(movesRequired + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static long getMovesRequired(int[] arr, int k) {
    Map<Integer, Integer> modCount = new HashMap<Integer, Integer>();
    for (var num : arr) {
      int mod = num % k;
      if (mod == 0) {
        continue;
      }

      if (!modCount.containsKey(mod)) {
        modCount.put(mod, 1);
      } else {
        modCount.put(mod, modCount.get(mod) + 1);
      }
    }

    int maxFreqDiff = 0;
    int maxFreq = 0;
    for (var entry : modCount.entrySet()) {
      // System.out.println(entry.getKey() + " " + entry.getValue() + " " + (k - entry.getKey()));
      int diff = k - entry.getKey();
      int freq = entry.getValue();

      if (freq > maxFreq) {
        maxFreq = freq;
        maxFreqDiff = diff;
      } else if (freq == maxFreq) {
        maxFreqDiff = Math.max(maxFreqDiff, diff);
      }
    }

    if (maxFreq == 0) {
      return 0;
    }
    return (long) (maxFreq - 1) * k + maxFreqDiff + 1;
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