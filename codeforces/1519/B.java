// Author : RegalBeast

import java.io.*;
import java.util.*;

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
    for (int t = 0; t <  tests; t++) {
      int n = in.nextInt();
      int m = in.nextInt();
      int k = in.nextInt();

      boolean achievable = isAchievable(n, m, k);
      out.println(achievable ? "Yes" : "No");
    }

    in.close();
    out.close();
  }

  static boolean isAchievable(int n, int m, int k) {
    List<List<Set<Integer>>> potentialCosts = new ArrayList<List<Set<Integer>>>();
    for (int r = 0; r < m; r++) {
      potentialCosts.add(new ArrayList<Set<Integer>>());
      for (int c = 0; c < n; c++) {
        potentialCosts.get(r).add(new HashSet<Integer>());
      }
    }

    potentialCosts.get(0).get(0).add(0);
    for (int r = 0; r < m; r++) {
      for (int c = 0; c < n; c++) {
        if (r + 1 < m) {
          int add = c+1;
          for (var cost : potentialCosts.get(r).get(c)) {
            potentialCosts.get(r+1).get(c).add(cost + add);
          }
        }
        if (c + 1 < n) {
          int add = r+1;
          for (var cost : potentialCosts.get(r).get(c)) {
            potentialCosts.get(r).get(c+1).add(cost + add);
          }
        }
      }
    }

    return potentialCosts.get(m-1).get(n-1).contains(k);
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
    if (st.hasMoreElements()) {
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