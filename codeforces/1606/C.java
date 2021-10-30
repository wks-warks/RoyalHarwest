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
      int k = in.nextInt();
      int[] pows = new int[n];
      for (int i = 0; i < n; i++) {
        pows[i] = in.nextInt();
      }

      long ans = getAns(pows, k);
      out.println(ans);
    }
    
    in.close();
    out.close();
  }

  private static long getAns(int[] pows, int k) {
    if (pows.length == 1) {
      return k+1;
    }

    long max = 1L;
    int prev = 0;

    for (int i = 0; i < pows.length; i++) {
      long num = (long) Math.pow(10, pows[i]);
      long rem = num - 1;
      if (i + 1 == pows.length) {
        if (prev == k) {
          return num + rem;
        } else {
          long mul = (k-prev+1);
          return num * mul + rem;
        }
      } else {
        int next =(int)(prev + (long) Math.pow(10, pows[i+1]) / num - 1);
        if (true) {
          // out.println(i + " " + next + " NEXT");
        }
        if (next > k) {
          long mul = (k-prev+1);
          return num * mul + rem;
        }
        prev = next;
      }
    }
    return -1;
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
