// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), String.join(
                                  "All that is gold does not glitter,",
                                  "Not all those who wander are lost;",
                                  "The old that is strong does not wither,",
                                  "Deep roots are not reached by the frost.",
                                  
                                  "From the ashes a fire shall be woken,",
                                  "A light from the shadows shall spring;",
                                  "Renewed shall be blade that was broken,",
                                  "The crownless again shall be king."
                                ), 1<<25).start();
  }

  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int arrSz = in.nextInt();
      long[] arr = new long[arrSz];
      for (int i = 0; i < arrSz; i++) {
        arr[i] = in.nextLong();
      }

      int ans = computeAns(arr);
      out.println(ans);
    }
    
    in.close();
    out.close();
  }

  static int computeAns(long[] arr) {
    long[] diff = new long[arr.length-1];
    for (int i = 1; i < arr.length; i++) {
      diff[i-1] = Math.abs(arr[i] - arr[i-1]);
    }

    GCDTable table = new GCDTable(diff);
    return 1 + table.longestCommon();
  }
  
  static PrintWriter Output() {
    return new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  }
  
  static PrintWriter Output(String fileName) {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return pw;
  }
}

class GCDTable {
  long[][] sparseTable;

  public GCDTable(long[] diff) {
    int pow = 0;
    for (int i = 0; i < 18; i++) {
      if ((1<<i) <= diff.length) {
        pow = i+1;
      }
    }

    sparseTable = new long[diff.length][pow];
    for (int i = 0; i < diff.length; i++) {
      sparseTable[i][0] = diff[i];
    }

    for (int j = 1; j < pow; j++) {
      for (int i = 0; i + (1<<j) <= sparseTable.length; i++) {
        int partner = i + (1<<(j-1));
        sparseTable[i][j] = gcd(sparseTable[i][j-1], sparseTable[partner][j-1]);
      }
    }
  }

  private static long gcd(long num1, long num2) {
    if (num1 == 0) {
      return num2;
    }
    return gcd(num2 % num1, num1);
  }

  private long queryGCD(int from, int to) {
    if (from > to) {
      return 0;
    }

    int len = to - from + 1;
    int largest = 0;
    int left = 0;
    int right = 17;
    while (left <= right) {
      int mid = (left + right) >> 1;
      int candidate = (1<<mid);

      if (candidate <= len) {
        largest = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    long ans = sparseTable[from][largest];
    return gcd(ans, queryGCD(from + (1<<largest), to));
  }

  public long optimizedQueryGCD(int from, int to) {
    int len = to - from + 1;
    int largest = 0;
    int left = 0;
    int right = 17;

    while (left <= right) {
      int mid = (left + right) >> 1;
      int candidate = (1 << mid);

      if (candidate <= len) {
        largest = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    long ans = gcd(sparseTable[from][largest], sparseTable[to-(1<<largest) + 1][largest]);
    return ans;
  }

  public int longestCommon() {
    int ans = 0;
    for (int i = 0; i < sparseTable.length; i++) {
      int temp = 0;
      int left = i;
      int right = sparseTable.length-1;
      
      while (left <= right) {
        int mid = (left + right) >> 1;

        if (optimizedQueryGCD(i, mid) > 1) {
        // if (queryGCD(i, mid) > 1) {
          ans = Math.max(ans, mid - i + 1);
          temp = mid - i + 1;
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }

    return ans;
  }
}

class Input {
  BufferedReader br;
  StringTokenizer st;
  public Input() {
    br = new BufferedReader(new InputStreamReader(System.in));
  }

  public Input(String fileName) {
    try {
      br = new BufferedReader(new FileReader(fileName));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public String next() {
    while (st == null || !st.hasMoreElements()) {
      try {
        st = new StringTokenizer(br.readLine());
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return st.nextToken();
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }

  public long nextLong() {
    return Long.parseLong(next());
  }

  public Float nextFloat() {
    return Float.parseFloat(next());
  }

  public Double nextDouble() {
    return Double.parseDouble(next());
  }

  public String nextLine() {
    if (st != null && st.hasMoreElements()) {
      StringBuilder sb = new StringBuilder();
      while (st.hasMoreElements()) {
        sb.append(next());
      }
      return sb.toString();
    }

    String str = null;
    try {
      str = br.readLine();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return str;
  }

  public void close() {
    try {
      br.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
