// Author : RegalBeast

import java.util.stream.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    for (int t = 0; t < tests; ++t) {
      int n = FR.nextInt();
      int k = FR.nextInt();
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = FR.nextInt();
      }

      int unnecessary = countUnnecessary(n, k, a);
      solution.append(unnecessary);
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int countUnnecessary(int n, int k, int[] a) {
    Sorter.sort(a);

    int unnecessary = 0;
    int left = 0;
    int right = n-1;
    while (left <= right) {
      int mid = (left + right) >> 1;
      if (isUnnecessary(mid, n, k, a)) {
        unnecessary = mid + 1;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return unnecessary;
  }

  static boolean isUnnecessary(int pos, int n, int k, int[] a) {
    BitSet bs = new BitSet(k);
    bs.set(0);

    for (int i = 0; i < n; i++) {
      if (i != pos) {
        for (int j = k-a[i]-1; j >= 0; j--) {
          if (bs.get(j)) {
            bs.set(j+a[i]);
            if (k - a[pos] <= j+a[i]) {
              return false;
            }
          }
        }
      }
    }
    return a[pos] < k-1;
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

class Sorter {
  static void sort(int[] arr) {
    ThreadLocalRandom rand = ThreadLocalRandom.current();
 
    for (int i = 0; i < arr.length-1; i++) {
      int newPos = rand.nextInt(i, arr.length-1);
      int temp = arr[i];
      arr[i] = arr[newPos];
      arr[newPos] = temp;
    }
 
    Arrays.sort(arr);
  }

  static void reverseSort(int[] arr) {
    sort(arr);
    int mid = arr.length >> 1;

    for (int left = 0, right = arr.length-1; left < mid; left++, right--) {
      int temp = arr[left];
      arr[left] = arr[right];
      arr[right] = temp;
    }
  }
}