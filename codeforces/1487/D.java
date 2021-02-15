// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    // if (1 == 1) {
    //   int upto = 200;
    //   for (int i = 1; i <= upto; i += 1) {
    //     for (int j = i; j <= upto; j += 1) {
    //       for (int k = j; k <= upto; k += 1) {
    //         if (isNormalTriplet(i, j, k) && isSpecialTriplet(i, j, k)) {
    //           System.out.println(i + " " + j + " " + k);
    //         }
    //       }
    //     }
    //   }
    //   return;
    // }
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int n = FR.nextInt();
      int tripleCount = getTripleCount(n);
      solution.append(tripleCount + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }
  
  static int getTripleCount(int n) {
    int left = 0;
    int right = n / 5;
    int ans = 0;
    while (left <= right) {
      int mid = (left + right) >> 1;
      int term = getTerm(mid);
      if (term <= n) {
        left = mid + 1;
        ans = mid;
      } else {
        right = mid - 1;
      }
    }
    return ans;
  }

  static int getTerm(int idx) {
    if (idx == 0) {
      return 0;
    } else {
      int summation = idx -1;
      int sum = getSum(summation);
      return 5 + sum;
    }
  }

  static int getSum(int terms) {
    long t = terms;
    long sum = (t) * (8 + (terms-1) * 2);
    return (int) Math.min(Integer.MAX_VALUE - 5, sum);
  }

  static boolean isNormalTriplet(int i, int j, int k) {
    if (i * i == j * j + k * k) {
      return true;
    }
    if (j * j == i * i + k * k) {
      return true;
    }
    if (k * k == i * i + j * j) {
      return true;
    }
    return false;
  }

  static boolean isSpecialTriplet(int i, int j, int k) {
    if(i == j * j - k) {
      return true;
    }
    if (i == k * k - j) {
      return true;
    }
    if (j == i * i - k) {
      return true;
    }
    if (j == k * k - i) {
      return true;
    }
    if (k == i * i - j) {
      return true;
    }
    if (k == j * j - i) {
      return true;
    }
    return false;
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