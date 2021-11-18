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
                                ), 1<<28).start();
  }

  public void run() {
    int N = in.nextInt();
    int[][] F = new int[N][10];
    for (int s = 0; s < N; s++) {
      for (int p = 0; p < 10; p++) {
        F[s][p] = in.nextInt();
      }
    }

    int[][] profits = new int[N][11];
    for (int s = 0; s < N; s++) {
      for (int p = 0; p <= 10; p++) {
        profits[s][p] = in.nextInt();
      }
    }

    int profit = Integer.MIN_VALUE;
    for (int bm = 1; bm < 1_024; bm++) {
      int candidate = getProfit(bm, F, profits);
      profit = Math.max(profit, candidate);
    }
    out.println(profit);
    
    in.close();
    out.close();
  }

  private static int getProfit(int bm, int[][] F, int[][] profits) {
    BitSet open = new BitSet(10);
    for (int i = 0; i < 10; i++) {
      if ((bm & (1<<i)) > 0) {
        open.set(i);
      }
    }
    
    int[] common = new int[F.length];
    for (int s = 0; s < F.length; s++) {
      for (int p = 0; p < 10; p++) {
        if (F[s][p] == 1 && open.get(p)) {
          common[s]++;
        }
      }
    }

    int profit = 0;
    for (int s = 0; s < F.length; s++) {
      profit += profits[s][common[s]];
    }
    return profit;
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
