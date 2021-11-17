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
    int h = in.nextInt();
    int w = in.nextInt();

    int[][] costs = new int[10][10];
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 10; col++) {
        costs[row][col] = in.nextInt();
      }
    }

    int[][] effectiveCosts = getEffectiveCosts(costs);

    int[][] digits = new int[h][w];
    for (int row = 0; row < h; row++) {
      for (int col = 0; col < w; col++) {
        digits[row][col] = Math.abs(in.nextInt());
      }
    }

    int minTotal = getMinTotal(digits, effectiveCosts);
    out.println(minTotal);
    
    in.close();
    out.close();
  }

  private static int getMinTotal(int[][] digits, int[][] effectiveCosts) {
    int total = 0;
    for (var row : digits) {
      for (var digit : row) {
        total += effectiveCosts[digit][1];
      }
    }
    return total;
  }

  private static int[][] getEffectiveCosts(int[][] costs) {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 10; k++) {
          costs[j][k] = Math.min(costs[j][k], costs[j][i] + costs[i][k]);
        }
      }
    }
    return costs;
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
