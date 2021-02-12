// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    for (int t = 0; t < tests; ++t) {
      int arrLen = FR.nextInt();
      int queries = FR.nextInt();
      int maxVal = FR.nextInt();
      int[] arr = new int[arrLen + 2];
      arr[arrLen+1] = maxVal + 1;
      for (int i = 1; i <= arrLen; i += 1) {
        arr[i] = FR.nextInt();
      }
      long[] possiblePrefixSum = getPossiblePrefixSum(arr);
      for (int q = 0; q < queries; q += 1) {
        int left = FR.nextInt();
        int right = FR.nextInt();
        long possibleArrays = getAnswer(possiblePrefixSum, arr, left, right, maxVal);
        solution.append(possibleArrays + "\n");
      }
    }
		PW.print(solution.toString());
    PW.close();
  }

  static long getAnswer(long[] possiblePrefixSum, int[] arr, int left, int right, int maxVal) {
    long possibleArrays = possiblePrefixSum[right] - possiblePrefixSum[left-1];
    long addition = arr[left-1] + maxVal - arr[right + 1] + 1;
    return possibleArrays + addition;
  }
  

  static long[] getPossiblePrefixSum(int[] arr) {
    int[] possibles = new int[arr.length];
    for (int i = 1; i < arr.length-1; i += 1) {
      possibles[i] = arr[i+1] - arr[i-1] - 2;
    }
    return getPrefixSum(possibles);
  }

  static long[] getPrefixSum(int[] arr) {
    long[] psum = new long[arr.length];
    for (int i = 1; i < arr.length; i += 1) {
      psum[i] = psum[i-1] + arr[i];
    }
    return psum;
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