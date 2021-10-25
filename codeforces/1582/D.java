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
      int n = in.nextInt();

      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = in.nextInt();
      }

      int[] b = new int[n];
      setB(a, b, 0);

      for (var num : b) {
        out.print(num + " ");
      }
      out.println();
    }
    
    in.close();
    out.close();
  }

  private static void setB(int[] a, int[] b, int idx) {
    int remaining = a.length-idx;
    if (remaining == 0) {
      return;
    }
    int toSettle = 2 + (remaining & 1);
    if (toSettle == 2) {
      b[idx] = -a[idx+1];
      b[idx+1] = a[idx];
      setB(a, b, idx+2);
    } else {
      if (a[idx] + a[idx+1] != 0) {
        b[idx] = b[idx+1] = -a[idx+2];
        b[idx+2] = a[idx] + a[idx+1];
      } else if (a[idx+1] + a[idx+2] != 0) {
        b[idx+1] = b[idx+2] = -a[idx];
        b[idx] = a[idx+1] + a[idx+2];
      } else {
        b[idx] = b[idx+2] = -a[idx+1];
        b[idx+1] = a[idx] + a[idx+2];
      }
      // int g = gcd(Math.abs(a[idx]), Math.abs(a[idx+1]));

      // b[idx+2] = 1;
      // if (a[idx+2] % g > 0) {
      //   a[idx+2] *= g;
      //   b[idx+2] *= g;
      // }
      // int[] xy = new int[2];
      // findSolution(a[idx], a[idx+1], a[idx+2], xy);
      // b[idx] = -xy[0];
      // b[idx+1] = -xy[1];
      // while (b[idx] == 0 || b[idx+1] == 0) {
      //   b[idx] += a[idx+1] / g;
      //   b[idx+1] -= a[idx] / g;
      // }
      setB(a, b, idx+3);
    }
  }

  private static int extendedGCD(int num1, int num2, int[] xy) {
    if (num2 == 0) {
      xy[0] = 1;
      xy[1] = 0;
      return num1;
    }

    int[] nextXY = new int[2];
    int g = extendedGCD(num2, num1 % num2, nextXY);
    xy[0] = nextXY[1];
    xy[1] = nextXY[0] - nextXY[1] * (num1 / num2);
    return g;
  }

  private static boolean findSolution(int a, int b, int c, int[] xy) {
    int g = extendedGCD(Math.abs(a), Math.abs(b), xy);
    if (c % g > 0) {
      return false;
    }

    xy[0] *= c / g;
    xy[1] *= c / g;
    if (a < 0) {
      xy[0] = -xy[0];
    }
    if (b < 0) {
      xy[1] = -xy[1];
    }
    return true;
  }

  private static int gcd(int num1, int num2) {
    if (num1 == 0) {
      return num2;
    }
    return gcd(num2 % num1, num1);
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
